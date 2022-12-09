package day9


import java.util.regex.Matcher

class Day9_2 {

    static final int SIZE = 10

    static void main(String[] args) {
        //process('src/main/resources/day9/example_2.txt')
        process('src/main/resources/day9/real.txt')
    }

    static void process(String name) {
        List lines = new File(name).readLines()
        Position[] heads = new Position[SIZE]
        for (int i = 0; i < SIZE; i++) {
            heads[i] = new Position(x: 0, y: 0)
        }

        Set<Position> positions = []

        lines.each { String line ->
            Matcher matcher = line =~ ~/^(?<direction>[RULD])\s(?<steps>[0-9]+)$/
            matcher.matches()
            String direction = matcher.group('direction')
            int steps = matcher.group('steps') as int

            println("\n== ${direction} ${steps} ==")

            for (int i = 0; i < steps; i++) {
                int oldHeadX = heads[SIZE - 1].x
                int oldHeadY = heads[SIZE - 1].y

                switch (direction) {
                    case 'R':
                        heads[SIZE - 1].x++
                        break
                    case 'U':
                        heads[SIZE - 1].y++
                        break
                    case 'L':
                        heads[SIZE - 1].x--
                        break
                    case 'D':
                        heads[SIZE - 1].y--
                        break
                }

                for (int j = SIZE - 1; j > 0; j--) {
                    int oldTailX = heads[j - 1].x
                    int oldTailY = heads[j - 1].y

                    int xDiff = Math.abs(heads[j].x - oldTailX)
                    int yDiff = Math.abs(heads[j].y - oldTailY)

                    int incrementX = heads[j].x > heads[j - 1].x ? 1 : -1
                    int incrementY = heads[j].y > heads[j - 1].y ? 1 : -1

                    if (xDiff > 1 && yDiff > 0 || xDiff > 0 && yDiff > 1) { // diagonal
                        heads[j - 1] = new Position(x: heads[j - 1].x + incrementX, y: heads[j - 1].y + incrementY)
                    } else if (xDiff > 1 && yDiff == 0) { // horizontal
                        heads[j - 1] = new Position(x: heads[j - 1].x + incrementX, y: oldTailY)
                    } else if (xDiff == 0 && yDiff > 1) { // vertical
                        heads[j - 1] = new Position(x: oldTailX, y: heads[j - 1].y + incrementY)
                    }

                    oldHeadX = oldTailX
                    oldHeadY = oldTailY

                    //println("\t${heads[j]} ${heads[j - 1]}")
                }

                println("${heads[SIZE - 1]} ${heads[0]} ${positions.contains(heads[0])}")
                positions << heads[0]
            }
        }

        println("\nTOTAL: ${positions.size()}")
    }
}
