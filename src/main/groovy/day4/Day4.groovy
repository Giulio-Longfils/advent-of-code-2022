package day4

import java.util.regex.Matcher

class Day4 {

    public static final String L1 = 'l1'
    public static final String R1 = 'r1'
    public static final String L2 = 'l2'
    public static final String R2 = 'r2'

    static void main(String[] args) {
        List<String> lines = new File('src/main/resources/day4/real.txt').readLines()

        int accumulator = 0
        lines.each { String line ->
            Matcher matcher = line =~ "(?<${L1}>[0-9]+)-(?<${R1}>[0-9]+),(?<${L2}>[0-9]+)-(?<${R2}>[0-9]+)"
            matcher.matches()
            int l1 = matcher.group(L1) as int
            int r1 = matcher.group(R1) as int
            int l2 = matcher.group(L2) as int
            int r2 = matcher.group(R2) as int

            Range first = l1..r1
            Range second = l2..r2

            if (fullyContains(first, second)) {
                println(first)
                accumulator++
            }

        }

        println("TOTAL: ${accumulator}")
    }

    static boolean fullyContains(Range r1, Range r2) {
        r1.from <= r2.from && r1.to >= r2.to || r2.from <= r1.from && r2.to >= r1.to
    }
}
