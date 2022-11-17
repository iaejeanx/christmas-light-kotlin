package models

import exception.MeshException

class Mesh<T>(xAxisLength: Int = 100, yAxisLength: Int = 100) {
    private var matrix: Array<Array<Light>>;
    private var on: Boolean = false

    init {
        if (xAxisLength <=  0) {
            throw MeshException.invalidSizeForXAxis()
        }
        if (yAxisLength <=  0) {
            throw MeshException.invalidSizeForYAxis()
        }
        this.matrix = Array(xAxisLength) { Array(yAxisLength) { Light() } }
        this.on = false
    }

    fun isOn(): Boolean {
        return on
    }

    fun turnOnMesh(): Mesh<T> {
        matrix.iterator().forEach { xAxis ->
            turnOnRow(xAxis.iterator())
        }
        on = true
        return this
    }

    fun turnOffMesh(): Mesh<T> {
        matrix.iterator().forEach { xAxis ->
            turnOffRow(xAxis.iterator())
        }
        on = false
        return this
    }

    fun getLightsTurnOn(): Int {
        var count = 0
        matrix.iterator().forEach { xAxis ->
            count += getLightsTurnOnInRor(xAxis.toCollection(ArrayList()))
        }
        return count
    }

    @Throws(MeshException::class)
    fun turnOnRange(startCoordinate: Coordinate, endCoordinate: Coordinate): Mesh<T> {
        checkCoordinatesInMesh(startCoordinate, endCoordinate)

        matrix.toCollection(ArrayList()).forEachIndexed { xIndex, xAxis ->
            xAxis.toCollection(ArrayList()).forEachIndexed { yIndex, light ->
                if (xIndex >= startCoordinate.getX() &&
                    yIndex >= startCoordinate.getY() &&
                    xIndex <= endCoordinate.getX() &&
                    yIndex <= endCoordinate.getY()
                ) {
                    light.turnOn()
                }
            }
        }
        return this
    }

    @Throws(MeshException::class)
    fun turnOffRange(startCoordinate: Coordinate, endCoordinate: Coordinate): Mesh<T> {
        checkCoordinatesInMesh(startCoordinate, endCoordinate)

        matrix.toCollection(ArrayList()).forEachIndexed { xIndex, xAxis ->
            xAxis.toCollection(ArrayList()).forEachIndexed { yIndex, light ->
                if (xIndex >= startCoordinate.getX() &&
                    yIndex >= startCoordinate.getY() &&
                    xIndex <= endCoordinate.getX() &&
                    yIndex <= endCoordinate.getY()
                ) {
                    light.turnOff()
                }
            }
        }
        return this
    }

    @Throws(MeshException::class)
    fun toggleRange(startCoordinate: Coordinate, endCoordinate: Coordinate): Mesh<T> {
        checkCoordinatesInMesh(startCoordinate, endCoordinate)

        matrix.toCollection(ArrayList()).forEachIndexed { xIndex, xAxis ->
            xAxis.toCollection(ArrayList()).forEachIndexed { yIndex, light ->
                if (xIndex >= startCoordinate.getX() &&
                    yIndex >= startCoordinate.getY() &&
                    xIndex <= endCoordinate.getX() &&
                    yIndex <= endCoordinate.getY()
                ) {
                    light.toggle()
                }
            }
        }
        return this
    }

    @Throws(MeshException::class)
    private fun checkCoordinatesInMesh(startCoordinate: Coordinate, endCoordinate: Coordinate) {
        if (startCoordinate.getX() > matrix.size) {
            throw MeshException.coordinateStartXIsBiggerThanMesh()
        }

        if (startCoordinate.getY() > matrix[0].size) {
            throw  MeshException.coordinateStartYIsBiggerThanMesh()
        }

        if (endCoordinate.getX() > matrix.size) {
            throw  MeshException.coordinateEndXIsBiggerThanMesh()
        }

        if (endCoordinate.getY() > matrix[0].size) {
            throw  MeshException.coordinateEndYIsBiggerThanMesh()
        }
    }

    private fun getLightsTurnOnInRor(row: Collection<Light>): Int {
        val rowOn = row.filter { light ->  light.isOn()}
        return rowOn.size

    }

    private fun turnOnRow(row: Iterator<Light>) {
        row.iterator().forEach { light -> light.turnOn() }
    }

    private fun turnOffRow(row: Iterator<Light>) {
        row.iterator().forEach { light -> light.turnOff() }
    }
}

