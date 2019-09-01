import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

class Matrix {

    private List<List<Integer>> matrix;

    private List<Integer> rowMaxValues;
    private List<Integer> colMinValues;

    private List<List<Integer>> columnLocationsforRowMax;
    private List<List<Integer>> rowLocationsforColumnMin;

    /**
     * Matrix Constructor
     * @param passedValues
     * @throws MalformedMatrixException
     *
     * This constructor takes a list which each index is a list of Integers, representing a matrix.
     *
     * The list is traversed in a nested loop to:
     *   1) make sure the matrix isn't jagged
     *       - saddle points are not defined in the case of a jagged matrix, throw exception if this is encountered
     *   2) pre-process the matrix looking for row maximums and column minimums
     *       a) two lists are maintained for the row maximums
     *           i) a list of integers, representing the maximum for that row of the index
     *           ii) a list containing a list of integers, representing all of the columns that the row maximum is encountered
     *               in the case of multiple saddlepoints
     *       b) two lists are maintained for the column minimums
     *
     * Once this is done, then store this data as a private variable
     */
    Matrix(List<List<Integer>> passedValues) throws MalformedMatrixException {
        // create a new list for defensive copy
        List<List<Integer>> values = new ArrayList<List<Integer>>();

        // Each index keeps track of i'th Max/Min respectively
        List<Integer> rowMaxValues = new ArrayList<Integer>();
        List<Integer> colMinValues = new ArrayList<Integer>();

        // Keep a 2-dimensional list, the first index represents the row (col) then the integers
        // in the nested list represent the col (row) of the max (min)
        List<List<Integer>> rowMaxCols = new ArrayList<List<Integer>>();
        List<List<Integer>> colMinRows = new ArrayList<List<Integer>>();

        int rows = passedValues.size();

        if (rows >= 1) {
            int columns = values.get(0).size();

            try {
                IntStream.range(0, rows).forEach(row -> {
                    values.add(new ArrayList<Integer>());

                    IntStream.range(0, columns).forEach(column -> {
                        int value = passedValues.get(row).get(column);

                        // add the value to the defensive copy of values
                        values.get(row).add(value);


                        if (row == rowMaxValues.size()) {
                            rowMaxValues.add(value);
                            rowMaxCols.add(new ArrayList<Integer>());
                            rowMaxCols.get(row).add(column);
                        }
                        else if (value == rowMaxValues.get(row)) {
                            rowMaxCols.get(row).add(column);
                        }
                        else if (value > rowMaxValues.get(row)) {
                            rowMaxValues.set(row, value);
                            rowMaxCols.get(row).clear();
                            rowMaxCols.get(row).add(column);
                        }

                        if (column == colMinValues.size()) {
                            colMinValues.add(value);
                            colMinRows.add(new ArrayList<Integer>());
                            colMinRows.get(column).add(row);
                        }
                        else if (value == colMinValues.get(column)) {
                            colMinRows.get(column).add(row);
                        }
                        else if (value < colMinValues.get(row)) {
                            colMinValues.set(column, value);
                            colMinRows.get(column).clear();
                            colMinRows.get(column).add(row);
                        }
                    });
                });
            } catch (IndexOutOfBoundsException e) {
                throw new MalformedMatrixException("Index doesn't exist in row, jagged matrices are not supported.");
            }
        }

        this.matrix = values;
        this.rowMaxValues = rowMaxValues;
        this.colMinValues = colMinValues;
        this.columnLocationsforRowMax = rowMaxCols;
        this.rowLocationsforColumnMin = colMinRows;
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
