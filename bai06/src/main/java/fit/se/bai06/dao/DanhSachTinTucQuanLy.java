package fit.se.bai06.dao;

import fit.se.bai06.model.DanhMuc;
import fit.se.bai06.model.TinTuc;
import fit.se.bai06.util.DBUtil;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DanhSachTinTucQuanLy implements Serializable {
    private DBUtil dbUtil;

    public DanhSachTinTucQuanLy(DataSource dataSource) {
        this.dbUtil = new DBUtil(dataSource);
    }

    // Lấy tất cả danh mục
    public List<DanhMuc> getAllDanhMuc() {
        String sql = "SELECT * FROM DANHMUC";
        List<DanhMuc> list = new ArrayList<>();
        try (Connection con = dbUtil.getDataSource().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                DanhMuc dm = new DanhMuc();
                dm.setId(rs.getInt("MADM"));
                dm.setName(rs.getString("TENDANHMUC"));
                dm.setNguoiQuanLy(rs.getString("NGUOIQUANLY"));
                dm.setGhiChu(rs.getString("GHICHU"));
                list.add(dm);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    // Lấy tin tức theo danh mục
    public List<TinTuc> getTinTucByDanhMuc(int madm) {
        String sql = "SELECT * FROM TINTUC WHERE MADM = ?";
        List<TinTuc> list = new ArrayList<>();
        try (Connection con = dbUtil.getDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, madm);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TinTuc tt = new TinTuc(
                            rs.getInt("MATT"),
                            rs.getString("TIEUDE"),
                            rs.getString("NOIDUNGTT"),
                            rs.getString("LIENKET"),
                            madm
                    );
                    list.add(tt);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<TinTuc> getAllTinTuc() throws Exception {
        List<TinTuc> list = new java.util.ArrayList<>();
        String sql = "SELECT * FROM TINTUC";
        try(
                Connection conn = dbUtil.getDataSource().getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ){
            while(rs.next()){

                int matt = rs.getInt("MATT");
                String tieude = rs.getString("TIEUDE");
                String noidungtt = rs.getString("NOIDUNGTT");
                String lienket = rs.getString("LIENKET");
                int madm = rs.getInt("MADM");
                TinTuc tt = new TinTuc(matt, tieude, noidungtt, lienket, madm);
                list.add(tt);

            }
            return list;
        }
    }

    // Thêm tin tức
    public void addTinTuc(TinTuc tt) {
        String sql = "INSERT INTO TINTUC (TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUES (?,?,?,?)";
        try (Connection con = dbUtil.getDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tt.getTitle());
            ps.setString(2, tt.getNoiDung());
            ps.setString(3, tt.getLienKet());
            ps.setInt(4, tt.getMaDM());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Xóa tin tức
    public void removeTinTuc(int id) {
        String sql = "DELETE FROM TINTUC WHERE MATT = ?";
        try (Connection con = dbUtil.getDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
