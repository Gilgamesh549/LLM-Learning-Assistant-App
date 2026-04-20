package com.example.llmlearningassistant.data

import com.example.llmlearningassistant.model.Question

object DummyData {

    fun getQuestions(): ArrayList<Question> {
        return arrayListOf(
            Question(
                "What is the time complexity of binary search?",
                listOf("O(n)", "O(log n)", "O(n^2)", "O(1)"),
                "O(log n)"
            ),
            Question(
                "Which data structure uses FIFO?",
                listOf("Stack", "Queue", "Tree", "Graph"),
                "Queue"
            ),
            Question(
                "What does HTML stand for?",
                listOf(
                    "Hyper Trainer Marking Language",
                    "Hyper Text Markup Language",
                    "High Text Markdown Language",
                    "Home Tool Markup Language"
                ),
                "Hyper Text Markup Language"
            )
        )
    }
}