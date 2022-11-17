package models

import exception.CoordinateException

class Coordinate(private var x: Int, private var y: Int) {

    init {
        if (x < 0) {
            throw CoordinateException.invalidArgumentForXAxis()
        }
        if (y < 0) {
            throw CoordinateException.invalidArgumentForYAxis()
        }
    }

    fun getX(): Int {
        return x
    }

    fun getY(): Int {
        return y
    }
}
