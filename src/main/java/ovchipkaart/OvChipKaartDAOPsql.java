package ovchipkaart;

import reiziger.ReizigerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OvChipKaartDAOPsql implements OvChipKaartDAO {

    private Connection connection;

    public OvChipKaartDAOPsql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(OvChipKaart ovChipKaart) throws SQLException {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO ov_chip_kaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) VALUES (?, ?, ?, ?, ?) RETURNING kaart_nummer");
            statement.setInt(1, ovChipKaart.getKaart_nummer());
            statement.setDate(2, ovChipKaart.getGeldig_tot());
            statement.setInt(3, ovChipKaart.getKlasse());
            statement.setDouble(4, ovChipKaart.getSaldo());
            statement.setInt(5, ovChipKaart.getReiziger().getReiziger_id());

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

            PreparedStatement statement = connection.prepareStatement("UPDATE ov_chip_kaart SET geldig_tot = ?, klasse = ?, saldo = ? WHERE kaart_nummer = ?");
            statement.setDate(1, ovChipKaart.getGeldig_tot());
            statement.setInt(2, ovChipKaart.getKlasse());
            statement.setDouble(3, ovChipKaart.getSaldo());
            statement.setInt(4, ovChipKaart.getKaart_nummer());
            statement.executeUpdate();
            return true;
    }

    @Override
    public boolean delete(OvChipKaart ovChipKaart) throws SQLException {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM ov_chip_kaart WHERE kaart_nummer = ?");
            statement.setInt(1, ovChipKaart.getKaart_nummer());
            statement.execute();
            return true;
    }

    @Override
    public OvChipKaart findById(int id) {
        return null;
    }

    @Override
    public List<OvChipKaart> findAll() {
        return null;
    }

    @Override
    public void setOvChipKaartDAO(OvChipKaartDAO ovChipKaartDAO) {

    }




}
