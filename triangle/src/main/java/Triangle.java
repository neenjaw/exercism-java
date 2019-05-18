import java.util.Arrays;
import java.util.stream.Stream;

class Triangle {
    private double a;
    private double b;
    private double c;

    Triangle(double side1, double side2, double side3) throws TriangleException {
        double[] sides = {side1, side2, side3};

        boolean allSidesNonZero = Arrays.stream(sides)
                                        .allMatch(side -> side > 0);

        double[] sortedSides = Arrays.stream(sides)
                                     .sorted()
                                     .toArray();

        a = sortedSides[0];
        b = sortedSides[1];
        c = sortedSides[2];

        boolean validTriangle = a + b > c;

        if (!(allSidesNonZero && validTriangle)) {
            throw new TriangleException("Invalid Triangle Side Lengths");
        }
    }

    boolean isEquilateral() {
        return (a == b && b == c);
    }

    boolean isIsosceles() {
        return (a == b || b == c || c == a);
    }

    boolean isScalene() {
        return (a != b && b != c && c != a);
    }

}
