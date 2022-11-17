package services

import contracts.MeshProgrammerInterface
import exception.CoordinateException
import exception.MeshException
import models.Coordinate
import models.Light
import models.Mesh

class MeshProgrammer: MeshProgrammerInterface {
    @Throws(MeshException::class, CoordinateException::class)
    override fun turnOnRange(mesh:Mesh<Light>, startCoordinate: Coordinate, endCoordinate: Coordinate): Mesh<Light> {
        return mesh.turnOnRange(startCoordinate, endCoordinate)
    }

    @Throws(MeshException::class, CoordinateException::class)
    override fun turnOffRange(mesh:Mesh<Light>, startCoordinate: Coordinate, endCoordinate: Coordinate): Mesh<Light> {
        return mesh.turnOffRange(startCoordinate, endCoordinate)
    }

    @Throws(MeshException::class, CoordinateException::class)
    override fun toggleRange(mesh:Mesh<Light>, startCoordinate: Coordinate, endCoordinate: Coordinate): Mesh<Light> {
        return mesh.toggleRange(startCoordinate, endCoordinate)
    }

    @Throws(MeshException::class, CoordinateException::class)
    override fun runWinPattern(mesh:Mesh<Light>): Mesh<Light> {
        //mesh.turnOnMesh().
        mesh.turnOnRange(Coordinate(887, 9), Coordinate(959, 629)).
        turnOnRange(Coordinate(454, 398), Coordinate(844, 448)).
        turnOffRange(Coordinate(539,243), Coordinate(559,965)).
        turnOffRange(Coordinate(370,819), Coordinate(676,868)).
        turnOffRange(Coordinate(145,40), Coordinate(370,997)).
        turnOffRange(Coordinate(301,3), Coordinate(808,453)).
        turnOnRange(Coordinate(351,678), Coordinate(951,908)).
        toggleRange(Coordinate(720,196), Coordinate(897,994)).
        toggleRange(Coordinate(831,394), Coordinate(904,860))
        return mesh
    }
}
