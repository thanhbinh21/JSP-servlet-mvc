package fit.se.bai06.model;

import java.io.Serializable;

public class DanhMuc implements Serializable {
    private int id;
    private String name;
    private String nguoiQuanLy;
    private String ghiChu;

    public DanhMuc() {
    }

    public DanhMuc(int id, String name, String nguoiQuanLy, String ghiChu) {
        this.id = id;
        this.name = name;
        this.nguoiQuanLy = nguoiQuanLy;
        this.ghiChu = ghiChu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNguoiQuanLy() {
        return nguoiQuanLy;
    }

    public void setNguoiQuanLy(String nguoiQuanLy) {
        this.nguoiQuanLy = nguoiQuanLy;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
