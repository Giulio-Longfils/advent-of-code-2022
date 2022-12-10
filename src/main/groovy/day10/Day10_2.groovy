package day10

import java.util.regex.Matcher

class Day10_2 {

    static final int LINE_WIDTH = 40
    static int currPixel = 0

    static void main(String[] args) {
        //process('src/main/resources/day10/example.txt')
        process('src/main/resources/day10/real.txt')
    }

    static void process(String name) {
        List lines = new File(name).readLines()
        int spriteMiddle = 1

        lines.each { String line ->
            switch (line) {
                case ~/^noop$/:    // noop
                    render spriteMiddle

                    break
                case ~/^addx\s(?<value>-?[0-9]+$)/:  // addx
                    Matcher matcher = line =~ ~/^addx\s(?<value>-?[0-9]+$)/
                    matcher.matches()
                    int value = matcher.group('value') as int

                    render spriteMiddle
                    render spriteMiddle
                    spriteMiddle += value
                    break
            }
        }
    }

    static void render(int spriteMiddle) {
        int pixelInLine = currPixel++ % LINE_WIDTH
        String newLine = pixelInLine == 0 ? '\n' : ''
        String renderedPixel = pixelInLine >= spriteMiddle - 1 && pixelInLine <= spriteMiddle + 1 ? '#' : '.'

        print("${newLine}${renderedPixel}")
    }
}
