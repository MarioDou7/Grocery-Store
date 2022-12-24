package supermarket.Models;

public class CurrencyConvertorImp implements CurrencyConvertor{
    Product product = null;
    public CurrencyConvertorImp(Product p) {
        this.product = p;
    }

    @Override
    public float getPrice() {
        return (product.getPrice()/25);
    }
}
