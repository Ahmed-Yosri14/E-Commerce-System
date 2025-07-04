import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cheese extends Product {
    public Cheese(String name, double price, int quantity, String expiryDate, double weight) {
        super(
                name,
                price,
                quantity,
                new DateExpirationStrategy(LocalDate.parse(expiryDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
                new WeightShippingStrategy(weight)
        );
    }
}