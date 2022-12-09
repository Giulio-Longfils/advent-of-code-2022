package day9

import groovy.transform.EqualsAndHashCode

import java.util.regex.Matcher

class Day9 {
    static void main(String[] args) {
        //process('src/main/resources/day9/example.txt')
        process('src/main/resources/day9/real.txt')
    }

    static void process(String name) {
        List lines = new File(name).readLines()
        Position head = new Position(x: 0, y: 0)
        Position tail = new Position(x: 0, y: 0)
        Set<Position> positions = []

        lines.each { String line ->
            Matcher matcher = line =~ ~/^(?<direction>[RULD])\s(?<steps>[0-9]+)$/
            matcher.matches()
            String direction = matcher.group('direction')
            int steps = matcher.group('steps') as int

            println("\n== ${direction} ${steps} ==")
            for (int i = 0; i < steps; i++) {
                int oldHeadX = head.x
                int oldHeadY = head.y

                switch (direction) {
                    case 'R':
                        head.x++
                        break
                    case 'U':
                        head.y++
                        break
                    case 'L':
                        head.x--
                        break
                    case 'D':
                        head.y--
                        break
                }

                int xDiff = Math.abs(head.x - tail.x)
                int yDiff = Math.abs(head.y - tail.y)

                if (xDiff > 1 && yDiff > 0 || xDiff > 0 && yDiff > 1) { // diagonal
                    tail = new Position(x: oldHeadX, y: oldHeadY)
                } else if (xDiff > 1 && yDiff == 0) { // horizontal
                    tail = new Position(x: oldHeadX, y: tail.y)
                } else if (xDiff == 0 && yDiff > 1) { // vertical
                    tail = new Position(x: tail.x, y: oldHeadY)
                }

                println("${head} ${tail} ${positions.contains(tail)}")
                positions << tail
            }
        }

        println("\nTOTAL: ${positions.size()}")
    }
}

@EqualsAndHashCode
class Position {
    int x
    int y

    @Override
    String toString() {
        "(${x},${y})"
    }
}
