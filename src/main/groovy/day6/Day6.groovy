package day6

class Day6 {
    static void main(String[] args) {
        process('src/main/resources/day6/example.txt')
        process('src/main/resources/day6/example2.txt')
        process('src/main/resources/day6/example3.txt')
        process('src/main/resources/day6/example4.txt')
        process('src/main/resources/day6/example5.txt')
        process('src/main/resources/day6/real.txt')
    }

    static void process(String name) {
        String line = new File(name).text
        int windowSize = 4
        int position = 0
        Map map

        do {
            map = [:]
            print("${position} ")
            for (int i = position; i < position + windowSize; i++) {
                map << [(line[i]): true]
            }

            position++
            println(map)
        } while (position + windowSize < line.length() && map.size() < windowSize)

        int count = position + windowSize - 1
        println("Count is ${count}")
        println(map)
        println()
    }
}
