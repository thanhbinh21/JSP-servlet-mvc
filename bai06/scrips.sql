bookstorebookstoreCREATE DATABASE QUANLYDANHMUC;
USE QUANLYDANHMUC;

CREATE TABLE DANHMUC (
    MADM INT PRIMARY KEY AUTO_INCREMENT,
    TENDANHMUC VARCHAR(100) NOT NULL,
    NGUOIQUANLY VARCHAR(50),
    GHICHU TEXT
);quanlydanhmuc

CREATE TABLE TINTUC (
    MATT INT PRIMARY KEY AUTO_INCREMENT,
    TIEUDE VARCHAR(200) NOT NULL,
    NOIDUNGTT TEXT NOT NULL,
    LIENKET VARCHAR(500) NOT NULL,
    MADM INT,
    FOREIGN KEY (MADM) REFERENCES DANHMUC(MADM)
);

-- Dữ liệu mẫu
INSERT INTO DANHMUC (TENDANHMUC, NGUOIQUANLY, GHICHU) VALUES 
('Thời sự', 'Nguyễn Văn A', 'Tin tức thời sự trong nước và quốc tế'),
('Thể thao', 'Trần Thị B', 'Tin tức thể thao các môn'),
('Giải trí', 'Lê Văn C', 'Tin tức giải trí, điện ảnh');

INSERT INTO  TINTUC (TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUES 
('Tin thời sự 1', 'Nội dung tin thời sự số 1', 'http://example.com/tin1', 1),
('Tin thể thao 1', 'Nội dung tin thể thao số 1', 'http://example.com/tin2', 2);