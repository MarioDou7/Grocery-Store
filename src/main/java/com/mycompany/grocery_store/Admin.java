package com.mycompany.grocery_store;

/**
 *
 * @author Luka
 */
public class Admin {
    
    private static Admin Instance = null;

    
    private Admin(){}

    public static Admin getInstance(){
        if (Instance == null)
            Instance = new Admin();

        return Instance;
    }

    public void Check_Order_History(ShopOwner owner)
    {
        //owner_id = select ID from user where Username=owner

        //product_ids = select p_id where owner_id =owner_id

        // history = select * from transaction where p_id = products_ids

        //return history
    }

    public void View_Transcation()
    {
        DatabaseHelper db = new DatabaseHelper();
        db.ConnectDB();
        
    }
}
