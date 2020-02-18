package mfh.faztech.online_library.web;

import mfh.faztech.online_library.repository.AuthorRepository;
import mfh.faztech.online_library.repository.BookRepository;
import mfh.faztech.online_library.repository.impl.MySQLAuthorRepositoryImpl;
import mfh.faztech.online_library.repository.impl.MySQLBookRepositoryImpl;
import mfh.faztech.online_library.util.Path;
import mfh.faztech.online_library.util.ServiceContainer;
import mfh.faztech.online_library.web.fabric.AuthorFabric;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        name = "author-controller",
        urlPatterns = "/authors"
)
public class AuthorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hello AuthorServlet");
        try {
            this.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String refer_to_page = (String) request.getAttribute("refer_to_page");
        request.getRequestDispatcher(
                refer_to_page).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private HttpServletRequest execute(HttpServletRequest request) throws SQLException {
        BookRepository bookRepository = new MySQLBookRepositoryImpl();
        AuthorRepository authorRepository = new MySQLAuthorRepositoryImpl();
        ServiceContainer serviceContainer = ServiceContainer.getInstance(bookRepository, authorRepository);
        AuthorFabric authorFabric = new AuthorFabric(serviceContainer.getBookService(), serviceContainer.getAuthorService());
        return authorFabric.execute(request);
    }
}
