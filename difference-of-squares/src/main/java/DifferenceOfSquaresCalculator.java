import java.util.stream.IntStream;

class DifferenceOfSquaresCalculator {
    private final String ILLEGAL_INPUT_ERROR = "Input must be a natural number.";

    private boolean validInput(int input) {
        return input >= 1;
    }

    int computeSumOfSquaresTo(int input) {
        if (!validInput(input)) {
            throw new IllegalArgumentException(ILLEGAL_INPUT_ERROR);
        }

        return IntStream.rangeClosed(1, input)
                        .map(n -> n*n)
                        .sum();
    }

    int computeSquareOfSumTo(int input) {
        if (!validInput(input)) {
            throw new IllegalArgumentException(ILLEGAL_INPUT_ERROR);
        }

        int rangeSum =  IntStream.rangeClosed(1, input)
                                 .sum();

        return rangeSum * rangeSum;
    }

    int computeDifferenceOfSquares(int input) {
        int sumOfSquares = computeSumOfSquaresTo(input);
        int squareOfSum = computeSquareOfSumTo(input);

        return squareOfSum - sumOfSquares;
    }

    int computeClosedFormSumOfSquaresTo(int input) {
        if (!validInput(input)) {
            throw new IllegalArgumentException(ILLEGAL_INPUT_ERROR);
        }

        return ((input * (input + 1) * ((2 * input) + 1)) / 6);
    }

    int computeClosedFormSquareOfSumTo(int input) {
        if (!validInput(input)) {
            throw new IllegalArgumentException(ILLEGAL_INPUT_ERROR);
        }

        int closedSum = (input * (input + 1)) / 2;

        return closedSum * closedSum;
    }

    int computeClosedFormDifferenceOfSquares(int input) {
        int sumOfSquares = computeClosedFormSumOfSquaresTo(input);
        int squareOfSum = computeClosedFormSquareOfSumTo(input);

        return squareOfSum - sumOfSquares;
    }
}
