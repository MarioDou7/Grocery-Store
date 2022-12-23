package supermarket.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.apache.commons.dbutils.DbUtils;
import supermarket.Models.Customer;
import java.sql.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AdminController {
    private final static Logger logger = Logger.getLogger(ProductController.class.getName());
    private static FileHandler fh;
    private static Connection conn = null;
    private static Statement st = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static Customer[] users = new Customer[50];

    @FXML ListView<String> userListView;
    @FXML TextField nameTextField;
    @FXML TextField usernameTextField;
    @FXML TextField kindTextField;
    @FXML Button deleteButton;
    @FXML Button saveButton;
    @FXML Label errorLabel;

    @FXML
    private void loadOwners() {
        errorLabel.setText("");
        nameTextField.setText("");
        usernameTextField.setText("");
        kindTextField.setText("");

        try {
            conn = DatabaseController.getConnection();

            String getUsersQuery =
                    "SELECT Id, ShopName, Password, is_accepted " +
                    "FROM ShopOwners " +
                    "ORDER BY Id";

            st = conn.createStatement();
            rs = st.executeQuery(getUsersQuery);

            int id, is_accpeted, i = 0;
            String username, name;
            while (rs.next()) {
                id = rs.getInt("Id");
                username = rs.getString("ShopName");

                users[i] = new Customer(id, username);

                i++;
            }

            ObservableList<String> userItems = FXCollections.observableArrayList();

            for (int j = 0; j < i; j++) {
                userItems.add(users[j].getId() + " - " + users[j].getName());
            }

            userListView.setItems(userItems);
            userListView.setPrefWidth(325);
        } catch (SQLException e) {
            errorLabel.setText("Loading Owners Database failure.");
        } catch (Exception e) {
            errorLabel.setText("Something went wrong.");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }

    @FXML
    private void newOwner() {
        try {
            fh = new FileHandler("logfile.log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            conn = DatabaseController.getConnection();

            String newUserQuery =
                    "INSERT INTO ShopOwners (ShopName, Password, is_accepted) VALUES ('NewShop', '1234', 1)";

            st = conn.createStatement();
            st.executeUpdate(newUserQuery);

            logger.log(Level.INFO, "A New Owner was added.");

            loadOwners();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
            errorLabel.setText("Add Owner Database failure.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            errorLabel.setText("Something went wrong.");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
            fh.close();
        }
    }

    @FXML
    private void save() {
        try {
            fh = new FileHandler("logfile.log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            int index = userListView.getSelectionModel().getSelectedIndex();

            conn = DatabaseController.getConnection();

            String editUserQuery =
                    "UPDATE ShopOwners " +
                    "SET ShopName = ?, Password = ?, is_accepted = ?" +
                    "WHERE Id = ?";

            ps = conn.prepareStatement(editUserQuery);
            ps.setString(1, usernameTextField.getText());
            ps.setString(2, nameTextField.getText());
            ps.setInt(3, 1);
            ps.setInt(4, users[index].getId());
            ps.executeUpdate();

            logger.log(Level.INFO, "A user was edited.");

            loadOwners();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
            errorLabel.setText("Save Owner Database failure.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            errorLabel.setText("Something went wrong.");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
            fh.close();
        }

        deleteButton.setDisable(true);
        saveButton.setDisable(true);
    }

    @FXML
    private void delete() {
        try {
            fh = new FileHandler("logfile.log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            int index = userListView.getSelectionModel().getSelectedIndex();

            conn = DatabaseController.getConnection();

            String deleteUserQuery =
                    "DELETE FROM ShopOwners " +
                    "WHERE Id = ?";

            ps = conn.prepareStatement(deleteUserQuery);
            ps.setInt(1, users[index].getId());
            ps.executeUpdate();

            logger.log(Level.INFO, "A user was deleted.");

            loadOwners();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
            errorLabel.setText("Delete Owner Database failure.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            errorLabel.setText("Something went wrong.");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
            fh.close();
        }

        deleteButton.setDisable(true);
        saveButton.setDisable(true);
    }

    @FXML
    private void updateDetails() {
        int index = userListView.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            usernameTextField.setText(users[index].getName());

            deleteButton.setDisable(false);
            saveButton.setDisable(false);
        } else {
            errorLabel.setText("Refresh the list first");
        }
    }

    @FXML
    private void back(javafx.event.ActionEvent event) {
        ScreenController.goToAdminHome(event);
    }

    @FXML
    private void logout(javafx.event.ActionEvent event) {
        ScreenController.logout(event);
    }
}
