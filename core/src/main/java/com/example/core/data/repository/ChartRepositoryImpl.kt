package com.example.core.data.repository

import com.example.core.data.dummydata.chartResponse
import com.example.core.data.remote.mapper.ChartMapper
import com.example.core.model.model.Charts
import com.example.core.model.repository.ChartRepository
import com.example.core.util.exception.Failure
import com.example.core.util.vo.Either
import javax.inject.Inject

class ChartRepositoryImpl @Inject constructor(
    private val promoMapper: ChartMapper,
) : ChartRepository {

    override suspend fun getCharts(): Either<Failure, Charts> {
        val response = Either.Success(chartResponse)
        val data = promoMapper.mapToDomain(response.body)
        return Either.Success(data)
    }
}