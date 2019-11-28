import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * WordCount
 */
public class WordCount {
  /**
   * phrase
   * this method takes a string phrase and separates it into words
   * delineated by whitespace and punctuation.
   * @param str
   * @return a mapping of the string to how many times they occur in
   *         the phrase.
   */
  public Map<String, Integer> phrase(String str) {
    var words = str.toLowerCase().split("[^\\p{Alnum}'\\-]");

    // Stream the array, filter out empty strings, then for each
    // word, remove surrounding single quotes, collect it into a map
    return Arrays.stream(words)
                 .filter(word -> !word.isEmpty())
                 .map(WordCount::removeQuotes)
                 .collect(
                   Collectors.groupingBy(
                     Function.identity(),
                     Collectors.summingInt(w -> 1)));
  }

  private static String removeQuotes(String word) {
    if (word.startsWith("'") && word.endsWith("'")) {
      return word.substring(1, word.length()-1);
    }

    return word;
  }
}