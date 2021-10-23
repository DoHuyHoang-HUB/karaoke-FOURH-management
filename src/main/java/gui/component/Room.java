package gui.component;

import entity.HoaDon;
import entity.Phong;
import gui.panel.PanelShadow;
import gui.swing.button.Button;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Admin
 */
public class Room extends PanelShadow {

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
    private final String fontName = "sansserif";
    private final int fontStyle = Font.PLAIN;
    private HoaDon hoaDon;
    private Phong phong;
    private double thoiGian;
    private JLabel lblGioHat;
    private Timer tm;
    private JPanel pnlDangHat;
    private JPanel pnlPhongTrong;
    private JPanel pnlPhongSua;
    private JPanel pnlPhongBan;
    private JPanel pnlPhongDangDon;
    private JPanel pnlPhongDatTruoc;
    private final JMenuItem mniKhachVaoHat;
    private final JMenuItem mniThanhToan;
    private final JMenuItem mniDoiPhong;
    private final JMenuItem mniThemDichVu;
    private final JMenuItem mniDatPhong;
    private final JMenuItem mniDonPhong;
    private final JMenuItem mniSuaPhong;

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public Room() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        JPopupMenu pop = new JPopupMenu();
        pop.setPreferredSize(new Dimension(150, 250));
        mniKhachVaoHat = new JMenuItem("Khách vào hát");
        mniThanhToan = new JMenuItem("Thanh toán");
        mniDoiPhong = new JMenuItem("Đổi phòng");
        mniThemDichVu = new JMenuItem("Thêm dịch vụ");
        mniDatPhong = new JMenuItem("Đặt phòng");
        mniDonPhong = new JMenuItem("Dọn phòng");
        mniSuaPhong = new JMenuItem("Sửa phòng");
        pop.add(mniKhachVaoHat);
        pop.addSeparator();
        pop.add(mniThanhToan);
        pop.add(mniDoiPhong);
        pop.add(mniThemDichVu);
        pop.add(mniDatPhong);
        pop.addSeparator();
        pop.add(mniDonPhong);
        pop.add(mniSuaPhong);
        setComponentPopupMenu(pop);
        add(buildPhongTrong());
    }

    private void checkTrangThai() {

    }

    private void showPanel(JPanel pane) {
        removeAll();
        add(pane);
        repaint();
        revalidate();
    }

    private JPanel buildRoomDangHaT() {
        mniKhachVaoHat.setEnabled(false);
        mniThanhToan.setEnabled(true);
        mniDoiPhong.setEnabled(true);
        mniThemDichVu.setEnabled(true);
        mniDatPhong.setEnabled(false);
        mniDonPhong.setEnabled(false);
        mniSuaPhong.setEnabled(false);
        pnlDangHat = new JPanel();
        pnlDangHat.setBackground(Color.RED);
        pnlDangHat.setLayout(new MigLayout("wrap", "push[center]push", "0[]5[]5[]5[]5[]5[]5[]push"));

        JLabel lblIcon = new JLabel();
        lblIcon.setIcon(new ImageIcon(getClass().getResource("/icon/users_20px.png")));
        pnlDangHat.add(lblIcon);

        JLabel lblTenPhong = new JLabel("Phòng A1");
        lblTenPhong.setFont(new Font(fontName, fontStyle, 24));
        lblTenPhong.setForeground(Color.WHITE);
//        lblTenPhong.setText(hoaDon.getPhong().getTenPhong());
        pnlDangHat.add(lblTenPhong);

        JLabel lblLoaiPhong = new JLabel("Phòng thường");
        lblLoaiPhong.setFont(new Font(fontName, fontStyle, 14));
        lblLoaiPhong.setForeground(Color.WHITE);
//        lblLoaiPhong.setText(hoaDon.getPhong().getLoaiPhong().getTenLoaiPhong());
        pnlDangHat.add(lblLoaiPhong);

        JLabel lblTrangThai = new JLabel("Đang hát");
        lblTrangThai.setForeground(Color.WHITE);
//        lblTrangThai.setText(hoaDon.getPhong().getTrangThai());
        lblTrangThai.setFont(new Font(fontName, fontStyle, 14));
        pnlDangHat.add(lblTrangThai);

        JLabel lblKhachHang = new JLabel("Đỗ Huy Hoàng");
        lblKhachHang.setForeground(Color.WHITE);
        lblKhachHang.setFont(new Font(fontName, fontStyle, 14));
//        lblKhachHang.setText(hoaDon.getKhachHang().getTenKhachHang());
        pnlDangHat.add(lblKhachHang);

        JLabel lblBatDau = new JLabel("22/10/2021 9:00");
        lblBatDau.setForeground(Color.WHITE);
        lblBatDau.setFont(new Font(fontName, fontStyle, 14));
//        lblBatDau.setText(sdf.format(hoaDon.getThoiGianBatDau()));
        pnlDangHat.add(lblBatDau);
        lblGioHat = new JLabel("15 phút");
        lblGioHat.setForeground(Color.WHITE);
        lblGioHat.setFont(new Font(fontName, fontStyle, 14));
        pnlDangHat.add(lblGioHat);
        tm = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                thoiGian = (hoaDon.getThoiGianBatDau().getTime() - Date.valueOf(LocalDate.now()).getTime()) / 1000;
                lblGioHat.setText(String.valueOf((int) thoiGian));
            }
        });

        Button btnThanhToan = new Button("Thanh toán", true);
        btnThanhToan.setForeground(Color.WHITE);
        btnThanhToan.setBackground(new Color(0, 31, 63));
        btnThanhToan.setBorderRadius(5);
        pnlDangHat.add(btnThanhToan, "split 2");

        Button btnThemDichVu = new Button("DV", true);
        btnThemDichVu.setForeground(Color.WHITE);
        btnThemDichVu.setBackground(new Color(0, 31, 63));
        btnThemDichVu.setBorderRadius(5);
        pnlDangHat.add(btnThemDichVu);
        return pnlDangHat;
    }

    public void tinhGio() {
        if (!tm.isRunning()) {
            tm.start();
        }
    }

    public void ketThuc() {
        if (tm.isRunning()) {
            tm.stop();
        }
    }

    private JPanel buildPhongTrong() {
        mniKhachVaoHat.setEnabled(true);
        mniThanhToan.setEnabled(false);
        mniDoiPhong.setEnabled(false);
        mniThemDichVu.setEnabled(false);
        mniDatPhong.setEnabled(true);
        mniDonPhong.setEnabled(true);
        mniSuaPhong.setEnabled(true);
        pnlPhongTrong = new JPanel();
        pnlPhongTrong.setBackground(new Color(0, 166, 90));
        pnlPhongTrong.setLayout(new MigLayout("wrap", "push[center]push", "0[]5[]5[]5[]90[]push"));
        
        JLabel lblIcon = new JLabel();
        lblIcon.setIcon(new ImageIcon(getClass().getResource("/icon/users_20px.png")));
        pnlPhongTrong.add(lblIcon);
        
        JLabel lblTenPhong = new JLabel("Phòng A1");
        lblTenPhong.setForeground(Color.WHITE);
        lblTenPhong.setFont(new Font(fontName, fontStyle, 24));
//        lblTenPhong.setText(phong.getTenPhong());
        pnlPhongTrong.add(lblTenPhong);
        
        JLabel lblLoaiPhong = new JLabel("Phòng thường");
        lblLoaiPhong.setForeground(Color.WHITE);
        lblLoaiPhong.setFont(new Font(fontName, fontStyle, 14));
//        lblLoaiPhong.setText(phong.getLoaiPhong().getTenLoaiPhong());
        pnlPhongTrong.add(lblLoaiPhong);
        
        JLabel lblTrangThai = new JLabel("Phòng trống");
        lblTrangThai.setForeground(Color.WHITE);
        lblTrangThai.setFont(new Font(fontName, fontStyle, 14));
//        lblTrangThai.setText(phong.getTrangThai());
        pnlPhongTrong.add(lblTrangThai);
        
        Button btnDat = new Button("Đặt", true);
        btnDat.setForeground(Color.WHITE);
        btnDat.setBackground(new Color(0, 31, 63));
        btnDat.setBorderRadius(5);
        pnlPhongTrong.add(btnDat, "split 2");

        Button btnThue = new Button("Thuê", true);
        btnThue.setForeground(Color.WHITE);
        btnThue.setBackground(new Color(0, 31, 63));
        btnThue.setBorderRadius(5);
        pnlPhongTrong.add(btnThue);
        return pnlPhongTrong;
    }

    /**
     * Xây dựng phòng sửa
     */
    private JPanel buildPhongSua() {
        mniKhachVaoHat.setEnabled(false);
        mniThanhToan.setEnabled(false);
        mniDoiPhong.setEnabled(false);
        mniThemDichVu.setEnabled(false);
        mniDatPhong.setEnabled(false);
        mniDonPhong.setEnabled(false);
        mniSuaPhong.setEnabled(false);
        pnlPhongSua = new JPanel();
        pnlPhongSua.setBackground(Color.GRAY);
        pnlPhongSua.setLayout(new MigLayout("wrap", "push[center]push", "0[]5[]5[]90[]push"));
        
        JLabel lblIcon = new JLabel();
        lblIcon.setIcon(new ImageIcon(getClass().getResource("/icon/users_20px.png")));
        pnlPhongSua.add(lblIcon);
        
        JLabel lblTenPhong = new JLabel("Phòng A1");
        lblTenPhong.setForeground(Color.WHITE);
        lblTenPhong.setFont(new Font(fontName, fontStyle, 24));
//        lblTenPhong.setText(phong.getTenPhong());
        pnlPhongSua.add(lblTenPhong);
        
        JLabel lblLoaiPhong = new JLabel("Phòng thường");
        lblLoaiPhong.setFont(new Font(fontName, fontStyle, 14));
        lblLoaiPhong.setForeground(Color.WHITE);
        pnlPhongSua.add(lblLoaiPhong);
        
        JLabel lblTrangThai = new JLabel("Đang sửa phòng");
        lblTrangThai.setForeground(Color.WHITE);
        lblTrangThai.setFont(new Font(fontName, fontStyle, 14));
//        lblTrangThai.setText(phong.getTrangThai());
        pnlPhongSua.add(lblTrangThai);
        
        Button btnSuaXong = new Button("Sửa xong", true);
        btnSuaXong.setForeground(Color.WHITE);
        btnSuaXong.setBackground(new Color(0, 31, 63));
        btnSuaXong.setBorderRadius(5);
        pnlPhongSua.add(btnSuaXong);

        return pnlPhongSua;
    }

    /**
     * Xây dựng phòng bẩn
     */
    private JPanel buildPhongBan() {
        mniKhachVaoHat.setEnabled(false);
        mniThanhToan.setEnabled(false);
        mniDoiPhong.setEnabled(false);
        mniThemDichVu.setEnabled(false);
        mniDatPhong.setEnabled(false);
        mniDonPhong.setEnabled(true);
        mniSuaPhong.setEnabled(false);
        
        pnlPhongBan = new JPanel();
        pnlPhongBan.setBackground(new Color(241, 202, 53 ));
        pnlPhongBan.setLayout(new MigLayout("wrap", "push[center]push", "0[]5[]5[]5[]90[]push"));
        
        JLabel lblIcon = new JLabel();
        lblIcon.setIcon(new ImageIcon(getClass().getResource("/icon/users_20px.png")));
        pnlPhongBan.add(lblIcon);
        
        JLabel lblTenPhong = new JLabel("Phòng A1");
        lblTenPhong.setForeground(Color.WHITE);
        lblTenPhong.setFont(new Font(fontName, fontStyle, 24));
//        lblTenPhong.setText(phong.getTenPhong());
        pnlPhongBan.add(lblTenPhong);
        
        JLabel lblLoaiPhong = new JLabel("Phòng thường");
        lblLoaiPhong.setFont(new Font(fontName, fontStyle, 14));
        lblLoaiPhong.setForeground(Color.WHITE);
        pnlPhongBan.add(lblLoaiPhong);
        
        JLabel lblTrangThai = new JLabel("Phòng bẩn");
        lblTrangThai.setForeground(Color.WHITE);
        lblTrangThai.setFont(new Font(fontName, fontStyle, 14));
//        lblTrangThai.setText(phong.getTrangThai());
        pnlPhongBan.add(lblTrangThai);
        
        Button btnDonPhong = new Button("Dọn phòng", true);
        btnDonPhong.setForeground(Color.WHITE);
        btnDonPhong.setBackground(new Color(0, 31, 63));
        btnDonPhong.setBorderRadius(5);
        pnlPhongBan.add(btnDonPhong);
        
        return pnlPhongBan;
    }

    /**
     * Xây dựng phòng đang dọn
     */
    private JPanel buildPhongDangDon() {
        mniKhachVaoHat.setEnabled(false);
        mniThanhToan.setEnabled(false);
        mniDoiPhong.setEnabled(false);
        mniThemDichVu.setEnabled(false);
        mniDatPhong.setEnabled(false);
        mniDonPhong.setEnabled(false);
        mniSuaPhong.setEnabled(false);
        
        pnlPhongDangDon = new JPanel();
        pnlPhongDangDon.setBackground(new Color(100, 100, 109));
        pnlPhongDangDon.setLayout(new MigLayout("wrap", "push[center]push", "0[]5[]5[]5[]90[]push"));
        
        JLabel lblIcon = new JLabel();
        lblIcon.setIcon(new ImageIcon(getClass().getResource("/icon/users_20px.png")));
        pnlPhongDangDon.add(lblIcon);
        
        JLabel lblTenPhong = new JLabel("Phòng A1");
        lblTenPhong.setForeground(Color.WHITE);
        lblTenPhong.setFont(new Font(fontName, fontStyle, 24));
//        lblTenPhong.setText(phong.getTenPhong());
        pnlPhongDangDon.add(lblTenPhong);
        
        JLabel lblLoaiPhong = new JLabel("Phòng thường");
        lblLoaiPhong.setFont(new Font(fontName, fontStyle, 14));
        lblLoaiPhong.setForeground(Color.WHITE);
        pnlPhongDangDon.add(lblLoaiPhong);
        
        JLabel lblTrangThai = new JLabel("Đang dọn phòng");
        lblTrangThai.setForeground(Color.WHITE);
        lblTrangThai.setFont(new Font(fontName, fontStyle, 14));
//        lblTrangThai.setText(phong.getTrangThai());
        pnlPhongDangDon.add(lblTrangThai);
        
        Button btnDonXong = new Button("Dọn xong", true);
        btnDonXong.setForeground(Color.WHITE);
        btnDonXong.setBackground(new Color(0, 31, 63));
        btnDonXong.setBorderRadius(5);
        pnlPhongDangDon.add(btnDonXong);
        
        return pnlPhongDangDon;
    }

    /**
     * Xây dựng phòng đặt trước
     */
    private JPanel buildPhongDatTruoc() {
        mniKhachVaoHat.setEnabled(true);
        mniThanhToan.setEnabled(false);
        mniDoiPhong.setEnabled(true);
        mniThemDichVu.setEnabled(false);
        mniDatPhong.setEnabled(false);
        mniDonPhong.setEnabled(false);
        mniSuaPhong.setEnabled(false);
        
        pnlPhongDatTruoc = new JPanel();
        pnlPhongDatTruoc.setBackground(new Color(60, 141, 188));
        pnlPhongDatTruoc.setLayout(new MigLayout("wrap", "push[center]push", "0[]5[]5[]5[]5[]5[]50[]push"));
        
        JLabel lblIcon = new JLabel();
        lblIcon.setIcon(new ImageIcon(getClass().getResource("/icon/users_20px.png")));
        pnlPhongDatTruoc.add(lblIcon);
        
        JLabel lblTenPhong = new JLabel("Phòng A1");
        lblTenPhong.setForeground(Color.WHITE);
        lblTenPhong.setFont(new Font(fontName, fontStyle, 24));
//        lblTenPhong.setText(phong.getTenPhong());
        pnlPhongDatTruoc.add(lblTenPhong);
        
        JLabel lblLoaiPhong = new JLabel("Phòng thường");
        lblLoaiPhong.setFont(new Font(fontName, fontStyle, 14));
        lblLoaiPhong.setForeground(Color.WHITE);
        pnlPhongDatTruoc.add(lblLoaiPhong);
        
        JLabel lblTrangThai = new JLabel("Đã đặt trước");
        lblTrangThai.setForeground(Color.WHITE);
        lblTrangThai.setFont(new Font(fontName, fontStyle, 14));
//        lblTrangThai.setText(phong.getTrangThai());
        pnlPhongDatTruoc.add(lblTrangThai);
        
        JLabel lblKhachHang = new JLabel("Đỗ Huy Hoàng");
        lblKhachHang.setForeground(Color.WHITE);
        lblKhachHang.setFont(new Font(fontName, fontStyle, 14));
//        lblKhachHang.setText(hoaDon.getKhachHang().getTenKhachHang());
        pnlPhongDatTruoc.add(lblKhachHang);

        JLabel lblBatDau = new JLabel("22/10/2021 9:00");
        lblBatDau.setForeground(Color.WHITE);
        lblBatDau.setFont(new Font(fontName, fontStyle, 14));
//        lblBatDau.setText(sdf.format(hoaDon.getThoiGianBatDau()));
        pnlPhongDatTruoc.add(lblBatDau);
        
        Button btnThue = new Button("Thuê", true);
        btnThue.setForeground(Color.WHITE);
        btnThue.setBackground(new Color(0, 31, 63));
        btnThue.setBorderRadius(5);
        pnlPhongDatTruoc.add(btnThue, "split 2");
        
        Button btnHuy = new Button("Hủy", true);
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setBackground(new Color(0, 31, 63));
        btnHuy.setBorderRadius(5);
        pnlPhongDatTruoc.add(btnHuy);
        
        return pnlPhongDatTruoc;
    }
}