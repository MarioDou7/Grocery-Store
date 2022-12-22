package model;

import java.awt.image.BufferedImage;

public class Fruit extends GroceryProduct{
    private String Flavor;

    public Fruit(String Name, double Price, int Stock, int Quantity, BufferedImage Image, String Flavor){
        super(Name, Price, Stock, Quantity, Image);
        this.Flavor = Flavor;
    }

    public String getFlavor() {
        return Flavor;
    }

    public void setFlavor(String Flavor) {
        this.Flavor = Flavor;
    }

}
