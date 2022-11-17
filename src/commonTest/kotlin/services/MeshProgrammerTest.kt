package services

import models.Coordinate
import models.Light
import models.Mesh
import kotlin.test.Test
import kotlin.test.assertEquals

internal class MeshProgrammerTest {
    @Test
    fun canTestTurnOffRange () {
        val meshProgrammer = MeshProgrammer()
        val mesh = Mesh<Light>(1000, 1000)
        mesh.turnOnMesh()

        assertEquals(1000*1000, mesh.getLightsTurnOn(), "Mesh is turn on")
        assertEquals(999*1000, meshProgrammer.turnOffRange(mesh, Coordinate(0,0), Coordinate(999,0)).getLightsTurnOn(), "Mesh is turn off at 0, 0 and 999, 999")
    }

    @Test
    fun canTestTurnOnRange () {
        val meshProgrammer = MeshProgrammer()
        val mesh = Mesh<Light>(1000, 1000)

        assertEquals(0, mesh.getLightsTurnOn(), "Mesh is turn off")
        assertEquals(1*1000, meshProgrammer.turnOnRange(mesh, Coordinate(0,0), Coordinate(999,0)).getLightsTurnOn(), "Mesh is turn on at 0, 0 and 999, 999")
    }

    @Test
    fun canToggleRange () {
        val mesh = Mesh<Light>(1000, 1000)
        val meshProgrammer = MeshProgrammer()

        assertEquals(0, mesh.getLightsTurnOn(), "Mesh is turn off")
        assertEquals(1*1000, meshProgrammer.toggleRange(mesh, Coordinate(0,0), Coordinate(999,0)).getLightsTurnOn(), "Mesh is turn on at 0, 0 and 999, 999")
        assertEquals(0, meshProgrammer.toggleRange(mesh, Coordinate(0,0), Coordinate(999,0)).getLightsTurnOn(), "Mesh is turn off")
    }

    @Test
    fun canTestExamples () {
        val mesh = Mesh<Light>(1000, 1000)
        val meshProgrammer = MeshProgrammer()

        assertEquals(0, meshProgrammer.turnOffRange(mesh, Coordinate(0,0), Coordinate(999,999)).getLightsTurnOn(), "Mesh is turn off")
        assertEquals(1000*1000, meshProgrammer.turnOnRange(mesh, Coordinate(0,0), Coordinate(999,999)).getLightsTurnOn(), "Mesh is turn on")
        assertEquals(999*1000, meshProgrammer.toggleRange(mesh, Coordinate(0,0), Coordinate(999,0)).getLightsTurnOn(), "First row is toggle")
        assertEquals((999*1000) - 4, meshProgrammer.turnOffRange(mesh, Coordinate(499,499), Coordinate(500,500)).getLightsTurnOn(), "Turn off 499, 499 and 500, 500")
    }

    @Test
    fun canTestWinPattern () {
        val mesh = Mesh<Light>(1000, 1000)
        val meshProgrammer = MeshProgrammer()

        assertEquals(230022, meshProgrammer.runWinPattern(mesh).getLightsTurnOn(), "Checking lights on")
        //assertEquals(513805, meshProgrammer.runWinPattern(mesh).getLightsTurnOn(), "Checking lights on")
    }
}