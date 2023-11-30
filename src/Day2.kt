fun main() {

    fun getListOfFirstPlayerMove(list: List<String>): List<GestureWithPoint> {
        return list.map {
            it.split(" ")
        }.map {
            it.first()
        }.map {
            it.mapGestureWithPoint()
        }
    }

    fun getListOfMyPlayerMove(list: List<String>): List<GestureWithPoint> {
        return list.map {
            it.split(" ")
        }.map {
            it.last()
        }.map {
            it.mapGestureWithPoint()
        }

    }

    fun getListHowRoundShouldEnd(list: List<String>): List<String> {
        return list.map {
            it.split(" ")
        }.map {
            it.last()
        }
    }

    fun getTheNumberOfPointsIScoredInThisMove(
        opponentChoose: GestureWithPoint, myChoose: GestureWithPoint
    ): Int {
        val winPremium = 6
        val drawPremium = 3
        return when (opponentChoose) {
            GestureWithPoint.ROCK -> {
                when (myChoose) {
                    GestureWithPoint.PAPER -> myChoose.point + winPremium
                    GestureWithPoint.ROCK -> myChoose.point + drawPremium
                    GestureWithPoint.SCISSORS -> myChoose.point
                }
            }

            GestureWithPoint.PAPER -> {
                when (myChoose) {
                    GestureWithPoint.ROCK -> myChoose.point
                    GestureWithPoint.PAPER -> myChoose.point + drawPremium
                    GestureWithPoint.SCISSORS -> myChoose.point + winPremium
                }
            }

            GestureWithPoint.SCISSORS -> {
                when (myChoose) {
                    GestureWithPoint.ROCK -> myChoose.point + winPremium
                    GestureWithPoint.PAPER -> myChoose.point
                    GestureWithPoint.SCISSORS -> myChoose.point + drawPremium
                }
            }
        }
    }

    fun getMoveBaseOfCodeAndPlayerMove(opponentChoose: GestureWithPoint, code: String): GestureWithPoint {
        return when (opponentChoose) {
            GestureWithPoint.ROCK -> {
                when (code) {
                    "X" -> GestureWithPoint.SCISSORS //lose
                    "Z" -> GestureWithPoint.PAPER //win
                    else -> opponentChoose
                }
            }

            GestureWithPoint.PAPER -> {
                when (code) {
                    "X" -> GestureWithPoint.ROCK //lose
                    "Z" -> GestureWithPoint.SCISSORS //win
                    else -> opponentChoose
                }
            }

            GestureWithPoint.SCISSORS -> {
                when (code) {
                    "X" -> GestureWithPoint.PAPER // lose
                    "Z" -> GestureWithPoint.ROCK // win
                    else -> opponentChoose
                }
            }
        }
    }

    fun part1(input: List<String>): Int {
        val firstPlayer = getListOfFirstPlayerMove(input)
        val myPlayer = getListOfMyPlayerMove(input)
        var totalPoint: Int = 0
        firstPlayer.forEachIndexed { index, it ->
            totalPoint += getTheNumberOfPointsIScoredInThisMove(it, myPlayer[index])
        }
        return totalPoint
    }

    fun part2(input: List<String>): Int {
        val firstPlayerList = getListOfFirstPlayerMove(input)
        val howRoundShouldEndList = getListHowRoundShouldEnd(input)
        var totalPoint: Int = 0
        firstPlayerList.forEachIndexed { index, it ->
            totalPoint += getTheNumberOfPointsIScoredInThisMove(
                opponentChoose = it,
                myChoose = getMoveBaseOfCodeAndPlayerMove(
                    opponentChoose = it,
                    code = howRoundShouldEndList[index]
                )
            )
        }
        return totalPoint
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    part1(input).println()

    check(part2(testInput) == 12)
    part2(input).println()
}