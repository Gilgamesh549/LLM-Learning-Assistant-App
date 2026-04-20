package com.example.llmlearningassistant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val username = intent.getStringExtra("username") ?: "Student"
        val interests = intent.getStringExtra("interests") ?: "Algorithms"

        val tvHello = findViewById<TextView>(R.id.tvHello)
        val tvTaskDesc = findViewById<TextView>(R.id.tvTaskDesc)
        val tvPrompt = findViewById<TextView>(R.id.tvPrompt)
        val tvFlashcards = findViewById<TextView>(R.id.tvFlashcards)
        val btnGenerateFlashcards = findViewById<Button>(R.id.btnGenerateFlashcards)
        val btnOpenTask = findViewById<Button>(R.id.btnOpenTask)

        tvHello.text = "Hello, $username"
        tvTaskDesc.text = "Personalised topic generated from your selected interests: $interests"

        btnGenerateFlashcards.setOnClickListener {
            val prompt = "Create 3 short study flashcards for the topic: $interests. Each flashcard should have a front and a back."
            tvPrompt.text = prompt
            tvFlashcards.text = "AI is generating flashcards..."
            btnGenerateFlashcards.isEnabled = false

            tvFlashcards.postDelayed({

                val shouldFail = false

                if (shouldFail) {
                    tvFlashcards.text = "Failed to generate flashcards. Please try again."
                } else {
                    val response = when {
                        interests.contains("Algorithms", ignoreCase = true) -> {
                            "Flashcard 1\nFront: What is binary search?\nBack: A searching algorithm that repeatedly divides a sorted list in half.\n\n" +
                                    "Flashcard 2\nFront: What is time complexity?\nBack: A measure of how runtime grows as input size increases.\n\n" +
                                    "Flashcard 3\nFront: What does O(log n) mean?\nBack: The algorithm grows slowly as input increases, often by halving work each step."
                        }

                        interests.contains("Data Structures", ignoreCase = true) -> {
                            "Flashcard 1\nFront: What is a stack?\nBack: A Last In First Out data structure.\n\n" +
                                    "Flashcard 2\nFront: What is a queue?\nBack: A First In First Out data structure.\n\n" +
                                    "Flashcard 3\nFront: What is an array?\nBack: A fixed-size collection of elements stored in contiguous memory."
                        }

                        interests.contains("Web Development", ignoreCase = true) -> {
                            "Flashcard 1\nFront: What does HTML do?\nBack: It structures content on a web page.\n\n" +
                                    "Flashcard 2\nFront: What does CSS do?\nBack: It controls the style and layout of a web page.\n\n" +
                                    "Flashcard 3\nFront: What does JavaScript do?\nBack: It adds interactivity and dynamic behavior to websites."
                        }

                        else -> {
                            "Flashcard 1\nFront: What is the main concept of this topic?\nBack: It is the core idea you should understand first.\n\n" +
                                    "Flashcard 2\nFront: Why is this topic important?\nBack: It helps build your understanding for later lessons.\n\n" +
                                    "Flashcard 3\nFront: How should you study this topic?\nBack: Review the basics, practice examples, and test yourself regularly."
                        }
                    }

                    tvFlashcards.text = response
                }

                btnGenerateFlashcards.isEnabled = true
            }, 1500)
        }

        btnOpenTask.setOnClickListener {
            val intent = Intent(this, TaskActivity::class.java)
            intent.putExtra("topic", interests)
            startActivity(intent)
        }
    }
}