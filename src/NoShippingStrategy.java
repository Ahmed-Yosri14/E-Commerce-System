public class NoShippingStrategy implements ShippingStrategy {

    @Override
    public boolean isShippable() {
        return false;
    }

    @Override
    public double getWeight() {
        return 0.0;
    }
}