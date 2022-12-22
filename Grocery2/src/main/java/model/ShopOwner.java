package model;

public class ShopOwner {
    private String ShopName;
    private Boolean is_Accepted;

    public ShopOwner(){}

    public String getShopName() {
        return ShopName;
    }
    public void setShopName(String shopName) {
        ShopName = shopName;
    }
    public Boolean getIs_Accepted() {
        return is_Accepted;
    }
    public void setIs_Accepted(Boolean is_Accepted) {
        this.is_Accepted = is_Accepted;
    }


}
