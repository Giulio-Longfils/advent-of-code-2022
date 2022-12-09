package day3

class Day3 {

    public static final LOWERCASE_A_OFFSET = 96
    public static final UPPERCASE_A_OFFSET = 64

    static void main(String[] args) {
        List<String> lines = new File('src/main/resources/day3/real.txt').readLines()

        int accumulator = 0
        lines.each { String line ->
            int half = line.size() / 2 as int
            String halfLeft = line[0..half - 1]
            String halfRight = line[half..-1]

            //println(halfLeft)
            //println(halfRight)

            Set<String> intersection = halfLeft.toSet().intersect(halfRight.toSet())
            intersection.each { String c ->
                int value = c.toCharacter() - (c.matches(/[a-z]/) ? LOWERCASE_A_OFFSET : UPPERCASE_A_OFFSET - 26)
                accumulator += value
                println("${c} -> ${value}")
            }
        }

        println("TOTAL: ${accumulator}")
    }
}
