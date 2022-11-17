package models

class Light {
    private var on: Boolean = false

    fun isOn(): Boolean {
        return on
    }

    fun turnOn(): Light {
        on = true
        return this
    }

    fun turnOff(): Light {
        on = false
        return this
    }

    fun toggle(): Light {
        on = !on
        return this
    }
}