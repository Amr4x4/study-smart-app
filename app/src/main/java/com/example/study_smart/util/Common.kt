package com.example.study_smart.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import com.example.study_smart.presentation.theme.Green
import com.example.study_smart.presentation.theme.Orange

enum class Priority(val title: String, val color: Color, val value: Int ){
    LOW(title = "Low", color = Green, value = 0),
    MEDIUM(title = "Medium", color = Orange, value = 1),
    HIGH(title = "High", color = Black , value = 2);

    companion object{
        fun fromInt(value: Int) = Priority.entries.firstOrNull { it.value == value } ?: MEDIUM
    }
}