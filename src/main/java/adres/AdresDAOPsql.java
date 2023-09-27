package adres;

import reiziger.Reiziger;
import reiziger.ReizigerDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {
    private Connection connection;

    public void setReizigerDAO(ReizigerDAO reizigerDAO) {
        this.reizigerDAO = reizigerDAO;
    }

    private ReizigerDAO reizigerDAO;
    public AdresDAOPsql(Connection connection) {
        this.connection = connection;
    }



    @Override
    public boolean save(Adres adres) throws SQLException {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO adres(adres_id, postcode ,huisnummer , straat, woonplaats, reiziger_id ) VALUES (?, ?, ?, ?, ?, ?)");
            int adres_id = adres.getAdres_id();
            String postcode = adres.getPostcode();
            String huisnummer = adres.getHuisnummer();
            String straat = adres.getStraat();
            String woonplaats = adres.getWoonplaats();
            int reiziger_id = adres.getReiziger().getReiziger_id();

            statement.setInt(1, adres_id);
            statement.setString(2, postcode);
            statement.setString(3, huisnummer);
            statement.setString(4, straat);
            statement.setString(5, woonplaats);
            statement.setInt(6, reiziger_id);
            statement.execute();
            return true;

    }

    @Override
    public boolean update(Adres adres) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE adres " +
                "SET postcode = ?, " +
                "huisnummer = ?, " +
                "straat = ?, " +
                "woonplaats = ? " +
                "WHERE adres_id = ?");

        statement.setString(1, adres.getPostcode());
        statement.setString(2, adres.getHuisnummer());
        statement.setString(3, adres.getStraat());
        statement.setString(4, adres.getWoonplaats());
        statement.setInt(5, adres.getAdres_id());
        statement.executeUpdate();
        return true;
    }

    @Override
    public boolean delete(Adres adres) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM adres WHERE adres_id = ?");
        statement.setInt(1, adres.getAdres_id());
        statement.execute();
        return true;
    }

    @Override
    public Adres findById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM adres WHERE adres_id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int adresId = resultSet.getInt("adres_id");
            String postcode = resultSet.getString("postcode");
            String huisnummer = resultSet.getString("huisnummer");
            String straat = resultSet.getString("straat");
            String woonplaats = resultSet.getString("woonplaats");

            int reizigerId = resultSet.getInt("reiziger_id");
            Reiziger reiziger = reizigerDAO.findById(reizigerId);

            return new Adres(adresId, postcode, huisnummer, straat, woonplaats, reiziger);
        } else {
            return null; // Address with the given ID was not found
        }
    }


    @Override
    public List<Adres> findAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT  * FROM adres");
        ResultSet resultSet = statement.executeQuery();
        List<Adres> alleAdressen = new ArrayList<>();
        while (resultSet.next()){
            // get reiziger if you know id
            Reiziger r = reizigerDAO.findById(resultSet.getInt(6));
            alleAdressen.add(new Adres(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    r
            ));
        }
        return alleAdressen;
    }


    public List<Adres> findByReiziger(Reiziger reiziger) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM adres WHERE reiziger_id = ?");
        statement.setInt(1, reiziger.getReiziger_id());
        ResultSet resultSet = statement.executeQuery();

        List<Adres> adressen = new ArrayList<>();
        while (resultSet.next()) {
            int adresId = resultSet.getInt("adres_id");
            String postcode = resultSet.getString("postcode");
            String huisnummer = resultSet.getString("huisnummer");
            String straat = resultSet.getString("straat");
            String woonplaats = resultSet.getString("woonplaats");

            Adres adres = new Adres(adresId, postcode, huisnummer, straat, woonplaats, reiziger);
            adressen.add(adres);
        }

        return adressen;
    }


}
