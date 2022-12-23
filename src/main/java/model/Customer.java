package model;

public class Customer {
    private String Name;
    private String Country;
    private String Address;
    private String Phone;
    private int CreditCard;

    public Customer(String Name, String Country, String Address, String Phone, int CreditCard){
        this.Name = Name;
        this.Country = Country;
        this.Address = Address;
        this.Phone = Phone;
        this.CreditCard = CreditCard;
    }

    public String getName() {
        return Name;
    }
    public String getCountry() {
        return Country;
    }
    public String getAddress() {
        return Address;
    }
    public String getPhone() {
        return Phone;
    }
    public int getCreditCard() {
        return CreditCard;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setCountry(String City) {
        this.Country = City;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setCreditCard(int CreditCard) {
        this.CreditCard = CreditCard;
    }

    public void purchase(GroceryProduct Item, int Quantity){
        // Add to Cart Code
    }
    public void cancelPurchase(GroceryProduct Item){
        // Remove from Cart Code
    }
    public void editPurchase(GroceryProduct Item){
        // Edit Products Inside the Cart Code
    }

    public void displayInfo(){
        System.out.println("Customer Name: " + this.getName());
        System.out.println("Customer City: " + this.getCountry());
        System.out.println("Customer Address: " + this.getAddress());
        System.out.println("Customer Phone Number: " + this.getPhone());
        System.out.println("Customer Credit Card Number: " + this.getCreditCard());
    }
}
