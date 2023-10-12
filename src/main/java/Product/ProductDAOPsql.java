package Product;

import ovchipkaart.OvChipKaart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public Product findById(int id) throws SQLException {
        Product p = null;
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE product_nummer = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            p = new Product();
            p.setProduct_nummer(resultSet.getInt(1));
            p.setNaam(resultSet.getString(2));
            p.setBeschrijving(resultSet.getString(3));
            p.setPrijs(resultSet.getInt(4));

        }
        return p;
    }


}
