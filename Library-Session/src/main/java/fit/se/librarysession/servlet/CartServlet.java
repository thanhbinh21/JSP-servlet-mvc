package fit.se.librarysession.servlet;

import fit.se.librarysession.dao.BookDAO;
import fit.se.librarysession.model.Cart;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private BookDAO bookDAO;

    @Resource(name = "jdbc/bookstore")
    private DataSource dataSource;

    public void init(){
        bookDAO = new BookDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("cart.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            session.setAttribute("cart",cart);
        }
        String action = req.getParameter("action");
        if("add".equals(action)){
            try {
                cart.addItem(bookDAO.getById(Integer.parseInt(req.getParameter("ID"))));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }else if("remove".equals(action)){
            cart.removeItem(Integer.parseInt(req.getParameter("ID")));
        }else if("update".equals(action)){
            cart.updateItem(Integer.parseInt(req.getParameter("ID")), Integer.parseInt(req.getParameter("QUANTITY")));
        }else if("clear".equals(action)){
            cart.clear();
        }

        resp.sendRedirect("cart");

    }
}
