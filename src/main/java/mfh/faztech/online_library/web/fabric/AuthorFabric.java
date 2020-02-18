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


public class AuthorFabric {
    private List<Book> mListBooks;
    private BookService mBookService;
    private AuthorService mAuthorService;
    private List<Author> mListAuthors;

    public AuthorFabric(BookService bookService, AuthorService authorService) {
        this.mBookService = bookService;
        this.mAuthorService = authorService;
    }

    public HttpServletRequest execute(HttpServletRequest request) throws SQLException {
        String action = "main";
        if (request.getParameter("id") != null) {
            action = "bookDetail";
        } else if (request.getParameter("name") != null) {
            action = "search";
        }

        request.removeAttribute("list_books");
        request.removeAttribute("book");
        request.removeAttribute("book_authors_name");
        request.removeAttribute("search");

        request.removeAttribute("list_authors");
        request.removeAttribute("author");
        request.removeAttribute("author_books_name");

        request.removeAttribute("nothing_found");
        request.removeAttribute("refer_to_page");
        String refer_to_page = Path.MAIN_PAGE;
        switch (action) {
            case "list_authors":
                request.setAttribute("list_authors", this.listAuthors());
                break;
            case "authorDetail":
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("author", this.findAuthorById(id));
                request.setAttribute("author_books_name", this.findBooksByAuthorId(id));
                refer_to_page = Path.AUTHOR_DETAIL_PAGE;
                break;
            case "search":
                String author_name = request.getParameter("name");
                Author authorToFind = this.findAuthorByName(author_name);
                if (authorToFind != null) {
                    request.setAttribute("author_books_name", this.findBooksByAuthorId(authorToFind.getId()));
                    request.setAttribute("author", authorToFind);
                    refer_to_page = Path.AUTHOR_DETAIL_PAGE;
                } else {
                    request.setAttribute("nothing_found", "nothing was found, repeat your query spelling properly");
                    refer_to_page = Path.SEARCH_BY_NAME_PAGE;
                }
                break;
            default:
                break;
        }
        request.setAttribute("refer_to_page", refer_to_page);
        return request;
    }

    private List<Author> listAuthors() throws SQLException {
        this.mListAuthors = mAuthorService.findAll();
        return mListAuthors;
    }

    private Author findAuthorByName(String author_name) throws SQLException {
        if (this.mListAuthors == null) {
            this.mListAuthors = listAuthors();
        }
        for (Author author : mListAuthors) {
            if (author.getName().trim().equalsIgnoreCase(author_name.trim())) {
                return author;
            }
        }

        return null;
    }

    private Set<String> findBooksByAuthorId(int author_id) throws SQLException {
        Author author = findAuthorById(author_id);
        Set<String> listBooksName = new HashSet<>();
        if (this.mListBooks == null) {
            this.mListBooks = mBookService.findAll();
        }
        for (Book book : mListBooks) {
            if (author.getBooks().contains(book.getId())) {
                listBooksName.add(book.getName());
            }
        }
        return listBooksName;
    }

    private Author findAuthorById(int id) throws SQLException {
        if (this.mListAuthors == null) {
            this.mListAuthors = listAuthors();
        }
        for (Author author : mListAuthors) {
            if (author.getId() == id) {
                return author;
            }
        }
        return null;
    }


}
