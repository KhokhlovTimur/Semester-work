package services.utils;

import dto.BookDto;

import java.util.Comparator;
import java.util.List;

public class SortService {
    private MapService mapService;

    public SortService(MapService mapService) {
        this.mapService = mapService;
    }

    private List<BookDto> sortBooksByAscendingPrice() {
        List<BookDto> books = mapService.convertAllBooksToBooksDtoFromBooks();
        books.sort(Comparator.comparingInt(BookDto::getPrice));
        return books;
    }

    private List<BookDto> sortBooksByDescendingPrice() {
        List<BookDto> books = mapService.convertAllBooksToBooksDtoFromBooks();
        books.sort((o1, o2) -> o2.getPrice() - o1.getPrice());
        return books;
    }

    private List<BookDto> sortBooksFromZtoA() {
        List<BookDto> books = mapService.convertAllBooksToBooksDtoFromBooksSortById();
        books.sort((o1, o2) -> o2.getTitle().compareToIgnoreCase(o1.getTitle()));
        return books;
    }

    private List<BookDto> sortBooksFromAtoZ() {
        List<BookDto> books = mapService.convertAllBooksToBooksDtoFromBooksSortById();
        books.sort((o1, o2) -> o1.getTitle().compareToIgnoreCase(o2.getTitle()));
        return books;
    }

    public List<BookDto> sortBy(String sortBy) {
        switch (sortBy) {
            case "priceAscend":
                return sortBooksByAscendingPrice();
            case "priceDescend":
                return sortBooksByDescendingPrice();
            case "az":
                return sortBooksFromAtoZ();
            case "za":
                return sortBooksFromZtoA();
        }
        return mapService.convertAllBooksToBooksDtoFromBooksSortById();
    }

    public List<BookDto> sortBooksAdminPage(String sortBy){
        switch (sortBy){
            case "byName":
                return sortBooksByName();
            case "bySurname":
                return sortBooksBySurname();
            case "byYear":
                return sortBooksByYear();
            case "byPrice":
                return sortBooksByAscendingPrice();
            case "byTitle":
                return sortBooksFromAtoZ();
            case "byId":
                return mapService.convertAllBooksToBooksDtoFromBooksSortById();
        }
        return mapService.convertAllBooksToBooksDtoFromBooksSortById();
    }

    private List<BookDto> sortBooksByName(){
        List<BookDto> bookDtoList = mapService.convertAllBooksToBooksDtoFromBooksSortById();
        bookDtoList.sort((o1, o2) -> o1.getAuthorName().compareTo(o2.getAuthorName()));
        return bookDtoList;
    }

    private List<BookDto> sortBooksBySurname(){
        List<BookDto> bookDtoList = mapService.convertAllBooksToBooksDtoFromBooksSortById();
        bookDtoList.sort((o1, o2) -> o1.getAuthorSurname().compareTo(o2.getAuthorSurname()));
        return bookDtoList;
    }

    private List<BookDto> sortBooksByYear(){
        List<BookDto> bookDtoList = mapService.convertAllBooksToBooksDtoFromBooksSortById();
        bookDtoList.sort(Comparator.comparingInt(BookDto::getYearOfPublication));
        return bookDtoList;
    }
}
