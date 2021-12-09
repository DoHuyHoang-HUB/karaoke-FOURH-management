/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.PhieuDatPhong;
import java.util.List;

/**
 *
 * @author Hao
 */
public interface PhieuDatPhongService {
    public boolean addPhieuDatPhong(PhieuDatPhong phieuDatPhong);
    public List<PhieuDatPhong> getDsPhieuDatPhong(int numPage);
    public List<String> getDSTrangThaiPhieu();
    public PhieuDatPhong getPhieuDatPhong(String maPhieuDat);
    public List<PhieuDatPhong> timDSPhieuDatPhongByAllProperty(String tenPhong, String tenKhachHang, String trangThai, String ngayDat, int numPage);
    public boolean capNhatTrangThaiPhieu(String maPhieu);
    public void capNhatTrangThaiHuy();
    public boolean capNhatPhieuDatPhong(PhieuDatPhong phieuDatPhong);
    public String getMaxID();
    
//    Huu
    public boolean updatePhieuDatPhong(String maPhieu);
    public boolean updatePhieuDatPhong(PhieuDatPhong maPhieu);
    public List<PhieuDatPhong> getPhieuHomNay(String maPhong);
    public PhieuDatPhong getPhieuById(String maPhieuDatPhong);
//    public boolean addPhieuDatPhong(PhieuDatPhong phieu,String ngayDat);
    public double getTienCoc(String maPhieuDat);
    public PhieuDatPhong getPhieuCuaPhong(String maKhachhang);
    public int getSoLuongPhieuDatPhong();
    public int getSoLuongPhieuDatPhongByAllProperty(String tenPhong, String tenKhachHang, String trangThai, String ngayDat);
}
