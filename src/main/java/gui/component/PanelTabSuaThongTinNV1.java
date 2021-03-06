package gui.component;

import dao.CaLam_DAO;
import dao.DiaChiMau_DAO;
import dao.LoaiNhanVien_DAO;
import dao.NhanVien_DAO;
import entity.CaLam;
import entity.DiaChi;
import entity.LoaiNhanVien;
import entity.NhanVien;
import gui.swing.event.EventAdd;
import gui.swing.model.AutoID;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author NGUYE
 */
public class PanelTabSuaThongTinNV1 extends javax.swing.JPanel {

//    private CaLam_DAO caLam_DAO;
//    private LoaiNhanVien_DAO loaiNhanVien_DAO;
//    private DiaChiMau_DAO diaChiMau_DAO;
//    private NhanVien_DAO nhanVien_DAO;
//
//    private List<CaLam> caLams;
//    private List<LoaiNhanVien> loaiNhanViens;
//    private EventAdd event;
//
//    private SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
//    private SimpleDateFormat fm2 = new SimpleDateFormat("dd-MM-yyyy");
     private NhanVien nhanVien;
    private CaLam_DAO caLam_DAO;
    private LoaiNhanVien_DAO loaiNhanVien_DAO;
    private DiaChiMau_DAO diaChiMau_DAO;
    private NhanVien_DAO nhanVien_DAO;

    private List<CaLam> caLams;
    private List<LoaiNhanVien> loaiNhanViens;

    private SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");

   public PanelTabSuaThongTinNV1(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
        initComponents();
        setPropertiesForm();
        caLam_DAO = new CaLam_DAO();
        loaiNhanVien_DAO = new LoaiNhanVien_DAO();
        diaChiMau_DAO = new DiaChiMau_DAO();
        nhanVien_DAO = new NhanVien_DAO();
        comboBoxHandler();

        HandlerData();
        btnSuaHandler();
//        clearForm();
    }

    private void HandlerData() {
        txtMaNV.setText(nhanVien.getMaNhanVien());

        txtTenNV.setText(nhanVien.getTenNhanVien());
        String gioiTinh = nhanVien.isGioiTinh() == true ? "N???" : "Nam";
        cmbGioiTinh.setSelectedItem(gioiTinh);

        jDateChooser1.setDateFormatString("dd-MM-yyyy");
        jDateChooser1.setDate(nhanVien.getNgaySinh());

        System.out.println(nhanVien.getNgaySinh());

        txtSDT.setText(nhanVien.getSoDienThoai());
        txtEmail.setText(nhanVien.getEmail());
        txtCCCD.setText(nhanVien.getCanCuocCD());

        cmbCaLam.setSelectedItem(nhanVien.getCaLam().getGioBatDau() + "-" + nhanVien.getCaLam().getGioKetThuc());
        cmbLoaiNV.setSelectedItem(nhanVien.getLoaiNhanVien().getTenLoaiNV());
        txtSoNha.setText(nhanVien.getDiaChi().getSoNha());
        txtTenDuong.setText(nhanVien.getDiaChi().getTenDuong());

        cmbTinhTP.setSelectedItem(nhanVien.getDiaChi().getTinhThanh());
        cmbQuanHuyen.setSelectedItem(nhanVien.getDiaChi().getQuanHuyen());
        cmbXaPhuong.setSelectedItem(nhanVien.getDiaChi().getXaPhuong());
//        txtMatKhau.setText(Arrays.toString(nhanVien.getMatKhau()));
//       System.out.println(Byte.toString((byte[])nhanVien.getMatKhau()));
//       System.out.println("string " + new String(nhanVien.getMatKhau(),StandardCharsets.UTF_8));
    }

    private void btnSuaHandler() {
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (validForm() == true) {
                    String ma = txtMaNV.getText();
                    String ten = txtTenNV.getText().trim();

                    LoaiNhanVien lnv = new LoaiNhanVien();
                    for (LoaiNhanVien i : loaiNhanViens) {
                        if (i.getTenLoaiNV().equals(cmbLoaiNV.getSelectedItem().toString())) {
                            lnv = i;
                            break;
                        }
                    }

                    CaLam cl = new CaLam();
                    for (CaLam i : caLams) {
                        String temp = i.getGioBatDau() + "-" + i.getGioKetThuc();
                        if (temp.equals(cmbCaLam.getSelectedItem().toString())) {
                            cl = i;
                            break;
                        }
                    }

                    String cccd = txtCCCD.getText().trim();
                    boolean gioiTinh = "Nam".equals(cmbGioiTinh.getSelectedItem()) ? false : true;

                    ArrayList<Integer> d = getNgaySinh();
                    java.sql.Date ngaySinh = new java.sql.Date(d.get(0) - 1900, d.get(1) - 1, d.get(2));

                    String sdt = txtSDT.getText().trim();
                    String email = txtEmail.getText().trim();
                    DiaChi diaChi = new DiaChi(txtSoNha.getText(), txtTenDuong.getText(), cmbXaPhuong.getSelectedItem().toString(), cmbQuanHuyen.getSelectedItem().toString(), cmbTinhTP.getSelectedItem().toString());
//                    byte[] matKhau = txtMatKhau.getText().getBytes();

//                        String x = cmbLoaiNV.getSelectedItem().toString();
                    //S???a nh??n vi??n
                    NhanVien nhanVienUpdate = new NhanVien(ma, ten, lnv, cl, cccd, gioiTinh, ngaySinh, sdt, email, diaChi, nhanVien.getMatKhau());
                    System.out.println("Nhan vieen dc sua \n " + nhanVienUpdate);
                    boolean result = nhanVien_DAO.updateNhanVien(nhanVienUpdate);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Sua th??nh c??ng");
//                            clearForm();
                    }
                } else {
                    return;
                }

            }
        });
    }

    /**
     * Tr??? v??? m???ng ng??y th??ng n??m sinh ki???u int.
     *
     * @return date date[0] n??m date[1] th??ng date[2] ng??y
     */
    private ArrayList<Integer> getNgaySinh() {
        ArrayList<Integer> date = new ArrayList<>();
        String[] d = fm1.format(jDateChooser1.getDate()).split("-");
        for (String string : d) {
            System.out.println(string);
        }
        date.add(Integer.parseInt(d[0])); //n??m 
        date.add(Integer.parseInt(d[1])); // th??ng
        date.add(Integer.parseInt(d[2])); //ng??y
        System.out.println("ham" + date.get(0) + "/" + date.get(1) + "/" + date.get(2));
        return date;
    }

    private void setPropertiesForm() {
        int txtRadius = 10;
        int cmbRadius = 10;
        int btnRadius = 10;
        Color colorBtn = new Color(184, 238, 241);

        txtMaNV.setEditable(false);


        cmbGioiTinh.addItem("Nam");
        cmbGioiTinh.addItem("N???");

        jDateChooser1.setDateFormatString("dd-MM-yyy");
        cmbCaLam.addItem("Ch???n");
        cmbLoaiNV.addItem("Ch???n");
        cmbXaPhuong.addItem("Ch???n");
        cmbQuanHuyen.addItem("Ch???n");
        cmbTinhTP.addItem("Ch???n");
        btnSua.setBorderline(true);
        btnSua.setBorderRadius(btnRadius);
        btnSua.setBackground(colorBtn);
    }

    private void comboBoxHandler() {
        caLams = caLam_DAO.getCaLams();
        for (CaLam i : caLams) {
            cmbCaLam.addItem(i.getGioBatDau() + "-" + i.getGioKetThuc());
        }

        loaiNhanViens = loaiNhanVien_DAO.getLoaiNhanViens();
        for (LoaiNhanVien i : loaiNhanViens) {
            cmbLoaiNV.addItem(i.getTenLoaiNV());
        }

        diaChiMau_DAO.getAllTinhThanh().forEach(i -> {
            cmbTinhTP.addItem(i);
        });

        cmbTinhTP.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String tinhThanh = cmbTinhTP.getSelectedItem().toString();

                // set la??i combobox
                cmbQuanHuyen.setSelectedIndex(0);
                cmbXaPhuong.setSelectedIndex(0);
                
                //Khi cho??n la??i combobox ti??nh thi?? xo??a  combobox quuanHuyen, xaPhuong
                
                int num1 = cmbQuanHuyen.getItemCount();
                System.out.println(".itemStateChanged() Sua thong tin NV: count quan huyen: "+ num1);
                if (num1 > 1) {
                    for (int i = num1 - 1; i > 0; i--) {
                        cmbQuanHuyen.removeItemAt(i);
                    }
                }

                int num2 = cmbXaPhuong.getItemCount();
                if (num2 > 1) {
                    for (int i = num2 - 1; i > 0; i--) {
                        cmbXaPhuong.removeItemAt(i);
                    }
                }
                
                diaChiMau_DAO.getQuanHuyenTheoTinhThanh(tinhThanh).forEach(i -> {
                    cmbQuanHuyen.addItem(i);
                    System.out.println("them quan huyen");
                });
            }
        });

        cmbQuanHuyen.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String quanHuyen = cmbQuanHuyen.getSelectedItem().toString();
                String tinhThanh = cmbTinhTP.getSelectedItem().toString();

                int num2 = cmbXaPhuong.getItemCount();
                if (num2 > 1) {
                    for (int i = num2 - 1; i > 0; i--) {
                        cmbXaPhuong.removeItemAt(i);
                    }
                }
                
                diaChiMau_DAO.getPhuongXaTheoQHTH(quanHuyen, tinhThanh).forEach(i -> {
                    cmbXaPhuong.addItem(i);
                    System.out.println("them xa phuong");
                });
            }
        });

    }

    private boolean validForm() {
        //Ki???m tra h??? t??n
        String hoTen = txtTenNV.getText().trim();
        if (!(hoTen.length() > 0)) {
            JOptionPane.showMessageDialog(null, "Ch??a nh???p h??? t??n.");
            txtTenNV.requestFocus();
            return false;
        } else {
                       
            if (!hoTen.matches("^([???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????A-Z]{1}[???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????a-z]*\\s)+([???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????A-Z]{1}[???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????a-z]*)$")) {
                JOptionPane.showMessageDialog(null, "Ch??a nh???p ?????y ????? h??? t??n. H??? t??n b???t ?????u b???ng ch??? in hoa.");
                txtTenNV.selectAll();
                txtTenNV.requestFocus();
                return false;
            }
        }

        if (jDateChooser1.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Ch??a ch???n ng??y sinh.");
            return false;
        }

        //Ki???m tra ng??y th??ng n??m sinh >= 18 tu???i
        ArrayList<Integer> d = getNgaySinh();
        int namSinh = d.get(0);
        int thangSinh = d.get(1);
        int ngaySinh = d.get(2);
        System.out.println("ngay sinh" + "/" + ngaySinh + "/" + thangSinh + "/" + namSinh);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();

        if ((now.getYear() - namSinh) <= 18) {

            if ((now.getYear() - namSinh) < 18) {
                JOptionPane.showMessageDialog(null, "Ch??a ????? 18 tu???i.");
                return false;
            } else {//N???u n??m == 18

                if (now.getMonthValue() <= thangSinh) {

                    if (now.getMonthValue() < thangSinh) {
                        JOptionPane.showMessageDialog(null, "Ch??a ????? 18 tu???i.");
                        return false;
                    } else if (now.getMonthValue() == thangSinh) {//N???u th??ng b???ng nhau

                        if (now.getDayOfMonth() < ngaySinh) {
                            JOptionPane.showMessageDialog(null, "Ch??a ????? 18 tu???i.");
                            return false;
                        }
                    }
                }
            }
        }

        String sdt = txtSDT.getText().trim();
        if (!(sdt.length() > 0)) {
            JOptionPane.showMessageDialog(null, "Ch??a nh???p s??? ??i???n tho???i.");
            txtSDT.requestFocus();
            return false;
        } else {
            if (!(sdt.matches("^0[0-9]{9}$"))) {
                JOptionPane.showMessageDialog(null,
                        "Nh???p sai ?????nh d???ng.\nS??? ??i???n tho???i bao g???m 10 ch??? s??? b???t ?????u l?? s??? 0.");
                txtSDT.selectAll();
                txtSDT.requestFocus();
                return false;
            }
        }

        String email = txtEmail.getText().trim();
        if (email.length() > 0) {

            if (!(email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"))) {
                JOptionPane.showMessageDialog(null, "Nh???p sai ?????nh d???ng email.");
                txtEmail.selectAll();
                txtEmail.requestFocus();
                return false;
            }
        }

        String cccd = txtCCCD.getText().trim();
        if (!(cccd.length() > 0)) {
            JOptionPane.showMessageDialog(null, "Ch??a nh???p s??? c??n c?????c c??ng d??n.");
            txtCCCD.requestFocus();
            return false;
        } else {
            if (!(cccd.matches("^0[0-9]{11}$"))) {
                JOptionPane.showMessageDialog(null,
                        "Nh???p sai ?????nh d???ng.\nS??? c??n c?????c g???m 12 ch??? s???.");
                txtCCCD.selectAll();
                txtCCCD.requestFocus();
                return false;
            }
        }

        if (cmbCaLam.getSelectedItem().equals("Ch???n")) {
            JOptionPane.showMessageDialog(null, "Ch??a ch???n ca l??m.");
            return false;
        }
        if (cmbLoaiNV.getSelectedItem().equals("Ch???n")) {
            JOptionPane.showMessageDialog(null, "Ch??a ch???n lo???i nh??n vi??n.");
            return false;
        }
        if (cmbTinhTP.getSelectedItem().equals("Ch???n")) {
            JOptionPane.showMessageDialog(null, "Ch??a ch???n t???nh/Th??nh ph???.");
            return false;
        }
        if (cmbQuanHuyen.getSelectedItem().equals("Ch???n")) {
            JOptionPane.showMessageDialog(null, "Ch??a ch???n qu???n/huy???n.");
            return false;
        }
        if (cmbXaPhuong.getSelectedItem().equals("Ch???n")) {
            JOptionPane.showMessageDialog(null, "Ch??a ch???n ph?????ng/x??.");
            return false;
        }

        String soNha = txtSoNha.getText().trim();
        if (!(soNha.length() > 0)) {
            JOptionPane.showMessageDialog(null, "Ch??a nh???p s??? nh??.");
            txtSoNha.requestFocus();
            return false;
        }

        String tenDuong = txtTenDuong.getText().trim();
        if (!(tenDuong.length() > 0)) {
            JOptionPane.showMessageDialog(null, "Ch??a nh???p t??n ???????ng.");
            txtTenDuong.requestFocus();
            return false;
        }

        return true;
    }

    private void clearForm() {

        txtMaNV.setText("");

        txtTenNV.setText("");
        cmbGioiTinh.setSelectedIndex(0);
        jDateChooser1.setDate(null);
        txtSDT.setText("");
        txtEmail.setText("");
        txtCCCD.setText("");
        cmbCaLam.setSelectedIndex(0);
        cmbLoaiNV.setSelectedIndex(0);
        txtSoNha.setText("");
        txtTenDuong.setText("");
        cmbXaPhuong.setSelectedIndex(0);
        cmbQuanHuyen.setSelectedIndex(0);
        cmbTinhTP.setSelectedIndex(0);
//        txtMatKhau.setText("Mk#123");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblMaNV = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblCCCD = new javax.swing.JLabel();
        lblCaLam = new javax.swing.JLabel();
        lblLoaiNV = new javax.swing.JLabel();
        lblSoNha = new javax.swing.JLabel();
        lblTenDuong = new javax.swing.JLabel();
        lblXaPhuong = new javax.swing.JLabel();
        lblQuanHuyen = new javax.swing.JLabel();
        lblTinhTP = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        btnSua = new gui.swing.button.Button();
        txtMaNV = new javax.swing.JTextField();
        txtTenNV = new javax.swing.JTextField();
        cmbGioiTinh = new javax.swing.JComboBox<>();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtCCCD = new javax.swing.JTextField();
        cmbCaLam = new javax.swing.JComboBox<>();
        cmbLoaiNV = new javax.swing.JComboBox<>();
        cmbTinhTP = new javax.swing.JComboBox<>();
        cmbQuanHuyen = new javax.swing.JComboBox<>();
        cmbXaPhuong = new javax.swing.JComboBox<>();
        txtSoNha = new javax.swing.JTextField();
        txtTenDuong = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(709, 812));

        lblMaNV.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblMaNV.setText("M?? nh??n vi??n");

        lblTenNV.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTenNV.setText("T??n nh??n vi??n");

        lblGioiTinh.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblGioiTinh.setText("Gi???i t??nh");

        lblNgaySinh.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblNgaySinh.setText("Ng??y sinh");

        lblSDT.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblSDT.setText("SDT");

        lblEmail.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblEmail.setText("Email");

        lblCCCD.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblCCCD.setText("C??n c?????c CD");

        lblCaLam.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblCaLam.setText("Ca l??m");

        lblLoaiNV.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblLoaiNV.setText("Lo???i nh??n vi??n");

        lblSoNha.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblSoNha.setText("S??? nh??");

        lblTenDuong.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTenDuong.setText("T??n ???????ng");

        lblXaPhuong.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblXaPhuong.setText("X??/Ph?????ng");

        lblQuanHuyen.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblQuanHuyen.setText("Qu???n/Huy???n");

        lblTinhTP.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTinhTP.setText("T???nh/Th??nh ph???");

        jDateChooser1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        btnSua.setText("L??u");
        btnSua.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        txtMaNV.setEditable(false);
        txtMaNV.setBackground(new java.awt.Color(255, 255, 255));
        txtMaNV.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtTenNV.setBackground(new java.awt.Color(255, 255, 255));
        txtTenNV.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        cmbGioiTinh.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        txtSDT.setBackground(new java.awt.Color(255, 255, 255));
        txtSDT.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtEmail.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtCCCD.setBackground(new java.awt.Color(255, 255, 255));
        txtCCCD.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        cmbCaLam.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        cmbLoaiNV.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        cmbTinhTP.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        cmbQuanHuyen.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        cmbXaPhuong.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        txtSoNha.setBackground(new java.awt.Color(255, 255, 255));
        txtSoNha.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtTenDuong.setBackground(new java.awt.Color(255, 255, 255));
        txtTenDuong.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNgaySinh)
                                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblLoaiNV)
                                    .addComponent(lblCaLam)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblGioiTinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblSDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                                    .addComponent(cmbGioiTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSDT)
                                    .addComponent(txtEmail)
                                    .addComponent(cmbCaLam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbLoaiNV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblSoNha, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(txtSoNha))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTenNV)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(lblMaNV))
                                    .addComponent(lblCCCD))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaNV)
                                    .addComponent(txtTenNV)
                                    .addComponent(txtCCCD)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblTenDuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblXaPhuong, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblQuanHuyen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(lblTinhTP))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbTinhTP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbQuanHuyen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbXaPhuong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTenDuong, javax.swing.GroupLayout.Alignment.TRAILING))))))
                .addGap(70, 70, 70))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLoaiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbLoaiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTinhTP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTinhTP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuanHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbQuanHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblXaPhuong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbXaPhuong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoNha, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoNha, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenDuong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenDuong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.button.Button btnSua;
    private javax.swing.JComboBox<String> cmbCaLam;
    private javax.swing.JComboBox<String> cmbGioiTinh;
    private javax.swing.JComboBox<String> cmbLoaiNV;
    private javax.swing.JComboBox<String> cmbQuanHuyen;
    private javax.swing.JComboBox<String> cmbTinhTP;
    private javax.swing.JComboBox<String> cmbXaPhuong;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCCCD;
    private javax.swing.JLabel lblCaLam;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblLoaiNV;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblQuanHuyen;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblSoNha;
    private javax.swing.JLabel lblTenDuong;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblTinhTP;
    private javax.swing.JLabel lblXaPhuong;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoNha;
    private javax.swing.JTextField txtTenDuong;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}
