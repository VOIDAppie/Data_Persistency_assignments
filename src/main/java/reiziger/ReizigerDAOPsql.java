package reiziger;

import ovchipkaart.OvChipKaart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    private Connection connection;

    public ReizigerDAOPsql(Connection connection) {
        this.connection = connection;


    }


    @Override
    public boolean save(Reiziger reiziger) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO reiziger(reiziger_id, voorletters,tussenvoegsel, achternaam, geboortedatum ) VALUES (?, ?, ?, ?, ?)");
            int reiziger_id = reiziger.getReiziger_id();
            String voorletters = reiziger.getVoorletters();
            String tussenvoegsel = reiziger.getTussenvoegsel();
            String achternaam = reiziger.getAchternaam();
            Date geboortedatum = reiziger.getGeboortedatum();

            statement.setInt(1, reiziger_id);
            statement.setString(2, voorletters);
            statement.setString(3, tussenvoegsel);
            statement.setString(4, achternaam);
            statement.setDate(5, geboortedatum);
            statement.execute();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE reiziger " +
                    "SET voorletters = ?, " +
                    "tussenvoegsel = ?, " +
                    "achternaam = ?, " +
                    "geboortedatum = ? " +
                    "WHERE reiziger_id = ?");

            statement.setString(1, reiziger.getVoorletters());
            statement.setString(2, reiziger.getTussenvoegsel());
            statement.setString(3, reiziger.getAchternaam());
            statement.setDate(4, reiziger.getGeboortedatum());
            statement.setInt(5, reiziger.getReiziger_id());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM reiziger WHERE reiziger_id = ?");
            statement.setInt(1, reiziger.getReiziger_id());
            statement.execute();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Reiziger findById(int id) throws SQLException {
        Reiziger r = null;
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM reiziger WHERE reiziger_id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            r = new Reiziger();
            r.setReiziger_id(resultSet.getInt(1));
            r.setVoorletters(resultSet.getString(2));
            r.setTussenvoegsel(resultSet.getString(3));
            r.setAchternaam(resultSet.getString(4));
            r.setGeboortedatum(resultSet.getDate(5));
        }
        return r;
    }


//            resultSet.next();
//
//            String voorletters = resultSet.getString(2);
//            String tussenvoegsel = resultSet.getString(3);
//            String achternaam = resultSet.getString(4);
//            Date geboortedatum = resultSet.getDate(5);
//            }

    @Override
    public List<Reiziger> findByGbDatum(String datum) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reiziger WHERE geboortedatum = ?");
            statement.setDate(1, java.sql.Date.valueOf(datum));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

//            int reiziger_id = reiziger.getReizigerId();
//            String voorletters = reiziger.getVoorletters();
//            String tussenvoegsel = reiziger.getTussenvoegsel();
//            String achternaam = reiziger.getAchternaam();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Reiziger> findAll() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT  * FROM reiziger");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            List<Reiziger> alleReizigers = new ArrayList<>();
            while (resultSet.next()){
                alleReizigers.add(new Reiziger(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4), resultSet.getDate(5)));
            }

        return alleReizigers;
        } catch (Exception e) {
            System.out.println("reizigers niet gevonden " + e.getMessage());
        }
        return null;
    }


    public boolean linkOvChipKaart(Reiziger reiziger, OvChipKaart ovChipKaart) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE ov_chip_kaart SET reiziger_id = ? WHERE kaart_nummer = ?");
            statement.setInt(1, reiziger.getReiziger_id());
            statement.setInt(2, ovChipKaart.getKaart_nummer());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OvChipKaart> findOvChipKaartenByReizigerId(int reizigerId) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ov_chip_kaart WHERE reiziger_id = ?");
            statement.setInt(1, reizigerId);
            ResultSet resultSet = statement.executeQuery();

            List<OvChipKaart> chipKaarten = new ArrayList<>();
            while (resultSet.next()) {
                OvChipKaart ovChipKaart = new OvChipKaart(
                        resultSet.getInt("kaart_nummer"),
                        resultSet.getDate("geldig_tot"),
                        resultSet.getInt("klasse"),
                        resultSet.getDouble("saldo"),
                        null
                );
                chipKaarten.add(ovChipKaart);
            }

            return chipKaarten;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
