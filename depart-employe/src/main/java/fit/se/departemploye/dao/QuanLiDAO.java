package fit.se.departemploye.dao;

import fit.se.departemploye.model.Department;
import fit.se.departemploye.model.Employee;
import fit.se.departemploye.util.DBUtil;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuanLiDAO implements Serializable {
    private DBUtil dbUtil;

    public QuanLiDAO(DataSource dataSource) {
        this.dbUtil = new DBUtil(dataSource);
    }

    // ================= DEPARTMENT =================
    public List<Department> getAllDept() {
        String sql = "SELECT * FROM departments";
        List<Department> list = new ArrayList<>();
        try (Connection connection = dbUtil.getDataSource().getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Department d = new Department();
                d.setId(rs.getInt("dept_id"));
                d.setName(rs.getString("dept_name"));
                list.add(d);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Department getDeptById(int id) {
        String sql = "SELECT * FROM departments WHERE dept_id = ?";
        try (Connection connection = dbUtil.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Department d = new Department();
                    d.setId(rs.getInt("dept_id"));
                    d.setName(rs.getString("dept_name"));
                    return d;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void addDept(Department d) throws SQLException {
        String sql = "INSERT INTO departments (dept_name) VALUES(?)";
        try (Connection connection = dbUtil.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, d.getName());
            ps.executeUpdate();
        }
    }

    public void updateDept(Department d) throws SQLException {
        String sql = "UPDATE departments SET dept_name=? WHERE dept_id=?";
        try (Connection connection = dbUtil.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, d.getName());
            ps.setInt(2, d.getId());
            ps.executeUpdate();
        }
    }

    public void removeDept(int id) throws SQLException {
        String sql = "DELETE FROM departments WHERE dept_id=?";
        try (Connection connection = dbUtil.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Department> searchDeptByName(String key) {
        String sql = "SELECT * FROM departments WHERE dept_name LIKE ?";
        List<Department> list = new ArrayList<>();
        try (Connection connection = dbUtil.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + key + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Department d = new Department();
                    d.setId(rs.getInt("dept_id"));
                    d.setName(rs.getString("dept_name"));
                    list.add(d);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    // ================= EMPLOYEE =================
    public List<Employee> getAllEmp() {
        String sql = "SELECT * FROM employees";
        List<Employee> list = new ArrayList<>();
        try (Connection connection = dbUtil.getDataSource().getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("emp_id"));
                e.setName(rs.getString("emp_name"));
                e.setSalary(rs.getDouble("salary"));
                e.setDepartId(rs.getInt("dept_id"));
                list.add(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Employee getEmpById(int id) {
        String sql = "SELECT * FROM employees WHERE emp_id = ?";
        try (Connection connection = dbUtil.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Employee e = new Employee();
                    e.setId(rs.getInt("emp_id"));
                    e.setName(rs.getString("emp_name"));
                    e.setSalary(rs.getDouble("salary"));
                    e.setDepartId(rs.getInt("dept_id"));
                    return e;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void addEmp(Employee e) throws SQLException {
        String sql = "INSERT INTO employees (emp_name, salary, dept_id) VALUES (?, ?, ?)";
        try (Connection connection = dbUtil.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, e.getName());
            ps.setDouble(2, e.getSalary());
            ps.setInt(3, e.getDepartId());
            ps.executeUpdate();
        }
    }

    public List<Employee> getEmpByDeptId(int deptId) throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE dept_id=?";
        try (Connection conn = dbUtil.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, deptId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Employee e = new Employee();
                    e.setId(rs.getInt("emp_id"));
                    e.setName(rs.getString("emp_name"));
                    e.setSalary(rs.getDouble("salary"));
                    e.setDepartId(rs.getInt("dept_id"));
                    list.add(e);
                }
            }
        }
        return list;
    }


    public void updateEmp(Employee e) throws SQLException {
        String sql = "UPDATE employees SET emp_name=?, salary=?, dept_id=? WHERE emp_id=?";
        try (Connection connection = dbUtil.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, e.getName());
            ps.setDouble(2, e.getSalary());
            ps.setInt(3, e.getDepartId());
            ps.setInt(4, e.getId());
            ps.executeUpdate();
        }
    }

    public void removeEmp(int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE emp_id=?";
        try (Connection connection = dbUtil.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Employee> searchEmpByName(String key) {
        String sql = "SELECT * FROM employees WHERE emp_name LIKE ?";
        List<Employee> list = new ArrayList<>();
        try (Connection connection = dbUtil.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + key + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Employee e = new Employee();
                    e.setId(rs.getInt("emp_id"));
                    e.setName(rs.getString("emp_name"));
                    e.setSalary(rs.getDouble("salary"));
                    e.setDepartId(rs.getInt("dept_id"));
                    list.add(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
