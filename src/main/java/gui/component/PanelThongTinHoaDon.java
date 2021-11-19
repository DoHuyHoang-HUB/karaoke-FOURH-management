/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.component;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ImageIcon;

/**
 *
 * @author Hao
 */
public class PanelThongTinHoaDon extends javax.swing.JPanel {

    private HoaDon hoaDon;
    /**
     * Creates new form PanelThongTinHoaDon
     */
    public PanelThongTinHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
        initComponents();
        xuLyDuLieu();
        tblChiTiet.setFont(new Font("SansSerif",Font.PLAIN, 14)); 
        tblChiTiet.fixTable(scrChiTiet);
    }
    
    private void xuLyDuLieu(){
       DecimalFormat dcf = new DecimalFormat("#,###   VNĐ  ");
        DecimalFormat dcf1 = new DecimalFormat("#,###");
        SimpleDateFormat fm1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat fm2 = new SimpleDateFormat("HH:mm");
        lblTTMaHD.setText(hoaDon.getMaHoaDon());
        lblTTTenKhachHang.setText(hoaDon.getKhachHang().getTenKhachHang());
        lblTTGia.setText(dcf.format(hoaDon.getPhong().getLoaiPhong().getGiaPhong()));
        lblTTTenPhong.setText(hoaDon.getPhong().getTenPhong());
        lblTTLoaiPhong.setText(hoaDon.getPhong().getLoaiPhong().getTenLoaiPhong());
        lblTTNgayLap.setText(fm1.format(hoaDon.getNgayLapHoaDon()));
        lblTTSDTKH.setText(hoaDon.getKhachHang().getSoDienThoai());
        lblTTSoGio.setText(hoaDon.getGioHat());
        lblTTTGBatDau.setText(fm2.format(hoaDon.getThoiGianBatDau()));
        lblTTTGKetThuc.setText(fm2.format(hoaDon.getThoiGianKetThuc()));
        lblTTTongTien.setText(dcf.format(hoaDon.getTongHoaDon()));
        lblTenNhanVien.setText(hoaDon.getNhanVien().getTenNhanVien());
        lblTTTienMatHang.setText(dcf.format(hoaDon.getTongTienMatHang()));
        List<ChiTietHoaDon> dsChiTiet = hoaDon.getDsChiTietHoaDon();
        for(ChiTietHoaDon ct: dsChiTiet){
            tblChiTiet.addRow(new Object[]{ct.getMatHang().getMaMatHang(), ct.getMatHang().getTenMatHang(), dcf1.format(ct.getMatHang().getDonGia()), ct.getSoLuong(),dcf1.format(ct.getThanhTien())});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCT = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        lblLienHe = new javax.swing.JLabel();
        lblTieuDe = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        lblTTMaHD = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        lblTTTenKhachHang = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        lblTTSDTKH = new javax.swing.JLabel();
        lblNgayLap = new javax.swing.JLabel();
        lblTTNgayLap = new javax.swing.JLabel();
        lblTenPhong = new javax.swing.JLabel();
        lblTTTenPhong = new javax.swing.JLabel();
        lblLoaiPhong = new javax.swing.JLabel();
        lblTTLoaiPhong = new javax.swing.JLabel();
        lblTGBatDau = new javax.swing.JLabel();
        lblTTTGBatDau = new javax.swing.JLabel();
        lblTGKetThuc = new javax.swing.JLabel();
        lblTTTGKetThuc = new javax.swing.JLabel();
        lblGia = new javax.swing.JLabel();
        lblTTGia = new javax.swing.JLabel();
        lblSoGio = new javax.swing.JLabel();
        lblTTSoGio = new javax.swing.JLabel();
        lblTongHoaDon = new javax.swing.JLabel();
        lblTTTongTien = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        lblTenNhanVien = new javax.swing.JLabel();
        lblTienMatHang = new javax.swing.JLabel();
        lblTTTienMatHang = new javax.swing.JLabel();
        scrChiTiet = new javax.swing.JScrollPane();
        tblChiTiet = new gui.swing.table2.MyTable();
        pictureBox1 = new gui.swing.image.PictureBox();

        setBackground(new java.awt.Color(255, 255, 255));

        lblCT.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        lblCT.setText("CÔNG TY KARAOKE FOUR- H");

        lblDiaChi.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        lblDiaChi.setText("Số 12 Nguyễn Văn Bảo, Phường 4, Quận Gò Vấp, TP.Hồ Chí Minh");

        lblLienHe.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        lblLienHe.setText("Điện thoại: 0824601014");

        lblTieuDe.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lblTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDe.setText("HÓA ĐƠN");

        lblMaHD.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblMaHD.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaHD.setText("Mã: ");

        lblTTMaHD.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTMaHD.setText("HD1111111");

        lblTenKH.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTenKH.setText("Tên khách hàng: ");

        lblTTTenKhachHang.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTTenKhachHang.setText("Nguyễn Thị Hảo");

        lblSDT.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblSDT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSDT.setText("Số điện thoại: ");

        lblTTSDTKH.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTSDTKH.setText("0824601014");

        lblNgayLap.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        lblNgayLap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNgayLap.setText("Ngày lập hóa đơn: ");

        lblTTNgayLap.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTNgayLap.setText("2021-12-12");

        lblTenPhong.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTenPhong.setText("Tên phòng: ");

        lblTTTenPhong.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTTenPhong.setText("Phòng 1111");

        lblLoaiPhong.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblLoaiPhong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblLoaiPhong.setText("Loại phòng: ");

        lblTTLoaiPhong.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTLoaiPhong.setText("Phòng Vip");

        lblTGBatDau.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTGBatDau.setText("Bắt đầu: ");

        lblTTTGBatDau.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTTGBatDau.setText("13:05");

        lblTGKetThuc.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTGKetThuc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTGKetThuc.setText("Kết thúc: ");

        lblTTTGKetThuc.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTTGKetThuc.setText("14:05");

        lblGia.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblGia.setText("Giá phòng/giờ: ");

        lblTTGia.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTGia.setText("300,000,000,000");

        lblSoGio.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblSoGio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSoGio.setText("Số phút hát: ");

        lblTTSoGio.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTSoGio.setText("5");

        lblTongHoaDon.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTongHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongHoaDon.setText("Tổng hóa đơn: ");

        lblTTTongTien.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTTongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTTTongTien.setText("1,000,000");

        lblNhanVien.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblNhanVien.setText("Nhân viên lập hóa đơn: ");

        lblTenNhanVien.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTenNhanVien.setText("Nguyễn Thị Hảo");

        lblTienMatHang.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTienMatHang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTienMatHang.setText("Tổng tiền mặt hàng: ");

        lblTTTienMatHang.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTTienMatHang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTTTienMatHang.setText("100,000");

        tblChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã mặt hàng", "Tên mặt hàng", "Đơn giá", "Số lượng", "Chiết khấu", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTiet.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        scrChiTiet.setViewportView(tblChiTiet);
        if (tblChiTiet.getColumnModel().getColumnCount() > 0) {
            tblChiTiet.getColumnModel().getColumn(0).setMinWidth(100);
            tblChiTiet.getColumnModel().getColumn(1).setMinWidth(250);
            tblChiTiet.getColumnModel().getColumn(3).setMinWidth(80);
            tblChiTiet.getColumnModel().getColumn(3).setMaxWidth(80);
        }

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/icon/logo.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrChiTiet)
                    .addComponent(lblTieuDe, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblGia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTGBatDau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTenKH))
                            .addComponent(lblTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTTTenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                            .addComponent(lblTTTenPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTTTGBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTTGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblTGKetThuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblLoaiPhong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSoGio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTTLoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTTSDTKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTTSoGio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTTTGKetThuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblNgayLap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTTNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblNhanVien)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblTienMatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTTTienMatHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblTongHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTTTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblLienHe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(lblMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTTMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCT, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLienHe, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTTMaHD)
                    .addComponent(lblMaHD, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTieuDe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTTNgayLap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenKH)
                    .addComponent(lblTTTenKhachHang)
                    .addComponent(lblSDT)
                    .addComponent(lblTTSDTKH))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenPhong)
                    .addComponent(lblTTTenPhong)
                    .addComponent(lblLoaiPhong)
                    .addComponent(lblTTLoaiPhong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTGBatDau)
                    .addComponent(lblTTTGBatDau)
                    .addComponent(lblTGKetThuc)
                    .addComponent(lblTTTGKetThuc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGia)
                    .addComponent(lblTTGia)
                    .addComponent(lblSoGio)
                    .addComponent(lblTTSoGio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTienMatHang)
                    .addComponent(lblTTTienMatHang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongHoaDon)
                    .addComponent(lblTTTongTien))
                .addGap(54, 54, 54))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCT;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblGia;
    private javax.swing.JLabel lblLienHe;
    private javax.swing.JLabel lblLoaiPhong;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblNgayLap;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblSoGio;
    private javax.swing.JLabel lblTGBatDau;
    private javax.swing.JLabel lblTGKetThuc;
    private javax.swing.JLabel lblTTGia;
    private javax.swing.JLabel lblTTLoaiPhong;
    private javax.swing.JLabel lblTTMaHD;
    private javax.swing.JLabel lblTTNgayLap;
    private javax.swing.JLabel lblTTSDTKH;
    private javax.swing.JLabel lblTTSoGio;
    private javax.swing.JLabel lblTTTGBatDau;
    private javax.swing.JLabel lblTTTGKetThuc;
    private javax.swing.JLabel lblTTTenKhachHang;
    private javax.swing.JLabel lblTTTenPhong;
    private javax.swing.JLabel lblTTTienMatHang;
    private javax.swing.JLabel lblTTTongTien;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblTenNhanVien;
    private javax.swing.JLabel lblTenPhong;
    private javax.swing.JLabel lblTienMatHang;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblTongHoaDon;
    private gui.swing.image.PictureBox pictureBox1;
    private javax.swing.JScrollPane scrChiTiet;
    private gui.swing.table2.MyTable tblChiTiet;
    // End of variables declaration//GEN-END:variables
    

}
