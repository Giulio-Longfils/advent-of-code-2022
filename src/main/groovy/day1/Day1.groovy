package day1

class Day1 {
    static void main(String[] args) {
        TreeSet<Integer> maxes = new TreeSet<>()
        List<String> lines = new File('src/main/resources/day1/real.txt').readLines()
        int accumulator = 0

        lines.each { String line ->
            if (line.empty) {
                maxes.add(accumulator)
                println("TOTAL: ${accumulator}. MAX: ${maxes.max()}\n")
                accumulator = 0
            } else {
                accumulator += line as int
                println line
            }
        }

        maxes.add(accumulator)
        println("TOTAL: ${accumulator}. MAX: ${maxes.max()}\n")
        println("MAX 3: ${maxes.pollLast() + maxes.pollLast() + maxes.pollLast()}")
    }
}
