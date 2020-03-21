public class AlreadyRegisteredException extends Exception {

  public AlreadyRegisteredException(String name) {
    super(name + " is already registered.");
  }
}