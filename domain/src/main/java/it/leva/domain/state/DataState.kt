package it.leva.domain.state

sealed class DataState<T>(
    val data: T? = null,
    val errorMessage: String? = null,
    val errorCode: String? = null
) {
    class Success<T>(
        data: T? = null,
    ) : DataState<T>(data = data)

    class Error<T>(
        errorMessage: String? = null,
        errorCode: String? = null,
    ) : DataState<T>(
        errorMessage = errorMessage,
        errorCode = errorCode,
    )
}

fun <T> DataState<T>.mapToViewState() = when (this) {
    is DataState.Error -> ViewState.Error(this.errorMessage)
    is DataState.Success -> ViewState.Success(this.data)
}
