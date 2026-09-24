import java.time.Duration;
import java.time.LocalDateTime;

public class CancellationPolicy {

    public double getRefundPercentage(
            LocalDateTime cancellationTime,
            LocalDateTime checkInTime) {

        long hours = Duration.between(
                cancellationTime,
                checkInTime).toHours();

        if (hours > 72) {
            return 1.0;
        } else if (hours >= 24) {
            return 0.50;
        } else {
            return 0.0;
        }
    }
}