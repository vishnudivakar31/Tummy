package com.wanderingthinkter.tummy.entities

data class Recipe(
    var id: String,
    var username: String,
    var title: String,
    var cookingTime: Long,
    var likes: Int,
    var likesList: List<String>,
    var isAbusive: Boolean,
    var abusiveReportList: List<String>,
    var commentsCount: Int,
    var comments: List<Comment>,
    var ingridents: List<Ingrident>,
    var avgRatings: Float,
    var ratings: List<Rating>,
    var steps: List<String>,
    var cuisine: String,
    var posted_date: String,
    var updated_date: String
)