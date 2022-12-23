package supermarket.Controllers;

import java.sql.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.dbutils.DbUtils;
import supermarket.Models.Customer;

public class LoginController {
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    @FXML TextField usernameField;
    @FXML PasswordField passwordField;
    @FXML Label errorLabel;

    @FXML
    private void login(javafx.event.ActionEvent event) {
        int id = -1;
        String username = usernameField.getText();
        String password = passwordField.getText();
        try {
            conn = DatabaseController.getConnection();

            String validateUser =
                    "SELECT Id, Username " +
                    "FROM Users " +
                    "WHERE Username = ? AND Password = ?" +
                    "LIMIT 1";

            ps = conn.prepareStatement(validateUser);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt("Id");
            }
        } catch (SQLException e) {
            if (e.getSQLState().equals("08004")) {
                errorLabel.setText(e.getMessage());
            } else {
                errorLabel.setText("Something went wrong, please try again.");
            }
        } catch (Exception e) {
            errorLabel.setText("Something went horribly wrong.");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }

        if (username != null && id != -1) {
            if (username.equals("Admin")) {
                new Customer(id, username).setUserInstance();
                ScreenController.goToAdminHome(event);
            } else {
                new Customer(id, username).setUserInstance();
                ScreenController.goToProducts(event);
            }
        } else {
            errorLabel.setText("Wrong username or password.");
        }
    }
}
