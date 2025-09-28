package fit.se.departemploye.servlet;

import fit.se.departemploye.dao.QuanLiDAO;
import fit.se.departemploye.model.Department;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {
    private QuanLiDAO dao;

    @Resource(name = "jdbc/employeeDB")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        dao = new QuanLiDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "add":   // hiển thị form thêm
                    req.getRequestDispatcher("dept-add.jsp").forward(req, resp);
                    break;

                case "edit":  // hiển thị form sửa
                    int idEdit = Integer.parseInt(req.getParameter("id"));
                    Department deptEdit = dao.getDeptById(idEdit);
                    req.setAttribute("department", deptEdit);
                    req.getRequestDispatcher("dept-edit.jsp").forward(req, resp);
                    break;

                case "delete": // xóa
                    int idDel = Integer.parseInt(req.getParameter("id"));
                    Department deptDel = new Department();
                    deptDel.setId(idDel);
                    dao.removeDept(idDel);
                    resp.sendRedirect("departments");
                    break;

                case "search": // tìm kiếm
                    String keyword = req.getParameter("keyword");
                    req.setAttribute("departments", dao.searchDeptByName(keyword));
                    req.getRequestDispatcher("dept-list.jsp").forward(req, resp);
                    break;

                default: // list
                    req.setAttribute("departments", dao.getAllDept());
                    req.getRequestDispatcher("dept-list.jsp").forward(req, resp);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");

        Department d = new Department();
        d.setName(name);

        try {
            if (id == null || id.isEmpty()) {
                dao.addDept(d);
            } else {
                d.setId(Integer.parseInt(id));
                dao.updateDept(d);
            }
            resp.sendRedirect("departments");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
