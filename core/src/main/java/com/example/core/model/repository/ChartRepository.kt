package com.example.core.model.repository

import com.example.core.model.model.Charts
import com.example.core.util.exception.Failure
import com.example.core.util.vo.Either

interface ChartRepository {

    suspend fun getCharts(): Either<Failure, Charts>

}