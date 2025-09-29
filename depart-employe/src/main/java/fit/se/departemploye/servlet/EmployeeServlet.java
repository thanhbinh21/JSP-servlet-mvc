package fit.se.departemploye.servlet;

import fit.se.departemploye.dao.QuanLiDAO;
import fit.se.departemploye.model.Employee;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    private QuanLiDAO dao;

    @Resource(name = "jdbc/employeeDB")
    private DataSource dataSource;

    @Override
    public void init() {
        dao = new QuanLiDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "new":  // show add form
                    req.getRequestDispatcher("emp-form.jsp").forward(req, resp);
                    break;

                case "edit": // show edit form
                    int id = Integer.parseInt(req.getParameter("id"));
                    Employee emp = dao.getEmpById(id);   // cần thêm trong QuanLiDAO

                    req.setAttribute("employee", emp);
                    req.getRequestDispatcher("emp-form.jsp").forward(req, resp);
                    break;

                case "delete":
                    int delId = Integer.parseInt(req.getParameter("id"));
                    dao.removeEmp(delId);   // cần thêm trong QuanLiDAO
                    resp.sendRedirect("employees");
                    break;

                case "list":
                    String deptIdStr = req.getParameter("deptId");
                    if (deptIdStr != null) {
                        int deptId = Integer.parseInt(deptIdStr);
                        req.setAttribute("employees", dao.getEmpByDeptId(deptId)); // ✅ dùng list
                        req.setAttribute("department", dao.getDeptById(deptId));
                    }
                    req.getRequestDispatcher("emp-list.jsp").forward(req, resp);
                    break;


            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            String name = req.getParameter("empName");
            double salary = Double.parseDouble(req.getParameter("salary"));
            int deptId = Integer.parseInt(req.getParameter("deptId"));

            Employee e = new Employee();
            e.setName(name);
            e.setSalary(salary);
            e.setDepartId(deptId);

            if (id == null || id.isEmpty()) {
                dao.addEmp(e);   // cần thêm trong QuanLiDAO
            } else {
                e.setId(Integer.parseInt(id));
                dao.updateEmp(e);  // cần thêm trong QuanLiDAO
            }
            resp.sendRedirect("employees");
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
