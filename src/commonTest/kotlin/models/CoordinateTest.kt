package models

import exception.CoordinateException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class CoordinateTest {
    @Test
    fun cannotCreateCoordinateWithNegativeX () {
        assertFailsWith<CoordinateException>("Fail creating mesh with negative X length") { Coordinate(-1, 1) }
    }

    @Test
    fun cannotCreateCoordinateWithNegativeY () {
        assertFailsWith<CoordinateException>("Fail creating mesh with negative Y length") { Coordinate(1, -1) }
    }

    @Test
    fun canGetX () {
        assertEquals(10, Coordinate(10, 0).getX(), "Get right X point")
    }

    @Test
    fun canGetY () {
        assertEquals(0, Coordinate(10, 0).getY(), "Get right Y point")
    }
}