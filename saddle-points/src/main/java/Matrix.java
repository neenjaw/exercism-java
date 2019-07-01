import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

class Matrix {

    private List<List<Integer>> matrix;

    Matrix(List<List<Integer>> values) throws MalformedMatrixException {
        int rows = values.size();

        if (rows >= 1) {
            int columns = values.get(0).size();

            try {
                IntStream.range(0, rows).forEach(row -> {
                    IntStream.range(0, columns).forEach(column -> {
                        values.get(row).get(column);
                    });
                });
            } catch (IndexOutOfBoundsException e) {
                throw new MalformedMatrixException("Index doesn't exist in row, jagged matrices are not supported.");
            }
        }

        matrix = values;
    }

    Set<MatrixCoordinate> getSaddlePoints() {
        Set<MatrixCoordinate> points = new HashSet<>();

        int rows = matrix.size();

        if (rows >= 1) {
            int columns = matrix.get(0).size();

            IntStream.range(0, rows).forEach(row -> {
                IntStream.range(0, columns).forEach(column -> {
                    int currentElement = matrix.get(row).get(column);

                    boolean isSaddlePoint = true;
                    isSaddlePoint = isSaddlePoint && isGreaterOrEqualToRowElements(currentElement, row);
                    isSaddlePoint = isSaddlePoint && isLessorOrEqualToColumnElements(currentElement, column);
    
                    if (isSaddlePoint) {
                        // convert coordinate from 0-indexed to 1-indexed
                        points.add(new MatrixCoordinate(row+1, column+1));
                    }
                });
            });
        }

        return points;
    }

    private boolean isGreaterOrEqualToRowElements(int element, int row) {
        return IntStream.range(0, matrix.get(row).size())
                        .allMatch(column -> {
                            return element >= matrix.get(row).get(column);
                        });
    }


    private boolean isLessorOrEqualToColumnElements(int element, int column) {
        return IntStream.range(0, matrix.size())
                        .allMatch(row -> {
                            return element <= matrix.get(row).get(column);
                        });
    }
}
