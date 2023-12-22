package com.capstone.cuacatani.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.capstone.cuacatani.R
import com.capstone.cuacatani.ViewModelFactory
import com.capstone.cuacatani.data.pref.UserPreference
import com.capstone.cuacatani.databinding.ActivityMainBinding
import com.capstone.cuacatani.model.WeatherApp
import com.capstone.cuacatani.network.WeatherService
import com.capstone.cuacatani.response.LoginResult
import com.capstone.cuacatani.ui.about.AboutActivity
import com.capstone.cuacatani.ui.fragment.CornFragment
import com.capstone.cuacatani.ui.fragment.FruitFragment
import com.capstone.cuacatani.ui.fragment.VegFragment
import com.capstone.cuacatani.ui.plants.PlantsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//211dec237321e8df95005da2c4b2976f

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var userPreference: UserPreference
    private lateinit var tabLayout: TabLayout
    private lateinit var bottomNavigationView: BottomNavigationView

    private val cornFragment = CornFragment()
    private val vegFragment = VegFragment()
    private val fruitFragment = FruitFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.user.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        userPreference = UserPreference(this)
        setupUserInfo()
        handleLayout()
        supportActionBar?.hide()
        setDefaultTab(CornFragment())
        setOnTabClickedListener(CornFragment())

        fetchWeatherData("Surabaya")
        searchCity()
    }

    private fun setupUserInfo(){
        val loginResult: LoginResult? = userPreference.gainUser()
        if (loginResult != null) {
            binding.tvNames.text = loginResult.name
        } else {
            binding.tvNames.text = "Please Login"
        }
    }

    private fun handleLayout(){
        bindUIComponents()
        bottomNavigationView.selectedItemId = R.id.navigation_home

        handleTabButtonPress()
        tabLayout = binding.tabs
        tabLayout.addTab(tabLayout.newTab().setText("Corn"))
        tabLayout.addTab(tabLayout.newTab().setText("Vegetable"))
        tabLayout.addTab(tabLayout.newTab().setText("Fruit"))

    }

    private fun searchCity(){
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    fetchWeatherData(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun fetchWeatherData(cityName: String) {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build().create(WeatherService::class.java)

        val response = retrofit.getWeather(cityName, "211dec237321e8df95005da2c4b2976f", "metric")
        response.enqueue(object : Callback<WeatherApp> {
            override fun onResponse(call: Call<WeatherApp>, response: Response<WeatherApp>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    val temperature = responseBody.main.temp
                    val decimalFormat = DecimalFormat("#")
                    val formattedTemperature = decimalFormat.format(temperature)
                    val pressure = responseBody.main.pressure
                    val wind = responseBody.wind.speed
                    val humidity = responseBody.main.humidity
                    val condition = responseBody.weather.firstOrNull()?.main ?: "unknown"

                    binding.tvSuhu.text = formattedTemperature
                    binding.tvCondt.text = condition
                    binding.tvPressure2.text = "$pressure km/h"
                    binding.tvWind2.text = "$wind m/s"
                    binding.tvHumadity2.text = "$humidity %"
                    binding.tvDay.text = dayName(System.currentTimeMillis())
                    binding.tvDate.text = date()
                    binding.city.text = "$cityName"

                    setWeatherImage(condition)
//                    Log.d("TAG", "onResponse: $temperature")
                }
            }

            override fun onFailure(call: Call<WeatherApp>, t: Throwable) {

            }
        })
    }

    private fun setWeatherImage(condition: String) {
        val imageResId: Int = when (condition.toLowerCase(Locale.getDefault())) {
            "clouds" -> R.drawable.cloudy
            "clear" -> R.drawable.sunny
            "sunny" -> R.drawable.sunny
            "rain" -> R.drawable.rain
            "snow" -> R.drawable.snow
            "haze" -> R.drawable.haze
            else -> R.drawable.sunny
        }

        binding.ivAwan.setImageResource(imageResId)
    }

    private fun date(): String {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return sdf.format((Date()))
    }

    fun dayName(timestamp: Long): String {
        val sdf = SimpleDateFormat("EEE, ", Locale.getDefault())
        return sdf.format((Date()))
    }

    private fun bindUIComponents() {
        bottomNavigationView = findViewById(R.id.nav_view)
    }

    private fun handleTabButtonPress() {
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_plant -> {
                    loadActivities(PlantsActivity())
                    return@setOnItemSelectedListener true
                }

                R.id.navigation_about -> {
                    loadActivities(AboutActivity())
                    return@setOnItemSelectedListener true
                }

            }
            return@setOnItemSelectedListener false
        }
    }

    private fun loadActivities(activity: AppCompatActivity) {
        val intent = Intent(applicationContext, activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
        finish()
    }

    private fun setDefaultTab(fragment: CornFragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commitNow()
    }

    private fun setOnTabClickedListener(fragment: CornFragment) {
        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val selectedFragment = when (tab?.position) {
                    0 -> cornFragment
                    1 -> vegFragment
                    2 -> fruitFragment
                    else -> cornFragment
                }

                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }
}