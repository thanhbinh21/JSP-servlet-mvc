package fit.se.cartshoping.servlet;

import fit.se.cartshoping.dao.ProductDAO;
import fit.se.cartshoping.model.Product;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Resource(name = "jdbc/cartshoping" )
    private DataSource dataSource;
    public void init(){
        productDAO = new ProductDAO(dataSource);
    }
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if(id == null || id.isEmpty()){
            List<Product> products = productDAO.getAllProducts();
            request.setAttribute("products", products);
            request.getRequestDispatcher("product-list.jsp").forward(request, response);
        }else{
            Product a = productDAO.getProductById(Integer.parseInt(id));
            request.setAttribute("product", a);
            request.getRequestDispatcher("product-detail.jsp").forward(request, response);
        }
    }


}
