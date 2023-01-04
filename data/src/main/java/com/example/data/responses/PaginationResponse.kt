package com.example.data.responses

import com.example.domain.model.ModelMapper
import com.example.domain.model.PaginationModel
import com.google.gson.annotations.SerializedName

data class PaginationResponse(
    @SerializedName("total_count")
    val totalCount: Int?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("ofset")
    val offset: Int?
) {
    companion object : ModelMapper<PaginationModel, PaginationResponse> {
        override fun mapTo(model: PaginationModel): PaginationResponse {
            return with(model) {
                PaginationResponse(
                    totalCount = model.totalCount,
                    count = model.count,
                    offset = model.offset
                )
            }
        }

        override fun mapToDomain(model: PaginationResponse): PaginationModel {
            return with(model) {
                PaginationModel(
                    totalCount = model.totalCount,
                    count = model.count,
                    offset = model.offset
                )
            }
        }
    }
}