package gui;

import dao.KhachHang_DAO;
import entity.KhachHang;
import gui.swing.button.Button;
import gui.swing.event.EventPagination;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import service.KhachHangService;

public class GD_QuanLyKhachHang extends javax.swing.JPanel implements ActionListener, KeyListener{
    List<KhachHang> dsKhachHang ;
    private KhachHangService khachHangService;
    private JTextField txtTimKiem;
    private Button btnLamMoi;


    /**
     * Creates new form GD_KhachHang
     */
    public GD_QuanLyKhachHang() {
        initComponents();
        buildGD();
        
    }
    
    private void buildGD(){
        khachHangService = new KhachHang_DAO();

        String fontName = "sansserif";
        int fontStyle = Font.PLAIN;
        int fontSize = 14;
        Color colorBtn = new Color(184, 238, 241);
        
        pnlTop.setLayout(new MigLayout("", "push[center]5[center] 20[center]push", "60[center]10"));
        pnlTop.add(createPanelTitle(), "span,pos 0al 0al 100% n, h 40!");
      
        JLabel lblKhachHang = new JLabel("Nhập tên/ Căn cước/ Số điện thoại");
        lblKhachHang.setFont(new Font(fontName, fontStyle, fontSize));
        pnlTop.add(lblKhachHang);
        
        /*Ô nhập thông tin tìm kiếm*/
        txtTimKiem = new JTextField();
        txtTimKiem.setFont(new Font(fontName, fontStyle, fontSize));
        pnlTop.add(txtTimKiem, "w 40%, h 36!");
        
        btnLamMoi = new Button("Làm mới");
        btnLamMoi.setFont(new Font("sansserif", Font.BOLD, 12));
        btnLamMoi.setForeground(Color.WHITE);
        btnLamMoi.setBackground(new Color(54, 88, 153));
        btnLamMoi.setBorderline(true);
        btnLamMoi.setBorderRadius(5);
        pnlTop.add(btnLamMoi, "w 90!, h 30!");
        
        
        btnLamMoi.addActionListener(this);
        txtTimKiem.addKeyListener(this);
        tblKhachHang.addKeyListener(this);
       
        createTable();
        createPanelBottom();
        setOpaque(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new gui.swing.panel.PanelShadow();
        pnlBottom = new gui.swing.panel.PanelShadow();
        srcKhachHang = new javax.swing.JScrollPane();
        tblKhachHang = new gui.swing.table.MyTableFlatlaf();
        pnlBottom_Page = new javax.swing.JPanel();
        pnlPage = new gui.swing.table.PanelPage();

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        pnlTop.setShadowOpacity(0.3F);
        pnlTop.setShadowSize(2);
        pnlTop.setShadowType(gui.swing.graphics.ShadowType.TOP);

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 247, Short.MAX_VALUE)
        );

        pnlBottom.setBackground(new java.awt.Color(255, 255, 255));
        pnlBottom.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 5, 5));
        pnlBottom.setShadowOpacity(0.3F);
        pnlBottom.setShadowSize(2);
        pnlBottom.setShadowType(gui.swing.graphics.ShadowType.TOP);
        pnlBottom.setLayout(new java.awt.BorderLayout());

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Mã khách hàng", "Tên khách hàng", "CCCD", "Số điện thoại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tblKhachHang.setRowHeight(40);
        tblKhachHang.setSelectionBackground(new java.awt.Color(239, 244, 255));
        tblKhachHang.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblKhachHang.setShowGrid(true);
        tblKhachHang.setShowVerticalLines(false);
        srcKhachHang.setViewportView(tblKhachHang);
        if (tblKhachHang.getColumnModel().getColumnCount() > 0) {
            tblKhachHang.getColumnModel().getColumn(0).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblKhachHang.getColumnModel().getColumn(1).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(2).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(2).setPreferredWidth(250);
            tblKhachHang.getColumnModel().getColumn(3).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(3).setPreferredWidth(200);
            tblKhachHang.getColumnModel().getColumn(4).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(4).setPreferredWidth(200);
        }

        pnlBottom.add(srcKhachHang, java.awt.BorderLayout.CENTER);

        pnlBottom_Page.setOpaque(false);
        pnlBottom_Page.setPreferredSize(new java.awt.Dimension(1022, 32));

        pnlPage.setOpaque(false);

        javax.swing.GroupLayout pnlBottom_PageLayout = new javax.swing.GroupLayout(pnlBottom_Page);
        pnlBottom_Page.setLayout(pnlBottom_PageLayout);
        pnlBottom_PageLayout.setHorizontalGroup(
            pnlBottom_PageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPage, javax.swing.GroupLayout.DEFAULT_SIZE, 1022, Short.MAX_VALUE)
        );
        pnlBottom_PageLayout.setVerticalGroup(
            pnlBottom_PageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPage, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        pnlBottom.add(pnlBottom_Page, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private JPanel createPanelTitle() {
        JPanel pnlTitle = new JPanel();
        pnlTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 0.1f)));
        pnlTitle.setOpaque(false);
        pnlTitle.setLayout(new MigLayout("fill", "", ""));
        JLabel lblTitle = new JLabel();
        lblTitle.setText("Quản lý khách hàng");
        lblTitle.setFont(new Font("sansserif", Font.PLAIN, 16));
        lblTitle.setForeground(new Color(68, 68, 68));
        pnlTitle.add(lblTitle);
        return  pnlTitle;
    }
    
    private void xuLySuKien(){
       tblKhachHang.getModel().addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    try{
                        int col = e.getColumn();
                        int row = e.getFirstRow();
                        if(col==4){
                            if(valiDataSDT(tblKhachHang.getValueAt(row, 4).toString())){
                                String soDienThoai = tblKhachHang.getValueAt(row, 4).toString().trim();
                                String maKhachHang = tblKhachHang.getValueAt(row, 1).toString().trim();
                                if(khachHangService.capNhatKhachHang(maKhachHang, soDienThoai)) {
                                    JOptionPane.showMessageDialog(GD_QuanLyKhachHang.this, "Cập nhật số điện thoại khách hàng thành công.");
                                    xoaDuLieu();
                                    loadData(pnlPage.getCurrentIndex());
                                }else{
                                    JOptionPane.showMessageDialog(GD_QuanLyKhachHang.this, "Cập nhật số điện thoại khách hàng không thành công");         
                                }
                            }else{
                                JOptionPane.showMessageDialog(GD_QuanLyKhachHang.this, "Số điện thoại khách hàng không hợp lệ"); 
                                xoaDuLieu();
                                loadData(pnlPage.getCurrentIndex());
                            }
                        }
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(GD_QuanLyKhachHang.this, "Lựa chọn không phù hợp"); 
                    }
                }
            });
    }
    private void loadData(int numPage) {
        dsKhachHang = khachHangService.getDSKhachHang(numPage);
        xoaDuLieu();
        if (dsKhachHang != null) {
            dsKhachHang.forEach((kh) -> {
                ((DefaultTableModel) tblKhachHang.getModel()).addRow(new Object[] {new JCheckBox(),kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getCanCuocCD(), kh.getSoDienThoai()});
            });
        }
        tblKhachHang.repaint();
        tblKhachHang.revalidate();
    }
    
    
    public void xoaDuLieu(){
        DefaultTableModel df = (DefaultTableModel) tblKhachHang.getModel();
        df.setRowCount(0);
    }
    
    public void taiLaiDuLieu(int numPage){
        ((DefaultTableModel) tblKhachHang.getModel()).setRowCount(0);
        String tuKhoa = txtTimKiem.getText().trim();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<KhachHang> dsKhachHang = khachHangService.getDSKhachHangByTuKhoa(tuKhoa,numPage);
                if (dsKhachHang != null) {
                    dsKhachHang.forEach((kh) -> {
                        ((DefaultTableModel) tblKhachHang.getModel()).addRow(new Object[] {new JCheckBox(),kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getCanCuocCD(), kh.getSoDienThoai()});
                    });
                }
                tblKhachHang.repaint();
                tblKhachHang.revalidate();
            }
        }).start();
    }
    
    private void createTable() {
        tblKhachHang.getTableHeader().setFont(new Font("Sansserif", Font.BOLD, 14));
        loadData(pnlPage.getCurrentIndex());
        xuLySuKien();
    }
    
    private void loadPage() {
        String tuKhoa = txtTimKiem.getText().trim();
        int soLuongKhachHang= khachHangService.getSoLuongKhachHangByTuKhoa(tuKhoa);
        pnlPage.init(soLuongKhachHang% 20 == 0 ? soLuongKhachHang / 20 : (soLuongKhachHang / 20) + 1);
    }
    
     private void createPanelBottom() {
        pnlPage.addEventPagination(new EventPagination() {
            @Override
            public void onClick(int pageClick) {
                loadData(pageClick);
            }
        });
        loadPage();
    }

    private boolean valiDataSDT(String soDienThoai){
        if(!(soDienThoai.matches("^(09|03|07|08|05)([0-9]{8})"))) {
            return false;
        }
        return true;
    }    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.panel.PanelShadow pnlBottom;
    private javax.swing.JPanel pnlBottom_Page;
    private gui.swing.table.PanelPage pnlPage;
    private gui.swing.panel.PanelShadow pnlTop;
    private javax.swing.JScrollPane srcKhachHang;
    private gui.swing.table.MyTableFlatlaf tblKhachHang;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj.equals(btnLamMoi)){
           txtTimKiem.setText("");
           dsKhachHang = khachHangService.getDSKhachHang(pnlPage.getCurrentIndex());
           xoaDuLieu();
           loadPage();
           taiLaiDuLieu(pnlPage.getCurrentIndex());
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        Object obj = arg0.getSource();
        if(obj.equals(txtTimKiem)){
            xoaDuLieu();
            //loadData(0);
            loadPage();
            taiLaiDuLieu(pnlPage.getCurrentIndex());
        }
    }

    
}
