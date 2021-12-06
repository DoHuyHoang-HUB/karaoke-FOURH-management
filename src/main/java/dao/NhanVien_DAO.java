package dao;

import entity.NhanVien;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.NhanVienService;
import util.HibernateUtil;

/**
 *
 * @author NGUYE
 */
public class NhanVien_DAO implements NhanVienService {

    private SessionFactory sessionFactory;

    /**
     *
     */
    public NhanVien_DAO() {
        HibernateUtil hibernateUtil = HibernateUtil.getInstance();
        this.sessionFactory = hibernateUtil.getSessionFactory();
    }

    /**
     * kiểm tra kết nối đến database
     *
     * @return
     */
    @Override
    public boolean checkConnect() {
        return sessionFactory.openSession().isConnected();
    }

    /**
     * Thêm 1 nhân viên
     *
     * @param nhanVien
     * @return true: thêm thành công, false: thêm thất bại
     */
    @Override
    public boolean addNhanVien(NhanVien nhanVien) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            session.save(nhanVien);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();

        }
        return false;
    }

    /**
     * Cập nhật 1 nhân viên
     *
     * @param nhanVien
     * @return true: cập nhật thành công, false: cập nhật thất bại
     */
    @Override
    public boolean updateNhanVien(NhanVien nhanVien) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            session.update(nhanVien);
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    /**
     * Xóa 1 nhân viên
     *
     * @param id
     * @return true: thành công, false: thất bại
     */
    @Override
    public boolean deleteNhanVien(String id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            session.delete(session.find(NhanVien.class, id));
            tr.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public NhanVien getNhanVien(String id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            NhanVien nhanVien = session.find(NhanVien.class, id);
            transaction.commit();

            return nhanVien;
        } catch (Exception e) {
            System.err.println(e);
            transaction.rollback();
        }
        return null;
    }

    @Override
    public List<NhanVien> getNhanViens() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            List<NhanVien> nhanViens = session.createNamedQuery("getDSNhanVien", NhanVien.class).getResultList();

            transaction.commit();

            return nhanViens;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return null;
    }

    @Override
    public NhanVien getLastNhanVien() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            String query = "SELECT * FROM NhanVien "
                    + " WHERE maNhanVien = (SELECT MAX(maNhanVien) FROM NhanVien)";
            NhanVien nhanVien = session.createNativeQuery(query, NhanVien.class).getSingleResult();
            transaction.commit();

            return nhanVien;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return null;

    }

    @Override
    public NhanVien getNhanVienByLogin(String sdt, byte[] matKhau) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        String sql = "select * from NhanVien "
                + "where sdt = '" + sdt + "' "
                + "and matKhau = :x";

        try {
            tr.begin();
            NhanVien nhanVien = session
                    .createNativeQuery(sql, NhanVien.class)
                    .setParameter("x", matKhau)
                    .getSingleResult();
            tr.commit();
            return nhanVien;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public List<NhanVien> searchNhanVien(String textSearch, String searchOption, int gioiTinh, String maLoaiNV, String maCaLam, int numPage) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        int soLuong = numPage * 20;
        System.out.println("offset DAO: " + soLuong);
        if (soLuong < 0) {
            soLuong = Math.abs(soLuong);
        }

        String gioiTinhString = " and  gioiTinh = "+gioiTinh+" ";
        if (gioiTinh == 2) {
            gioiTinhString = "";
        }

        String query = "select * from NhanVien where  "
                + "tenNhanVien like N'%" + textSearch + "%'"
//                + "and ( gioiTinh =" + gioiTinh0 + "  OR  gioiTinh = " + gioiTinh1 + " ) "
                + gioiTinhString
                + "   and maCa like '%" + maCaLam + "%'"
                + "and maLoaiNhanVien like '%" + maLoaiNV + "%'"
                + " order by maNhanVien offset " + soLuong + " rows fetch next 20 rows only";

        if (searchOption == "Mã nhân viên") {
            query = "select * from NhanVien where  "
                    + "maNhanVien like '%" + textSearch + "%'"
//                    + "and ( gioiTinh =" + gioiTinh0 + "  OR  gioiTinh = " + gioiTinh1 + " ) "
                    + gioiTinhString
                    + "   and maCa like '%" + maCaLam + "%'"
                    + "and maLoaiNhanVien like '%" + maLoaiNV + "%'"
                    + " order by maNhanVien offset " + soLuong + " rows fetch next 20 rows only";
        } else if (searchOption == "Căn cước công dân") {
            query = "select * from NhanVien where  "
                    + "cccd like '%" + textSearch + "%'"
//                    + "and ( gioiTinh =" + gioiTinh0 + "  OR  gioiTinh = " + gioiTinh1 + " ) "
                    + gioiTinhString
                    + "   and maCa like '%" + maCaLam + "%'"
                    + "and maLoaiNhanVien like '%" + maLoaiNV + "%'"
                    + " order by maNhanVien offset " + soLuong + " rows fetch next 20 rows only";
        }

        try {
            transaction.begin();

            List<NhanVien> nhanViens = session.createNativeQuery(query, NhanVien.class).getResultList();
            transaction.commit();

            return nhanViens;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return null;

    }

    @Override
    public int getSoLuongNhanVien(String textSearch, String searchOption, int gioiTinh, String maLoaiNV, String maCaLam) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        String gioiTinhString = " and  gioiTinh = "+gioiTinh+" ";
        if (gioiTinh == 2) {
            gioiTinhString = " ";
        }
//        int gioiTinh0 = gioiTinh;
//        int gioiTinh1 = gioiTinh;
//        if (gioiTinh == 2) { // gán lại giá trị cho gioiTinh0 và gioiTinh1 để lấy dc tất cả
//            gioiTinh0 = 0;
//            gioiTinh1 = 1;
//        }

        String query = "select count(*) from NhanVien where  "
                + " tenNhanVien like N'%" + textSearch + "%' "
//                + "and ( gioiTinh =" + gioiTinh0 + "  OR  gioiTinh = " + gioiTinh1 + " ) "
                + gioiTinhString
                + "   and maCa like '%" + maCaLam + "%'"
                + " and maNhanVien like '%" + maLoaiNV + "%'";

        if ("Mã nhân viên".equals(searchOption)) {
            query = "select count(*) from NhanVien where  "
                    + " maNhanVien like '%" + textSearch + "%'"
//                    + "and ( gioiTinh =" + gioiTinh0 + "  OR  gioiTinh = " + gioiTinh1 + " ) "
                    + gioiTinhString
                    + "   and maCa like '%" + maCaLam + "%'"
                    + "  and maNhanVien like '%" + maLoaiNV + "%'";

        } else if ("Căn cước công dân".equals(searchOption)) {
            query = "select count(*) from NhanVien where  "
                    + " cccd like '%" + textSearch + "%'"
//                    + "and ( gioiTinh =" + gioiTinh0 + "  OR  gioiTinh = " + gioiTinh1 + " ) "
                    + gioiTinhString
                    + "   and maCa like '%" + maCaLam + "%'"
                    + " and maNhanVien like '%" + maLoaiNV + "%'";
        }

        try {
            transaction.begin();

            int rs = (int) session.createNativeQuery(query).getSingleResult();
            transaction.commit();

            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return 0;
    }

    @Override
    public NhanVien getNhanVienBySdtOrEmail(String sdtOrEmail) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        String sql = "select * from NhanVien "
                + "where sdt = :x "
                + "or email = :x";

        try {
            tr.begin();
            NhanVien nhanVien = session
                    .createNativeQuery(sql, NhanVien.class)
                    .setParameter("x", sdtOrEmail)
                    .getSingleResult();
            tr.commit();
            return nhanVien;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public List<String> getMaNhanVienQuanLy() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select n.maNhanVien from [dbo].[NhanVien] n\n"
                + "  where maLoaiNhanVien = 'LNV005'";
        try {
            tr.begin();
            List<String> dsMa = session
                    .createNativeQuery(sql)
                    .getResultList();
            tr.commit();
            return dsMa;
        } catch (Exception e) {
            System.err.println(e);
            tr.rollback();
        }
        session.close();

        return null;
    }

    /**
     * Kiểm tra trùng số điện thoại
     *
     * @param sdt
     * @return true: trùng, false: chưa có
     */
    @Override
    public boolean checkSDT(String sdt) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            String query = "SELECT * FROM NhanVien WHERE sdt = '" + sdt + "'";
            NhanVien nhanVien = session.createNativeQuery(query, NhanVien.class).getSingleResult();

            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
        }
        return false;
    }

    /**
     * Kiểm tra trùng căn cước công dân
     *
     * @param cccd
     * @return true: trùng, false: chưa có
     */
    @Override
    public boolean checkCCCD(String cccd) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            String query = "SELECT * FROM NhanVien WHERE cccd = '" + cccd + "'";
            NhanVien nhanVien = session.createNativeQuery(query, NhanVien.class).getSingleResult();
            transaction.commit();

            return true;
        } catch (Exception e) {
            transaction.rollback();
        }

        return false;
    }

}
