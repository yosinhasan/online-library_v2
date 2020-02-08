package mfh.faztech.online_library.util;

import mfh.faztech.online_library.repository.AuthorRepository;
import mfh.faztech.online_library.repository.BookRepository;
import mfh.faztech.online_library.service.AuthorService;
import mfh.faztech.online_library.service.BookService;
import mfh.faztech.online_library.service.impl.AuthorServiceImpl;
import mfh.faztech.online_library.service.impl.BookServiceImpl;

public class ServiceContainer {
    private static ServiceContainer mInstance;
    private BookService mBookService;
    private AuthorService mAuthorService;

    private ServiceContainer(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.mBookService = new BookServiceImpl(bookRepository);
        this.mAuthorService = new AuthorServiceImpl(authorRepository);
    }

    public static ServiceContainer getInstance(BookRepository bookRepository, AuthorRepository authorRepository) {
        if (mInstance == null) {
            mInstance = new ServiceContainer(bookRepository, authorRepository);
        }
        return mInstance;
    }

    public BookService getBookService() {
        return this.mBookService;
    }

    public AuthorService getAuthorService() {
        return this.mAuthorService;
    }
}
