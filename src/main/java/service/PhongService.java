package service;

import entity.LoaiPhong;
import entity.Phong;
import entity.TrangThaiPhong;
import java.util.List;

public interface PhongService {
    public boolean addPhong(Phong phong);
    public boolean updatePhong(Phong phong);
    public boolean deletePhong(String maPhong);
    public Phong getPhong(String maPhong);
    public List<Phong> getDsPhong();
    public int getSoLuongPhongTheoTrangThai(TrangThaiPhong trangThai);
    public List<Phong> getPhongByAttributes(int tang, String tenPhong, LoaiPhong loaiPhong, TrangThaiPhong trangThai);
    public List<Phong> getPhongBySDT(String sdt, int tang);
    
}
