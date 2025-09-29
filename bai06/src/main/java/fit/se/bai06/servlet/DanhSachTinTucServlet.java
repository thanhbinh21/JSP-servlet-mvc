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

@WebServlet("/listTinTuc")
public class DanhSachTinTucServlet extends HttpServlet {
    private DanhSachTinTucQuanLy dao;
    @Resource(name = "jdbc/QUANLYDANHMUC")
    private DataSource dataSource;
    @Override
    public void init(){
        dao = new DanhSachTinTucQuanLy(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("madm")==null){
            req.setAttribute("listDanhMuc",dao.getAllDanhMuc());
            try {
                req.setAttribute("listTinTuc",dao.getAllTinTuc());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            req.getRequestDispatcher("/DanhSachTinTuc.jsp").forward(req,resp);
        }else{
            req.setAttribute("listTinTuc",dao.getTinTucByDanhMuc(Integer.parseInt(req.getParameter("madm"))));
            req.setAttribute("listDanhMuc",dao.getAllDanhMuc());
            req.getRequestDispatcher("/DanhSachTinTuc.jsp").forward(req,resp);

        }
    }
}
