import java.sql.*;

public class DatabaseHelper {

    private String MySQLURL = "jdbc:mysql://localhost:3306/Grocery_Store";
    private String databseUserName = "root";
    private String databasePassword = "My$qlR00t";
    private Connection con = null;

    public DatabaseHelper(){}

    public void ConnectDB()
    {
        try {
            con = DriverManager.getConnection(MySQLURL, databseUserName, databasePassword);
            if (con != null) 
                System.out.println("Database connection is successful !!!!");     

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }
    }
    
    public void View_all_Table(String Table)
    {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * from ?");
            stmt.setString(1, Table);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                System.out.print(rs.toString());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
    }
    
    public int Select_ID_Username(String Shoponwer)
    {
        int OwnerID = -1;
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT ID from users where Username=?");
            stmt.setString(1, Shoponwer);

            ResultSet rs = stmt.executeQuery();
            while (rs.next())
                System.out.print(rs.toString());

            OwnerID = rs.getInt(0);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return OwnerID;

    }
    
    public int[] Select_ProdID_ownerid(int owner_id)
    {
        int[] product_ids ;
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT P_Id from Products where Owner_Id=?");
            stmt.setInt(1, owner_id);

            ResultSet rs = stmt.executeQuery();
            
            rs.last();                  // Place the record pointer onto the last row
            int counter = rs.getRow(); // Get the row number (there's your count)
            rs.first();   
            
            product_ids = new int[counter]; //specify the array size with length of rows returned
            counter = 0;

            while (rs.next())
                System.out.print(rs.toString());
                product_ids[counter] = rs.getInt("P_Id");

            return product_ids;
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return new int[]{00};

    }
    
    public void filter_Transaction_ProdID(int[] products_ids)
    {
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * from Transactions where P_Id=?");
            for (int i = 0; i < products_ids.length; i++) {
                
                stmt.setInt(1, products_ids[i]);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()){
                    System.out.print(rs.toString());
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public ResultSet Get_PendingShops()
    {
        ResultSet result = null;
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM ShopOwners WHERE is_accepted=FALSE");
            result = stmt.executeQuery();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }
   
    public void Add_ShopOwner(int owner_id)
    {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE ShopOwners SET is_accepted=TRUE WHERE ID=?");
            stmt.setInt(1, owner_id);
            stmt.executeUpdate();
            con.commit();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void Remove_ShopOwner(int owner_id)
    {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM ShopOwners WHERE Id=?");
            stmt.setInt(1, owner_id);

            stmt.executeUpdate();
            con.commit();
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void Close_Connection()
    {
        try {
            con.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
