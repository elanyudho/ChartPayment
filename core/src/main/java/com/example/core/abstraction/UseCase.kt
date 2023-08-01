package com.example.core.abstraction

import com.example.core.util.exception.Failure
import com.example.core.util.vo.Either

abstract class UseCase<out Type, in Params> {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    object None

}