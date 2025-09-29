package fit.se.bai06.model;

import java.io.Serializable;

public class TinTuc implements Serializable {
    private int id;
    private String title;
    private String noiDung;
    private String lienKet;
    private int maDM;

    public TinTuc() {
    }

    public TinTuc(int id, String title, String noiDung, String lienKet, int maDM) {
        this.id = id;
        this.title = title;
        this.noiDung = noiDung;
        this.lienKet = lienKet;
        this.maDM = maDM;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getLienKet() {
        return lienKet;
    }

    public void setLienKet(String lienKet) {
        this.lienKet = lienKet;
    }

    public int getMaDM() {
        return maDM;
    }

    public void setMaDM(int maDM) {
        this.maDM = maDM;
    }
}
