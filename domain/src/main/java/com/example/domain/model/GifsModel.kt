package com.example.domain.model

data class GifsModel(
    var data: MutableList<Gif>?,
    val paginationModel: PaginationModel?
)