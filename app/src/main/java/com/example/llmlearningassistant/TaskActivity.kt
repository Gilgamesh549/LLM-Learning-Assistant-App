package com.example.llmlearningassistant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.llmlearningassistant.data.DummyData

class TaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        val topic = intent.getStringExtra("topic") ?: "Algorithms"
        val questions = DummyData.getQuestions()
        val question = questions[0]

        val tvTopic = findViewById<TextView>(R.id.tvTopic)
        val tvQuestion1 = findViewById<TextView>(R.id.tvQuestion1)
        val rgQuestion1 = findViewById<RadioGroup>(R.id.rgQuestion1)
        val rb1 = findViewById<RadioButton>(R.id.rbQ1Option1)
        val rb2 = findViewById<RadioButton>(R.id.rbQ1Option2)
        val rb3 = findViewById<RadioButton>(R.id.rbQ1Option3)
        val rb4 = findViewById<RadioButton>(R.id.rbQ1Option4)
        val btnHint = findViewById<Button>(R.id.btnHint)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val tvPrompt = findViewById<TextView>(R.id.tvPrompt)
        val tvHint = findViewById<TextView>(R.id.tvHint)

        tvTopic.text = "AI-Generated Learning Task\nTopic: $topic"
        tvQuestion1.text = question.questionText
        rb1.text = question.options[0]
        rb2.text = question.options[1]
        rb3.text = question.options[2]
        rb4.text = question.options[3]

        btnHint.setOnClickListener {
            val prompt = "Give a short study hint for this question without revealing the final answer: ${question.questionText}"
            tvPrompt.text = prompt
            tvHint.text = "AI is generating hint..."
            btnHint.isEnabled = false

            tvHint.postDelayed({

                val shouldFail = false

                if (shouldFail) {
                    tvHint.text = "Failed to generate hint. Please try again."
                } else {
                    val hint = when (question.questionText) {
                        "What is the time complexity of binary search?" ->
                            "Think about what happens when the search space is cut into half repeatedly."

                        "Which data structure uses FIFO?" ->
                            "Focus on the structure where the first inserted item leaves first."

                        "What does HTML stand for?" ->
                            "It is the standard markup language used to structure web pages."

                        else ->
                            "Focus on the core concept in the question."
                    }

                    tvHint.text = hint
                }

                btnHint.isEnabled = true
            }, 1500)
        }

        btnSubmit.setOnClickListener {
            val selectedId = rgQuestion1.checkedRadioButtonId

            if (selectedId == -1) {
                Toast.makeText(this, "Please choose an answer", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedAnswer = findViewById<RadioButton>(selectedId).text.toString()
            val isCorrect = selectedAnswer == question.correctAnswer

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("question", question.questionText)
            intent.putExtra("selectedAnswer", selectedAnswer)
            intent.putExtra("correctAnswer", question.correctAnswer)
            intent.putExtra("isCorrect", isCorrect)
            startActivity(intent)
        }
    }
}