class RotationalCipher {
    private final int CIPHER_SHIFT_KEY;
    private final int ALPHABET_LENGTH = 26;

    private final int UPPER_CASE_OFFSET = 'A';
    private final int LOWER_CASE_OFFSET = 'a';

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

    int rotateCodePoint(int codepoint) {
        int codepointType = Character.getType(codepoint);

        if (codepointType == Character.UPPERCASE_LETTER) {
            return shiftCodePoint(codepoint, UPPER_CASE_OFFSET);
        }

        if (codepointType == Character.LOWERCASE_LETTER) {
            return shiftCodePoint(codepoint, LOWER_CASE_OFFSET);
        }

        return codepoint;
    }

    int shiftCodePoint(int codepoint, int offset) {
        return (((codepoint - offset) + CIPHER_SHIFT_KEY) % ALPHABET_LENGTH) + offset;
    }
}
