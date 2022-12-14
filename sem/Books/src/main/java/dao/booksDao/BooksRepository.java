package dao.booksDao;

import models.Book;

import java.util.List;
import java.util.Optional;

public interface BooksRepository {
    public void saveBook(Book book);

    public Optional<Book> findBookById(Long id);

    public void deleteBookById(Long id);

    public void updateBook(Book book);

    public List<Book> findAllBooks();

    public List<Book> orderBooksById();

}
