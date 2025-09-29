package fit.se.bai06.servlet;

import fit.se.bai06.dao.DanhSachTinTucQuanLy;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/deleteTinTuc")
public class DeleteTinTucServlet extends HttpServlet {
    private DanhSachTinTucQuanLy dao;

    @Resource(name = "jdbc/QUANLYDANHMUC")
    private DataSource dataSource;

    @Override
    public void init() {
        dao = new DanhSachTinTucQuanLy(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String madmStr = req.getParameter("madm");

        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            dao.removeTinTuc(id);
        }

        // Quay lại danh sách theo danh mục cũ
        if (madmStr != null) {
            resp.sendRedirect(req.getContextPath() + "/listTinTuc?madm=" + madmStr);
        } else {
            resp.sendRedirect(req.getContextPath() + "/listTinTuc");
        }
    }
}
