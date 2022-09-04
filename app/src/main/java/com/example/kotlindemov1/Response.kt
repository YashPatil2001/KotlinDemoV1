package com.example.kotlindemov1

sealed class Response<T>(val data: T ? = null, val errorMessage: String ? = null,val errorCode: Int ? = null) {
    class Loader<T>() : Response<T>()
    class Success<T>(data:  T ? = null) : Response<T>(data = data)
    class Error<T>(message: String, errorCode: Int ? = null) : Response<T>(errorMessage = message, errorCode = errorCode)
}
