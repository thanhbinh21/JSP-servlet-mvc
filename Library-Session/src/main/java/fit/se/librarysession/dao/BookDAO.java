package fit.se.librarysession.dao;

import fit.se.librarysession.model.Book;
import fit.se.librarysession.util.DBUtil;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements Serializable {

    private DBUtil dbUtil;

    public BookDAO(DataSource dataSource ) {
        this.dbUtil = new DBUtil(dataSource);
    }


    public List<Book> getAll(){
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try(Connection connection = dbUtil.getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            while (resultSet.next()){
                Book a = new Book();
                a.setId(resultSet.getInt("ID"));
                a.setAuthor(resultSet.getString("AUTHOR"));
                a.setDes(resultSet.getString("DESCRIPTION"));
                a.setImg(resultSet.getString("IMGURL"));
                a.setPrice(resultSet.getDouble("PRICE"));
                a.setTitle(resultSet.getString("TITLE"));
                a.setQuantity(resultSet.getInt("QUANTITY"));
                list.add(a);


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Book> getByTitleKey(String key ) {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ?";
        try (Connection connection = dbUtil.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + key + "%");
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    Book a = new Book();
                    a.setId(resultSet.getInt("ID"));
                    a.setAuthor(resultSet.getString("AUTHOR"));
                    a.setDes(resultSet.getString("DESCRIPTION"));
                    a.setImg(resultSet.getString("IMGURL"));
                    a.setPrice(resultSet.getDouble("PRICE"));
                    a.setTitle(resultSet.getString("TITLE"));
                    a.setQuantity(resultSet.getInt("QUANTITY"));
                    list.add(a);

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public Book getById(int id) throws SQLException {
        String sql ="SELECT * from books WHERE ID = ?";
        Book a = new Book();

        try(Connection connection = dbUtil.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            try(ResultSet resultSet= preparedStatement.executeQuery()){
                if(resultSet.next()){
                    a.setId(resultSet.getInt("ID"));
                    a.setAuthor(resultSet.getString("AUTHOR"));
                    a.setDes(resultSet.getString("DESCRIPTION"));
                    a.setImg(resultSet.getString("IMGURL"));
                    a.setPrice(resultSet.getDouble("PRICE"));
                    a.setTitle(resultSet.getString("TITLE"));
                    a.setQuantity(resultSet.getInt("QUANTITY"));

                }
            }
        }
        return a;


    }


}
