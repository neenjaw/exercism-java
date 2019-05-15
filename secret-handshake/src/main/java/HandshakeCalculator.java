import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class HandshakeCalculator {
    List<Signal> calculateHandshake(int number) {
        List<Signal> handshakeSignals = Arrays.asList(Signal.values())
                                              .stream()
                                              .filter(s -> isSignalInHandshake(number, s))
                                              .collect(Collectors.toList());

        if (handshakeSignals.remove(Signal.REVERSE)) {
            Collections.reverse(handshakeSignals);
        }
        
        return handshakeSignals;
    }

    private boolean isSignalInHandshake(int number, Signal signal) {
        int signalBitmask = getSignalBitmask(signal);

        return ((signalBitmask & number) == signalBitmask);
    }

    private int getSignalBitmask(Signal signal) {
        return (int) Math.pow(2, signal.ordinal());
    }
}
