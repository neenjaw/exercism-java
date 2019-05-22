import java.util.Arrays;
import java.util.stream.DoubleStream;

class Triangle {
    private final double[] sides;

    Triangle(double side1, double side2, double side3) throws TriangleException {
        sides = DoubleStream.of(side1, side2, side3)
                            .sorted()
                            .toArray();

        boolean smallestNonZero = sides[0] != 0;

        boolean validTriangle = sides[0] + sides [1] > sides[2];

        if (!(smallestNotZero && validTriangle)) {
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
        return !(isEquilateral() || isIsosceles());
    }
}
