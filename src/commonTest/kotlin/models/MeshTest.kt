package models

import exception.MeshException
import kotlin.test.*

internal class MeshTest {
    @Test
    fun cannotCreateMeshWithNegativeX () {
        assertFailsWith<MeshException>("Fail creating mesh with negative X length") { Mesh<Light>(-1000, 1000) }
    }

    @Test
    fun cannotCreateMeshWithNegativeY () {
        assertFailsWith<MeshException>("Fail creating mesh with negative Y length") { Mesh<Light>(1000, -1000) }
    }

    @Test
    fun canTurnOnMesh () {
        assertTrue(Mesh<Light>(1000, 1000).turnOnMesh().isOn(), "Mesh is turn on")
    }

    @Test
    fun canTurnOffMesh () {
        assertFalse(Mesh<Light>(1000, 1000).turnOffMesh().isOn(), "Mesh is turn off")
    }

    @Test
    fun canGetLightsTurnOnMesh () {
        val mesh = Mesh<Light>(1000, 1000)

        assertEquals(0, mesh.getLightsTurnOn(), "All mesh is turn off")

        mesh.turnOnMesh()
        assertEquals(1000 * 1000, mesh.getLightsTurnOn(), "All mesh is turn on")

        mesh.turnOffMesh()
        assertEquals(0, mesh.getLightsTurnOn(), "All mesh is turn off")
    }

    @Test
    fun cannotMoveMeshWithCoordinateStartXInvalid () {
        val coordinateStart = Coordinate(1001, 1000)
        val coordinateEnd = Coordinate(1000, 1000)
        val mesh = Mesh<Light>(1000, 1000)

        assertFailsWith<MeshException>("Test cannot turn on mesh with coordinate start X invalid") {mesh.turnOnRange(coordinateStart, coordinateEnd)}
        assertFailsWith<MeshException>("Test cannot turn off mesh with coordinate start X invalid") {mesh.turnOffRange(coordinateStart, coordinateEnd)}
        assertFailsWith<MeshException>("Test cannot toggle mesh with coordinate start X invalid") {mesh.toggleRange(coordinateStart, coordinateEnd)}
    }

    @Test
    fun cannotMoveMeshWithCoordinateStartYInvalid () {
        val coordinateStart = Coordinate(1000, 1001)
        val coordinateEnd = Coordinate(1000, 1000)
        val mesh = Mesh<Light>(1000, 1000)

        assertFailsWith<MeshException>("Test cannot turn on mesh with coordinate start Y invalid") {mesh.turnOnRange(coordinateStart, coordinateEnd)}
        assertFailsWith<MeshException>("Test cannot turn off mesh with coordinate start Y invalid") {mesh.turnOffRange(coordinateStart, coordinateEnd)}
        assertFailsWith<MeshException>("Test cannot toggle mesh with coordinate start Y invalid") {mesh.toggleRange(coordinateStart, coordinateEnd)}
    }


    @Test
    fun cannotMoveMeshWithCoordinateEndXInvalid () {
        val coordinateStart = Coordinate(1000, 1000)
        val coordinateEnd = Coordinate(1001, 1000)
        val mesh = Mesh<Light>(1000, 1000)

        assertFailsWith<MeshException>("Test cannot turn on mesh with coordinate end X invalid") {mesh.turnOnRange(coordinateStart, coordinateEnd)}
        assertFailsWith<MeshException>("Test cannot turn off mesh with coordinate end X invalid") {mesh.turnOffRange(coordinateStart, coordinateEnd)}
        assertFailsWith<MeshException>("Test cannot toggle mesh with coordinate end X invalid") {mesh.toggleRange(coordinateStart, coordinateEnd)}
    }

    @Test
    fun cannotMoveMeshWithCoordinateEndYInvalid () {
        val coordinateStart = Coordinate(1000, 1000)
        val coordinateEnd = Coordinate(1000, 1001)
        val mesh = Mesh<Light>(1000, 1000)

        assertFailsWith<MeshException>("Test cannot turn on mesh with coordinate end Y invalid") {mesh.turnOnRange(coordinateStart, coordinateEnd)}
        assertFailsWith<MeshException>("Test cannot turn off mesh with coordinate end Y invalid") {mesh.turnOffRange(coordinateStart, coordinateEnd)}
        assertFailsWith<MeshException>("Test cannot toggle mesh with coordinate end Y invalid") {mesh.toggleRange(coordinateStart, coordinateEnd)}
    }



    @Test
    fun canTurnOnRangeMesh () {
        val mesh = Mesh<Light>(1000, 1000)

        assertEquals(0, mesh.getLightsTurnOn(), "All mesh is turn off")
        assertEquals(1 * 5, mesh.turnOnRange(Coordinate(0,0), Coordinate(0, 4)).getLightsTurnOn(), "Mesh is turn on in 0, 5 & 0, 5")
    }

    @Test
    fun canTurnOffRangeMesh () {
        val mesh = Mesh<Light>(1000, 1000)

        assertEquals(1000*1000, mesh.turnOnMesh().getLightsTurnOn(), "All mesh is turn on")
        assertEquals(1000*1000 - 1 * 5, mesh.turnOffRange(Coordinate(0,0), Coordinate(0, 4)).getLightsTurnOn(), "Mesh is turn off in 0, 5 & 0, 5")
    }


    @Test
    fun canToggleRangeMesh () {
        val mesh = Mesh<Light>(1000, 1000)

        assertEquals(0, mesh.getLightsTurnOn(), "All mesh is turn off")
        assertEquals(1 * 5, mesh.toggleRange(Coordinate(0,0), Coordinate(0, 4)).getLightsTurnOn(), "Mesh is turn on in 0, 5 and  0, 5")
        assertEquals(0, mesh.toggleRange(Coordinate(0,0), Coordinate(0, 4)).getLightsTurnOn(), "All mesh is turn off")
    }
}