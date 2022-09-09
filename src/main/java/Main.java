import java.sql.*;
public class Main {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql:ovchip";
        String user = "postgres";
        String password = "postgres";

        try {
            Connection dbConnection = DriverManager.getConnection(url, user, password);

//            String SQL = "SELECT * FROM reiziger";
//            Statement statement = dbConnection.createStatement();
//            ResultSet rs = statement.executeQuery(SQL);
//            while (rs.next()) {
//                System.out.println(new Reiziger(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
//
//            }





        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
