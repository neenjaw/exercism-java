import java.util.stream.DoubleStream;

class Triangle {
    private final double[] sides;

    Triangle(double side1, double side2, double side3) throws TriangleException {
        sides = DoubleStream.of(side1, side2, side3)
                            .sorted()
                            .toArray();

        boolean invalidSideLength = sides[0] <= 0;

        boolean invalidTriangle = sides[0] + sides [1] <= sides[2];

        if (invalidSideLength || invalidTriangle) {
            throw new TriangleException("Invalid Triangle Side Lengths");
        }
    }

    boolean isEquilateral() {
        return sides[0] == sides[2];
    }

    boolean isIsosceles() {
        return (sides[0] == sides[1]) || (sides[1] == sides[2]);
    }

    boolean isScalene() {
        return !isIsosceles();
    }
}
