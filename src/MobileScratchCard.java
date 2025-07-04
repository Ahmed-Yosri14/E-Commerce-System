public class MobileScratchCard extends Product {

    public MobileScratchCard(String name, double price, int quantity) {
        super(name,price,quantity,new NoExpirationStrategy(),new NoShippingStrategy());
    }
}