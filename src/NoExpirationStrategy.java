public class NoExpirationStrategy implements ExpirationStrategy {
    @Override
    public boolean isExpired() {
        return false;
    }
} 