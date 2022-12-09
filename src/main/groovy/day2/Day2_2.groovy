package day2

import java.util.regex.Matcher

class Day2_2 {

    public static final String OPPONENT = 'opponent'
    public static final String PLAYER = 'player'

    static Shape rock = new Shape(score: 1)
    static Shape paper = new Shape(score: 2)
    static Shape scissors = new Shape(score: 3)

    static Result win = new Result(score: 6)
    static Result draw = new Result(score: 3)
    static Result lose = new Result(score: 0)

    enum EnumResult {

        X(lose),
        Y(draw),
        Z(win)

        Result result

        EnumResult(Result result) {
            this.result = result
        }
    }

    enum EnumShape {

        A(rock),
        B(paper),
        C(scissors),
        X(rock),
        Y(paper),
        Z(scissors)

        Shape shape

        EnumShape(Shape shape) {
            this.shape = shape
        }
    }

    static void main(String[] args) {
        paper.draw = paper
        rock.draw = rock
        scissors.draw = scissors

        paper.defeats = rock
        rock.defeats = scissors
        scissors.defeats = paper

        paper.defeatedBy = scissors
        rock.defeatedBy = paper
        scissors.defeatedBy = rock

        List<String> rounds = new File('src/main/resources/day2/real.txt').readLines()
        int accumulator = 0

        rounds.each { String round ->
            Matcher matcher = round =~ "(?<${OPPONENT}>[ABC])\\s(?<${PLAYER}>[XYZ])"
            matcher.matches()
            String opponentChoice = matcher.group(OPPONENT)
            String resultString = matcher.group(PLAYER)

            Shape opponentShape = (opponentChoice as EnumShape).shape
            Result result = (resultString as EnumResult).result

            int roundScore = result.score

            if (result == win) {
                roundScore += opponentShape.defeatedBy.score
            } else if (result == draw) {
                roundScore += opponentShape.score
            } else {
                roundScore += opponentShape.defeats.score
            }

            accumulator += roundScore
            println("${opponentChoice} -> ${resultString} ${roundScore}")
        }

        println("TOTAL: ${accumulator}")
    }
}
