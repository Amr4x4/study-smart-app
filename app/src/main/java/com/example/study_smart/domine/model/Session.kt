package com.example.study_smart.domine.model

data class Session(
    val relatedToSubject: String,
    val date: Long,
    val duration: Long,
    val sessionSubjectId: Int,
    val sessionId: Int? = null
)