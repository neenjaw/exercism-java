/**
 * BankAccount
 */
public class BankAccount {
  private Boolean open;
  private Integer balance;

  private final String ERROR_CLOSED = "Account closed";
  private final String ERROR_EMPTY = "Cannot withdraw money from an empty account";
  private final String ERROR_OVERDRAW = "Cannot withdraw more money than is currently in the account";
  private final String ERROR_NEGATIVE_AMOUNT = "Cannot deposit or withdraw negative amount";

  public BankAccount() {
    open = false;
    balance = 0;
  }

  public void open() {
    synchronized (open) {
      open = true;
    }
  }

  public void close() {
    synchronized (open) {
      open = false;
    }
  }

  public int getBalance() throws BankAccountActionInvalidException {
    synchronized (open) {
      if (!open) {
        throw new BankAccountActionInvalidException(ERROR_CLOSED);
      }

      synchronized (balance) {
        return balance;
      }
    }
  }

  public void deposit(int amount) throws BankAccountActionInvalidException {
    if (amount <= 0) {
      throw new BankAccountActionInvalidException(ERROR_NEGATIVE_AMOUNT);
    }

    synchronized (open) {
      if (!open) {
        throw new BankAccountActionInvalidException(ERROR_CLOSED);
      }

      synchronized (balance) {
        balance += amount;
      }
    }
  }

  public void withdraw(int amount) throws BankAccountActionInvalidException {
    if (amount <= 0) {
      throw new BankAccountActionInvalidException(ERROR_NEGATIVE_AMOUNT);
    }

    synchronized (open) {
      if (!open) {
        throw new BankAccountActionInvalidException(ERROR_CLOSED);
      }

      synchronized (balance) {
        if (balance == 0) {
          throw new BankAccountActionInvalidException(ERROR_EMPTY);
        }
        if (balance - amount < 0) {
          throw new BankAccountActionInvalidException(ERROR_OVERDRAW);
        }

        balance -= amount;
      }
    }
  }
}
