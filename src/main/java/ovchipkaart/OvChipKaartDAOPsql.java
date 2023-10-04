package ovchipkaart;

import reiziger.Reiziger;
import reiziger.ReizigerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OvChipKaartDAOPsql implements OvChipKaartDAO {

    private Connection connection;
    private ReizigerDAO reizigerDAO;

    public OvChipKaartDAOPsql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(OvChipKaart ovChipKaart) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("INSERT INTO ov_chipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) VALUES (?, ?, ?, ?, ?) RETURNING kaart_nummer");
        statement.setInt(1, ovChipKaart.getKaart_nummer());
        statement.setDate(2, ovChipKaart.getGeldig_tot());
        statement.setInt(3, ovChipKaart.getKlasse());
        statement.setDouble(4, ovChipKaart.getSaldo());
        statement.setInt(5, ovChipKaart.getReiziger_id());

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int generatedKaartNummer = resultSet.getInt(1);
            ovChipKaart.setKaart_nummer(generatedKaartNummer);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(OvChipKaart ovChipKaart) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("UPDATE ov_chipkaart SET geldig_tot = ?, klasse = ?, saldo = ? WHERE kaart_nummer = ?");
        statement.setDate(1, ovChipKaart.getGeldig_tot());
        statement.setInt(2, ovChipKaart.getKlasse());
        statement.setDouble(3, ovChipKaart.getSaldo());
        statement.setInt(4, ovChipKaart.getKaart_nummer());
        statement.executeUpdate();
        return true;
    }

    @Override
    public boolean delete(OvChipKaart ovChipKaart) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("DELETE FROM ov_chipkaart WHERE kaart_nummer = ?");
        statement.setInt(1, ovChipKaart.getKaart_nummer());
        statement.execute();
        return true;
    }

    @Override

    public OvChipKaart findById(int id) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM ov_chipkaart WHERE kaart_nummer = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        OvChipKaart ovChipKaart = null;
        while (resultSet.next()) {
            Reiziger r = reizigerDAO.findById(resultSet.getInt(5));
            ovChipKaart = new OvChipKaart(
                    resultSet.getInt("kaart_nummer"),
                    resultSet.getDate("geldig_tot"),
                    resultSet.getInt("klasse"),
                    resultSet.getDouble("saldo"),
                    r
            );

        }
        return ovChipKaart;
    }

    @Override
    public List<OvChipKaart> findAll() throws SQLException {
        return null;
    }

    @Override
    public void setOvChipKaartDAO(OvChipKaartDAO ovChipKaartDAO) throws SQLException {

    }

    public void setReizigerDAO(ReizigerDAO reizigerDAO) {
        this.reizigerDAO = reizigerDAO;
    }
}