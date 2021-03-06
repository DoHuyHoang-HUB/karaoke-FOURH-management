package entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
/**
 * @author Bùi Quang Hữu
 */
@Entity
public class LoHang {

    @Id
    private String maLoHang;
    @ManyToOne
    @JoinColumn(name = "maNCC", nullable = false)
    private NhaCungCap nhaCungCap;
    private Date ngayNhap;
    @ManyToOne
    @JoinColumn(name = "maNhanVien", nullable = false)
    private NhanVien nguoiNhap;
    @OneToMany(mappedBy = "loHang")
    private List<ChiTietNhapHang> dsChiTietNhapHang;
    @Column(columnDefinition = "money")
    private double tongTien;

    /**
     */
    public LoHang() {
        this.ngayNhap = Date.valueOf(LocalDate.now());
        this.dsChiTietNhapHang = new ArrayList<>();
    }
    /**
     * @param maLoHang
     * @param nhaCungCap
     * @param ngayNhap
     * @param nguoiNhap 
     */
    public LoHang(String maLoHang, NhaCungCap nhaCungCap, Date ngayNhap, NhanVien nguoiNhap) {
        this.maLoHang = maLoHang;
        this.nhaCungCap = nhaCungCap;
        this.ngayNhap = ngayNhap;
        this.nguoiNhap = nguoiNhap;
        this.dsChiTietNhapHang = new ArrayList<>();
        this.tongTien = getTongTien();
    }

    /**
     * @param maLoHang
     * @param nguoiNhap
     */
    public LoHang(String maLoHang, NhanVien nguoiNhap) {
        this.maLoHang = maLoHang;
        this.nguoiNhap = nguoiNhap;
        this.ngayNhap = Date.valueOf(LocalDate.now());
        this.dsChiTietNhapHang = new ArrayList<>();
        this.tongTien = getTongTien();
    }

    /**
     *
     * @param matHang
     * @param soLuongNhap
     * @param giaNhap
     */
    public void themCT_NhapHang(MatHang matHang, int soLuongNhap, double giaNhap) {
        ChiTietNhapHang chiTietNhapHang = new ChiTietNhapHang(this, matHang, soLuongNhap, giaNhap);
        dsChiTietNhapHang.add(chiTietNhapHang);
    }

    /**
     * @return the maLoHang
     */
    public String getMaLoHang() {
        return maLoHang;
    }

    /**
     * @param maLoHang the maLoHang to set
     */
    public void setMaLoHang(String maLoHang) {
        this.maLoHang = maLoHang;
    }

    /**
     * @return the nhaCungCap
     */
    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    /**
     * @param nhaCungCap the nhaCungCap to set
     * @throws java.lang.Exception
     */
    public void setNhaCungCap(NhaCungCap nhaCungCap) throws Exception {
        if(nhaCungCap != null) {
            this.nhaCungCap = nhaCungCap;
        } else {
            throw new Exception("Nhà cung cấp không được rỗng");
        }
    }

    /**
     * @return the ngayNhap
     */
    public Date getNgayNhap() {
        return ngayNhap;
    }

    /**
     * @param ngayNhap the ngayNhap to set
     * @throws java.lang.Exception
     */
    public void setNgayNhap(Date ngayNhap) throws Exception {
        if(ngayNhap != null) {
            this.ngayNhap = ngayNhap;
        } else {
            throw new Exception("Ngày nhập không được rỗng");
        }
    }

    /**
     * @return the nguoiNhap
     */
    public NhanVien getNguoiNhap() {
        return nguoiNhap;
    }

    /**
     * @param nguoiNhap the nguoiNhap to set
     * @throws java.lang.Exception
     */
    public void setNguoiNhap(NhanVien nguoiNhap) throws Exception {
        if(nguoiNhap != null) {
            this.nguoiNhap = nguoiNhap;
        } else {
            throw new Exception("Người nhập không được rỗng");
        }
    }

    /**
     * @return the dsChiTietNhapHang
     */
    public List<ChiTietNhapHang> getDsChiTietNhapHang() {
        return dsChiTietNhapHang;
    }

    /**
     * @param dsChiTietNhapHang the dsChiTietNhapHang to set
     */
    public void setDsChiTietNhapHang(List<ChiTietNhapHang> dsChiTietNhapHang) {
        this.dsChiTietNhapHang = dsChiTietNhapHang;
    }

    /**
     * @return the tongTien
     */
    public double getTongTien() {
        double tong = 0.0;
        for (ChiTietNhapHang chiTietNhapHang : dsChiTietNhapHang) {
            tong += chiTietNhapHang.getThanhTien();
        }
        return tong;
    }

    @Override
    public String toString() {
        return "LoHang [maLoHang=" + maLoHang + ", nhaCungCap=" + nhaCungCap + ", ngayNhap=" + ngayNhap + ", nguoiNhap="
                + nguoiNhap + ", dsChiTietNhapHang=" + dsChiTietNhapHang + ", tongTien=" + tongTien + "]";
    }
}
