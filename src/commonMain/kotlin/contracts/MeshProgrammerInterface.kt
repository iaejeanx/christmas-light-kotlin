package contracts

import exception.CoordinateException
import exception.MeshException
import models.Coordinate
import models.Light
import models.Mesh

interface MeshProgrammerInterface {
    @Throws(MeshException::class, CoordinateException::class)
    fun turnOnRange(mesh:Mesh<Light>, startCoordinate: Coordinate, endCoordinate: Coordinate): Mesh<Light>

    @Throws(MeshException::class, CoordinateException::class)
    fun turnOffRange(mesh:Mesh<Light>,startCoordinate: Coordinate, endCoordinate: Coordinate): Mesh<Light>

    @Throws(MeshException::class, CoordinateException::class)
    fun toggleRange(mesh:Mesh<Light>, startCoordinate: Coordinate, endCoordinate: Coordinate): Mesh<Light>

    @Throws(MeshException::class, CoordinateException::class)
    fun runWinPattern(mesh:Mesh<Light>): Mesh<Light>
}
