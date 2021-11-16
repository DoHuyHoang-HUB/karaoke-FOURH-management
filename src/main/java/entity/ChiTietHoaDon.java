package entity;

import java.text.DecimalFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import objectcombobox.ObjectComboBox;

@Entity
@IdClass(ChiTietHoaDon_PK.class)
public class ChiTietHoaDon {
	@Id
	@ManyToOne
	@JoinColumn(name = "maHoaDon", nullable = false)
	private HoaDon hoaDon;
	@Id
	@ManyToOne
	@JoinColumn(name = "maMatHang", nullable = false)
	private MatHang matHang;
	@Column(nullable = false)
	private int soLuong;
	private float chietKhau;
	@Column(columnDefinition = "money")
	private double thanhTien;
	/**
	 * @param matHang
	 * @param soLuong
	 * @param chietKhau
	 */
	public ChiTietHoaDon(MatHang matHang, int soLuong, float chietKhau) {
		this.matHang = matHang;
		this.soLuong = soLuong;
		this.chietKhau = chietKhau;
	}
        
        public ChiTietHoaDon(HoaDon hoaDon,MatHang matHang, int soLuong, float chietKhau) {
                this.hoaDon = hoaDon;
		this.matHang = matHang;
		this.soLuong = soLuong;
		this.chietKhau = chietKhau;
                this.thanhTien = getThanhTien();
	}
	/**
	 * 
	 */
	public ChiTietHoaDon() {
	}
	/**
	 * @return the hoaDon
	 */
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	/**
	 * @param hoaDon the hoaDon to set
	 */
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	/**
	 * @return the matHang
	 */
	public MatHang getMatHang() {
		return matHang;
	}
	/**
	 * @param matHang the matHang to set
	 */
	public void setMatHang(MatHang matHang) {
		this.matHang = matHang;
	}
	/**
	 * @return the soLuong
	 */
	public int getSoLuong() {
		return soLuong;
	}
	/**
	 * @param soLuong the soLuong to set
	 */
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	/**
	 * @return the chietKhau
	 */
	public float getChietKhau() {
		return chietKhau;
	}
	/**
	 * @param chietKhau the chietKhau to set
	 */
	public void setChietKhau(float chietKhau) {
		this.chietKhau = chietKhau;
	}
	/**
	 * @return the thanhTien
	 */
	public double getThanhTien() {
		return soLuong * matHang.getDonGia() * (1 - chietKhau);
	}
	@Override
	public String toString() {
		return "ChiTietHoaDon [matHang=" + matHang + ", soLuong=" + soLuong + ", chietKhau=" + chietKhau
				+ ", thanhTien=" + thanhTien + "]";
	}
        
        public Object[] convertToRowTableInGDLapHoaDon(){
            DecimalFormat df;
            df = new DecimalFormat("#,##0.00");
            return new Object[]{ new ObjectComboBox(matHang.getTenMatHang(),matHang.getMaMatHang()), soLuong,df.format(matHang.getDonGia()) ,getThanhTien()};
        }
}
