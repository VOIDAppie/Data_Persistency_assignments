package reiziger;

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
            PreparedStatement statement = connection.prepareStatement("UPDATE FROM reiziger WHERE reiziger_id = ?");
            statement.setInt(1, reiziger.getReiziger_id());
            statement.execute();
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
    public Reiziger findById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reiziger WHERE reiziger_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

//            String voorletters = resultSet.getString(2);
//            String tussenvoegsel = resultSet.getString(3);
//            String achternaam = resultSet.getString(4);
//            Date geboortedatum = resultSet.getDate(5);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

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

//
//            int reiziger_id = reiziger.getReizigerId();
//            String voorletters = reiziger.getVoorletters();
//            String tussenvoegsel = reiziger.getTussenvoegsel();
//            String achternaam = reiziger.getAchternaam();

        return alleReizigers;
        } catch (Exception e) {
            System.out.println("reizigers niet gevonden " + e.getMessage());
        }
        return null;
    }
}
