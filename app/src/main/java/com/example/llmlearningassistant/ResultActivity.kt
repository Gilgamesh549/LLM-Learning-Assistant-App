package com.example.llmlearningassistant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val question = intent.getStringExtra("question") ?: ""
        val selectedAnswer = intent.getStringExtra("selectedAnswer") ?: ""
        val correctAnswer = intent.getStringExtra("correctAnswer") ?: ""
        val isCorrect = intent.getBooleanExtra("isCorrect", false)

        val tvQuestion = findViewById<TextView>(R.id.tvQuestion)
        val tvSelected = findViewById<TextView>(R.id.tvSelected)
        val tvCorrect = findViewById<TextView>(R.id.tvCorrect)
        val tvPrompt = findViewById<TextView>(R.id.tvPrompt)
        val tvExplanation = findViewById<TextView>(R.id.tvExplanation)
        val btnExplain = findViewById<Button>(R.id.btnExplain)
        val btnContinue = findViewById<Button>(R.id.btnContinue)

        tvQuestion.text = "Question: $question"
        tvSelected.text = "Your answer: $selectedAnswer"
        tvCorrect.text = "Correct answer: $correctAnswer"

        btnExplain.setOnClickListener {
            val prompt = "Explain why the student's answer is correct or incorrect in a short and clear way. Question: $question. Student answer: $selectedAnswer. Correct answer: $correctAnswer."
            tvPrompt.text = prompt
            tvExplanation.text = "AI is generating explanation..."
            btnExplain.isEnabled = false

            tvExplanation.postDelayed({

                val shouldFail = false

                if (shouldFail) {
                    tvExplanation.text = "Failed to generate explanation. Please try again."
                } else {
                    val explanation = if (isCorrect) {
                        "Your answer is correct because it matches the expected concept for this question. The selected option is the same as the correct answer, so your understanding is accurate."
                    } else {
                        "Your answer is incorrect because it does not match the correct concept tested in this question. The correct answer is $correctAnswer, which better fits what the question is asking."
                    }

                    tvExplanation.text = explanation
                }

                btnExplain.isEnabled = true
            }, 1500)
        }

        btnContinue.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}