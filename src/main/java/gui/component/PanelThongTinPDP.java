/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.component;

import entity.PhieuDatPhong;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Hao
 */
public class PanelThongTinPDP extends javax.swing.JPanel {
    private PhieuDatPhong phieu;

    /**
     * Creates new form PanelThongTinPDP
     */
    public PanelThongTinPDP(PhieuDatPhong phieu) {
        this.phieu = phieu;
        initComponents();
        xuLyDuLieu();
    }
    
    private void xuLyDuLieu(){
        DecimalFormat dcf = new DecimalFormat("#,###   VNĐ  ");
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        lblTTMaPhieu.setText(phieu.getMaPhieuDat());
        lblTTTenKhachHang.setText(phieu.getKhachHang().getTenKhachHang());
        lblTTCCCD.setText(phieu.getKhachHang().getCanCuocCD());
        lblTTTenPhong.setText(phieu.getPhong().getTenPhong());
        lblTTTang.setText(Integer.toString(phieu.getPhong().getTang()));
        lblTTLoaiPhong.setText(phieu.getPhong().getLoaiPhong().getTenLoaiPhong());
        lblTTGiaPhong.setText(dcf.format(phieu.getPhong().getLoaiPhong().getGiaPhong()));
        lblTTNgayTaoPhieu.setText(fm.format(phieu.getNgayTao()));
        lblTTNgayDat.setText(fm.format(phieu.getNgayDat()));
        lblTTTrangThai.setText(phieu.getTrangThai().getTrangThai());
        lblTTTienCoc.setText(dcf.format(phieu.getTienCoc()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlThongTin = new javax.swing.JPanel();
        lblMaPhieu = new javax.swing.JLabel();
        lblKhachHang = new javax.swing.JLabel();
        lblPhong = new javax.swing.JLabel();
        lblNgayDat = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        lblTrangThaiPhieu = new javax.swing.JLabel();
        lblTienCoc = new javax.swing.JLabel();
        lblTTMaPhieu = new javax.swing.JLabel();
        lblTTTenKhachHang = new javax.swing.JLabel();
        lblTTTenPhong = new javax.swing.JLabel();
        lblTTNgayTaoPhieu = new javax.swing.JLabel();
        lblTTNgayDat = new javax.swing.JLabel();
        lblTTTrangThai = new javax.swing.JLabel();
        lblTTTienCoc = new javax.swing.JLabel();
        lblHinh = new javax.swing.JLabel();
        lblTenCT = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        lblDienThoai = new javax.swing.JLabel();
        lblPhieu = new javax.swing.JLabel();
        lblSDTKH = new javax.swing.JLabel();
        lblTTSDTKH = new javax.swing.JLabel();
        lblLoaiPhong = new javax.swing.JLabel();
        lblTTLoaiPhong = new javax.swing.JLabel();
        lblCCCD = new javax.swing.JLabel();
        lblTTCCCD = new javax.swing.JLabel();
        lblGiaPhong = new javax.swing.JLabel();
        lblTTGiaPhong = new javax.swing.JLabel();
        lblTang = new javax.swing.JLabel();
        lblTTTang = new javax.swing.JLabel();

        pnlThongTin.setBackground(new java.awt.Color(204, 204, 255));

        lblMaPhieu.setFont(new java.awt.Font("SansSerif", 2, 18)); // NOI18N
        lblMaPhieu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaPhieu.setText("Mã phiếu đặt: ");

        lblKhachHang.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblKhachHang.setText("Tên khách hàng: ");

        lblPhong.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblPhong.setText("Phòng: ");

        lblNgayDat.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblNgayDat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNgayDat.setText("Ngày nhận phòng: ");

        lblNgayTao.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblNgayTao.setText("Thời gian tạo phiếu: ");

        lblTrangThaiPhieu.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTrangThaiPhieu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTrangThaiPhieu.setText("Trạng thái phiếu: ");

        lblTienCoc.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTienCoc.setText("Tiền cọc:");

        lblTTMaPhieu.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTMaPhieu.setText("PD1111111");

        lblTTTenKhachHang.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTTenKhachHang.setText("Nguyễn Thị Hảo");

        lblTTTenPhong.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTTenPhong.setText("Phòng 007");

        lblTTNgayTaoPhieu.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTNgayTaoPhieu.setText("01-02-2020 17:30");

        lblTTNgayDat.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTNgayDat.setText("01-02-2020 18:00");

        lblTTTrangThai.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTTrangThai.setText("Đã tiếp nhận");

        lblTTTienCoc.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTTienCoc.setText("150,000");

        lblHinh.setIcon(new javax.swing.ImageIcon("D:\\PT_N1\\QL_KARAOKE_FOUH\\src\\main\\resources\\icon\\audio.jpg")); // NOI18N
        lblHinh.setText("   ");

        lblTenCT.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblTenCT.setText("CÔNG TY KARAOKE FOUR- H");

        lblDiaChi.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblDiaChi.setText("Số 12 Nguyễn Văn Bảo, Phường 4, Quận Gò Vấp, TP.Hồ Chí Minh");

        lblDienThoai.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblDienThoai.setText("Điện thoại: 0824601014");

        lblPhieu.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lblPhieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhieu.setText("PHIẾU ĐẶT PHÒNG");

        lblSDTKH.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblSDTKH.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSDTKH.setText("Số điện thoại: ");

        lblTTSDTKH.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTSDTKH.setText("0824601014");

        lblLoaiPhong.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblLoaiPhong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblLoaiPhong.setText("Loại phòng: ");

        lblTTLoaiPhong.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTLoaiPhong.setText("Phòng Vip");

        lblCCCD.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblCCCD.setText("Căn cước công dân: ");

        lblTTCCCD.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTCCCD.setText("261517679");

        lblGiaPhong.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblGiaPhong.setText("Giá phòng: ");

        lblTTGiaPhong.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTGiaPhong.setText("300,000");

        lblTang.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTang.setText("Tầng: ");

        lblTTTang.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTTTang.setText("1");

        javax.swing.GroupLayout pnlThongTinLayout = new javax.swing.GroupLayout(pnlThongTin);
        pnlThongTin.setLayout(pnlThongTinLayout);
        pnlThongTinLayout.setHorizontalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPhieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinLayout.createSequentialGroup()
                                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                                    .addComponent(lblTenCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(lblGiaPhong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblCCCD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblTTNgayTaoPhieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                                .addComponent(lblTTTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblSDTKH)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblTTSDTKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblTTGiaPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lblTTTenPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblTang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lblLoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblTTTang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lblTTLoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)))
                                            .addComponent(lblTTCCCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                                        .addComponent(lblNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTTNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTrangThaiPhieu)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTTTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                                        .addComponent(lblTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTTTienCoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addComponent(lblMaPhieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTTMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlThongTinLayout.setVerticalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(lblTenCT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTTMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTTNgayTaoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTTTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTTSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTTCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTTTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTang, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTTTang, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGiaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTTLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTTGiaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTTTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTTNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTrangThaiPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTTTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCCCD;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblDienThoai;
    private javax.swing.JLabel lblGiaPhong;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblLoaiPhong;
    private javax.swing.JLabel lblMaPhieu;
    private javax.swing.JLabel lblNgayDat;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblPhieu;
    private javax.swing.JLabel lblPhong;
    private javax.swing.JLabel lblSDTKH;
    private javax.swing.JLabel lblTTCCCD;
    private javax.swing.JLabel lblTTGiaPhong;
    private javax.swing.JLabel lblTTLoaiPhong;
    private javax.swing.JLabel lblTTMaPhieu;
    private javax.swing.JLabel lblTTNgayDat;
    private javax.swing.JLabel lblTTNgayTaoPhieu;
    private javax.swing.JLabel lblTTSDTKH;
    private javax.swing.JLabel lblTTTang;
    private javax.swing.JLabel lblTTTenKhachHang;
    private javax.swing.JLabel lblTTTenPhong;
    private javax.swing.JLabel lblTTTienCoc;
    private javax.swing.JLabel lblTTTrangThai;
    private javax.swing.JLabel lblTang;
    private javax.swing.JLabel lblTenCT;
    private javax.swing.JLabel lblTienCoc;
    private javax.swing.JLabel lblTrangThaiPhieu;
    private javax.swing.JPanel pnlThongTin;
    // End of variables declaration//GEN-END:variables
}