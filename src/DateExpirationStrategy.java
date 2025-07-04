import java.time.LocalDate;

public class DateExpirationStrategy implements ExpirationStrategy {
    private LocalDate expiryDate;
    public DateExpirationStrategy(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }
} 