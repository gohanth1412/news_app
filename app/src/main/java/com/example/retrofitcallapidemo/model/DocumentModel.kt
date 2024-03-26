package com.example.retrofitcallapidemo.model

data class DocumentModel(
    val avatar: Avatar? = null,
    val content: Content? = null,
    val content_type: String,
    val description: String,
    val document_id: String,
    val images: List<Image>? = null,
    val origin_url: String,
    val published_date: String,
    val publisher: Publisher,
    val title: String
)