package model;

import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.image.BufferedImage;

public abstract class GroceryProduct {
    private String Name;
    private double Price;
    private int Stock;
    private int Quantity;
    private BufferedImage Image;

    public GroceryProduct(String Name, double Price, int Stock, int Quantity, BufferedImage Image){
        this.Name = Name;
        this.Price = Price;
        this.Stock = Stock;
        this.Quantity = Quantity;
        this.Image = Image;
    }

    public String getName() {
        return Name;
    }
    public double getPrice() {
        return Price;
    }
    public int getStock() {
        return Stock;
    }
    public int getQuantity() {
        return Quantity;
    }
    public BufferedImage getImage() {
        return Image;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public void setPrice(double Price) {
        this.Price = Price;
    }
    public void setStock(int Stock) {
        this.Stock = Stock;
    }
    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
    public void setImage(BufferedImage Image) {
        this.Image = Image;
    }

    public double calcBill(){
        return this.Price * this.Quantity;
    }

    public void displayInfo(){
        System.out.println("Product Name: " + this.getName());
        System.out.println("Product Price: " + this.getPrice());
        System.out.println("Product Stock: " + this.getStock());
        System.out.println("Product Quantity: " + this.getQuantity());
        System.out.println("Product Image: " + this.getImage());
    }
}
