package fit.se.librarysession.servlet;

import fit.se.librarysession.dao.BookDAO;
import fit.se.librarysession.model.Book;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Link;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    private BookDAO bookDAO;

    @Resource(name = "jdbc/bookstore")
    private DataSource dataSource;

    public void init(){
        bookDAO = new BookDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String key = req.getParameter("key");

        if(id!=null){
            try {
                Book book = bookDAO.getById(Integer.parseInt(id));
                req.setAttribute("book",book);
                req.getRequestDispatcher("book-details.jsp").forward(req,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }else if(key!=null){
            List<Book> books = bookDAO.getByTitleKey(key);
            req.setAttribute("books",books);
            req.getRequestDispatcher("book-search.jsp").forward(req,resp);
        }else{
            List<Book> books = bookDAO.getAll();
            req.setAttribute("books",books);
            req.getRequestDispatcher("book-list.jsp").forward(req,resp);
        }

    }
}
