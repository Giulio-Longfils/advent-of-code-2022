package day10

import java.util.regex.Matcher

class Day10 {

    static final int START_CYCLE = 20
    static final int PACE = 40
    static int total = 0

    static void main(String[] args) {
        //process('src/main/resources/day10/example.txt')
        process('src/main/resources/day10/real.txt')
    }

    static void process(String name) {
        List lines = new File(name).readLines()
        int x = 1
        int currCycle = 0

        lines.each { String line ->
            switch (line) {
                case ~/^noop$/:    // noop
                    check(++currCycle, x)
                    break
                case ~/^addx\s(?<value>-?[0-9]+$)/:  // addx
                    Matcher matcher = line =~ ~/^addx\s(?<value>-?[0-9]+$)/
                    matcher.matches()
                    int value = matcher.group('value') as int

                    check(++currCycle, x)
                    check(++currCycle, x)
                    x += value
                    break
            }
        }

        println("\nTOTAL: ${total}")
    }

    static void check(int currCycle, int x) {
        if (currCycle == START_CYCLE || (currCycle - START_CYCLE) % PACE == 0) {
            int result = currCycle * x
            println("${currCycle} * ${x} = ${result}")
            total += result
        }
    }
}
