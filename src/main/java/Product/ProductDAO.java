package Product;

import ovchipkaart.OvChipKaart;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    boolean save(Product product) throws SQLException;
    boolean update(Product product) throws SQLException;
    boolean delete(Product product) throws SQLException;
    List<Product> findByOvChipKaart(OvChipKaart ov) throws SQLException;

//    void setOvChipkaartDAO(OvChipKaartDAO ovChipKaartDAO);
}
