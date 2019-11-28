import java.util.LinkedList;
import java.util.List;

/**
 * Flattener
 */
public class Flattener {

  public List<Object> flatten(List<Object> l) {
    var flattened = new LinkedList<Object>();

    l.stream().forEach(e -> {
      if (e instanceof List) {
        flattened.addAll(flatten((List<Object>) e));
      } else if (e != null) {
        flattened.add(e);
      }
    });

    return flattened;
  }
}