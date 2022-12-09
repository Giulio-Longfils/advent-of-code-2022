package day5

import java.util.regex.Matcher

class Day5 {
    static void main(String[] args) {
        List<String> lines = new File('src/main/resources/day5/real.txt').readLines()

        // find number of stacks
        int i = -1
        Matcher matcher
        do {
            matcher = lines[++i] =~ /\s[0-9].*/
        } while (!matcher.matches())

        int stackLinesBottom = i
        String stacksCountLine = lines[i]
        int stacksCount = stacksCountLine.findAll(/([0-9])/).size()

        Stack<String>[] stacks = new Stack<String>[stacksCount]
        stacks.eachWithIndex { Stack stack, int index -> stacks[index] = new Stack() }

        // fill stacks
        for (int j = stackLinesBottom - 1; j >= 0; j--) {
            List crates = lines[j].findAll(/(\[[A-Z]] ?|\s{4})/)

            crates.eachWithIndex { String crate, int index ->
                if (crate != '    ') {
                    stacks[index].push(crate)
                }
            }
        }

        stacks.each { Stack stack -> println(stack) }

        // parse moves
        for (int j = stackLinesBottom + 2; j < lines.size(); j++) {
            Matcher movesMatcher = lines[j] =~ /move (?<count>[0-9]*) from (?<source>[0-9]*) to (?<dest>[0-9]*)/
            movesMatcher.matches()
            int count = movesMatcher.group('count') as int
            int source = movesMatcher.group('source') as int - 1
            int dest = movesMatcher.group('dest') as int - 1

            println("\n${j}: ${lines[j]}")
            stacks.each { Stack stack -> println(stack) }

            for (int k = 0; k < count; k++) {
                stacks[dest].push(stacks[source].pop())
            }
        }

        StringBuilder stringBuilder = new StringBuilder()
        stacks.each { Stack stack -> stringBuilder.append(stack.last()) }
        println(stringBuilder.toString().replaceAll('\\[', '').replaceAll(']', '').replaceAll(' ', ''))
    }
}
