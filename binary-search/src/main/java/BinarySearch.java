import java.util.ArrayList;
import java.util.List;

/**
 * BinarySearch
 */
public class BinarySearch {
  private ArrayList<Integer> items;

  public BinarySearch(List<Integer> items) {
    this.items = new ArrayList<Integer>(items);
  }

  public int indexOf(int item) throws ValueNotFoundException {
    if (items.isEmpty()) {
      throw new ValueNotFoundException("Value not in array");
    }

    int focus = items.size() / 2;
    int start = 0;
    int end = items.size() - 1;
    boolean found = false;
    while (!found && start <= end) {
      if (items.get(focus) == item) {
        found = true;
        continue;
      }

      if (items.get(focus) > item) {
        end = focus - 1;
        focus = (end - start) / 2 + start;
        continue;
      }

      if (items.get(focus) < item) {
        start = focus + 1;
        focus = (end - start) / 2 + start;
        continue;
      }
    }

    if (!found) {
      throw new ValueNotFoundException("Value not in array");
    }

    return focus;
  }
}