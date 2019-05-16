
// import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MatrixTest {

    private static final String MALFORMED_EXCEPTION = "The supplied matrix as a string must contain equal columns.";
    private static final String COLUMN_NUMBER_MUST_BE_BETWEEN_1_AND_1 = "Column number must be between 1 and 1.";
    private static final String ROW_NUMBER_MUST_BE_BETWEEN_1_AND_1 = "Row number must be between 1 and 1.";
    private static final String NO_EXCEPTION_EXPECTED = "Did not expect an Exception to be thrown.";

    @Test
    public void testDefensiveCopy() {
        String matrixAsString = "1";
        int[] expectedRow = { 1 };
        int rowIndex = 1;

        try {
            Matrix matrix = new Matrix(matrixAsString);

            int[] row = matrix.getRow(rowIndex);
            row[0] = 2;

            assertArrayEquals(expectedRow, matrix.getRow(rowIndex));
        } catch (Exception e) {
            fail(NO_EXCEPTION_EXPECTED);
            e.printStackTrace();
        }
    }

    @Test
    public void testMalformedSmallExceptionMessage() {
        String matrixAsString = "1 2\n  4";

        try {
            new Matrix(matrixAsString);

            fail("Expected an MalformedMatrixException to be thrown");
        } catch (MalformedMatrixException e) {
            assertEquals(e.getMessage(), MALFORMED_EXCEPTION);
        }
    }

    @Test
    public void testMalformedMediumExceptionMessage() {
        String matrixAsString = "1 2 3\n4   6\n7 8 9";

        try {
            new Matrix(matrixAsString);

            fail("Expected an MalformedMatrixException to be thrown");
        } catch (MalformedMatrixException e) {
            assertEquals(e.getMessage(), MALFORMED_EXCEPTION);
        }
    }
    @Test
    public void testRowIndexHighExceptionMessage() {
        String matrixAsString = "1";
        int rowIndex = 2;

        try {
            Matrix matrix = new Matrix(matrixAsString);
            matrix.getRow(rowIndex);

            fail("Expected an MatrixIndexOutOfBoundsException to be thrown");
        } catch (MalformedMatrixException | MatrixIndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), ROW_NUMBER_MUST_BE_BETWEEN_1_AND_1);
        }
    }

    @Test
    public void testRowIndexLowExceptionMessage() {
        String matrixAsString = "1";
        int rowIndex = 0;

        try {
            Matrix matrix = new Matrix(matrixAsString);
            matrix.getRow(rowIndex);

            fail("Expected an MatrixIndexOutOfBoundsException to be thrown");
        } catch (MalformedMatrixException | MatrixIndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), ROW_NUMBER_MUST_BE_BETWEEN_1_AND_1);
        }
    }

    @Test
    public void testColumnIndexHighExceptionMessage() {
        String matrixAsString = "1";
        int columnIndex = 2;

        try {
            Matrix matrix = new Matrix(matrixAsString);
            matrix.getColumn(columnIndex);

            fail("Expected an MatrixIndexOutOfBoundsException to be thrown");
        } catch (MalformedMatrixException | MatrixIndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), COLUMN_NUMBER_MUST_BE_BETWEEN_1_AND_1);
        }
    }

    @Test
    public void testColumnIndexLowExceptionMessage() {
        String matrixAsString = "1";
        int columnIndex = 0;

        try {
            Matrix matrix = new Matrix(matrixAsString);
            matrix.getColumn(columnIndex);

            fail("Expected an MatrixIndexOutOfBoundsException to be thrown");
        } catch (MalformedMatrixException | MatrixIndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), COLUMN_NUMBER_MUST_BE_BETWEEN_1_AND_1);
        }
    }
    @Test
    public void extractRowFromOneNumberMatrixTest() {
        String matrixAsString = "1";
        int rowIndex = 1;
        int[] expectedRow = { 1 };

        try {
            Matrix matrix = new Matrix(matrixAsString);

            assertArrayEquals(expectedRow, matrix.getRow(rowIndex));
        } catch (MalformedMatrixException | MatrixIndexOutOfBoundsException e) {
            fail(NO_EXCEPTION_EXPECTED);
            e.printStackTrace();
        }
    }

    // @Ignore("Remove to run test")
    @Test
    public void extractRowFromMatrixTest() {
        String matrixAsString = "1 2\n3 4";
        int rowIndex = 2;
        int[] expectedRow = { 3, 4 };

        try {
            Matrix matrix = new Matrix(matrixAsString);

            assertArrayEquals(expectedRow, matrix.getRow(rowIndex));
        } catch (MalformedMatrixException | MatrixIndexOutOfBoundsException e) {
            fail(NO_EXCEPTION_EXPECTED);
            e.printStackTrace();
        }
    }

    // @Ignore("Remove to run test")
    @Test
    public void extractRowFromDiffWidthsMatrixTest() {
        String matrixAsString = "1 2\n10 20";
        int rowIndex = 2;
        int[] expectedRow = { 10, 20 };

        try {
            Matrix matrix = new Matrix(matrixAsString);

            assertArrayEquals(expectedRow, matrix.getRow(rowIndex));
        } catch (MalformedMatrixException | MatrixIndexOutOfBoundsException e) {
            fail(NO_EXCEPTION_EXPECTED);
            e.printStackTrace();
        }
    }

    // @Ignore("Remove to run test")
    @Test
    public void extractRowFromNonSquareMatrixTest() {
        String matrixAsString = "1 2 3\n4 5 6\n7 8 9\n8 7 6";
        int rowIndex = 3;
        int[] expectedRow = { 7, 8, 9 };

        try {
            Matrix matrix = new Matrix(matrixAsString);

            assertArrayEquals(expectedRow, matrix.getRow(rowIndex));
        } catch (MalformedMatrixException | MatrixIndexOutOfBoundsException e) {
            fail(NO_EXCEPTION_EXPECTED);
            e.printStackTrace();
        }
    }

    // @Ignore("Remove to run test")
    @Test
    public void extractColumnFromOneNumberMatrixTest() {
        String matrixAsString = "1";
        int columnIndex = 1;
        int[] expectedColumn = { 1 };

        try {
            Matrix matrix = new Matrix(matrixAsString);

            assertArrayEquals(expectedColumn, matrix.getColumn(columnIndex));
        } catch (MalformedMatrixException | MatrixIndexOutOfBoundsException e) {
            fail(NO_EXCEPTION_EXPECTED);
            e.printStackTrace();
        }
    }

    // @Ignore("Remove to run test")
    @Test
    public void extractColumnMatrixTest() {
        String matrixAsString = "1 2 3\n4 5 6\n7 8 9";
        int columnIndex = 3;
        int[] expectedColumn = {3, 6, 9};

        try {
            Matrix matrix = new Matrix(matrixAsString);

            assertArrayEquals(expectedColumn, matrix.getColumn(columnIndex));
        } catch (MalformedMatrixException | MatrixIndexOutOfBoundsException e) {
            fail(NO_EXCEPTION_EXPECTED);
            e.printStackTrace();
        }
    }

    // @Ignore("Remove to run test")
    @Test
    public void extractColumnFromNonSquareMatrixTest() {
        String matrixAsString = "1 2 3\n4 5 6\n7 8 9\n8 7 6";
        int columnIndex = 3;
        int[] expectedColumn = {3, 6, 9, 6};

        try {
            Matrix matrix = new Matrix(matrixAsString);

            assertArrayEquals(expectedColumn, matrix.getColumn(columnIndex));
        } catch (MalformedMatrixException | MatrixIndexOutOfBoundsException e) {
            fail(NO_EXCEPTION_EXPECTED);
            e.printStackTrace();
        }
    }

    // @Ignore("Remove to run test")
    @Test
    public void extractColumnFromDiffWidthsMatrixTest() {
        String matrixAsString = "89 1903 3\n18 3 1\n9 4 800";
        int columnIndex = 2;
        int[] expectedColumn = {1903, 3, 4};

        try {
            Matrix matrix = new Matrix(matrixAsString);

            assertArrayEquals(expectedColumn, matrix.getColumn(columnIndex));
        } catch (MalformedMatrixException | MatrixIndexOutOfBoundsException e) {
            fail(NO_EXCEPTION_EXPECTED);
            e.printStackTrace();
        }
    }
}
