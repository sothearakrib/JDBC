
package jbdc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class JBDC {

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/JBDC_DTB", "root", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Not connected to server and message is " + e.getMessage());
        }
        return con;
    }

    public static void main(String[] args) {
        Connection con = getConnection();

        if (con != null) {
            try {
                Statement statement = con.createStatement();
                
                String sql = "SELECT * FROM Product";
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    double pricePerUnit = resultSet.getDouble("price_per_unit");
                    boolean activeForSell = resultSet.getBoolean("active_for_sell");
                    System.out.println("ID: " + id + ", Name: " + name + ", Price: " + pricePerUnit + ", Active for sell: " + (activeForSell ? 1 : 0));
                }
                resultSet.close();
                statement.close();
                con.close(); 
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Failed to establish connection.");
        }
    }
}
