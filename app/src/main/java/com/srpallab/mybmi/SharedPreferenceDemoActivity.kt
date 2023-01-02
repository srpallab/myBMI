package com.srpallab.mybmi

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SharedPreferenceDemoActivity : AppCompatActivity() {
    private lateinit var nameText : EditText
    private lateinit var ageText : EditText
    private lateinit var btnNext: Button
    private lateinit var sharedPreference: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference_demo)

        nameText = findViewById(R.id.etName)
        ageText = findViewById(R.id.etAge)
        btnNext = findViewById(R.id.btnNext)
        sharedPreference = getSharedPreferences("my_ibm_sf", MODE_PRIVATE)
        sharedPreferencesEditor = sharedPreference.edit()
    }

    override fun onPause() {
        super.onPause()
        val name = nameText.text.toString()
        val age = ageText.text.toString().toInt()
        sharedPreferencesEditor.apply{
            putString("sp_name", name)
            putInt("sp_age", age)
            commit()
        }
    }

    override fun onResume() {
        super.onResume()
        val name = sharedPreference.getString("sp_name", null)
        val age = sharedPreference.getInt("sp_age", 0)
        nameText.setText(name)
        if (age != 0){
            ageText.setText(age.toString())
        }
    }

}