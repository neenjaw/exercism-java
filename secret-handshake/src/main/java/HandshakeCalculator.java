import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class HandshakeCalculator {
    List<Signal> calculateHandshake(int number) {
        List<Signal> handshakeSignals = 
            Arrays.asList(Signal.values())
                  .stream()
                  .filter(s -> isSignalInHandshake(number, s))
                  .collect(Collectors.toList());

        if (handshakeSignals.remove(Signal.REVERSE)) {
            Collections.reverse(handshakeSignals);
        }
        
        return handshakeSignals;
    }

    /**
     * Returns boolean value if the signal appears in the the handshake 
     * number by comparing the number with a bitmask.
     * @param number
     * @param signal
     * @return boolean whether signal appears in handshake number
     */
    private boolean isSignalInHandshake(int number, Signal signal) {
        int signalBitmask = getSignalBitmask(signal);

        return (signalBitmask & number) == signalBitmask;
    }

    /**
     * Takes a Signal enumeration object, returns the bitmask associated
     * with the ordinal value of the object.
     * @param signal
     * @return integer representation of bitmask
     */
    private int getSignalBitmask(Signal signal) {
        // this makes the assumption that the ordinal() method will 
        // return a 0-based index, which is a positive integer
        return 1 << signal.ordinal();
    }
}
