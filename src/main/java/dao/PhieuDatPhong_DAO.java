package dao;

import entity.PhieuDatPhong;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.PhieuDatPhongService;
import util.HibernateUtil;

public class PhieuDatPhong_DAO implements PhieuDatPhongService {

    List<PhieuDatPhong> dsPhieu = new ArrayList<>();
    private SessionFactory sessionFactory;

    public PhieuDatPhong_DAO() {
        HibernateUtil util = HibernateUtil.getInstance();
        this.sessionFactory = util.getSessionFactory();
    }
    /**
     * Thêm một phiếu đặt phòng
     * @param phieuDatPhong
     * @return true: thêm thành công, false: thêm thất bại
     */
    @Override
    public boolean addPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            session.saveOrUpdate(phieuDatPhong);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }
    
    

    /**
     * Lấy tất cả các phiếu đặt phòng
     *
     * @param numPage: số dòng dữ liệu hiển thị lên trang đầu tiên
     * @return danh sách phiếu đặt phòng
     */
    @Override
    public List<PhieuDatPhong> getDsPhieuDatPhong(int numPage) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select p.* from PhieuDatPhong p order by p.maPhieuDat DESC offset :x row fetch next 20 rows only";
        try {
            tr.begin();
            List<PhieuDatPhong> dsPhieuDatPhong = session
                    .createNativeQuery(sql, PhieuDatPhong.class)
                    .setParameter("x", numPage * 20)
                    .getResultList();
            tr.commit();
            return dsPhieuDatPhong;
        } catch (Exception e) {
            tr.rollback();
        }
        session.close();
        return null;
    }

    /**
     * Lấy ra tất cả trạng thái của phiếu
     *
     * @return danh sách trạng thái phiếu
     */
    @Override
    public List<String> getDSTrangThaiPhieu() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select DISTINCT(p.trangThai) from PhieuDatPhong p";
        try {
            tr.begin();
            List<String> dsTrangThai = session
                    .createNativeQuery(sql)
                    .getResultList();
            tr.commit();
            return dsTrangThai;
        } catch (Exception e) {
            tr.rollback();
        }
        session.close();
        return null;
    }

    /**
     * Cập nhật thông tin phiếu đặt phòng
     *
     * @param phieuDatPhong thông tin phiếu đặt phòng mới
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    @Override
    public boolean capNhatPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            session.update(phieuDatPhong);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    /**
     * Tìm những phiếu đặt phòng thỏa thông tin truyền vào dưới đây
     *
     * @param tenPhong: tên phòng
     * @param tenKhachHang: tên khách hàng
     * @param trangThai: trạng thái
     * @param ngayDat: ngày đặt
     * @param numPage: số dòng dữ liệu hiển thị lên trang đầu tiên
     * @return danh sách phiếu đặt phòng
     */
    @Override
    public List<PhieuDatPhong> timDSPhieuDatPhongByAllProperty(String tenPhong, String tenKhachHang, String trangThai, String ngayDat, int numPage) {
        String sql;
        if (ngayDat == null) {
            sql = "SELECT PhieuDatPhong.* FROM KhachHang JOIN PhieuDatPhong ON KhachHang.maKhachHang = PhieuDatPhong.maKhachHang JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong \n"
                    + "where Phong.tenPhong like '%" + tenPhong + "%' and KhachHang.tenKhachHang like N'%" + tenKhachHang + "%' and PhieuDatPhong.trangThai like '%" + trangThai + "%'"
                    + "order by PhieuDatPhong.maPhieuDat DESC offset :x row fetch next 20 rows only";
        } else {
            sql = "SELECT PhieuDatPhong.* FROM KhachHang JOIN PhieuDatPhong ON KhachHang.maKhachHang = PhieuDatPhong.maKhachHang JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong \n"
                    + "where Phong.tenPhong like '%" + tenPhong + "%' and KhachHang.tenKhachHang like N'%" + tenKhachHang + "%' and PhieuDatPhong.trangThai like '%" + trangThai + "%' and CONVERT(date, ngayDat) = CONVERT(date, '" + ngayDat + "') "
                    + "order by PhieuDatPhong.maPhieuDat DESC offset :x row fetch next 20 rows only";
        }
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            dsPhieu = session
                    .createNativeQuery(sql, PhieuDatPhong.class)
                    .setParameter("x", numPage * 20)
                    .getResultList();
            tr.commit();
            return dsPhieu.isEmpty() ? dsPhieu = new ArrayList<>() : dsPhieu;
        } catch (Exception e) {
            tr.rollback();
        }
        session.close();
        return null;
    }
    /**
     * Lấy phiếu đặt phòng theo mã phiếu
     * @param maPhieuDat
     * @return phieuDatPhong
     */
    @Override
    public PhieuDatPhong getPhieuDatPhong(String maPhieuDat) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            PhieuDatPhong phieu = session.find(PhieuDatPhong.class, maPhieuDat);
            tr.commit();

            return phieu;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
    
    /**
     * Lấy mã phiếu đặt phòng mới nhất
     * @return id
     */
    @Override
    public String getMaxID() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "select max(maPhieuDat) from PhieuDatPhong";

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
    
    /**
     * Lấy phiếu đặt phòng hôm nay theo mã phòng
     * @param maPhong
     * @return dsPhong
     */
    @Override
    public List<PhieuDatPhong> getPhieuHomNay(String maPhong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatterNgay = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String sql = "select * from PhieuDatPhong where datediff(day,NgayDat,'" + formatterNgay.format(date) + "') = 0 "
                + "and datediff(MINUTE,'" + formatterNgay.format(date) + "',NgayDat) > 0"
                + "and maPhong = '" + maPhong + "' and trangThai = 'DANG_DOI'";

        try {
            tr.begin();
            List<PhieuDatPhong> phieuDatPhong = session.createNativeQuery(sql, PhieuDatPhong.class).getResultList();
            tr.commit();
            return phieuDatPhong;
        } catch (Exception e) {
            tr.rollback();
        }

        return null;

    }

    /**
     * Trả về số lượng của phiếu bởi nhiều thuộc tinh truyền vào
     * @param tenPhong
     * @param tenKhachHang
     * @param trangThai
     * @param ngayDat
     * @return 
     */
    @Override
    public int getSoLuongPhieuDatPhongByAllProperty(String tenPhong, String tenKhachHang, String trangThai, String ngayDat) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql;
        if (ngayDat == null) {
            sql = "SELECT count(*) FROM KhachHang JOIN PhieuDatPhong ON KhachHang.maKhachHang = PhieuDatPhong.maKhachHang JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong \n"
                    + "where Phong.tenPhong like '%" + tenPhong + "%' and KhachHang.tenKhachHang like N'%" + tenKhachHang + "%' and PhieuDatPhong.trangThai like '%" + trangThai + "%'";
        } else {
            sql = "SELECT count(*) FROM KhachHang JOIN PhieuDatPhong ON KhachHang.maKhachHang = PhieuDatPhong.maKhachHang JOIN Phong ON PhieuDatPhong.maPhong = Phong.maPhong \n"
                    + "where Phong.tenPhong like '%" + tenPhong + "%' and KhachHang.tenKhachHang like N'%" + tenKhachHang + "%' and PhieuDatPhong.trangThai like '%" + trangThai + "%' and CONVERT(date, ngayDat) = CONVERT(date, '" + ngayDat + "')";
        }
        try {
            tr.begin();
            int rs = (int) session.
                    createNativeQuery(sql)
                    .getSingleResult();
            tr.commit();
            return rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return 0;
    }
    /**
     * Lấy phiếu đặt phognf theo mã phiếu đặt phòng
     * @param maPhong
     * @return phieuDatPhong
     */
    @Override
    public PhieuDatPhong getPhieuDatPhongByIDPhong(String maPhong) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "select pdp.* from PhieuDatPhong pdp inner join Phong p on pdp.maPhong = p.maPhong where p.maPhong = :x and pdp.trangThai = 'DANG_DOI'";

        try {
            tr.begin();
            PhieuDatPhong phieuDatPhong = session.createNativeQuery(sql, PhieuDatPhong.class)
                    .setParameter("x", maPhong)
                    .getSingleResult();
            tr.commit();
            return phieuDatPhong;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
    /**
     * Lấy danh sách phòng theo mã  phòng
     * @param maPhong
     * @return dsPhieuDatPhong
     */
    @Override
    public List<PhieuDatPhong> getDsPhieuDatByPhong(String maPhong) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        String sql = "select * from PhieuDatPhong\n"
                + "where maPhong = :x and trangThai = 'DANG_DOI' and DATEDIFF(MINUTE, GETDATE(), ngayDat) < 130";

        try {
            tr.begin();
            List<PhieuDatPhong> rs = session
                    .createNativeQuery(sql, PhieuDatPhong.class)
                    .setParameter("x", maPhong)
                    .getResultList();
            tr.commit();
            return rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
}
