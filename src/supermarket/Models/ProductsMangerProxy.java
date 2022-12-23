package supermarket.Models;

public class ProductsMangerProxy implements MangeProducts{
    DatabaseHelper db = new DatabaseHelper();

    boolean owner_Status;

    public ProductsMangerProxy(Boolean is_Accepted) {
        this.owner_Status = is_Accepted;
    }

    @Override
    public void Add_product(String Product_name, int Owner_Id, float Price, int Stock, String P_imagePath, int Category_id) {
        //we need to get the customer
        if (owner_Status == false)
        {
            System.out.println("This Shop Owner haven't been accepted by the admin yet");
            return;
        }
        db.ConnectDB();
        db.Add_product(Product_name,Owner_Id,Price,Stock,P_imagePath,Category_id);
        db.Close_Connection();
    }

    @Override
    public void Edit_product(String Product_name, float Price, int Stock, String P_imagePath, int Category_id) {

        //we need to get the customer
        if (owner_Status == false)
        {
            System.out.println("This Shop Owner haven't been accepted by the admin yet");
            return;
        }
        db.ConnectDB();
        db.Edit_product(Product_name,Price,Stock,P_imagePath,Category_id);
        db.Close_Connection();
    }

    @Override
    public void Remove_Product(int P_id) {
        //we need to get the customer
        if (owner_Status == false)
        {
            System.out.println("This Shop Owner haven't been accepted by the admin yet");
            return;
        }
        db.ConnectDB();
        db.Remove_Product(P_id);
        db.Close_Connection();
    }

    @Override
    public void View_Report(int Owner_id) {
        int[] product_ids = db.Select_ProdID_ownerid(Owner_id); // get the products_id of this owner
        if(product_ids[0] == 00)
        {
            db.Close_Connection();
            System.out.println("This owner doesn't exist");
            return;
        }

        // select * from transaction where p_id = products_ids
        db.filter_Transaction_ProdID(product_ids);  //filter the transaction table with the products ids

        db.Close_Connection();
    }
}
