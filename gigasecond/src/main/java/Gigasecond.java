import java.time.LocalDate;
import java.time.LocalDateTime;

class Gigasecond {
    private final long GIGASECOND = 1_000_000_000l;
    private final LocalDateTime gigaMoment;

    Gigasecond(LocalDate moment) {
        this(moment.atStartOfDay());
    }

    Gigasecond(LocalDateTime moment) {
        gigaMoment = moment.plusSeconds(GIGASECOND);
    }

    LocalDateTime getDateTime() {
        return gigaMoment;
    }

}
