package supermarket.Models;

public class ShopOwner {
    private int ID;
    private String ShopName;
    private Boolean is_Accepted;

    private supermarket.Models.MangeProducts manger ;


    public ShopOwner(){
        manger = new ProductsMangerProxy(is_Accepted);
    }

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

    public void Add_Product(String ProductName,float Price,int Stock,String P_image,int category_id)
    {
        try {
            manger.Add_product(ProductName,ID,Price,Stock,P_image,category_id);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void Edit_Product(String ProductName,float Price,int Stock,String Path_image,int category_id)
    {
        try {
            manger.Edit_product(ProductName,Price,Stock,Path_image,category_id);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void Remove_Product(int P_ID)
    {
        try {
            manger.Remove_Product(P_ID);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void Report_Best_Seller()
    {

    }
}
