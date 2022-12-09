package day3

class Day3_2 {

    public static final LOWERCASE_A_OFFSET = 96
    public static final UPPERCASE_A_OFFSET = 64

    static void main(String[] args) {
        List<String> lines = new File('src/main/resources/day3/real.txt').readLines()

        int accumulator = 0
        for (int i = 0; i < lines.size(); i += 3) {
            Set<String> intersection = lines[i].toSet()
                    .intersect(lines[i+1].toSet())
                    .intersect(lines[i+2].toSet())

            intersection.each { String c ->
                int value = c.toCharacter() - (c.matches(/[a-z]/) ? LOWERCASE_A_OFFSET : UPPERCASE_A_OFFSET - 26)
                accumulator += value
                println("${c} -> ${value}")
            }
        }

        println("TOTAL: ${accumulator}")
    }
}
