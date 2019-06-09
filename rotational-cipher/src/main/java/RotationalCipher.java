class RotationalCipher {
    private final int CIPHER_SHIFT_KEY;
    private final int ALPHABET_LENGTH = 26;

    private final int UPPER_CASE_RANGE_START = (int) 'A';
    private final int UPPER_CASE_RANGE_END = (int) 'Z';

    private final int LOWER_CASE_RANGE_START = (int) 'a';
    private final int LOWER_CASE_RANGE_END = (int) 'z';

    RotationalCipher(int shiftKey) {
        CIPHER_SHIFT_KEY = shiftKey;
    }

    String rotate(String data) {
        return data.codePoints()
                   .map(this::rotateCodePoint)
                   .collect(StringBuilder::new,
                            StringBuilder::appendCodePoint,
                            StringBuilder::append)
                   .toString();
    }

    int rotateCodePoint(int c) {
        if (!inLowerCaseRange(c) && !inUpperCaseRange(c)) {
            return c;
        }

        if (inUpperCaseRange(c)) {
            return shiftCodePoint(c, UPPER_CASE_RANGE_START);
        } 
        else {
            return shiftCodePoint(c, LOWER_CASE_RANGE_START);
        }
    }

    boolean inUpperCaseRange(int c) {
        return (UPPER_CASE_RANGE_START <= c) && (c <= UPPER_CASE_RANGE_END);
    }

    boolean inLowerCaseRange(int c) {
        return (LOWER_CASE_RANGE_START <= c) && (c <= LOWER_CASE_RANGE_END);
    }

    int shiftCodePoint(int c, int rangeStart) {
        return (((c - rangeStart) + CIPHER_SHIFT_KEY) % ALPHABET_LENGTH) + rangeStart;
    }
}
