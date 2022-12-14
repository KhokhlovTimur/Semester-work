package dao.authorsDao;

import models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorsRepository {
    public void saveAuthor(Author author);

    public Optional<Author> findAuthorById(Long id);

    public void deleteAuthorById(Long id);

    public void updateAuthor(Author author);

    public List<Author> findAllAuthors();
}
