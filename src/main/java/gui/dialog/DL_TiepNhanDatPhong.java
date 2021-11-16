package gui.dialog;

import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.TimeChangeListener;
import com.github.lgooddatepicker.zinternaltools.TimeChangeEvent;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.MatHang_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.MatHang;
import entity.Phong;
import entity.NhanVien;
import gui.swing.event.EventAdd;
import gui.swing.event.EventMinus;
import gui.swing.event.EventSelectedRow;
import gui.swing.model.ModelAdd;
import gui.swing.table2.SpinnerEditor;
import gui.swing.textfield.PanelSearch;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import service.HoaDonService;
import service.KhachHangService;
import service.MatHangService;
import service.PhieuDatPhongService;

public class DL_TiepNhanDatPhong extends javax.swing.JDialog {

    private MatHangService matHangService;
    private HoaDonService hoaDonService;
    private PhieuDatPhongService phieuDatPhongService;
    private KhachHangService khachHangService;
    private Phong phong;
    private NhanVien nhanVien;
    private HoaDon hoaDon;
    private final DecimalFormat df = new DecimalFormat("#,##0");
    private PanelSearch search;
    private JPopupMenu menu;

    public DL_TiepNhanDatPhong(Phong phong, NhanVien nv) {
        this.hoaDonService = new HoaDon_DAO();
        this.matHangService = new MatHang_DAO();
        this.khachHangService = new KhachHang_DAO();
        this.phong = phong;
        this.nhanVien = nv;
        this.hoaDon = new HoaDon();
        initComponents();
        setModal(true);
        setResizable(false);
        setTitle("Tiếp nhận đặt phòng");
        buildDisplay();
    }

    private void buildDisplay() {
        loadDataForm();
        createTableMatHang();
        createTableCTHoaDon();
        createTxtKhachHang();
    }

    private void createTableMatHang() {
        loadDataTableMatHang();
    }

    private void createTableCTHoaDon() {
        tableCTHoaDon.getColumnModel().getColumn(2).setCellEditor(new SpinnerEditor(200));
        loadDataTableCTHoaDon();
    }

    private void loadDataForm() {
        if (phong != null | nhanVien != null) {
            tPTGBatDau.setTimeToNow();
            tPTGBatDau.addTimeChangeListener(new TimeChangeListener() {
                @Override
                public void timeChanged(TimeChangeEvent event) {
                    tPTGBatDauTimeChanged(event);
                }
            });
            tPTGBatDau.getSettings().setDisplaySpinnerButtons(true);
            tPTGBatDau.getSettings().setDisplayToggleTimeMenuButton(false);
            tPTGKetThuc.setTimeToNow();
            tPTGKetThuc.getSettings().setDisplaySpinnerButtons(true);
            tPTGKetThuc.getSettings().setDisplayToggleTimeMenuButton(false);
            txtTenPhong.setText(phong.getTenPhong());
            txtLoaiPhong.setText(phong.getLoaiPhong().getTenLoaiPhong());
            lblNhanVien.setText("Nhân viên: " + nhanVien.getTenNhanVien());
            lblRole.setText(nhanVien.getLoaiNhanVien().getTenLoaiNV());
            txtTongTienMatHang.setText(df.format(hoaDon.getTongTienMatHang()));
        }
    }

    private void loadDataTableMatHang() {
        List<MatHang> dsMatHang = matHangService.getDsMatHang();
        if (dsMatHang != null) {
            EventAdd event = (Object obj) -> {
                MatHang matHang = (MatHang) obj;
                try {
                    matHang.setsLTonKho(matHang.getsLTonKho() - 1);
                    hoaDon.themCT_HoaDon(matHang, 1, 0);
                    loadDataTableCTHoaDon();
                    ((DefaultTableModel) tableMatHang.getModel()).setValueAt(matHang.getsLTonKho(), tableMatHang.getSelectedRow(), 1);
                    txtTongTienMatHang.setText(df.format(hoaDon.getTongTienMatHang()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Mặt hàng này đã hết!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            };

            dsMatHang.forEach(matHang -> {
                ((DefaultTableModel) tableMatHang.getModel()).addRow(matHang.convertToRowTableInGDTiepNhanDatPhong(event));
            });
        }
    }

    private void loadDataTableCTHoaDon() {
        ((DefaultTableModel) tableCTHoaDon.getModel()).setRowCount(0);
        List<ChiTietHoaDon> dsChiTietHoaDon = hoaDon.getDsChiTietHoaDon();
        EventMinus event = () -> {
            try {
                int row = tableCTHoaDon.getSelectedRow();
                ChiTietHoaDon chiTietHoaDon = hoaDon.getDsChiTietHoaDon().get(row);
                MatHang matHang = chiTietHoaDon.getMatHang();
                matHang.setsLTonKho(matHang.getsLTonKho() + chiTietHoaDon.getSoLuong());
                hoaDon.getDsChiTietHoaDon().remove(chiTietHoaDon);
                loadDataTableCTHoaDon();
                ((DefaultTableModel) tableMatHang.getModel()).setValueAt(matHang.getsLTonKho(), tableMatHang.getSelectedRow(), 1);
            } catch (Exception ex) {
                Logger.getLogger(DL_TiepNhanDatPhong.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        dsChiTietHoaDon.forEach(chiTietHoaDon -> {
            ((DefaultTableModel) tableCTHoaDon.getModel()).addRow(chiTietHoaDon.convertToRowTableInTiepNhanHoaDon(event));
        });
    }

    private void createTxtKhachHang() {
        menu = new JPopupMenu();
        search = new PanelSearch("tenKhachHang");
        menu.setBorder(BorderFactory.createLineBorder(new Color(164, 164, 164)));
        menu.add(search);
        menu.setFocusable(false);
        search.addEventClick((Object obj) -> {
            KhachHang khachHang = (KhachHang) obj;
            menu.setVisible(false);
            txtTenKhachHang.setText(khachHang.getTenKhachHang());
            txtSdt.setText(khachHang.getSoDienThoai());
            txtCCCD.setText(khachHang.getCanCuocCD());
        });
    }

    private void searchKhachHang(JTextField textField) {
        try {
            String text = textField.getText().trim().toLowerCase();
            search.setData(khachHangService.getDsKhachHangLimit(text));
            if (search.getItemSize() > 0) {
                menu.show(textField, 0, textField.getHeight());
                menu.setPopupSize(menu.getWidth(), (search.getItemSize() * 30) + 2);
            } else {
                menu.setVisible(false);
            }
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(DL_TiepNhanDatPhong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDataTTKH() {
        KhachHang khachHang = (KhachHang) search.getSelectedRow();
        txtTenKhachHang.setText(khachHang.getTenKhachHang());
        txtCCCD.setText(khachHang.getCanCuocCD());
        txtSdt.setText(khachHang.getSoDienThoai());
        menu.setVisible(false);
    }
    
    private void tPTGBatDauTimeChanged(TimeChangeEvent event) {
        if(tPTGBatDau.getTime().compareTo(tPTGKetThuc.getTime()) == 1) {
            Time gioBatDau = Time.valueOf(tPTGBatDau.getTime());
            Time gioKetTHuc = Time.valueOf(tPTGKetThuc.getTime());
            
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateTimePicker1 = new com.github.lgooddatepicker.components.DateTimePicker();
        bg = new javax.swing.JPanel();
        pnlMain = new javax.swing.JPanel();
        pnlBottomBar = new javax.swing.JPanel();
        btnGiaoPhong = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblNhanVien = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        pnlMatHang = new javax.swing.JPanel();
        txtSearch = new gui.swing.textfield.MyTextField();
        cbLoaiDichVu = new javax.swing.JComboBox<>();
        lblLoaiDichVu = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMatHang = new gui.swing.table2.MyTableFlatlaf();
        pnlCenter = new javax.swing.JPanel();
        lblTenPhong = new javax.swing.JLabel();
        txtTenPhong = new javax.swing.JTextField();
        lblLoaiPhong = new javax.swing.JLabel();
        txtLoaiPhong = new javax.swing.JTextField();
        pnlTGThuePhong = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tPTGBatDau = new com.github.lgooddatepicker.components.TimePicker();
        tPTGKetThuc = new com.github.lgooddatepicker.components.TimePicker();
        myTextFieldPerUnit1 = new gui.swing.textfield.MyTextFieldPerUnit();
        pnlDatTruoc = new javax.swing.JPanel();
        txtDaCoc = new javax.swing.JTextField();
        lblDaCoc = new javax.swing.JLabel();
        btnExpand = new javax.swing.JToggleButton();
        pnlTTKH = new javax.swing.JPanel();
        lblTenKhachHang = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        lblCCCD = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        lblSdt = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        pnlTTHD = new javax.swing.JPanel();
        txtGiamGia = new javax.swing.JTextField();
        lblGiamGia = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        lblMaHoaDon = new javax.swing.JLabel();
        txtChietKhau = new javax.swing.JTextField();
        lblChietKhau = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCTHoaDon = new gui.swing.table2.MyTableFlatlaf();
        txtTongTienMatHang = new gui.swing.textfield.MyTextFieldPerUnit();
        pnlExpand = new javax.swing.JPanel();
        spPhieuDatPhong = new javax.swing.JScrollPane();
        tablePhieuDatPhong = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        bg.setLayout(new java.awt.BorderLayout());

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setLayout(new java.awt.BorderLayout());

        pnlBottomBar.setBackground(new java.awt.Color(255, 255, 255));
        pnlBottomBar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));

        btnGiaoPhong.setText("Giao phòng");

        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        lblNhanVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNhanVien.setText("Nhân viên: Đỗ Huy Hoàng");

        lblRole.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblRole.setForeground(new java.awt.Color(153, 153, 153));
        lblRole.setText("Quản lý");

        javax.swing.GroupLayout pnlBottomBarLayout = new javax.swing.GroupLayout(pnlBottomBar);
        pnlBottomBar.setLayout(pnlBottomBarLayout);
        pnlBottomBarLayout.setHorizontalGroup(
            pnlBottomBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBottomBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBottomBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNhanVien)
                    .addComponent(lblRole))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 610, Short.MAX_VALUE)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGiaoPhong)
                .addGap(22, 22, 22))
        );
        pnlBottomBarLayout.setVerticalGroup(
            pnlBottomBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBottomBarLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(pnlBottomBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGiaoPhong)
                    .addComponent(btnHuy))
                .addGap(12, 12, 12))
            .addGroup(pnlBottomBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRole)
                .addGap(5, 5, 5))
        );

        pnlMain.add(pnlBottomBar, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setBackground(new java.awt.Color(255, 255, 255));

        pnlMatHang.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)), "Cập nhật mặt hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        pnlMatHang.setOpaque(false);

        txtSearch.setBackgroundColor(new java.awt.Color(255, 255, 255));
        txtSearch.setBorderLine(true);
        txtSearch.setHint("Tìm kiếm...");
        txtSearch.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search_25px.png"))); // NOI18N

        cbLoaiDichVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đồ ăn", "Đồ uống" }));

        lblLoaiDichVu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblLoaiDichVu.setText("Loại dịch vụ");

        tableMatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên mặt hàng", "Tồn kho", "Giá bán", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMatHang.setFillsViewportHeight(true);
        tableMatHang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableMatHang.setRowHeight(30);
        tableMatHang.setShowGrid(true);
        tableMatHang.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableMatHang);
        if (tableMatHang.getColumnModel().getColumnCount() > 0) {
            tableMatHang.getColumnModel().getColumn(0).setPreferredWidth(200);
            tableMatHang.getColumnModel().getColumn(1).setResizable(false);
            tableMatHang.getColumnModel().getColumn(1).setPreferredWidth(60);
            tableMatHang.getColumnModel().getColumn(2).setResizable(false);
            tableMatHang.getColumnModel().getColumn(2).setPreferredWidth(100);
            tableMatHang.getColumnModel().getColumn(3).setResizable(false);
            tableMatHang.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        javax.swing.GroupLayout pnlMatHangLayout = new javax.swing.GroupLayout(pnlMatHang);
        pnlMatHang.setLayout(pnlMatHangLayout);
        pnlMatHangLayout.setHorizontalGroup(
            pnlMatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
            .addGroup(pnlMatHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMatHangLayout.createSequentialGroup()
                        .addComponent(lblLoaiDichVu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbLoaiDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlMatHangLayout.setVerticalGroup(
            pnlMatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMatHangLayout.createSequentialGroup()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLoaiDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoaiDichVu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(pnlMatHang);

        pnlCenter.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 1, new java.awt.Color(204, 204, 204)));
        pnlCenter.setOpaque(false);

        lblTenPhong.setText("Tên phòng");

        txtTenPhong.setText("Phòng 001");
        txtTenPhong.setEnabled(false);

        lblLoaiPhong.setText("Loại phòng");

        txtLoaiPhong.setText("Phòng thường");
        txtLoaiPhong.setEnabled(false);

        pnlTGThuePhong.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Thời gian thuê phòng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        pnlTGThuePhong.setOpaque(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Từ");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_advance_20px_3.png"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Tiền phòng dự kiến");

        myTextFieldPerUnit1.setEnabled(false);
        myTextFieldPerUnit1.setUnit("VND");

        javax.swing.GroupLayout pnlTGThuePhongLayout = new javax.swing.GroupLayout(pnlTGThuePhong);
        pnlTGThuePhong.setLayout(pnlTGThuePhongLayout);
        pnlTGThuePhongLayout.setHorizontalGroup(
            pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTGThuePhongLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlTGThuePhongLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(myTextFieldPerUnit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlTGThuePhongLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tPTGKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addGap(12, 12, 12)
                        .addComponent(tPTGBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33))
        );
        pnlTGThuePhongLayout.setVerticalGroup(
            pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTGThuePhongLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tPTGBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(tPTGKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(myTextFieldPerUnit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDatTruoc.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Đặt trước", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        pnlDatTruoc.setOpaque(false);

        txtDaCoc.setEnabled(false);

        lblDaCoc.setText("Đã cọc");

        javax.swing.GroupLayout pnlDatTruocLayout = new javax.swing.GroupLayout(pnlDatTruoc);
        pnlDatTruoc.setLayout(pnlDatTruocLayout);
        pnlDatTruocLayout.setHorizontalGroup(
            pnlDatTruocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatTruocLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDaCoc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDaCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlDatTruocLayout.setVerticalGroup(
            pnlDatTruocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatTruocLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatTruocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDaCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDaCoc))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        btnExpand.setText("Phiếu đặt phòng");
        btnExpand.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                btnExpandItemStateChanged(evt);
            }
        });

        pnlTTKH.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Thông tin khách hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        pnlTTKH.setOpaque(false);

        lblTenKhachHang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTenKhachHang.setText("Tên khách hàng");

        txtTenKhachHang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTenKhachHang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTenKhachHangFocusGained(evt);
            }
        });
        txtTenKhachHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTenKhachHangKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenKhachHangKeyReleased(evt);
            }
        });

        lblCCCD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCCCD.setText("CCCD");

        txtCCCD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCCCD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCCCDFocusGained(evt);
            }
        });
        txtCCCD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCCCDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCCCDKeyReleased(evt);
            }
        });

        lblSdt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSdt.setText("Số điện thoại");

        txtSdt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSdt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSdtFocusGained(evt);
            }
        });
        txtSdt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSdtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSdtKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTTKHLayout = new javax.swing.GroupLayout(pnlTTKH);
        pnlTTKH.setLayout(pnlTTKHLayout);
        pnlTTKHLayout.setHorizontalGroup(
            pnlTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTTKHLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTenKhachHang)
                    .addComponent(lblCCCD)
                    .addComponent(lblSdt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlTTKHLayout.setVerticalGroup(
            pnlTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTTKHLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenKhachHang))
                .addGap(18, 18, 18)
                .addGroup(pnlTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCCCD))
                .addGap(18, 18, 18)
                .addGroup(pnlTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSdt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlTTHD.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Thông tin hóa đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        pnlTTHD.setOpaque(false);

        txtGiamGia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblGiamGia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblGiamGia.setText("Giảm giá");

        txtMaHoaDon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblMaHoaDon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMaHoaDon.setText("Mã hóa đơn");

        txtChietKhau.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblChietKhau.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblChietKhau.setText("Chiết khấu");

        javax.swing.GroupLayout pnlTTHDLayout = new javax.swing.GroupLayout(pnlTTHD);
        pnlTTHD.setLayout(pnlTTHDLayout);
        pnlTTHDLayout.setHorizontalGroup(
            pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTTHDLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMaHoaDon)
                    .addComponent(lblGiamGia)
                    .addComponent(lblChietKhau))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                    .addComponent(txtMaHoaDon)
                    .addComponent(txtChietKhau))
                .addContainerGap())
        );
        pnlTTHDLayout.setVerticalGroup(
            pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTTHDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGiamGia)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaHoaDon)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChietKhau)
                    .addComponent(txtChietKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Tổng tiền mặt hàng");

        tableCTHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên mặt hàng", "Số lượng", "Đơn giá", "Thành tiền", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCTHoaDon.setFillsViewportHeight(true);
        tableCTHoaDon.setRowHeight(30);
        tableCTHoaDon.setShowGrid(true);
        tableCTHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableCTHoaDonKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tableCTHoaDon);
        if (tableCTHoaDon.getColumnModel().getColumnCount() > 0) {
            tableCTHoaDon.getColumnModel().getColumn(0).setResizable(false);
            tableCTHoaDon.getColumnModel().getColumn(0).setPreferredWidth(60);
            tableCTHoaDon.getColumnModel().getColumn(1).setPreferredWidth(200);
            tableCTHoaDon.getColumnModel().getColumn(2).setResizable(false);
            tableCTHoaDon.getColumnModel().getColumn(2).setPreferredWidth(60);
            tableCTHoaDon.getColumnModel().getColumn(3).setResizable(false);
            tableCTHoaDon.getColumnModel().getColumn(3).setPreferredWidth(100);
            tableCTHoaDon.getColumnModel().getColumn(4).setResizable(false);
            tableCTHoaDon.getColumnModel().getColumn(4).setPreferredWidth(100);
            tableCTHoaDon.getColumnModel().getColumn(5).setResizable(false);
        }

        txtTongTienMatHang.setEnabled(false);
        txtTongTienMatHang.setUnit("VND");

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addComponent(lblTenPhong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                        .addGap(24, 24, 24)
                        .addComponent(btnExpand))
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongTienMatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlTGThuePhong, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(pnlTTKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTTHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDatTruoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenPhong)
                    .addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoaiPhong)
                    .addComponent(txtLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExpand))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTongTienMatHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTTHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDatTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTGThuePhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(pnlCenter);

        pnlMain.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        bg.add(pnlMain, java.awt.BorderLayout.CENTER);

        pnlExpand.setBackground(new java.awt.Color(255, 255, 255));
        pnlExpand.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)), "Phiếu đặt phòng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        tablePhieuDatPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu", "Khách hàng", "Giờ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePhieuDatPhong.setFillsViewportHeight(true);
        spPhieuDatPhong.setViewportView(tablePhieuDatPhong);
        if (tablePhieuDatPhong.getColumnModel().getColumnCount() > 0) {
            tablePhieuDatPhong.getColumnModel().getColumn(0).setResizable(false);
            tablePhieuDatPhong.getColumnModel().getColumn(1).setResizable(false);
            tablePhieuDatPhong.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout pnlExpandLayout = new javax.swing.GroupLayout(pnlExpand);
        pnlExpand.setLayout(pnlExpandLayout);
        pnlExpandLayout.setHorizontalGroup(
            pnlExpandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExpandLayout.createSequentialGroup()
                .addComponent(spPhieuDatPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pnlExpandLayout.setVerticalGroup(
            pnlExpandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlExpandLayout.createSequentialGroup()
                .addComponent(spPhieuDatPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                .addContainerGap())
        );

        bg.add(pnlExpand, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1026, 616));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnExpandItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_btnExpandItemStateChanged
        int state = evt.getStateChange();
        if (state == ItemEvent.SELECTED) {
            setSize(new java.awt.Dimension(getPreferredSize()));
            setLocationRelativeTo(null);
        } else {
            setSize(new java.awt.Dimension(1026, 611));
            setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_btnExpandItemStateChanged

    private void tableCTHoaDonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableCTHoaDonKeyReleased
        int row = tableCTHoaDon.getSelectedRow();
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            int sl = (int) ((DefaultTableModel) tableCTHoaDon.getModel()).getValueAt(row, 2);
            MatHang matHang = hoaDon.getDsChiTietHoaDon().get(row).getMatHang();
            try {
                matHang.setsLTonKho(matHangService.getMatHang(matHang.getMaMatHang()).getsLTonKho() - sl);
                hoaDon.getDsChiTietHoaDon().get(tableCTHoaDon.getSelectedRow()).setSoLuong(0);
                hoaDon.themCT_HoaDon(matHang, sl, 0);
                loadDataTableCTHoaDon();
                for (int i = 0; i < tableMatHang.getRowCount(); i++) {
                    ModelAdd data = (ModelAdd) ((DefaultTableModel) tableMatHang.getModel()).getValueAt(i, 3);
                    MatHang mh = (MatHang) data.getObj();
                    if (mh.equals(matHang)) {
                        ((DefaultTableModel) tableMatHang.getModel()).setValueAt(matHang.getsLTonKho(), i, 1);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Mặt hàng không đủ số lượng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                loadDataTableCTHoaDon();
            }
        } else {

        }
    }//GEN-LAST:event_tableCTHoaDonKeyReleased

    private void txtTenKhachHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKhachHangKeyReleased
        if (evt.getKeyCode() != KeyEvent.VK_UP && evt.getKeyCode() != KeyEvent.VK_DOWN && evt.getKeyCode() != KeyEvent.VK_ENTER) {
            searchKhachHang(txtTenKhachHang);
        }
    }//GEN-LAST:event_txtTenKhachHangKeyReleased

    private void txtTenKhachHangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKhachHangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            search.keyUp();
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            search.keyDown();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loadDataTTKH();
        }
    }//GEN-LAST:event_txtTenKhachHangKeyPressed

    private void txtCCCDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCCCDKeyReleased
        if (evt.getKeyCode() != KeyEvent.VK_UP && evt.getKeyCode() != KeyEvent.VK_DOWN && evt.getKeyCode() != KeyEvent.VK_ENTER) {
            searchKhachHang(txtCCCD);
        }
    }//GEN-LAST:event_txtCCCDKeyReleased

    private void txtCCCDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCCCDKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            search.keyUp();
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            search.keyDown();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loadDataTTKH();
        }
    }//GEN-LAST:event_txtCCCDKeyPressed

    private void txtTenKhachHangFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenKhachHangFocusGained
        search.setColumnName("tenKhachHang");
        if (search.getItemSize() > 0) {
            menu.show(txtTenKhachHang, 0, txtTenKhachHang.getHeight());
            search.clearSelected();
            searchKhachHang(txtTenKhachHang);

        }
    }//GEN-LAST:event_txtTenKhachHangFocusGained

    private void txtCCCDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCCCDFocusGained
        search.setColumnName("canCuocCD");
        if (search.getItemSize() > 0) {
            menu.show(txtCCCD, 0, txtCCCD.getHeight());
            search.clearSelected();
            searchKhachHang(txtCCCD);
        }
    }//GEN-LAST:event_txtCCCDFocusGained

    private void txtSdtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSdtFocusGained
        search.setColumnName("soDienThoai");
        if (search.getItemSize() > 0) {
            menu.show(txtSdt, 0, txtSdt.getHeight());
            search.clearSelected();
            searchKhachHang(txtSdt);
        }
    }//GEN-LAST:event_txtSdtFocusGained

    private void txtSdtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSdtKeyReleased
        if (evt.getKeyCode() != KeyEvent.VK_UP && evt.getKeyCode() != KeyEvent.VK_DOWN && evt.getKeyCode() != KeyEvent.VK_ENTER) {
            searchKhachHang(txtSdt);
        }
    }//GEN-LAST:event_txtSdtKeyReleased

    private void txtSdtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSdtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            search.keyUp();
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            search.keyDown();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loadDataTTKH();
        }
    }//GEN-LAST:event_txtSdtKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JToggleButton btnExpand;
    private javax.swing.JButton btnGiaoPhong;
    private javax.swing.JButton btnHuy;
    private javax.swing.JComboBox<String> cbLoaiDichVu;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblCCCD;
    private javax.swing.JLabel lblChietKhau;
    private javax.swing.JLabel lblDaCoc;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblLoaiDichVu;
    private javax.swing.JLabel lblLoaiPhong;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblSdt;
    private javax.swing.JLabel lblTenKhachHang;
    private javax.swing.JLabel lblTenPhong;
    private gui.swing.textfield.MyTextFieldPerUnit myTextFieldPerUnit1;
    private javax.swing.JPanel pnlBottomBar;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlDatTruoc;
    private javax.swing.JPanel pnlExpand;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlMatHang;
    private javax.swing.JPanel pnlTGThuePhong;
    private javax.swing.JPanel pnlTTHD;
    private javax.swing.JPanel pnlTTKH;
    private javax.swing.JScrollPane spPhieuDatPhong;
    private com.github.lgooddatepicker.components.TimePicker tPTGBatDau;
    private com.github.lgooddatepicker.components.TimePicker tPTGKetThuc;
    private gui.swing.table2.MyTableFlatlaf tableCTHoaDon;
    private gui.swing.table2.MyTableFlatlaf tableMatHang;
    private javax.swing.JTable tablePhieuDatPhong;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtChietKhau;
    private javax.swing.JTextField txtDaCoc;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtLoaiPhong;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtSdt;
    private gui.swing.textfield.MyTextField txtSearch;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTenPhong;
    private gui.swing.textfield.MyTextFieldPerUnit txtTongTienMatHang;
    // End of variables declaration//GEN-END:variables
}
