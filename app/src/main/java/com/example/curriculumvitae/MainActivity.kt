package com.example.curriculumvitae

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fullname = findViewById<EditText>(R.id.FullName)
        val age = findViewById<EditText>(R.id.age)
        val email = findViewById<EditText>(R.id.email)
        val buttonNext = findViewById<Button>(R.id.buttonNext)
        val buttonReset = findViewById<Button>(R.id.buttonReset)
        val radioButtonHomme = findViewById<RadioButton>(R.id.radioButtonHomme)
        val radioButtonFemme = findViewById<RadioButton>(R.id.radioButtonFemme)
        val android = findViewById<SeekBar>(R.id.Android)
        val androidprogress: Int = android.getProgress()
        val ios = findViewById<SeekBar>(R.id.IOS)
        val iosprogress: Int = ios.getProgress()
        val flutter = findViewById<SeekBar>(R.id.Flutter)
        val flutterprogress: Int = flutter.getProgress()


        buttonNext.setOnClickListener {

            val email = email.text.toString().trim()
            val pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$")
            val matcher = pattern.matcher(email)

            if (TextUtils.isEmpty(fullname.text) ||
                TextUtils.isEmpty(age.text) ||
                TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Check your input!", Toast.LENGTH_SHORT).show()


            } else if (!matcher.matches()) {
                Toast.makeText(this, "Check your email!", Toast.LENGTH_SHORT).show()

            } else {
                val highestSkillValue = maxOf(androidprogress, iosprogress, flutterprogress)

                if (highestSkillValue > 80) {
                    when (highestSkillValue) {
                        androidprogress -> Toast.makeText(this, "Vous êtes excellent en android!", Toast.LENGTH_SHORT).show()
                        iosprogress -> Toast.makeText(this, "Vous êtes excellent en ios!", Toast.LENGTH_SHORT).show()
                        flutterprogress -> Toast.makeText(this, "Vous êtes excellent en flutter!", Toast.LENGTH_SHORT).show()
                    }
                } else if (androidprogress <= 30 && iosprogress <= 30 && flutterprogress <= 30) {
                    Toast.makeText(this, "Vous devez travailler vos skills!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Vous avez de bons skills!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        buttonReset.setOnClickListener {

            email.setText("")
            fullname.setText("")
            age.setText("")
            radioButtonHomme.isChecked = true
            radioButtonFemme.isChecked = false

        }


    }
}