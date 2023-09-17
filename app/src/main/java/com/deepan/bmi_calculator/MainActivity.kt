package com.deepan.bmi_calculator

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
        val calcButton = findViewById<Button>(R.id.btnCalculate)

        calcButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if (validInput(weight,height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * height.toFloat() / 100)

                val bmi2digit = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2digit)
            }
        }

    }
    private fun validInput(weight :String?,height : String?):Boolean{
        return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this,"Weight is Empty",Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this,"Height is Empty",Toast.LENGTH_SHORT).show()
                return false
        }else->{
            return true

            }        }
    }
    private fun displayResult(bmi: Float){

        val result = findViewById<TextView>(R.id.tvResult)
        val resultdescrptn = findViewById<TextView>(R.id.tvResultDesc)
        val info = findViewById<TextView>(R.id.tvInfo)

        result.text = bmi.toString()
        info.text = "( Normal range is 18.5 - 24.9 )"

        var resultText = ""
        var color = 0

        when{
            bmi<18.50 ->{
                resultText = "Underweight"
                color = R.color.underWeight
            }
            bmi in 18.50..24.99 ->{
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99->{
                resultText ="Overweight"
                color = R.color.overWeight
            }
            bmi>29.99 -> {
                resultText="Obese"
                color = R.color.obese
            }

        }
        resultdescrptn.setTextColor(ContextCompat.getColor(this,color))
        resultdescrptn.text = resultText
    }
}