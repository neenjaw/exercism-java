import java.util.HashSet;
import java.util.Set;

/**
 * RobotRegistry
 */
public class RobotRegistry {
  private Set<String> registry;
  private int maximumPossibleNames;

  public RobotRegistry(int maximumPossibleNames) {
    this.registry = new HashSet<String>();
    this.maximumPossibleNames = maximumPossibleNames;
  }

  public void registerName(String name) throws AlreadyRegisteredException, FullRegistryException {
    if (registry.size() == maximumPossibleNames) throw new FullRegistryException();
    if (registry.contains(name)) throw new AlreadyRegisteredException(name);

    registry.add(name);
  }
}