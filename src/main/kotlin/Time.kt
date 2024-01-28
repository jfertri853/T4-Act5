package org.example

class Time(hours: Int, minutes: Int, seconds: Int) {
    private var hours: Int = hours
        set(value) {
            verifyHours(value)
            field = value
        }

    private var minutes: Int = minutes
        set(value) {
            verifyMinutes(value)
            field = value
        }

    private var seconds: Int = seconds
        set(value) {
            verifySeconds(value)
            field = value
        }

    init {
        modifyTime(hours, minutes, seconds)
        verifyHours(hours)
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
    private fun modifyTime(hours: Int, minutes: Int, seconds: Int) {
        var newSeconds = seconds
        var newMinutes = minutes
        var newHours = hours

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

        this.seconds = newSeconds
        this.minutes = newMinutes
//        this.hours = newHours
    }


    override fun toString(): String {
        return "${hours}h ${minutes}m ${seconds}s"
    }
}