package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalc)

        calcButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            val check = validateInput(weight, height)

            if (check){
                val bmi = weight.toFloat()/((height.toFloat()/100)*(height.toFloat()/100))
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digits)
            }
        }
    }

    private fun validateInput(weight: String?, height: String?):Boolean{
        when{
            weight.isNullOrEmpty() ->{
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_LONG).show()
                return false
            }
            weight.toInt() == 0 ->{
                Toast.makeText(this, "Weight can not equal to 0", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() ->{
                Toast.makeText(this, "Height is empty", Toast.LENGTH_LONG).show()
                return false
            }
            height.toInt() == 0 ->{
                Toast.makeText(this, "Height can not equal to 0", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }

    private fun displayResult(bmi: Float){
        val viewIndex = findViewById<TextView>(R.id.tvIndex)
        val viewResultDes = findViewById<TextView>(R.id.tvResult)
        val viewInfo = findViewById<TextView>(R.id.tvInfo)

        viewIndex.text = bmi.toString()
        viewInfo.text =  "(Normal range is 18.5 - 24.9)"

        var resultText = ""
        var resultColor = 0
        when{
            bmi< 18.50 ->{
                resultText = "Underweight"
                resultColor = R.color.under_weight
            }
            bmi in 18.50..24.99 ->{
                resultText = "Healthy"
                resultColor = R.color.normal
            }
            bmi in 25.00..29.99 ->{
                resultText = "Overweight"
                resultColor = R.color.over_weight
            }
            bmi> 29.99 ->{
                resultText = "Obese"
                resultColor = R.color.obese
            }
        }
        viewResultDes.setTextColor(ContextCompat.getColor(this, resultColor))
        viewResultDes.text = resultText
    }
}















