package supermarket.Controllers;

import javafx.fxml.FXML;
import org.apache.commons.dbutils.DbUtils;
import supermarket.GlobalConstants;
import supermarket.Models.DatabaseHelper;

import java.io.File;
import java.sql.*;

public class DatabaseController {
    private static Statement st = null;
    private static Connection conn = null;
    private static ResultSet rs = null;
    private DatabaseHelper dh = new DatabaseHelper();
    static Connection getConnection() {
        String driverClassName = "com.mysql.jdbc.Driver";
        Connection conn = null;

        try {
            Class.forName(driverClassName).newInstance();
            conn = DriverManager.getConnection(GlobalConstants.DB_URL, GlobalConstants.DB_USERNAME, GlobalConstants.DB_PASSWORD);
        } catch (Exception e) {
            System.out.println("An Error Occurred");
            System.out.println(e.getMessage());
        }

        return conn;
    }

    @FXML
    public void create() {
        try {
            conn = DatabaseController.getConnection();
            st = conn.createStatement();

            st.executeUpdate("DROP TABLE IF EXISTS Transactions CASCADE");
            st.executeUpdate("DROP TABLE IF EXISTS Products CASCADE");
            st.executeUpdate("DROP TABLE IF EXISTS Users CASCADE");
            st.executeUpdate("DROP TABLE IF EXISTS ShopOwners CASCADE");
            st.executeUpdate("DROP TABLE IF EXISTS Categories CASCADE");
            st.executeUpdate("DROP TABLE IF EXISTS Cart CASCADE");
            st.executeUpdate("DROP TABLE IF EXISTS ShopOwners CASCADE");

            String createTableUsers =
                    "CREATE TABLE Users (Id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT, \n" +
                            "Username VARCHAR(20) NOT NULL, \n" +
                            "Email VARCHAR(50) NOT NULL,\n" +
                            "PhoneNo VARCHAR(15) NOT NULL,\n" +
                            "Country VARCHAR(50) NOT NULL,\n" +
                            "Password VARCHAR(256) NOT NULL,\n" +
                            "PRIMARY KEY (Id) \n" +
                            ");";
            st.executeUpdate(createTableUsers);

            String createTableOwners =
                    "CREATE TABLE ShopOwners (Id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT, \n" +
                            "ShopName VARCHAR(20) NOT NULL, \n" +
                            "Password VARCHAR(256) NOT NULL,\n" +
                            "is_accepted boolean not null,\n" +
                            "PRIMARY KEY (Id) \n" +
                            ");";
            st.executeUpdate(createTableOwners);

            String createTableCategories =
                    "CREATE TABLE Categories (ID smallint unsigned NOT NULL auto_increment,\n" +
                            "Cat_name VARCHAR(20) NOT NULL,\n" +
                            "primary key (ID)\n" +
                            ");";
            st.executeUpdate(createTableCategories);

            String createTableCart =
                    "CREATE TABLE Cart (Id SMALLINT UNSIGNED primary key NOT NULL AUTO_INCREMENT, \n" +
                            "PhoneNo text NOT NULL\n" +
                            ");";
            st.executeUpdate(createTableCart);

            String createTableProducts =
                    "CREATE TABLE Products (P_Id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT, \n" +
                            "Prod_Name VARCHAR(20) NOT NULL, \n" +
                            "Owner_Id SMALLINT UNSIGNED NOT NULL,\n" +
                            "Price FLOAT NOT NULL,\n" +
                            "Stock INTEGER NOT NULL,\n" +
                            "P_Image BLOB,\n" +
                            "Category SMALLINT UNSIGNED NOT NULL,\n" +
                            "PRIMARY KEY (P_Id),\n" +
                            "FOREIGN KEY (Category) REFERENCES Categories(id),\n" +
                            "FOREIGN KEY (Owner_Id) REFERENCES ShopOwners(Id)\n" +
                            ");";
            st.executeUpdate(createTableProducts);

            String createTableTransactions =
                    "CREATE TABLE Transactions (T_Id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT, \n" +
                            "P_Id SMALLINT UNSIGNED NOT NULL, \n" +
                            "U_Id SMALLINT UNSIGNED NOT NULL,\n" +
                            "Quantity INTEGER NOT NULL,\n" +
                            "Is_Pending BOOLEAN NOT NULL,\n" +
                            "Trans_Date Date NOT NULL,\n" +
                            "PRIMARY KEY (T_Id),\n" +
                            "FOREIGN KEY (P_Id) REFERENCES Products(P_Id),\n" +
                            "FOREIGN KEY (U_Id) REFERENCES Users(Id)\n" +
                            ");";
            st.executeUpdate(createTableTransactions);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert() {
        try {
            st.executeUpdate("INSERT INTO Users (Username, Email, PhoneNO, Country, Password) VALUES (\"Admin\", \"Admin.423@gmail.com\",\"01223456789\", \"Egypt\", \"StrongPasswd\");");
            st.executeUpdate("INSERT INTO Users (Username, Email, PhoneNO, Country, Password) VALUES (\"Ibrahim\", \"Ibrahim.012@gmail.com\",\"01223456788\", \"Qatar\", \"12345678\");");
            st.executeUpdate("INSERT INTO Users (Username, Email, PhoneNO, Country, Password) VALUES (\"Peter\", \"Peter.456@gmail.com\",\"01223456787\", \"USA\", \"qwerty123\");");

            st.executeUpdate("INSERT INTO Categories (Cat_name) VALUES (\"Fruits\");\n");
            st.executeUpdate("INSERT INTO Categories (Cat_name) VALUES (\"Vegetables\");\n");

            st.executeUpdate("INSERT INTO ShopOwners (ShopName, Password, is_accepted) VALUES (\"Circle K\", \"circle_k\",TRUE);");
            st.executeUpdate("INSERT INTO ShopOwners (ShopName, Password, is_accepted) VALUES (\"Carrefour\", \"carrefour\",TRUE);");
            st.executeUpdate("INSERT INTO ShopOwners (ShopName, Password, is_accepted) VALUES (\"Walmart\", \"walmart\",TRUE);");

            st.executeUpdate("INSERT INTO Cart (PhoneNo) VALUES (\"01226908555\");");
            st.executeUpdate("INSERT INTO Cart (PhoneNo) VALUES (\"01226908554\");");

//            dh.ConnectDB();
//            dh.Add_product("Apple",1,4,50,"src/resources/images/apple.png",1);
//            dh.Add_product("Orange",2,40,80,"src/resources/images/apple.png",1);
            st.executeUpdate("INSERT INTO Products (Prod_Name, Owner_Id, Price, Stock, P_Image, Category) VALUES (\"Apple\", 1, 40.5, 150, null, 1);");
            st.executeUpdate("INSERT INTO Products (Prod_Name, Owner_Id, Price, Stock, P_Image, Category) VALUES (\"Orange\", 2, 16, 80, null, 1);");
            st.executeUpdate("INSERT INTO Products (Prod_Name, Owner_Id, Price, Stock, P_Image, Category) VALUES (\"Peach\", 3, 21.5, 110, null, 1);");
            st.executeUpdate("INSERT INTO Products (Prod_Name, Owner_Id, Price, Stock, P_Image, Category) VALUES (\"Tomato\", 1, 8, 270, null, 2);");
            st.executeUpdate("INSERT INTO Products (Prod_Name, Owner_Id, Price, Stock, P_Image, Category) VALUES (\"Asparagus\", 2, 91.5, 30, null, 2);");
            st.executeUpdate("INSERT INTO Products (Prod_Name, Owner_Id, Price, Stock, P_Image, Category) VALUES (\"Green Peas\", 3, 39.5, 110, null, 2);");

            } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }
    }
}
