import java.util.stream.IntStream;

class Scrabble {
    private final String WORD;

    Scrabble(String word) {
        WORD = word;
    }

    int getScore() {
        ScrabbleMap letterScoreMap = new ScrabbleMap();
        
        int score = 
            IntStream.range(0, WORD.length())
                     .map(i -> letterScoreMap.getLetterScore(WORD.charAt(i)))
                     .sum();

        return score;
    }

}
