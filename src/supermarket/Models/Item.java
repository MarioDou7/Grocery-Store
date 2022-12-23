package supermarket.Models;

public class Item {
    private String name;
    private double price;
    private String imgSrc;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
