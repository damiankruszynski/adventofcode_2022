enum class GestureWithPoint(val point: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3),
}

fun String.mapGestureWithPoint(): GestureWithPoint {
    return when (this) {
        "A" -> GestureWithPoint.ROCK
        "B" -> GestureWithPoint.PAPER
        "C" -> GestureWithPoint.SCISSORS
        "X" -> GestureWithPoint.ROCK
        "Y" -> GestureWithPoint.PAPER
        "Z" -> GestureWithPoint.SCISSORS
        else -> throw Exception("Code not allowed: $this")
    }
}

