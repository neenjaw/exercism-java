import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

class HandshakeCalculator {
    private final HashMap<Signal, Integer> DECODER_MAP;
    
    private final Signal[] ORDERED_SIGNALS = {
        Signal.REVERSE, 
        Signal.WINK, 
        Signal.DOUBLE_BLINK, 
        Signal.CLOSE_YOUR_EYES, 
        Signal.JUMP
    };

    HandshakeCalculator() {
        DECODER_MAP = new HashMap<Signal, Integer>();

        DECODER_MAP.put(Signal.WINK,            0b00001);
        DECODER_MAP.put(Signal.DOUBLE_BLINK,    0b00010);
        DECODER_MAP.put(Signal.CLOSE_YOUR_EYES, 0b00100);
        DECODER_MAP.put(Signal.JUMP,            0b01000);
        DECODER_MAP.put(Signal.REVERSE,         0b10000);
    }

    List<Signal> calculateHandshake(int number) {
        List<Signal> handshakeSignals = Arrays.asList(ORDERED_SIGNALS)
                                              .stream()
                                              .filter(s -> (DECODER_MAP.get(s) & number) == DECODER_MAP.get(s))
                                              .collect(Collectors.toList());

        if (handshakeSignals.remove(Signal.REVERSE)) {
            Collections.reverse(handshakeSignals);
        }
        
        return handshakeSignals;
    }

}
