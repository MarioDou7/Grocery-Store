public class Admin {
    
    private static Admin Instance = null;
    private DatabaseHelper db =new DatabaseHelper();
    
    private Admin(){}

    public static Admin getInstance(){
        if (Instance == null)
            Instance = new Admin();

        return Instance;
    }

    public void Check_Order_History(String owner)
    {
        db.ConnectDB();
        //owner_id = select ID from user where Username=owner
        int owner_id = db.Select_ID_Username(owner);
        if(owner_id == -1)
            {
                db.Close_Connection();
                System.out.println("This owner doesn't exist");
                return;
            }
        //product_ids = select p_id from products where owner_id =owner_id
        int[] product_ids = db.Select_ProdID_ownerid(owner_id);
        if(product_ids[0] == 00)
        {
            db.Close_Connection();
            System.out.println("This owner doesn't exist");
            return;
        }

        // select * from transaction where p_id = products_ids
        db.filter_Transaction_ProdID(product_ids);
        
        db.Close_Connection();
    }

    public void View_Transcation()
    {
        db.ConnectDB();

        db.View_all_Table("Transactions");

        db.Close_Connection();
    }

    public void Accept_ShopOwner(int shopOwner_id)
    {
        db.ConnectDB();

        db.Add_ShopOwner(shopOwner_id);

        db.Close_Connection();
    }

    public void Remove_ShopOwner(int shopOwner_id)
    {
        db.ConnectDB();

        db.Remove_ShopOwner(shopOwner_id);

        db.Close_Connection();
    }

    public void Add_Category(String Category_name)
    {
        db.ConnectDB();

        db.Add_Category(Category_name);

        db.Close_Connection();
    }

    public void Remove_Category(String Category_name)
    {
        db.ConnectDB();
        db.Remove_Category(Category_name);
        db.Close_Connection();
    }

}
