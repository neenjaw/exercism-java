import java.util.Arrays;
import java.util.stream.Stream;

class Matrix {
    private final String ROW_DELIMITER = "\n";
    private final String COL_DELIMITER = " ";

    private int[][] matrix;

    /**
     * Creates a multidimentional int[][] array from a supplied String. Matrix rows
     * are delimited by "\n" and columns delimited by " ".
     * 
     * @param matrixAsString
     * @throws MalformedMatrixException
     */
    Matrix(String matrixAsString) throws MalformedMatrixException {
        // break string into rows
        String[] rowsAsString = matrixAsString.split(ROW_DELIMITER);

        // create temporary matrix, initialize the rows
        matrix = new int[rowsAsString.length][];


        // iterate through the rows, checking for equal columns, keeping track of the index
        int columnCount = -1;
        int rowIndex = 0;
        for (String rowAsString : rowsAsString) {
            // break the row string into cells
            String[] cellsAsStrings = rowAsString.split(COL_DELIMITER);

            // If first iteration, then set the matrix width (column-count)
            if (rowIndex == 0) {
                columnCount = cellsAsStrings.length;
            // Otherwise, make sure the rows are all equal width, if not throw exception
            } else {
                if (columnCount != cellsAsStrings.length) {
                    throw new MalformedMatrixException("The supplied matrix as a string must contain equal columns.");
                }
            }

            // initialize the columns for the row
            matrix[rowIndex] = new int[cellsAsStrings.length];

            // iterate through the cells, building the columns
            int colIndex = 0;
            for (String cellAsString : cellsAsStrings) {
                matrix[rowIndex][colIndex] = Integer.parseInt(cellAsString);

                colIndex++;
            }

            rowIndex++;
        }
    }

    /**
     * Returns the int[] at the specified row number
     * 
     * @param rowNumber
     * @return int[] of column values
     * @throws MatrixIndexOutOfBoundsException
     */
    int[] getRow(int rowNumber) throws MatrixIndexOutOfBoundsException {
        if (rowNumber < 1 || rowNumber > matrix.length) {
            throw new MatrixIndexOutOfBoundsException("Row number must be between 1 and " + matrix.length + ".");
        }

        int[] row = matrix[rowNumber-1];

        return Arrays.copyOf(row, row.length);
    }

    /**
     * Returns an int[] associated with the specified column, uses functional stream
     * to map each row to the single column element, then returns the resulting
     * array.
     * 
     * @param columnNumber
     * @return int[] of column values
     * @throws MatrixIndexOutOfBoundsException
     */
    int[] getColumn(int columnNumber) throws MatrixIndexOutOfBoundsException {
        if (columnNumber < 1 || columnNumber > matrix[0].length) {
            throw new MatrixIndexOutOfBoundsException("Column number must be between 1 and " + matrix[0].length + ".");
        }

        return Stream.of(matrix)
                     .mapToInt(row -> row[columnNumber - 1])
                     .toArray();
    }
}
