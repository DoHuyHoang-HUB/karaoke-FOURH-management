package dao;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Phong;
import java.text.SimpleDateFormat;
import entity.TrangThaiHoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.jdbc.ReturningWork;
import service.HoaDonService;
import util.HibernateUtil;

public class HoaDon_DAO implements HoaDonService {

    List<HoaDon> dsPhieu = Collections.emptyList();
    private SessionFactory sessionFactory;

    public HoaDon_DAO() {
        HibernateUtil util = HibernateUtil.getInstance();
        this.sessionFactory = util.getSessionFactory();
    }

    /**
     * Thêm hóa đơn
     * @param hoaDon
     * @return true nếu thêm thành công, false nếu thất bại
     */
    @Override
    public boolean addHoaDon(HoaDon hoaDon) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            session.save(hoaDon);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    /**
     * 
     * @param hoaDon
     * @return 
     */
    @Override
    public boolean finishHoaDon(HoaDon hoaDon) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            session.update(hoaDon);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        } finally {
            session.close();
        }
        return false;
    }

    /**
     * Lấy danh sách tất cả các hóa đơn theo numPage và mã nhân viên
     * @param numPage: số dòng của trang
     * @param maNhanVien: mã nhân viên của nhân viên lập hóa đơn
     * @return danh sách hóa đơn phù hợp
     */
    @Override
    public List<HoaDon> getDsHoaDon(int numPage, String maNhanVien) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select h.* from HoaDon h where ISDATE(h.ngayLapHoaDon) = 1 and  h.maNhanVien like '%"+maNhanVien+"%' order by h.ngayLapHoaDon desc offset :x row fetch next 20 rows only";
        try {
            tr.begin();
            List<HoaDon> dsHoaDon = session
                    .createNativeQuery(sql, HoaDon.class)
                    .setParameter("x", numPage*20)
                    .getResultList();
            tr.commit();
            return dsHoaDon;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }

    /**
     * Tìm kiếm hóa đơn theo mã
     * @param maHoaDon: mã hóa đơn
     * @return Hóa đơn có mã là maHoaDon
     */
    @Override
    public HoaDon getHoaDon(String maHoaDon) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            HoaDon hoaDon = session.find(HoaDon.class, maHoaDon);
            tr.commit();
            return hoaDon;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        return null;
    }

    /**
     * Tìm kiếm danh sách hóa đơn của nhân viên, từ ngày đến ngày
     * @param from từ ngày
     * @param to đến ngày
     * @param numPage số dòng lấy ra
     * @param maNhanVien mã nhân viên
     * @return danh sách hóa đơn phù hợp
     */
    @Override
    public List<HoaDon> getDSHoaDonFromDateToDate(String from, String to,int numPage, String maNhanVien) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select h.* from [dbo].[HoaDon] h\n"
                + "where ISDATE(h.ngayLapHoaDon) = 1 and  h.maNhanVien like '%"+maNhanVien+"%' and h.ngayLapHoaDon between CONVERT(date, '" + from + "') and CONVERT(date, '" + to + "')"
                + "order by h.ngayLapHoaDon desc offset :x row fetch next 20 rows only";
        try {
            tr.begin();
            List<HoaDon> dsHoaDon = session
                    .createNativeQuery(sql, HoaDon.class)
                    .setParameter("x", numPage*20)
                    .getResultList();
            tr.commit();
            return dsHoaDon;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }

    /**
     * Tìm hóa đơn theo tiêu chi nào dó
     * @param cot: tiêu chí tìm ( có thể là mã hóa đơn, ....)
     * @param duLieu: thông tin cần tìm
     * @param numPage: số dữ liệu lấy ra trước tiên
     * @param maNhanVien: mã nhân viên của nhân viên đang thao tác
     * @return những hóa đơn phù hợp với yêu cầu tìm kiếm
     */
    @Override
    public List<HoaDon> getDSHoaDonByTieuChiKhac(int cot, String duLieu,int numPage, String maNhanVien) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = null;
        if (cot==1){
            sql = "select  h.* from [dbo].[HoaDon] h where ISDATE(h.ngayLapHoaDon) = 1 and  h.maNhanVien like '%"+maNhanVien+"%' and h.maHoaDon like '%" + duLieu + "%' order by h.ngayLapHoaDon desc offset :x row fetch next 20 rows only";
        }else if(cot==2){
            sql = "select h.* from [dbo].[HoaDon] h join [dbo].[KhachHang] k on k.maKhachHang=h.maKhachHang where ISDATE(h.ngayLapHoaDon) = 1 and  h.maNhanVien like '%"+maNhanVien+"%' and k.tenKhachHang like N'%" + duLieu + "%' order by h.ngayLapHoaDon desc offset :x row fetch next 20 rows only";
        }else if(cot==3){
            sql = "select h.* from [dbo].[HoaDon] h join [dbo].[Phong] p on p.maPhong=h.maPhong where ISDATE(h.ngayLapHoaDon) = 1 and  h.maNhanVien like '%"+maNhanVien+"%' and p.tenPhong like N'%" + duLieu + "%' order by h.ngayLapHoaDon desc offset :x row fetch next 20 rows only";
        }
        try {
            tr.begin();
            List<HoaDon> dsHoaDon = session
                    .createNativeQuery(sql, HoaDon.class)
                    .setParameter("x", numPage*20)
                    .getResultList();
            tr.commit();
            return dsHoaDon;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }

    /**
     * Lấy ra ngày lập hóa đơn nhỏ nhất trong tất cả các hóa đơn
     * @return ngày lập hóa đơn nhỏ nhất
     */
    @Override
    public String layNgayLapNhoNhat() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = " select convert(varchar,MIN(ngayLapHoaDon),23) from [dbo].[HoaDon] where ISDATE([ngayLapHoaDon]) = 1";
        try {
            tr.begin();
            String ngayNho = (String) session.createNativeQuery(sql).uniqueResult();
            tr.commit();
            return ngayNho;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }

    /**
     * Lấy ra ngày lập hóa đơn lớn nhất của tất cả các hóa đơn
     * @return ngày lập hóa đơn lớn nhất
     */
    @Override
    public String layNgayLapLonNhat() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select convert(varchar,MAX(ngayLapHoaDon),23) from [dbo].[HoaDon] where ISDATE([ngayLapHoaDon]) = 1";
        try {
            tr.begin();
            String ngayLon = (String) session.createNativeQuery(sql).uniqueResult();
            tr.commit();
            return ngayLon;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }

    /**
     * Lọc hóa đơn theo tháng - quý - name tùy chọn
     * @param from: từ ngày
     * @param to: đến ngày
     * @param thang: tháng
     * @param quy: quý
     * @param nam: năm
     * @param numPage: số  dòng dữ liệu hiện trnag đầu
     * @param maNhanVien: mã nhân viên của nhân viên đang thao tác
     * @return lọc hóa đon phù hợp với yêu cầu
     */
    @Override
    public List<HoaDon> locHoaDonByThang_Quy_Nam(String from, String to, String thang, String quy, String nam,int numPage, String maNhanVien) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select * from [dbo].[HoaDon] where ISDATE(ngayLapHoaDon) = 1 and  maNhanVien like '%"+maNhanVien+"%' and (convert(date,ngayLapHoaDon) between CONVERT(date, '" + from + "') and CONVERT(date, '" + to + "')) and (DATEPART(year, ngayLapHoaDon) like '%" + nam + "%' and DATEPART(quarter, ngayLapHoaDon) like '%" + quy + "%' and DATEPART(month, ngayLapHoaDon) like '%" + thang + "%')"
                + "order by ngayLapHoaDon desc offset :x row fetch next 20 rows only";
        try {
            tr.begin();
            List<HoaDon> dsHoaDon = session
                    .createNativeQuery(sql, HoaDon.class)
                    .setParameter("x", numPage*20)
                    .getResultList();
            tr.commit();
            return dsHoaDon;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }

    /**
     * Lấy ra toàn bộ tháng trong ngày lập hóa đơn của toàn bộ hóa dơn
     * @return 
     */
    @Override
    public List<Integer> getDSThangTheoNgayLap(int quy) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql =null;
        if(quy==0){
            sql = "select DISTINCT(DATEPART(month, ngayLapHoaDon)) from [dbo].[HoaDon] where ISDATE([ngayLapHoaDon]) = 1 order by DATEPART(month, ngayLapHoaDon)";
        }else{
            sql = "select DISTINCT(DATEPART(month, ngayLapHoaDon)) from [dbo].[HoaDon] where ISDATE([ngayLapHoaDon]) = 1 and DATEPART(QUARTER, ngayLapHoaDon)= "+quy+" order by DATEPART(month, ngayLapHoaDon)";
        }
        
        try {
            tr.begin();
            List<Integer> dsThang = session
                    .createNativeQuery(sql)
                    .getResultList();
            tr.commit();
            return dsThang;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }

    /**
     *  Lấy ra toàn bộ năm trong ngày lập hóa đơn của toàn bộ hóa dơn
     * @return 
     */
    @Override
    public List<Integer> getDSNamTheoNgayLap() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select DISTINCT(DATEPART(year, ngayLapHoaDon)) from [dbo].[HoaDon] where ISDATE([ngayLapHoaDon]) = 1 order by DATEPART(year, ngayLapHoaDon)";
        try {
            tr.begin();
            List<Integer> dsThang = session
                    .createNativeQuery(sql)
                    .getResultList();
            tr.commit();
            return dsThang;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }

    /**
     *  Lấy ra toàn bộ quý trong ngày lập hóa đơn của toàn bộ hóa dơn
     * @return 
     */
    @Override
    public List<Integer> getDSQuyTheoNgayLap() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select DISTINCT(DATEPART(quarter, ngayLapHoaDon)) from [dbo].[HoaDon] where ISDATE([ngayLapHoaDon]) = 1 order by DATEPART(quarter, ngayLapHoaDon)";
        try {
            tr.begin();
            List<Integer> dsThang = session
                    .createNativeQuery(sql)
                    .getResultList();
            tr.commit();
            return dsThang;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();
        return null;
    }
    
    /**
     * tính số lượng hóa đơn của nhân viên có mã truyền vào
     * @param maNhanvien: mã nhân viên
     * @return số lượng hóa đơn của nhân viên đó
     */
    @Override
    public int getSoLuongHoaDon(String maNhanvien) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select count(*) from HoaDon where ISDATE([ngayLapHoaDon]) = 1 and  maNhanVien like '%"+maNhanvien+"%'";
        try {
            tr.begin();
            int rs = (int) session.
                    createNativeQuery(sql)
                    .getSingleResult();
            tr.commit();
            return  rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return 0;
    }

    /**
     * tính số lượng hóa đơn của nhân viên có mã truyền vào theo tiêu chí nào đó
     * @param cot: tiêu chí tìm kiếm
     * @param duLieu: dữ liệu tìm kiếm theo tiêu chí
     * @param maNhanVien: mã nhân viên
     * @return  số lượng hóa đơn
     */
    @Override
    public int getSoLuongHoaDonByTieuChiKhac(int cot, String duLieu, String maNhanVien) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = null;
        if(cot==1){
            sql ="select count(*) from [dbo].[HoaDon] h where ISDATE(h.ngayLapHoaDon) = 1 and h.maHoaDon like '%" + duLieu + "%' and  h.maNhanVien like '%"+maNhanVien+"%'";
        }else if(cot==2){
            sql = "select count(*) from [dbo].[HoaDon] h join [dbo].[KhachHang] k on k.maKhachHang=h.maKhachHang where ISDATE(h.ngayLapHoaDon) = 1 and k.tenKhachHang like N'%" + duLieu + "%' and  h.maNhanVien like '%"+maNhanVien+"%'";
        }else if(cot==3){
            sql = "select count(*) from [dbo].[HoaDon] h join [dbo].[Phong] p on p.maPhong=h.maPhong where ISDATE(h.ngayLapHoaDon) = 1 and p.tenPhong like N'%" + duLieu + "%'and  h.maNhanVien like '%"+maNhanVien+"%'";
        }
        try {
            tr.begin();
            int rs = (int) session.
                    createNativeQuery(sql)
                    .getSingleResult();
            tr.commit();
            return  rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return 0;
    }

    /**
     * tính số lượng hóa đơn của nhân viên có mã truyền vào từ ngày nào đó đến ngày khác
     * @param from: từ ngày
     * @param to: đến ngày
     * @param maNhanVien: mã nhân viên
     * @return số lượng hóa đơn
     */
    @Override
    public int getSoLuongHoaDonFromDateToDate(String from, String to, String maNhanVien) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select count(*) from [dbo].[HoaDon] h where ISDATE(h.ngayLapHoaDon) = 1 and  h.maNhanVien like '%"+maNhanVien+"%' and h.ngayLapHoaDon between CONVERT(date, '" + from + "') and CONVERT(date, '" + to + "')";
        try {
            tr.begin();
            int rs = (int) session.
                    createNativeQuery(sql)
                    .getSingleResult();
            tr.commit();
            return  rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return 0;
    }

    /**
     * Tính số lượng hóa đơn của nhân viên có mã truyền vào theo tất cả thuộc tính tìm kiếm
     * @param from: từ ngày
     * @param to: đến ngày
     * @param thang: tháng
     * @param quy: quý
     * @param nam: năm
     * @param maNhanVien: mã nhân viên
     * @return  số lượng hóa đơn
     */
    @Override
    public int getSoLuongHoaDonByAll(String from, String to, String thang, String quy, String nam, String maNhanVien) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select count(*) from [dbo].[HoaDon] where ISDATE(ngayLapHoaDon) = 1 and  maNhanVien like '%"+maNhanVien+"%' and (convert(date,ngayLapHoaDon) between CONVERT(date, '" + from + "') and CONVERT(date, '" + to + "')) and (DATEPART(year, ngayLapHoaDon) like '%" + nam + "%' and DATEPART(quarter, ngayLapHoaDon) like '%" + quy + "%' and DATEPART(month, ngayLapHoaDon) like '%" + thang + "%')";
        try {
            tr.begin();
            int rs = (int) session.
                    createNativeQuery(sql)
                    .getSingleResult();
            tr.commit();
            return  rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return 0;
    }
    
    /**
     * Câp nhật trạng thái phiếu là Hết Hạn cho những phiếu có trạng thái Đang Đợi đã quá hạn đặt
     */
    @Override
    public void capNhatTrangThaiPhieuHetHan() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "update PhieuDatPhong set trangThai = 'HET_HAN' where ngayDat > GETDATE()  and trangThai =  'DANG_DOI'";
        try {
            tr.begin();
                session.createNativeQuery(sql).executeUpdate();  
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
    }
    
//    Huu
    @Override
    public boolean insertCTHoaDon(ChiTietHoaDon ctHoaDon) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
                session.save(ctHoaDon);  
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }
    @Override
    public boolean updateCTHoaDon(ChiTietHoaDon ctHoaDon) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql ="";
        if(ctHoaDon.getSoLuong() >0){
            sql = "update ChiTietHoaDon set soLuong = "+ctHoaDon.getSoLuong()+",thanhTien = "+ctHoaDon.getThanhTien()
                +"where maHoaDon = '"+ctHoaDon.getHoaDon().getMaHoaDon()+"' "
                + "and maMatHang = '" +ctHoaDon.getMatHang().getMaMatHang()+"' ";
        }else{
            sql = "delete ChiTietHoaDon"
                +" where maHoaDon = '"+ctHoaDon.getHoaDon().getMaHoaDon()+"' "
                + "and maMatHang = '"+ctHoaDon.getMatHang().getMaMatHang()+"'";
        }
        
        try {
            tr.begin();
                session.createNativeQuery(sql).executeUpdate();  
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    @Override
    public HoaDon getHoaDon(Phong phong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "select top 1 * from HoaDon where maPhong = '"+phong.getMaPhong()+"' order by maHoaDon desc";
        try {
            tr.begin();
                HoaDon hoadon= session.createNativeQuery(sql,HoaDon.class).getSingleResult();
            tr.commit();
            return hoadon;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }

    @Override
    public boolean updateHoaDon(HoaDon hoaDon,String gioHat,double tongTienPhong,double tongTien,double tongTienMatHang) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        SimpleDateFormat gio = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        String ngayLapHoaDon = gio.format(date);
        
        String sql = "update HoaDon "
                + "set donGiaPhong = "+tongTienPhong+","
                + "gioHat = '"+gioHat+"',"
                + "ngayLapHoaDon = CAST(N'"+ngayLapHoaDon+"' AS datetime)"
                + ",thoiGianKetThuc = CAST(N'"+ngayLapHoaDon+"' AS datetime)"
                + ",tongHoaDon = "+tongTien
                + ",tongTienMatHang = "+tongTienMatHang+" where maHoaDon = '"+hoaDon.getMaHoaDon()+"' ";
        try {
            tr.begin();
                session.createNativeQuery(sql).executeUpdate();  
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        
        return false;
    }
    
    @Override
    public String getlastMaHoaDonTang() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "select top 1 maHoaDon from HoaDon order by maHoaDon desc";
        
        try {
            tr.begin();
                String maHoaDon = "";
                String maCuoiCung = "HD";

                try {
                    maHoaDon = (String)session.createNativeQuery(sql).uniqueResult(); 
                    int so = Integer.parseInt(maHoaDon.split("HD")[1]) + 1;
                int soChuSo = String.valueOf(so).length();

                for (int i = 0; i< 7-soChuSo; i++){
                    maCuoiCung += "0";
                }
                maCuoiCung += String.valueOf(so);
                } catch (Exception e) {
                    maCuoiCung = "HD0000001";
                }      
            tr.commit();
            return maCuoiCung;
        } catch (Exception e) {
            e.printStackTrace();
             tr.rollback();
        }
        return null;
    }
    @Override
    public String getMaxID() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "select max(maHoaDon) from HoaDon";

        try {
            tr.begin();
            String id = (String) session
                    .createNativeQuery(sql)
                    .getSingleResult();
            tr.commit();
            return id;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
    
    @Override
    public boolean updateHoaDonDoiPhong(HoaDon hoaDon) {
//        String sql = "update HoaDon set maPhong = :x"
//                +",gioHat = '00:00', "
//                + ",donGiaPhongCu = :y"
//                + ",thoiGianBatDau = :z"
//                + ",thoiGianKetThuc = :a"
//                + " where maHoaDon = :b";
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
                session.update(hoaDon);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }
    
    @Override
    public List<HoaDon> findHoaDon(String batDau, String ketThuc,String ma) {
        String sql= "";
        if(ma != null){
            sql = "select * from HoaDon as hd join Phong as p on hd.maPhong = p.maPhong WHERE ngayLapHoaDon BETWEEN  '"+batDau+"' and '"+ketThuc+"' and p.maLoaiPhong = '"+ma+"'";
        }else{
            sql = "select * from HoaDon WHERE ngayLapHoaDon BETWEEN  '"+batDau+"' and '"+ketThuc+"'";
        }
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
                List<HoaDon> dsHoaDon = null;
                try {
                    dsHoaDon = session.createNativeQuery(sql,HoaDon.class).getResultList();
                } catch (Exception e) {
                    return null;
                }
            tr.commit();
            return dsHoaDon;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        
        return null;
    }
    
//    select *  from HoaDon where DATEPART(DAY, ngayLapHoaDon) = 9

    @Override
    public List<HoaDon> findHoaDonByThangNam(int thangOrNam,String loaiPhong,Boolean thang,int year) {
        
        String sql ="";
        
        if(loaiPhong !=null){
            if(thang == true){
                sql = "select *  from HoaDon as hd join Phong as p on hd.maPhong = p.maPhong  where DATEPART(MONTH, ngayLapHoaDon)= "+thangOrNam+" and DATEPART(YEAR, ngayLapHoaDon) = "+year+" and p.maLoaiPhong = '"+loaiPhong+"'";
            }else{
                sql = "select *  from HoaDon as hd join Phong as p on hd.maPhong = p.maPhong  where DATEPART(YEAR, ngayLapHoaDon)= "+thangOrNam+" and p.maLoaiPhong = '"+loaiPhong+"'";
                
            }
        }else{
            if(thang == true){
                sql= "select *  from HoaDon where DATEPART(MONTH, ngayLapHoaDon) = "+thangOrNam;
            }else{
                sql= "select *  from HoaDon where DATEPART(YEAR, ngayLapHoaDon) = "+thangOrNam;
            }
        }
        
        
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
                List<HoaDon> dsHoaDon = null;
                try {
                    dsHoaDon = session.createNativeQuery(sql,HoaDon.class).getResultList();
                } catch (Exception e) {
                    return null;
                }
            tr.commit();
            return dsHoaDon;
        } catch (Exception e) {
            tr.rollback();
        }
        
        return null;
    }

    @Override
    public boolean insertHoaDon(HoaDon hoaDon) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
                session.save(hoaDon);
            tr.commit();
            session.clear();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }
    @Override
    public HoaDon getHoaDonByIdPhong(String id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "select * from HoaDon where maPhong = '" + id + "' and trangThai = 'DANG_XU_LY'";

        try {
            tr.begin();
            HoaDon hoaDon = session
                    .createNativeQuery(sql, HoaDon.class)
                    .getSingleResult();
            tr.commit();
            return hoaDon;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public Map<Integer, Double> getDoanhThuHoaDonTheoNam(int nam) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "exec doanhThuTheoNam ?";

        try {
            tr.begin();
            Map<Integer, Double> rs = session.doReturningWork(new ReturningWork<Map<Integer, Double>>() {
                @Override
                public Map<Integer, Double> execute(Connection arg0) throws SQLException {
                    PreparedStatement st = null;
                    Map<Integer, Double> map = new HashMap<>();
                    try {
                        st = arg0.prepareStatement(sql);
                        st.setInt(1, nam);
                        ResultSet rs = st.executeQuery();
                        while (rs.next()) {                            
                            map.put(rs.getInt("thang"), rs.getDouble("tongTien"));
                        }
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    return map;
                }
            });
            tr.commit();
            return rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public Map<Integer, Double> getDoanhThuHoaDonTheoThang(int thang, int nam) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        String sql = "exec doanhThuTheoThang ?, ?";
        try {
            tr.begin();
            Map<Integer, Double> rs = session.doReturningWork(new ReturningWork<Map<Integer, Double>>() {
                @Override
                public Map<Integer, Double> execute(Connection arg0) throws SQLException {
                    PreparedStatement st = null;
                    Map<Integer, Double> map = new HashMap<>();
                    try {
                        st = arg0.prepareStatement(sql);
                        st.setInt(1, thang);
                        st.setInt(2, nam);
                        ResultSet rs = st.executeQuery();
                        while (rs.next()) {   
                            map.put(rs.getInt("ngay"), rs.getDouble("tongTien"));
                        }
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    return map;
                }
            });
            tr.commit();
            return rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    
}
