package dao.authors.impl;

import dao.authors.AuthorsRepository;
import models.Author;
import models.Book;
import providers.MyDriverManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorsRepositoryImpl implements AuthorsRepository {
    private final Connection connection = MyDriverManager.getConnection();

    //language=SQL
    private static final String SQL_SAVE_AUTHOR = "insert into authors(name, surname, birth_year) VALUES (?, ?, ?)";

    //language=SQL
    private static final String SQL_FIND_AUTHOR_BY_ID = "select * from authors where id = ?";

    //language=SQL
    private static final String SQL_FIND_ALL_AUTHORS = "select * from authors";

    //language=SQL
    private static final String SQL_DELETE_AUTHOR_BY_ID = "delete from authors where id = ?";

    //language=SQL
    private static final String SQL_UPDATE_AUTHOR_BY_ID = "update authors set name=?, surname=?, birth_year=? where id=?";

    @Override
    public void saveAuthor(Author author) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_AUTHOR, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getSurname());
            preparedStatement.setInt(3, author.getBirthYear());

            int rows = preparedStatement.executeUpdate();
            if (rows != 1) {
                throw new SQLException("Can't save user");
            }
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                author.setId(keys.getLong("id"));
            } else {
                throw new SQLException();
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Optional<Author> findAuthorById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_AUTHOR_BY_ID)) {

            statement.setLong(1, id);
            statement.execute();

            ResultSet author = statement.executeQuery();
            if (author.next()) {
                return Optional.of(Author.builder()
                        .id(author.getLong("id"))
                        .name(author.getString("name"))
                        .surname(author.getString("surname"))
                        .birthYear(author.getInt("birth_year"))
                        .build());
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return Optional.empty();
    }

    @Override
    public void deleteAuthorById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_AUTHOR_BY_ID)) {
            try {
                preparedStatement.setLong(1, id);
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new IllegalArgumentException();
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void updateAuthor(Author author) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_AUTHOR_BY_ID)) {
            try {
                preparedStatement.setString(1, author.getName());
                preparedStatement.setString(2, author.getSurname());
                preparedStatement.setInt(3, author.getBirthYear());
                preparedStatement.setLong(4, author.getId());
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new IllegalArgumentException();
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<Author> findAllAuthors() {
        List<Author> authors = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_AUTHORS)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                authors.add(Author.builder().id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .birthYear(resultSet.getInt("birth_year"))
                        .build());
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return authors;
    }
}
