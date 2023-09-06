import reiziger.Reiziger;
import reiziger.ReizigerDAO;
import reiziger.ReizigerDAOPsql;

import java.sql.*;
import java.util.List;

public class Main {
    private static Connection conn = null;

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql:ovchip";
        String user = "postgres";
        String password = "postgres";

        if (conn == null) {
            conn = DriverManager.getConnection(url, user, password);
        }
        return conn;
    }
    public static void main(String[] args) throws SQLException {
        ReizigerDAO rdao = new ReizigerDAOPsql(getConnection());
        testReizigerDAO(rdao);


//            String SQL = "SELECT * FROM reiziger";
//            Statement statement = dbConnection.createStatement();
//            ResultSet rs = statement.executeQuery(SQL);
//            while (rs.next()) {
//                System.out.println(new reiziger.Reiziger(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
//
//            }


    }

    public static void testReiziger(ReizigerDAO rdao) {
        //test 1 save de reiziger saven (Create)

//        System.out.println("we slaan een reiziger op.");
//        Reiziger pieter = new Reiziger(54,"a", "", "mkj",  java.sql.Date.valueOf("1995-05-01"));
//        System.out.println(rdao.findAll());
//        System.out.println("nu is hij opgeslagen");
//        rdao.save(pieter);

        // test 2 de reiziger zoeken (Read)
//        System.out.println(rdao.findAll());
//        System.out.println("we gaan hem nu vinden");


        // test 4 de reiziger deleten (Delete)
//        rdao.delete(pieter);
//        System.out.println(rdao.findAll());
//        System.out.println("De reiziger is verwijderd");


    }

    /**
     * P2. Reiziger DAO: persistentie van een klasse
     * <p>
     * Deze methode test de CRUD-functionaliteit van de Reiziger DAO
     *
     * @throws SQLException
     */
    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
//        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Update de nieuwe toegevoegde reiziger
        String nieuweGeboorteDatum = "1981-04-14";
        System.out.println("[Test] ReizigerDAO.update() update de reiziger:\n");
        sietske.setAchternaam("NieuweAchternaam");
        sietske.setGeboortedatum(Date.valueOf(nieuweGeboorteDatum));
        rdao.update(sietske);

        // delete de nieuwe toegevoegde reiziger
        System.out.println("[Test] ReizigerDAO.delete() verwijdert de reiziger:");
        rdao.delete(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers na verwijderen\n");


    }
}
