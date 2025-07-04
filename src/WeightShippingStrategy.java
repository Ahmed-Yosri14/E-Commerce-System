public class WeightShippingStrategy implements ShippingStrategy {
    private final double weight;

    public WeightShippingStrategy(double weight) {
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean isShippable() {
        return true;
    }
}