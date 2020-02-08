package mfh.faztech.online_library.web.fabric;

import mfh.faztech.online_library.entity.Author;
import mfh.faztech.online_library.entity.Book;
import mfh.faztech.online_library.service.AuthorService;
import mfh.faztech.online_library.service.BookService;
import mfh.faztech.online_library.util.Path;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BookFabric {
    private List<Book> mListBook;
    private BookService mBookService;
    private AuthorService mAuthorService;
    private List<Author> mListAuthors;

    public BookFabric(BookService bookService, AuthorService authorService) {
        this.mBookService = bookService;
        this.mAuthorService = authorService;
    }


    public HttpServletRequest execute(HttpServletRequest request) throws SQLException {
        String action = request.getParameter("action");

        request.removeAttribute("list_books");
        request.removeAttribute("book");
        request.removeAttribute("book_authors_name");
        request.removeAttribute("search");

        request.removeAttribute("list_authors");
        request.removeAttribute("author");
        request.removeAttribute("author_books_name");

        request.removeAttribute("refer_to_page");

        String refer_to_page = Path.MAIN_PAGE;
        switch (action) {
            case "main":
                request.setAttribute("list_books", this.listBooks());
                break;
            case "bookDetail":
                int id = Integer.parseInt(request.getParameter("book_id"));
                request.setAttribute("book", this.findBookById(id));
                request.setAttribute("book_authors_name", this.findAuthorsByBookId(id));
                refer_to_page = Path.BOOK_DETAIL_PAGE;
                break;
            case "search":
                String book_name = request.getParameter("search_name").trim();
                Book bookToFind = this.findBookByName(book_name);
                if (bookToFind != null) {
                    request.setAttribute("book_authors_name", this.findAuthorsByBookId(bookToFind.getId()));
                    request.setAttribute("book", bookToFind);
                    refer_to_page = Path.BOOK_DETAIL_PAGE;
                } else {
                    refer_to_page = "/authors?action=search&search_name=" + book_name;
                }

                break;
            default:
                break;
        }
        request.setAttribute("refer_to_page", refer_to_page);
        return request;
    }

    private List<Book> listBooks() throws SQLException {
        this.mListBook = mBookService.findAll();
        return mListBook;
    }

    private Book findBookById(int id) throws SQLException {
        if (this.mListBook == null) {
            this.mListBook = listBooks();
        }
        for (Book book : mListBook) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    private Book findBookByName(String book_name) throws SQLException {
        if (this.mListBook == null) {
            this.mListBook = listBooks();
        }
        for (Book book : mListBook) {
            if (book.getName().equalsIgnoreCase(book_name)) {
                return book;
            }
        }
        return null;
    }

    private Set<String> findAuthorsByBookId(int book_id) throws SQLException {
        Book book = findBookById(book_id);
        Set<String> listAuthorsName = new HashSet<>();
        if (this.mListAuthors == null) {
            this.mListAuthors = mAuthorService.findAll();
        }
        for (Author author : mListAuthors) {
            if (book.getAuthors().contains(author.getId())) {
                listAuthorsName.add(author.getName());
            }
        }
        return listAuthorsName;
    }
}
