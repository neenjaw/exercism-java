import java.util.stream.IntStream;
import java.util.HashMap;

class Scrabble {
    static class ScoreMap {
        private final HashMap<Character, Integer> LETTER_SCORE;
    
        ScoreMap() {
            LETTER_SCORE = new HashMap<Character, Integer>();
    
            LETTER_SCORE.put(' ', 0);
            LETTER_SCORE.put('a', 1);
            LETTER_SCORE.put('b', 3);
            LETTER_SCORE.put('c', 3);
            LETTER_SCORE.put('d', 2);
            LETTER_SCORE.put('e', 1);
            LETTER_SCORE.put('f', 4);
            LETTER_SCORE.put('g', 2);
            LETTER_SCORE.put('h', 4);
            LETTER_SCORE.put('i', 1);
            LETTER_SCORE.put('j', 8);
            LETTER_SCORE.put('k', 5);
            LETTER_SCORE.put('l', 1);
            LETTER_SCORE.put('m', 3);
            LETTER_SCORE.put('n', 1);
            LETTER_SCORE.put('o', 1);
            LETTER_SCORE.put('p', 3);
            LETTER_SCORE.put('q', 10);
            LETTER_SCORE.put('r', 1);
            LETTER_SCORE.put('s', 1);
            LETTER_SCORE.put('t', 1);
            LETTER_SCORE.put('u', 1);
            LETTER_SCORE.put('v', 4);
            LETTER_SCORE.put('w', 4);
            LETTER_SCORE.put('x', 8);
            LETTER_SCORE.put('y', 4);
            LETTER_SCORE.put('z', 10);
        }
    
        public int getLetterScore(char c) {
            if (!LETTER_SCORE.containsKey(Character.toLowerCase(c))) {
                throw new IllegalArgumentException("Not a scrabble letter");
            }

            return LETTER_SCORE.get(Character.toLowerCase(c));
        }
    }

    private Scrabble.ScoreMap scoreMap;
    private String word;

    Scrabble(String word) {
        this.scoreMap = new Scrabble.ScoreMap();
        this.word = word;
    }

    int getScore() {
        return IntStream.range(0, this.word.length())
                        .map(i -> this.scoreMap.getLetterScore(this.word.charAt(i)))
                        .sum();
    }
}
