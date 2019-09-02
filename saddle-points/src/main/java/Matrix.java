import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

class Matrix {

    private List<List<Integer>> matrix;

    private Set<MatrixCoordinate>  rowMaximums;
    private Set<MatrixCoordinate>  columnMinimums;

    /**
     * Matrix Constructor
     * @param values
     * @throws MalformedMatrixException
     *
     * This constructor takes a list which each index is a list of Integers, representing a matrix.
     *
     * The list is traversed in a nested loop to:
     *   1) make sure the matrix isn't jagged
     *       - saddle points are not defined in the case of a jagged matrix, throw exception if this is encountered
     *   2) pre-process the matrix looking for row maximums and column minimums
     *       a) an ArrayList<Integer>, HashMap<Integer, HashSet<MatrixCoordinate>> is maintained for the row maximums
     *           i) a list of integers, representing the maximum for that row of the index
     *           ii) a map containing the maximums for that row, able to be retrieved O(1) in the event that they must be cleared
     *       b) two lists are maintained for the column minimums
     *   3) collect the hashsets for each of the rows/cols into one for all rows or all columns
     *
     * Once this is done, then store this data as a private field
     */
    Matrix(List<List<Integer>> values) throws MalformedMatrixException {
        // defensive copy
        matrix = new ArrayList<>(values.size());
        for (List<Integer> row : values) {
            matrix.add(new ArrayList<>(row));
        }

        // Each index keeps track of i'th Max/Min respectively
        List<Integer> rowMaxValues = new ArrayList<Integer>();
        List<Integer> colMinValues = new ArrayList<Integer>();

        // HashMap keeping the maximums and minimums for each row accessible
        Map<Integer, Set<MatrixCoordinate>> rowMaximumsMap = new HashMap<Integer, Set<MatrixCoordinate>>();
        Map<Integer, Set<MatrixCoordinate>> columnMinimumsMap = new HashMap<Integer, Set<MatrixCoordinate>>();

        int rows = matrix.size();

        if (rows >= 1) {
            int columns = matrix.get(0).size();

            try {
                IntStream.range(0, rows).forEach(row -> {

                    IntStream.range(0, columns).forEach(column -> {
                        int value = matrix.get(row).get(column);

                        // Keep track of the row's max value:
                        // -- if a new row, make the list and add the first element
                        if (row == rowMaxValues.size()) {
                            rowMaxValues.add(value);
                            rowMaximumsMap.put(row, new HashSet<MatrixCoordinate>());
                            rowMaximumsMap.get(row).add(new MatrixCoordinate(row+1, column+1));
                        }
                        // -- if the value is equal to the current max, then add it to the list
                        else if (value == rowMaxValues.get(row)) {
                            rowMaximumsMap.get(row).add(new MatrixCoordinate(row+1, column+1));
                        }
                        // -- if the value is greater, then record the new max, clear the list, add itself
                        else if (value > rowMaxValues.get(row)) {
                            rowMaxValues.set(row, value);
                            rowMaximumsMap.get(row).clear();
                            rowMaximumsMap.get(row).add(new MatrixCoordinate(row+1, column+1));
                        }

                        // Keep track of the columns's min value:
                        // -- if a new col, make the list and add the first element
                        if (column == colMinValues.size()) {
                            colMinValues.add(value);
                            columnMinimumsMap.put(column, new HashSet<MatrixCoordinate>());
                            columnMinimumsMap.get(column).add(new MatrixCoordinate(row+1, column+1));
                        }
                        // -- if the value is equal to the current min, then add it to the list
                        else if (value == colMinValues.get(column)) {
                            columnMinimumsMap.get(column).add(new MatrixCoordinate(row+1, column+1));
                        }
                        // -- if the value is lesser, then record the new min, clear the list, add itself
                        else if (value < colMinValues.get(column)) {
                            colMinValues.set(column, value);
                            columnMinimumsMap.get(column).clear();
                            columnMinimumsMap.get(column).add(new MatrixCoordinate(row+1, column+1));
                        }
                    });
                });
            } catch (IndexOutOfBoundsException e) {
                throw new MalformedMatrixException(e.getMessage() + ", jagged matrices are not supported.");
            }
        }

        // Squash map values into one set, assign to field
        rowMaximums = rowMaximumsMap.values().stream().collect(HashSet::new, Set::addAll, Set::addAll);
        columnMinimums = columnMinimumsMap.values().stream().collect(HashSet::new, Set::addAll, Set::addAll);
    }

    /**
     * getSaddlePoints
     *
     * Using the previously collected data about row maximums and column minimums, stream the row maximums, look through
     * the column minimums for an intersection.
     *
     * If an intersection is found, then create the point to return.
     *
     * Timing:
     *   O(n) to iterate through the stream of the set of MatrixCoordinates
     *
     * @return Set<MatrixCoordinate>
     */
    Set<MatrixCoordinate> getSaddlePoints() {
        Set<MatrixCoordinate> points = new HashSet<>();

        rowMaximums.stream()
                   .filter(coordinate -> columnMinimums.contains(coordinate))
                   .forEach(coordinate -> points.add(coordinate));

        return points;
    }
}
