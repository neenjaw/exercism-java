import java.util.stream.*;

class Hamming {
    final private int hammingDistance;

    Hamming(String leftStrand, String rightStrand) {
        if (leftStrand.length() != rightStrand.length()) {
            if (leftStrand.length() == 0)
                throw new IllegalArgumentException("left strand must not be empty.");

            if (rightStrand.length() == 0)
                throw new IllegalArgumentException("right strand must not be empty.");

            throw new IllegalArgumentException("leftStrand and rightStrand must be of equal length.");
        }

        int hammingCount = (int) IntStream.range(0, leftStrand.length())
                                          .reduce(0, (a, i) -> {
                                              if (leftStrand.charAt(i) != rightStrand.charAt(i))
                                                  return a + 1;
                                              else
                                                  return a;
                                          });

        this.hammingDistance = hammingCount;
    }

    int getHammingDistance() {
        return this.hammingDistance;
    }
}
