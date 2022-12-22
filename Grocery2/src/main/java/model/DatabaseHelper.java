package model;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;

public class DatabaseHelper {

    private String MySQLURL = "jdbc:mysql://localhost:3306/Grocery_Store";
    private String databseUserName = "root";
    private String databasePassword = "My$qlR00t";
    private Connection con = null;

    public DatabaseHelper(){}

    public void ConnectDB() //create a connection to database
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

    public void View_all_Table(String Table)    //view all columns of any table
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

    public int Select_ID_Username(String Shoponwer)  //retrieve the userID from user tables
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

    public int[] Select_ProdID_ownerid(int owner_id) //retireve the products ids from the products table that belongs to this owner id
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

    public void filter_Transaction_ProdID(int[] products_ids) //filter the transaction table with the products ids
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

    public ResultSet Get_PendingShops() //retrives the shops thats admin didn't accept
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

    public void Add_Category(String Category_name)
    {
        try {
            PreparedStatement stmt = con.prepareStatement("insert INTO Categories (Cat_name) VALUES (?)");
            stmt.setString(1, Category_name);
            stmt.executeUpdate();
            con.commit();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void Remove_Category(String Category_name)
    {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Categories WHERE Cat_name=?");
            stmt.setString(1, Category_name);

            stmt.executeUpdate();
            con.commit();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void Add_product(String Product_name,int Owner_Id,float Price,int Stock,String P_imagePath,int Category_id)
    {
        try {
            InputStream in = new FileInputStream(P_imagePath);

            PreparedStatement statement = con.prepareStatement("INSERT INTO Products (Prod_Name, Owner_Id, Price, Stock, P_Image, Category) VALUES (?,?,?,?,?,?);");
            statement.setString(1, Product_name);
            statement.setInt(2, Owner_Id);
            statement.setFloat(3, (float) Price);
            statement.setInt(4, Stock);
            statement.setBlob(5, in);
            statement.setInt(6, Category_id);

            statement.executeUpdate();
            con.commit();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void Edit_product(String Product_name,float Price,int Stock,String P_imagePath,int Category_id)
    {
        try {
            InputStream in = new FileInputStream(P_imagePath);

            PreparedStatement statement = con.prepareStatement("UPDATE Products SET Prod_Name=?, Price=?, Stock=?, P_Image=?, Category=?");
            statement.setString(1, Product_name);
            statement.setFloat(2, (float) Price);
            statement.setInt(3, Stock);
            statement.setBlob(4, in);
            statement.setInt(5, Category_id);

            statement.executeUpdate();
            con.commit();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void Remove_Product(int P_id)
    {
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Products WHERE P_Id=?");
            stmt.setInt(1, P_id);

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