package com.example.core.util.exception

import com.example.core.util.vo.RequestResults

data class  Failure(val requestResults: RequestResults, val throwable: Throwable, val code:String="")