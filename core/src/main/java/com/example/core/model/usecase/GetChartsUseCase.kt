package com.example.core.model.usecase

import com.example.core.abstraction.UseCase
import com.example.core.model.model.Charts
import com.example.core.model.repository.ChartRepository
import com.example.core.util.exception.Failure
import com.example.core.util.vo.Either
import javax.inject.Inject

class GetChartsUseCase @Inject constructor(private val repo: ChartRepository): UseCase<Charts, UseCase.None>(){

    override suspend fun run(params: None): Either<Failure, Charts> {
        return repo.getCharts()
    }
}