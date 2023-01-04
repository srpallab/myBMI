package com.srpallab.mybmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton

class HomeActivity : AppCompatActivity() {
    private lateinit var etWeight: EditText
    private lateinit var etHeight: EditText
    private lateinit var btnCalculate : MaterialButton
    private lateinit var tvResult: TextView
    private lateinit var tvFinalScore: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        var color = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        etWeight = findViewById(R.id.etWeight)
        etHeight = findViewById(R.id.etHeight)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvFinalScore = findViewById(R.id.tvFinalScore)
        tvResult = findViewById(R.id.tvResult)

        fun validateInputEditText(): Boolean{
            if (etWeight.text?.isEmpty() == true || etHeight.text?.isEmpty() == true) {
                Toast.makeText(
                    this, "Forms can't be empty.", Toast.LENGTH_LONG
                ).show()
                return false
            }
            return true
        }

        btnCalculate.setOnClickListener {
            if (validateInputEditText()){
                val weight = etWeight.text.toString().toFloat()
                val height = etHeight.text.toString().toFloat() / 100
                println("Weight: $weight, Height: $height")
                val bmiScore = weight / (height * height)
                var bmiText = ""
                when {
                    bmiScore < 18.50 -> {
                        bmiText = "Underweight"
                        color = R.color.under_weight
                    }
                    bmiScore in 18.50..24.99 -> {
                        bmiText = "Healthy"
                        color = R.color.normal
                    }
                    bmiScore in 25.00..29.99 -> {
                        bmiText = "Over Weight"
                        color = R.color.over_weight
                    }
                    bmiScore > 29.99 -> {
                        bmiText = "Obese"
                        color = R.color.obese
                    }
                }
                tvResult.text = bmiText
                tvResult.setTextColor(resources.getColor(color, theme))
                tvFinalScore.text = bmiScore.toString()
            }
        }


    }
}