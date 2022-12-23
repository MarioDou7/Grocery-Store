package supermarket.Models;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;

public interface MangeProducts {

    public void Add_product(String Product_name,int Owner_Id,float Price,int Stock,String P_imagePath,int Category_id);

    public void Edit_product(String Product_name,float Price,int Stock,String P_imagePath,int Category_id);

    public void Remove_Product(int P_id);

    public void View_Report(int Owner_id);
}
