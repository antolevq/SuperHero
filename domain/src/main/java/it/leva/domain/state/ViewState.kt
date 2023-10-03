package it.leva.domain.state

sealed class ViewState<T>(
    val data: T? = null,
    val errorMessage: String? = null,
    val errorCode: String? = null
) {
    class Success<T>(
        data: T? = null,
    ) : ViewState<T>(data = data)

    class Error<T>(
        errorMessage: String? = null,
        errorCode: String? = null,
    ) : ViewState<T>(
        errorMessage = errorMessage,
        errorCode = errorCode,
    )

    class Loading<T>: ViewState<T>()
}