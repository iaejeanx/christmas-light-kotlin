package exception

class CoordinateException(message: String): Exception(message) {
    companion object {
        fun invalidArgumentForXAxis(): CoordinateException {
            return CoordinateException("Invalid argument for X axis")
        }

        fun invalidArgumentForYAxis(): CoordinateException {
            return CoordinateException("Invalid argument for Y axis")
        }
    }
}
