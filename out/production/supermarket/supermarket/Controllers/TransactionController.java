package supermarket.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.apache.commons.dbutils.DbUtils;
import supermarket.Models.Transaction;
import supermarket.Models.Customer;
import java.sql.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TransactionController {
    private final static Logger logger = Logger.getLogger(ProductController.class.getName());
    private static FileHandler fh;
    private static Connection conn = null;
    private static Statement st = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static Transaction[] transactions = new Transaction[50];

    @FXML ListView<String> transactionListView;
    @FXML TextField nameTextField;
    @FXML TextField amountTextField;
    @FXML TextField dateTextField;
    @FXML Button saveButton;
    @FXML Button deleteButton;
    @FXML Label detailsLabel;
    @FXML Label errorLabel;

    @FXML
    private void loadTransactions() {
        errorLabel.setText("");
        nameTextField.setText("");
        amountTextField.setText("");
        dateTextField.setText("");

        if (Customer.getUserInstance().getName().equals("Admin")) {
            amountTextField.setEditable(true);
            dateTextField.setEditable(true);

            saveButton.setVisible(true);
            deleteButton.setVisible(true);
        }

        try {
            conn = DatabaseController.getConnection();

            if (Customer.getUserInstance().getName().equals("Admin")) {
                String getTransactionsQuery =
                        "SELECT * " +
                        "FROM Transactions " +
                        "ORDER BY T_Id";

                st = conn.createStatement();
                rs = st.executeQuery(getTransactionsQuery);
            } else {
                String getTransactionsQuery =
                        "SELECT * " +
                        "FROM Transactions " +
                        "WHERE U_Id = ? " +
                        "ORDER BY T_Id";

                ps = conn.prepareStatement(getTransactionsQuery);
                ps.setInt(1, Customer.getUserInstance().getId());
                rs = ps.executeQuery();
            }

            int id, user_id, product_id, amount, i = 0;
            String purchase_date;

            while (rs.next()) {
                id = rs.getInt("T_Id");
                user_id = rs.getInt("U_Id");
                product_id = rs.getInt("P_Id");
                amount = rs.getInt("Quantity");
                purchase_date = rs.getString("Trans_Date");

                if (rs.wasNull()) {
                    transactions[i] = new Transaction(id, user_id, product_id, amount);
                } else {
                    transactions[i] = new Transaction(id, user_id, product_id, amount, purchase_date);
                }

                i++;
            }

            ObservableList<String> transactionItems = FXCollections.observableArrayList();

            for (int j = 0; j < i; j++) {
                String getProductNameQuery =
                        "SELECT Prod_Name " +
                        "FROM Products " +
                        "WHERE P_Id = " + transactions[j].getProduct_id();

                st = conn.createStatement();
                rs = st.executeQuery(getProductNameQuery);

                String productName = "";

                while (rs.next()) {
                    productName = rs.getString("Prod_Name");
                }

                String getUserNameQuery =
                        "SELECT Username " +
                        "FROM Users " +
                        "WHERE Id = " + transactions[j].getUser_id();

                st = conn.createStatement();
                rs = st.executeQuery(getUserNameQuery);

                String userName = "";

                while (rs.next()) {
                    userName = rs.getString("Username");
                }

                transactionItems.add(userName + " - " + productName);
            }

            transactionListView.setItems(transactionItems);
            transactionListView.setPrefWidth(325);
        } catch (SQLException e) {
            errorLabel.setText("Load Transaction Database failure.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }
    }

    @FXML
    private void updateDetails() {
        errorLabel.setText("");

        int index = transactionListView.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            try {
                conn = DatabaseController.getConnection();

                String getProductNameQuery =
                        "SELECT Prod_Name " +
                        "FROM Products " +
                        "WHERE P_Id = " + transactions[index].getProduct_id();

                st = conn.createStatement();
                rs = st.executeQuery(getProductNameQuery);

                String productName = "";

                while (rs.next()) {
                    productName = rs.getString("Prod_Name");
                }

                nameTextField.setText(productName);
                amountTextField.setText(Integer.toString(transactions[index].getAmount()));
                dateTextField.setText(transactions[index].getDate());
            } catch (SQLException e) {
                errorLabel.setText("Update Database failure.");
            } catch (Exception e) {
                errorLabel.setText("Something went wrong.");
            } finally {
                DbUtils.closeQuietly(rs);
                DbUtils.closeQuietly(ps);
                DbUtils.closeQuietly(conn);
            }

            deleteButton.setDisable(false);
            saveButton.setDisable(false);
        } else {
            errorLabel.setText("Refresh the list first");
        }
    }

    @FXML
    private void delete() {
        try {
            fh = new FileHandler("logfile.log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            int index = transactionListView.getSelectionModel().getSelectedIndex();

            conn = DatabaseController.getConnection();

            String deleteProductQuery =
                    "DELETE FROM Transactions " +
                    "WHERE T_Id = ?";

            ps = conn.prepareStatement(deleteProductQuery);
            ps.setInt(1, transactions[index].getId());
            ps.executeUpdate();

            logger.log(Level.INFO, "A transaction was deleted.");

            loadTransactions();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
            errorLabel.setText("Transaction Delete Database failure.");
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
    private void save() {
        try {
            fh = new FileHandler("logfile.log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            int index = transactionListView.getSelectionModel().getSelectedIndex();

            conn = DatabaseController.getConnection();

            String editProductQuery =
                    "UPDATE Transactions " +
                    "SET Quantity = ?, Trans_Date = ? " +
                    "WHERE T_Id = ?";

            ps = conn.prepareStatement(editProductQuery);
            ps.setInt(1, Integer.parseInt(amountTextField.getText()));
            ps.setString(2, dateTextField.getText());
            ps.setInt(3, transactions[index].getId());
            ps.executeUpdate();

            logger.log(Level.INFO, "A transaction was edited.");

            loadTransactions();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
            errorLabel.setText("Save Transaction Database failure.");
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
    private void back(javafx.event.ActionEvent event) {
        if (Customer.getUserInstance().getName() == "Admin") {
            ScreenController.goToAdminHome(event);
        } else {
            ScreenController.goToUserHome(event);

        }
    }

    @FXML
    private void logout(javafx.event.ActionEvent event) {
        ScreenController.logout(event);
    }
}
