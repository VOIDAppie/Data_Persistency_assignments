import reiziger.Reiziger;
import reiziger.ReizigerDAO;
import reiziger.ReizigerDAOPsql;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql:ovchip";
        String user = "postgres";
        String password = "postgres";





        try {
            Connection dbConnection = DriverManager.getConnection(url, user, password);
            ReizigerDAO rdao = new ReizigerDAOPsql(dbConnection);
            testReiziger(rdao);


//            String SQL = "SELECT * FROM reiziger";
//            Statement statement = dbConnection.createStatement();
//            ResultSet rs = statement.executeQuery(SQL);
//            while (rs.next()) {
//                System.out.println(new reiziger.Reiziger(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
//
//            }

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void testReiziger (ReizigerDAO rdao) {
        //test 1 save de reiziger saven;

        System.out.println("we slaan een reiziger op.");
        Reiziger pieter = new Reiziger(54,"a", "", "mkj",  java.sql.Date.valueOf("1995-05-01"));
        System.out.println(rdao.findAll());
        System.out.println("nu is hij opgeslagen");
        rdao.save(pieter);
        System.out.println(rdao.findAll());
        System.out.println("we gaan nu hem ");
    }
}
