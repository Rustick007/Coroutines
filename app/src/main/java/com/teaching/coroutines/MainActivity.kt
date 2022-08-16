package com.teaching.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.teaching.coroutines.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)

    }

    private fun loadData(){
        binding.progress.isVisible = true
        binding.buttonLoad.isEnabled = true
        val city = loadCity()

        binding.tvLocation.text = city
        val temp = loadTemperature(city)
        binding.tvTemperature.text = temp.toString()
        binding.progress.isVisible = false
        binding.buttonLoad.isEnabled = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonLoad.setOnClickListener {
            loadData()

        }
    }

    private fun loadCity(): String{
        Thread.sleep(5000)
        return "Moscow"
    }

    private fun loadTemperature(city: String): Int{
        Toast.makeText(
            this,
            "Loading temperature for city: {city}",
            Toast.LENGTH_SHORT
        ).show()
        Thread.sleep(5000)
        return 17
    }
}