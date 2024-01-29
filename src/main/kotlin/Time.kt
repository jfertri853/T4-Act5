package org.example

class Time(hours: Int, minutes: Int, seconds: Int) {
    private var seconds: Int = 0
        set(value) {
            verifySeconds(value)
            field = value
        }

    private var minutes: Int = 0
        set(value) {
            verifyMinutes(value)
            field = value
        }

    private var hours: Int = 0
        set(value) {
            verifyHours(value)
            field = value
        }

    init {
        updateTime(seconds, minutes, hours)
    }

    companion object {
        const val MIN_HOUR = 0
        const val MAX_HOUR = 23
        const val MIN_MINUTE = 0
        const val MAX_MINUTE = 59
        const val MIN_SECOND = 0
        const val MAX_SECOND = 59
    }

    /** Crea objetos Time sin definir los segundos
     *
     * @param hours
     * @param minutes
     */
    constructor(hours: Int, minutes: Int) : this(hours, minutes, 0)

    /** Crea objetos Time sin definir los minutos ni los segundos
     *
     * @param hours
     */
    constructor(hours: Int) : this(hours, 0, 0)


    /** Verifica que las horas estén entre el mínimo y el máximo
     *
     * @param hours
     */
    private fun verifyHours(hours: Int) {
        require(hours in MIN_HOUR..MAX_HOUR) {"Las horas deben estar entre $MIN_HOUR y $MAX_HOUR"}
    }


    /** Verifica que los minutos estén entre el mínimo y el máximo
     *
     * @param minutes
     */
    private fun verifyMinutes(minutes: Int) {
        require(minutes in MIN_MINUTE..MAX_MINUTE) {"Los minutos no pueden ser menores a $MIN_MINUTE" +
                " ni mayores a $MAX_MINUTE"}
    }


    /** Verifica que los minutos estén entre el mínimo y el máximo
     *
     * @param seconds
     */
    private fun verifySeconds(seconds: Int) {
        require(seconds in MIN_SECOND..MAX_SECOND) {"Los segundos no pueden ser menores a $MIN_SECOND" +
                " ni mayores a $MAX_SECOND"}
    }


    /** Modifica el tiempo en caso de que haya valores por encima de su Máximo
     *
     * @param hours
     * @param minutes
     * @param seconds
     */
    private fun updateTime(seconds: Int, minutes: Int, hours: Int) {
        var newSeconds = seconds + this.seconds
        var newMinutes = minutes + this.minutes
        var newHours = hours + this.hours

        if (newSeconds > MAX_SECOND) {
            newMinutes += newSeconds / 60
            newSeconds %= 60
        }

        if (newMinutes > MAX_MINUTE) {
            newHours += newMinutes / 60
            newMinutes %= 60
        }

//        while (newHours > MAX_HOUR) {
//            newHours %= MAX_HOUR
//        }
        // Este trozo era para que se reiniciase a 0 las horas mientras estas superasen el máximo

        verifyHours(newHours)
        this.seconds += newSeconds - this.seconds
        this.minutes += newMinutes - this.minutes
        this.hours += newHours - this.hours
    }


    /** Pide horas, minutos y segundos al usuario e incrementa el tiempo del objeto esa cantidad introducida
     */
    fun increaseTime(): Boolean {
        var addedNumber: String

        try {
            print("Introduce la cantidad de horas que quieres añadir: ")
            addedNumber = readln()
            val addedHours = if (addedNumber.trim().isEmpty()) 0 else addedNumber.toInt()

            print("Introduce la cantidad de minutos que quieres añadir: ")
            addedNumber = readln()
            val addedMinutes = if (addedNumber.trim().isEmpty()) 0 else addedNumber.toInt()

            print("Introduce la cantidad de segundos que quieres añadir: ")
            addedNumber = readln()
            val addedSeconds = if (addedNumber.trim().isEmpty()) 0 else addedNumber.toInt()

            if (addedHours < 0 || addedMinutes < 0 || addedSeconds < 0) {
                throw NumberFormatException("No puedes añadir tiempo negativo (podrías si supiese programar)")
            } else {
                updateTime(addedSeconds, addedMinutes, addedHours)
            }
        } catch (e: NumberFormatException) {
            println("**ERROR** - $e")
            return false
        } catch (e: IllegalArgumentException) {
            println("**ERROR** - $e")
            return false
        }
        return true
    } //TODO mejorar método increaseTime()


    override fun toString(): String {
        return "${hours}h ${minutes}m ${seconds}s"
    }
}