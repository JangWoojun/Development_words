package com.example.developmentwords.quiz

data class Quiz (
        val id : Int,
        val question: String,
        val option1 : String,
        val option2 : String,
        val option3 : String,
        val option4 : String,
        val chk : Int
)