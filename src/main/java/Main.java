import adres.Adres;
import adres.AdresDAO;
import adres.AdresDAOPsql;
import ovchipkaart.OvChipKaart;
import ovchipkaart.OvChipKaartDAO;
import ovchipkaart.OvChipKaartDAOPsql;
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
//        testReizigerDAO(rdao);
        AdresDAO adao = new AdresDAOPsql(getConnection());
        OvChipKaartDAO odao = new OvChipKaartDAOPsql(getConnection());
        adao.setReizigerDAO(rdao);
//        testAdresDAO(adao , rdao);
        testOvChipKaartDAO(odao , rdao);





    }



    /**
     * P2. Reiziger DAO: persistentie van een klasse
     * <p>
     * Deze methode test de CRUD-functionaliteit van de Reiziger DAO
     *
     * @throws SQLException
     */
//    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
//        System.out.println("\n---------- Test ReizigerDAO -------------");
//
//        // Haal alle reizigers op uit de database
//        List<Reiziger> reizigers = rdao.findAll();
//        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
//        for (Reiziger r : reizigers) {
//            System.out.println(r);
//        }
//        System.out.println();
//
//        // Maak een nieuwe reiziger aan en persisteer deze in de database
//        String gbdatum = "1981-03-14";
//        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
//        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
////        rdao.save(sietske);
//        reizigers = rdao.findAll();
//        System.out.println(reizigers.size() + " reizigers\n");
//
//        // Update de nieuwe toegevoegde reiziger
//        String nieuweGeboorteDatum = "1981-04-14";
//        System.out.println("[Test] ReizigerDAO.update() update de reiziger:\n");
//        sietske.setAchternaam("NieuweAchternaam");
//        sietske.setGeboortedatum(Date.valueOf(nieuweGeboorteDatum));
//        rdao.update(sietske);
//
//        // delete de nieuwe toegevoegde reiziger
//        System.out.println("[Test] ReizigerDAO.delete() verwijdert de reiziger:");
//        rdao.delete(sietske);
//        reizigers = rdao.findAll();
//        System.out.println(reizigers.size() + " reizigers na verwijderen\n");
//
//
//    }

//    private static void testAdresDAO(AdresDAO adao , ReizigerDAO rdao) throws SQLException{
//        //adressen ophalen
//        List<Adres> adressen = adao.findAll();
//        System.out.println("[Test] AdresDAO.findAll() gives the following addresses:");
//        for (Adres a : adressen) {
//            System.out.println(a);
//        }
//        System.out.println();
//
//        //create reiziger en adres + koppelen
//        String gbdatum = "1981-03-14";
//        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
//        rdao.save(sietske);
//        Adres newAdres = new Adres(77, "1234 AB", "12", "Heidelberglaan", "Utrecht", sietske);
//
//        boolean saveSuccess = adao.save(newAdres);
//        if (saveSuccess) {
//            System.out.println("Address saved successfully!");
//            System.out.println(newAdres + "\n");
//        } else {
//            System.out.println("Failed to save address.");
//        }
//        // read - find address for reiziger
//        List<Adres> addressesForReiziger = adao.findByReiziger(sietske);
//        if (!addressesForReiziger.isEmpty()) {
//            System.out.println("Addresses for Reiziger:");
//            for (Adres adres : addressesForReiziger) {
//                System.out.println(adres + "\n");
//            }
//        } else {
//            System.out.println("No addresses found for this Reiziger. \n");
//        }
//
//        // Read - Find an address by ID
//        Adres foundAdres = adao.findById(77);
//        if (foundAdres != null) {
//            System.out.println("Found address: " + foundAdres + "\n");
//        } else {
//            System.out.println("Address not found.\n");
//        }
//
//        // Update - Modify an address
//        newAdres.setStraat("Updated Straat");
//        boolean updateSuccess = adao.update(newAdres);
//        if (updateSuccess) {
//            System.out.println("Address updated successfully!");
//            System.out.println(newAdres + "\n");
//        } else {
//            System.out.println("Failed to update address.");
//        }
//
//        // Delete - Remove an address
//        boolean deleteSuccessAddress = adao.delete(newAdres);
//        if (deleteSuccessAddress) {
//            System.out.println("Address deleted successfully! \n");
//        } else {
//            System.out.println("Failed to delete address.");
//        }
//
//        boolean deleteSuccessUser = rdao.delete(sietske);
//        if (deleteSuccessUser) {
//            System.out.println("User deleted successfully!");
//        } else {
//            System.out.println("Failed to delete user.");
//        }


        private static void testOvChipKaartDAO(OvChipKaartDAO odao, ReizigerDAO rdao) throws SQLException {
            System.out.println("\n---------- Test OvChipKaartDAO -------------");

            //find all OvChipkaarten

//            List<OvChipKaart> ovChipKaarten = odao.findAll();
//                    System.out.println("[Test] OvChipkaartDAO.findAll() gives the following ovChipKaarten:");
//        for (OvChipKaart chipKaarten : ovChipKaarten) {
//            System.out.println(chipKaarten);
//        }
//        System.out.println();

            // Create a new OvChipKaart and persist it in the database
            String gbdatum = "1981-03-14";
            String vervalDatum = "2023-12-31";
            Reiziger siet = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
            rdao.save(siet);
            OvChipKaart ovChipKaart = new OvChipKaart(123, Date.valueOf(vervalDatum), 2, 50.0, siet);
            boolean saveSuccessOV = odao.save(ovChipKaart);

            if (saveSuccessOV) {
                System.out.println("OvChipKaart saved successfully!");
                System.out.println(ovChipKaart + "\n");
            } else {
                System.out.println("Failed to save OvChipKaart.");
            }

            // Read - Find OvChipKaart by ID
            OvChipKaart foundOvChipKaart = odao.findById(77);
            if (foundOvChipKaart != null) {
                System.out.println("Found OvChipKaart: " + foundOvChipKaart + "\n");
            } else {
                System.out.println("OvChipKaart not found.\n");
            }

            ovChipKaart.setSaldo(60.0);
            boolean updateSuccess = odao.update(ovChipKaart);
            if (updateSuccess) {
                System.out.println("OvChipKaart updated successfully!");
                System.out.println(ovChipKaart + "\n");
            } else {
                System.out.println("Failed to update OvChipKaart.");
            }

            // Delete - Remove an OvChipKaart
            boolean deleteSuccess = odao.delete(ovChipKaart);
            if (deleteSuccess) {
                System.out.println("OvChipKaart deleted successfully! \n");
            } else {
                System.out.println("Failed to delete OvChipKaart.");
            }

        }
    }

