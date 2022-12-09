package day8

class Day8_2 {
    static void main(String[] args) {
        //process('src/main/resources/day8/example.txt')
        process('src/main/resources/day8/real.txt')
    }

    static void process(String name) {
        List lines = new File(name).readLines()
        int rows = lines.size()
        int cols = lines[0].length()
        int maxScore = 0

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int current = lines[i][j] as int
                int comparison

                // from left
                int countLeft = 0

                for (int k = j - 1; k >= 0; k--) { // from left
                    countLeft++
                    if ((lines[i][k] as int) >= current) {
                        break
                    }
                }

                // from top
                int countTop = 0

                for (int k = i - 1; k >= 0; k--) { // from left
                    countTop++
                    if ((lines[k][j] as int) >= current) {
                        break
                    }
                }

                // from right
                int countRight = 0

                for (int k = j + 1; k < rows; k++) { // from left
                    countRight++
                    if ((lines[i][k] as int) >= current) {
                        break
                    }
                }

                // from bottom
                int countBottom = 0

                for (int k = i + 1; k < cols; k++) { // from left
                    countBottom++
                    if ((lines[k][j] as int) >= current) {
                        break
                    }
                }

                int score = countLeft * countTop * countRight * countBottom
                if (score > maxScore) {
                    maxScore = score
                }
            }
        }

        println("max score: ${maxScore}")
    }
}
