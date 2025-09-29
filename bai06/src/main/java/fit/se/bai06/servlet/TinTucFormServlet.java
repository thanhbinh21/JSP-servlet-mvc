package fit.se.bai06.servlet;

import fit.se.bai06.dao.DanhSachTinTucQuanLy;
import fit.se.bai06.model.TinTuc;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/addTinTuc")
public class TinTucFormServlet extends HttpServlet {
    private DanhSachTinTucQuanLy dao;

    @Resource(name = "jdbc/QUANLYDANHMUC")
    private DataSource dataSource;

    @Override
    public void init() {
        dao = new DanhSachTinTucQuanLy(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listdm", dao.getAllDanhMuc());
        req.getRequestDispatcher("/TinTucForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tieude = req.getParameter("tieude");
        String noidung = req.getParameter("noidung");
        String lienket = req.getParameter("lienket");
        String madmStr = req.getParameter("madm");

        // Validate cơ bản
        if (tieude == null || !tieude.matches(".{3,100}")) {
            req.setAttribute("error", "Tiêu đề phải từ 3–100 ký tự.");
            doGet(req, resp);
            return;
        }
        if (lienket == null || !lienket.matches("https?://.+")) {
            req.setAttribute("error", "Liên kết phải bắt đầu bằng http:// hoặc https://");
            doGet(req, resp);
            return;
        }

        int madm = Integer.parseInt(madmStr);
        TinTuc tin = new TinTuc(0, tieude, noidung, lienket, madm);
        dao.addTinTuc(tin);

        resp.sendRedirect(req.getContextPath() + "/listTinTuc?madm=" + madm);
    }
}
