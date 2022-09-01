package spring.works.hook.util

// https://www.geeksforgeeks.org/how-to-handle-api-responses-success-error-in-android/
sealed class ApiResponse<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T, message: String) : ApiResponse<T>(data, message)
    class Error<T>(data: T, errorMessage: String?) : ApiResponse<T>(data, message = errorMessage)
}
