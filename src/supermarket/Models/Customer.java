package supermarket.Models;

public class Customer {
    // =========================== Attributes ===========================
    private int Id;
    private String Name;
    private String Country;
    private String Address;
    private String Phone;
    private int CreditCard;
    private static Customer userInstance = null;

    private DatabaseHelper db = new DatabaseHelper();

    // =========================== Constructors ===========================
    public Customer(int Id, String Name, String Country, String Address, String Phone, int CreditCard){
        this.Id = Id;
        this.Name = Name;
        this.Country = Country;
        this.Address = Address;
        this.Phone = Phone;
        this.CreditCard = CreditCard;
    }
    public Customer(int Id, String Name){
        this.Id = Id;
        this.Name = Name;
    }
    public Customer(){}

    // =========================== Getters ===========================
    public static Customer getUserInstance() {
        return userInstance;
    }
    public int getId() {
        return Id;
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

    // =========================== Setters ===========================
    public void setUserInstance() {
        userInstance = this;
    }
    public void setId(int Id) { this.Id = Id; }
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

    // =========================== Customer Functionality ===========================
    public void purchase(int P_Id,int U_Id,int Quantity,int stock){
        // Add to transaction
        db.ConnectDB();

        db.insert_Transaction(P_Id,U_Id,Quantity);

        db.update_product_stock(P_Id,stock,Quantity);

        db.Close_Connection();

    }
    public void editPurchase(GroceryProduct Item){
        // Edit Products Inside the Cart Code
    }

    public void Add_to_cart()
    {

    }

    public void remove_from_cart()
    {

    }


    // =========================== Display User Info ===========================
    public void displayInfo(){
        System.out.println("Customer Id: " + this.getId());
        System.out.println("Customer Name: " + this.getName());
        System.out.println("Customer City: " + this.getCountry());
        System.out.println("Customer Address: " + this.getAddress());
        System.out.println("Customer Phone Number: " + this.getPhone());
        System.out.println("Customer Credit Card Number: " + this.getCreditCard());
    }
}
