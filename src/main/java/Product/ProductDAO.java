package Product;

import ovchipkaart.OvChipKaart;
import ovchipkaart.OvChipKaartDAO;

import java.sql.SQLException;

public interface ProductDAO {
    boolean save(Product product) throws SQLException;
    boolean update(Product product) throws SQLException;
    boolean delete(Product product) throws SQLException;
    Product findById(int id) throws SQLException;

//    void setOvChipkaartDAO(OvChipKaartDAO ovChipKaartDAO);
}
