package Product;

import ovchipkaart.OvChipKaart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDAOPsql implements ProductDAO {

    private Connection connection;

    public ProductDAOPsql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Product product) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO product (product_nummer, naam, beschrijving, prijs) " +
                        "VALUES (?, ?, ?, ?) RETURNING product_nummer"
        );
        statement.setInt(1, product.getProduct_nummer());
        statement.setString(2, product.getNaam());
        statement.setString(3, product.getBeschrijving());
        statement.setDouble(4, product.getPrijs());

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int generatedProductNummer = resultSet.getInt(1);
            product.setProduct_nummer(generatedProductNummer);
            return true;
        }
        return false;
    }


    @Override
    public boolean update(Product product) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE product SET naam = ?, beschrijving = ?, prijs = ? " +
                        "WHERE product_nummer = ?"
        );
        statement.setString(1, product.getNaam());
        statement.setString(2, product.getBeschrijving());
        statement.setDouble(3, product.getPrijs());
        statement.setInt(4, product.getProduct_nummer());

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean delete(Product product) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM product WHERE product_nummer = ?"
        );
        statement.setInt(1, product.getProduct_nummer());

        return statement.executeUpdate() > 0;
    }

    @Override
    public List<Product> findByOvChipKaart(OvChipKaart ov) throws SQLException {
        String selectQuery = "SELECT * FROM product" +
                "JOIN ov_chipkaart_product ON product.product_nummer = ov_chipkaart_product.product_nummer" +
                "JOIN ov_chipkaart ON ov_chipkaart.kaart_nummer = ov_chipkaart_product.kaart_nummer WHERE "
    }


}
