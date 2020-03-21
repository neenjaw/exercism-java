import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * Robot
 */
public class Robot {
  private static RobotRegistry registry = new RobotRegistry(26 * 26 * 10 * 10 * 10);
  private static Random random = new Random();
  private String name;

  public Robot() throws FullRegistryException {
    this.name = generateUniqueName();
  }

  public String getName() {
    return this.name;
  }

  public void reset() throws FullRegistryException {
    this.name = generateUniqueName();
  }

  private String generateUniqueName() throws FullRegistryException {
    String name;
    while (true) {
      try {
        name = generateName();
        registry.registerName(name);
        return name;
      } catch (final AlreadyRegisteredException e) {
        continue;
      }
    }
  }

  /**
   * Returns a new name following the convention: LLDDD where LL is a random
   * uppercase letter combination and DDD is a random number combination.
   *
   * @return new robot name
   */
  private String generateName() {
    final int targetAlphaLength = 2;
    final int targetDigitLength = 3;

    final StringBuilder buffer = new StringBuilder(targetAlphaLength + targetDigitLength);

    buffer.append(getRandomChars(targetAlphaLength, Robot::getAlphaChar));
    buffer.append(getRandomChars(targetDigitLength, Robot::getDigitChar));

    return buffer.toString();
  }

  private static StringBuilder getRandomChars(final int number, final Supplier<Character> randomChar) {
    return IntStream.rangeClosed(1, number).mapToObj(i -> randomChar.get()).collect(StringBuilder::new,
        StringBuilder::append, StringBuilder::append);
  }

  private static char getAlphaChar() {
    final int leftAlphaLimit = (int) 'A';
    final int rightAlphaLimit = (int) 'Z';

    return (char) getRandomLimitedInt(leftAlphaLimit, rightAlphaLimit);
  }

  private static char getDigitChar() {
    final int leftDigitLimit = (int) '0';
    final int rightDigitLimit = (int) '9';

    return (char) getRandomLimitedInt(leftDigitLimit, rightDigitLimit);
  }

  private static int getRandomLimitedInt(final int left, final int right) {
    return left + (int) (random.nextFloat() * (right - left + 1));
  }
}