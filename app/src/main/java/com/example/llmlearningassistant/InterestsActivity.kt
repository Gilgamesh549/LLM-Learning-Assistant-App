package com.example.llmlearningassistant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class InterestsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interests)

        val username = intent.getStringExtra("username") ?: "Student"

        val cbAlgorithms = findViewById<CheckBox>(R.id.cbAlgorithms)
        val cbDataStructures = findViewById<CheckBox>(R.id.cbDataStructures)
        val cbWebDev = findViewById<CheckBox>(R.id.cbWebDev)
        val cbTesting = findViewById<CheckBox>(R.id.cbTesting)
        val cbDatabases = findViewById<CheckBox>(R.id.cbDatabases)
        val cbMobile = findViewById<CheckBox>(R.id.cbMobile)
        val btnNext = findViewById<Button>(R.id.btnNext)

        btnNext.setOnClickListener {
            val selected = mutableListOf<String>()

            if (cbAlgorithms.isChecked) selected.add("Algorithms")
            if (cbDataStructures.isChecked) selected.add("Data Structures")
            if (cbWebDev.isChecked) selected.add("Web Development")
            if (cbTesting.isChecked) selected.add("Testing")
            if (cbDatabases.isChecked) selected.add("Databases")
            if (cbMobile.isChecked) selected.add("Mobile Development")

            if (selected.isEmpty()) {
                Toast.makeText(this, "Please select at least one interest", Toast.LENGTH_SHORT).show()
            } else {
                val nextIntent = Intent(this, HomeActivity::class.java)
                nextIntent.putExtra("username", username)
                nextIntent.putExtra("interests", selected.joinToString(", "))
                startActivity(nextIntent)
            }
        }
    }
}