package day8

import groovy.transform.EqualsAndHashCode

class Day8 {
    static void main(String[] args) {
        //process('src/main/resources/day8/example.txt')
        process('src/main/resources/day8/real.txt')
    }

    static void process(String name) {
        List lines = new File(name).readLines()
        int rows = lines.size()
        int cols = lines[0].length()

        Set indexes = []

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int current = lines[i][j] as int

                // from left
                boolean fromLeft = true

                for (int k = j - 1; k >= 0; k--) { // from left
                    if ((lines[i][k] as int) >= current) {
                        fromLeft = false
                        break
                    }
                }

                if (fromLeft) {
                    indexes << new Index(i: i, j: j)
                }

                // from top
                boolean fromTop = true

                for (int k = i - 1; k >= 0; k--) { // from left
                    if ((lines[k][j] as int) >= current) {
                        fromTop = false
                        break
                    }
                }

                if (fromTop) {
                    indexes << new Index(i: i, j: j)
                }

                // from right
                boolean fromRight = true

                for (int k = j + 1; k < rows; k++) { // from left
                    if ((lines[i][k] as int) >= current) {
                        fromRight = false
                        break
                    }
                }

                if (fromRight) {
                    indexes << new Index(i: i, j: j)
                }

                // from bottom
                boolean fromBottom = true

                for (int k = i + 1; k < cols; k++) { // from left
                    if ((lines[k][j] as int) >= current) {
                        fromBottom = false
                        break
                    }
                }

                if (fromBottom) {
                    indexes << new Index(i: i, j: j)
                }
            }
        }

        println("TOTAL: ${indexes.size()}")
        println(indexes)
    }
}

@EqualsAndHashCode
class Index {
    int i
    int j

    @Override
    String toString() {
        "(${i},${j})"
    }
}
