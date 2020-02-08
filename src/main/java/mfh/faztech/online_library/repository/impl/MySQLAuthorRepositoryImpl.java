package mfh.faztech.online_library.repository.impl;


import mfh.faztech.online_library.entity.Author;
import mfh.faztech.online_library.repository.AuthorRepository;
import mfh.faztech.online_library.util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLAuthorRepositoryImpl implements AuthorRepository {


    private final DBUtil dbUtil = DBUtil.getInstance();


    public boolean create(String email, String password) throws SQLException {
        String sql = "INSERT INTO author (name, password, email, role) VALUES (?, ?, ?, ?)";
        dbUtil.connect();

        PreparedStatement statement = dbUtil.getJdbcConnection().prepareStatement(sql);
        statement.setString(1, email.replaceFirst("@.+", ""));
        statement.setString(2, password);
        statement.setString(3, email);
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbUtil.disconnect();
        return rowInserted;
    }

    @Override
    public int create(Author author) throws SQLException {
        String sql = "INSERT INTO author (name, password, email, role) VALUES (?, ?, ?, ?)";
        dbUtil.connect();

        PreparedStatement statement = dbUtil.getJdbcConnection().prepareStatement(sql);
        statement.setString(1, author.getName());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbUtil.disconnect();
        if (rowInserted) {
            return author.getId();
        }
        return -1;
    }

    @Override
    public Author read(int id) throws SQLException {
        Author author = null;
        String sql = "SELECT * FROM author WHERE author_id = ?";

        dbUtil.connect();

        PreparedStatement statement = dbUtil.getJdbcConnection().prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
        }

        resultSet.close();
        statement.close();

        return author;
    }

    public Author read(String email, String password) throws SQLException {
        Author author = null;
        String sql = "SELECT * FROM author WHERE email = ? and password = ?";

        dbUtil.connect();

        PreparedStatement statement = dbUtil.getJdbcConnection().prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            int id = Integer.parseInt(resultSet.getString("author_id"));
        }

        resultSet.close();
        statement.close();
        System.out.println(author);
        return author;
    }

    @Override
    public boolean update(Author author) throws SQLException {
        String sql = "UPDATE author SET name = ?, password = ?, email = ?";
        sql += " WHERE author_id = ?";
        dbUtil.connect();

        PreparedStatement statement = dbUtil.getJdbcConnection().prepareStatement(sql);
        statement.setString(1, author.getName());
        statement.setInt(4, author.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        dbUtil.disconnect();
        return rowUpdated;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM author where author_id = ?";

        dbUtil.connect();

        PreparedStatement statement = dbUtil.getJdbcConnection().prepareStatement(sql);
        statement.setInt(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        dbUtil.disconnect();
        return rowDeleted;
    }

    @Override
    public List<Author> getAll() throws SQLException {
        List<Author> listAuthor = new ArrayList<>();
        Map<Integer, Author> mapAuthor = new HashMap<>();
        String sql = "SELECT * FROM author";
        dbUtil.connect();
        Statement statement = dbUtil.getJdbcConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("second_name");
            Date date = resultSet.getDate("date_of_birth");
            LocalDate dateOfBirth = LocalDate.parse(date.toString());
            Author author = new Author(id, name, description, dateOfBirth);
            listAuthor.add(author);
            mapAuthor.put(id, author);
        }
        sql = "SELECT * FROM relation_table;";
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int book_id = resultSet.getInt("book_id");
            int author_id = resultSet.getInt("author_id");
            if (mapAuthor.containsKey(author_id)) {
                mapAuthor.get(author_id).getBooks().add(book_id);
            }
        }

        resultSet.close();
        statement.close();

        dbUtil.disconnect();

        return listAuthor;
    }
}
