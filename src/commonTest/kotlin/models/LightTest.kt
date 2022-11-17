package models

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class LightTest {
    @Test
    fun canTurnOnLight () {
        assertTrue(Light().turnOn().isOn(), "Turn on light")
    }

    @Test
    fun canTurnOffLight () {
        assertFalse(Light().turnOff().isOn(), "Turn off light")
    }

    @Test
    fun canToggleLight () {
        val light = Light()
        assertTrue(light.toggle().isOn(), "Turn on light")
        assertFalse(light.toggle().isOn(), "Turn off light")
    }
}