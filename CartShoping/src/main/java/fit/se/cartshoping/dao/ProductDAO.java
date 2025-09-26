package fit.se.cartshoping.dao;

import fit.se.cartshoping.model.Product;
import fit.se.cartshoping.util.DBUtil;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Serializable {
    private DBUtil dbUtil;

    public ProductDAO(DataSource dataSource) {
        this.dbUtil = new DBUtil(dataSource);
    }
    public List<Product> getAllProducts(){
        String sql = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();

        try(Connection connection = dbUtil.getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()){
                Product a = new Product();
                a.setId(resultSet.getInt("ID"));
                a.setModel(resultSet.getString("MODEL"));
                a.setDescription(resultSet.getString("DESCRIPTION"));
                a.setQuantity(resultSet.getInt("QUANTITY"));
                a.setPrice(resultSet.getDouble("PRICE"));
                a.setImgUrl(resultSet.getString("IMGURL"));
                products.add(a);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE ID = " + id;
        Product a = null;

        try(Connection connection = dbUtil.getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            if (resultSet.next()){
                a = new Product();
                a.setId(resultSet.getInt("ID"));
                a.setModel(resultSet.getString("MODEL"));
                a.setDescription(resultSet.getString("DESCRIPTION"));
                a.setQuantity(resultSet.getInt("QUANTITY"));
                a.setPrice(resultSet.getDouble("PRICE"));
                a.setImgUrl(resultSet.getString("IMGURL"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return a;
    }
}
