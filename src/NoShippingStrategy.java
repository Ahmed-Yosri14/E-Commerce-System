public class NoShippingStrategy implements ShippingStrategy {

    @Override
    public boolean isShippable() {
        return false;
    }
}