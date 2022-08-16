package com.teaching.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.ProxyFileDescriptorCallback
import android.widget.Toast
import androidx.core.view.isVisible
import com.teaching.coroutines.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val handler = Handler()
    private fun loadData() {

        binding.progress.isVisible = true
        binding.buttonLoad.isEnabled = true
        loadCity {
            binding.tvLocation.text = it
            val temp = loadTemperature(it) {
                binding.tvTemperature.text = it.toString()
                binding.progress.isVisible = false
                binding.buttonLoad.isEnabled = true
            }

        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonLoad.setOnClickListener {
            loadData()
        }
    }

    private fun loadCity(callback: (String) -> Unit) {

        thread {
            handler.post {
                Thread.sleep(5000)

                callback.invoke("Moscow")
            }
        }


    }

    private fun loadTemperature(city: String, callback: (Int) -> Unit) {
        thread {
            handler.post {

                Toast.makeText(
                    this,
                    "Loading temperature for city: ${city}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            Thread.sleep(5000)

            handler.post { callback.invoke(17) }


        }


    }
}