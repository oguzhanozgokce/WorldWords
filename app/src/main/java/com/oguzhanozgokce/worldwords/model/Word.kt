package com.oguzhanozgokce.worldwords.model

import java.io.Serializable

data class Word(
    val turkish: String,
    val english: String,
    val difficulty: Int,
    val image: String,
    val usageEnglish: List<String> = emptyList(),
    val usageTurkish: List<String> = emptyList()
) : Serializable
