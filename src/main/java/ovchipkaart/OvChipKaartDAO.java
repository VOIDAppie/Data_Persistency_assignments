package ovchipkaart;

import reiziger.Reiziger;
import reiziger.ReizigerDAO;

import java.sql.SQLException;
import java.util.List;

public interface OvChipKaartDAO {
    boolean save(OvChipKaart ovChipKaart) throws SQLException;
    boolean update(OvChipKaart ovChipKaart) throws SQLException;
    boolean delete(OvChipKaart ovChipKaart) throws SQLException;
    OvChipKaart findById(int id) throws SQLException;
    List<OvChipKaart> findAll()throws SQLException;
    public void setOvChipKaartDAO(OvChipKaartDAO ovChipKaartDAO) throws SQLException ;
}
