package com.example.blinkbox

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.blinkbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding ?= null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initStartButton()
    }

    private fun initStartButton(){
        binding.appCompatButton.setOnClickListener {
            if (binding.numberEntryET.text.toString().equals("4",true) ||
                binding.numberEntryET.text.toString().equals("5",true) ||
                binding.numberEntryET.text.toString().equals("6",true) ||
                binding.numberEntryET.text.toString().equals("7",true) ||
                binding.numberEntryET.text.toString().equals("8",true) ||
                binding.numberEntryET.text.toString().equals("9",true) ||
                binding.numberEntryET.text.toString().equals("10",true))
            {
                val intent = Intent(this@MainActivity,MatrixActivity::class.java)
                intent.putExtra("Matrixvalue",binding.numberEntryET.text.toString().toInt())
                startActivity(intent)
            }
            else{
                Toast.makeText(this@MainActivity,"Please Enter Number From 4 to 10 only.",Toast.LENGTH_LONG).show()
            }
        }
    }

}