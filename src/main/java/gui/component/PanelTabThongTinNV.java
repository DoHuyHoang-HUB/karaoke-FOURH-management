package gui.component;

import entity.NhanVien;

public class PanelTabThongTinNV extends javax.swing.JPanel {

    private NhanVien nhanVien;

    public PanelTabThongTinNV(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
        initComponents();
        HandlerData();
    }

    private void HandlerData() {
        lblValueMaNhanVien.setText(nhanVien.getMaNhanVien());

        String gioiTinh = nhanVien.isGioiTinh() == true ? "Nữ" : "Nam";
        lblValueGioiTinh.setText(gioiTinh);

        System.out.println();
        String[] temp = nhanVien.getNgaySinh().toString().split("-");
        String ngaySinh = temp[2] + "/" + temp[1] + "/" + temp[0];

        lblValueNgaySinh.setText(ngaySinh);

        lblValueSDT.setText(nhanVien.getSoDienThoai());
        lblValueEmail.setText(nhanVien.getEmail());

        String caLam = nhanVien.getCaLam().getGioBatDau() + "-" + nhanVien.getCaLam().getGioKetThuc();
        lblValueCCCD.setText(caLam);

        lblValueCaLam.setText(nhanVien.getCanCuocCD());
        lblValueLoaiNhanVien.setText(nhanVien.getLoaiNhanVien().getTenLoaiNV());

        lblValueSoNha.setText(nhanVien.getDiaChi().getSoNha() + ", " + nhanVien.getDiaChi().getTenDuong());
        lblValueXaPhuong.setText(nhanVien.getDiaChi().getXaPhuong());
        lblValueQuanHuyen.setText(nhanVien.getDiaChi().getQuanHuyen());
        lblValueTinhThanhPho.setText(nhanVien.getDiaChi().getTinhThanh());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblMaNhanVien = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblCCCD = new javax.swing.JLabel();
        lblLoaiNhanVien = new javax.swing.JLabel();
        lblSoNha = new javax.swing.JLabel();
        lblValueMaNhanVien = new javax.swing.JLabel();
        lblValueGioiTinh = new javax.swing.JLabel();
        lblValueNgaySinh = new javax.swing.JLabel();
        lblValueSDT = new javax.swing.JLabel();
        lblValueEmail = new javax.swing.JLabel();
        lblValueCCCD = new javax.swing.JLabel();
        lblValueLoaiNhanVien = new javax.swing.JLabel();
        lblValueSoNha = new javax.swing.JLabel();
        lblXaPhuong = new javax.swing.JLabel();
        lblQuanHuyen = new javax.swing.JLabel();
        lblTinhThanhPho = new javax.swing.JLabel();
        lblCaLam = new javax.swing.JLabel();
        lblValueCaLam = new javax.swing.JLabel();
        lblValueXaPhuong = new javax.swing.JLabel();
        lblValueTinhThanhPho = new javax.swing.JLabel();
        lblValueQuanHuyen = new javax.swing.JLabel();

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

        lblMaNhanVien.setBackground(new java.awt.Color(204, 204, 204));
        lblMaNhanVien.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblMaNhanVien.setForeground(new java.awt.Color(68, 71, 90));
        lblMaNhanVien.setText("Mã nhân viên");

        lblGioiTinh.setBackground(new java.awt.Color(204, 204, 204));
        lblGioiTinh.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblGioiTinh.setForeground(new java.awt.Color(68, 71, 90));
        lblGioiTinh.setText("Giới tính");

        lblNgaySinh.setBackground(new java.awt.Color(204, 204, 204));
        lblNgaySinh.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblNgaySinh.setForeground(new java.awt.Color(68, 71, 90));
        lblNgaySinh.setText("Ngày sinh");

        lblSDT.setBackground(new java.awt.Color(204, 204, 204));
        lblSDT.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblSDT.setForeground(new java.awt.Color(68, 71, 90));
        lblSDT.setText("SDT");

        lblEmail.setBackground(new java.awt.Color(204, 204, 204));
        lblEmail.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(68, 71, 90));
        lblEmail.setText("Email");

        lblCCCD.setBackground(new java.awt.Color(204, 204, 204));
        lblCCCD.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblCCCD.setForeground(new java.awt.Color(68, 71, 90));
        lblCCCD.setText("Căn cước CD");

        lblLoaiNhanVien.setBackground(new java.awt.Color(204, 204, 204));
        lblLoaiNhanVien.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblLoaiNhanVien.setForeground(new java.awt.Color(68, 71, 90));
        lblLoaiNhanVien.setText("Loại nhân viên");

        lblSoNha.setBackground(new java.awt.Color(204, 204, 204));
        lblSoNha.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblSoNha.setForeground(new java.awt.Color(68, 71, 90));
        lblSoNha.setText("Số nhà");

        lblValueMaNhanVien.setBackground(new java.awt.Color(204, 204, 204));
        lblValueMaNhanVien.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblValueMaNhanVien.setForeground(new java.awt.Color(68, 71, 90));
        lblValueMaNhanVien.setText("123");

        lblValueGioiTinh.setBackground(new java.awt.Color(204, 204, 204));
        lblValueGioiTinh.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblValueGioiTinh.setForeground(new java.awt.Color(68, 71, 90));
        lblValueGioiTinh.setText("Nam");

        lblValueNgaySinh.setBackground(new java.awt.Color(204, 204, 204));
        lblValueNgaySinh.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblValueNgaySinh.setForeground(new java.awt.Color(68, 71, 90));
        lblValueNgaySinh.setText("01-01-2021");

        lblValueSDT.setBackground(new java.awt.Color(204, 204, 204));
        lblValueSDT.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblValueSDT.setForeground(new java.awt.Color(68, 71, 90));
        lblValueSDT.setText("0123456789");

        lblValueEmail.setBackground(new java.awt.Color(204, 204, 204));
        lblValueEmail.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblValueEmail.setForeground(new java.awt.Color(68, 71, 90));
        lblValueEmail.setText("email@gmail.com");

        lblValueCCCD.setBackground(new java.awt.Color(204, 204, 204));
        lblValueCCCD.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblValueCCCD.setForeground(new java.awt.Color(68, 71, 90));
        lblValueCCCD.setText("012345678910");

        lblValueLoaiNhanVien.setBackground(new java.awt.Color(204, 204, 204));
        lblValueLoaiNhanVien.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblValueLoaiNhanVien.setForeground(new java.awt.Color(68, 71, 90));
        lblValueLoaiNhanVien.setText("Lễ Tân");

        lblValueSoNha.setBackground(new java.awt.Color(204, 204, 204));
        lblValueSoNha.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblValueSoNha.setForeground(new java.awt.Color(68, 71, 90));
        lblValueSoNha.setText("12, Le Van Bao");

        lblXaPhuong.setBackground(new java.awt.Color(204, 204, 204));
        lblXaPhuong.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblXaPhuong.setForeground(new java.awt.Color(68, 71, 90));
        lblXaPhuong.setText("Xã/Phường");

        lblQuanHuyen.setBackground(new java.awt.Color(204, 204, 204));
        lblQuanHuyen.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblQuanHuyen.setForeground(new java.awt.Color(68, 71, 90));
        lblQuanHuyen.setText("Quận/Huyện");

        lblTinhThanhPho.setBackground(new java.awt.Color(204, 204, 204));
        lblTinhThanhPho.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblTinhThanhPho.setForeground(new java.awt.Color(68, 71, 90));
        lblTinhThanhPho.setText("Tỉnh/Thành phố");

        lblCaLam.setBackground(new java.awt.Color(204, 204, 204));
        lblCaLam.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblCaLam.setForeground(new java.awt.Color(68, 71, 90));
        lblCaLam.setText("Ca làm");

        lblValueCaLam.setBackground(new java.awt.Color(204, 204, 204));
        lblValueCaLam.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblValueCaLam.setForeground(new java.awt.Color(68, 71, 90));
        lblValueCaLam.setText("08:00-13:00");

        lblValueXaPhuong.setBackground(new java.awt.Color(204, 204, 204));
        lblValueXaPhuong.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblValueXaPhuong.setForeground(new java.awt.Color(68, 71, 90));
        lblValueXaPhuong.setText("Phuong 4");

        lblValueTinhThanhPho.setBackground(new java.awt.Color(204, 204, 204));
        lblValueTinhThanhPho.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblValueTinhThanhPho.setForeground(new java.awt.Color(68, 71, 90));
        lblValueTinhThanhPho.setText("Ho Chi Minh");

        lblValueQuanHuyen.setBackground(new java.awt.Color(204, 204, 204));
        lblValueQuanHuyen.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblValueQuanHuyen.setForeground(new java.awt.Color(68, 71, 90));
        lblValueQuanHuyen.setText("Go Vap");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblLoaiNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSoNha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblXaPhuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblQuanHuyen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTinhThanhPho))
                        .addGap(38, 38, 38)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblValueLoaiNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblValueSoNha, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblValueXaPhuong, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblValueTinhThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblValueQuanHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCCCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(lblCaLam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(38, 38, 38)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblValueCCCD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblValueEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                            .addComponent(lblValueSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblValueNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblValueGioiTinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblValueMaNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblValueCaLam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValueMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValueGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValueNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValueSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValueEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValueCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValueCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLoaiNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValueLoaiNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoNha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValueSoNha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblXaPhuong, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValueXaPhuong, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuanHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValueQuanHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTinhThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValueTinhThanhPho, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(173, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCCCD;
    private javax.swing.JLabel lblCaLam;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblLoaiNhanVien;
    private javax.swing.JLabel lblMaNhanVien;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblQuanHuyen;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblSoNha;
    private javax.swing.JLabel lblTinhThanhPho;
    private javax.swing.JLabel lblValueCCCD;
    private javax.swing.JLabel lblValueCaLam;
    private javax.swing.JLabel lblValueEmail;
    private javax.swing.JLabel lblValueGioiTinh;
    private javax.swing.JLabel lblValueLoaiNhanVien;
    private javax.swing.JLabel lblValueMaNhanVien;
    private javax.swing.JLabel lblValueNgaySinh;
    private javax.swing.JLabel lblValueQuanHuyen;
    private javax.swing.JLabel lblValueSDT;
    private javax.swing.JLabel lblValueSoNha;
    private javax.swing.JLabel lblValueTinhThanhPho;
    private javax.swing.JLabel lblValueXaPhuong;
    private javax.swing.JLabel lblXaPhuong;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
