/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import gui.swing.button.Button;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Hao
 */
public class GD_KhachHang extends javax.swing.JPanel {

    /**
     * Creates new form GD_KhachHang
     */
    public GD_KhachHang() {
        initComponents();
        buildGD();
    }

     public void buildGD(){
        
        String fontName = "sansserif";
        int fontStyle = Font.PLAIN;
        int fontSize = 16;
        Color colorBtn = new Color(184, 238, 241);
        
        pnlTop.setPreferredSize(new Dimension(1119, 255));
        pnlTop.setLayout(new MigLayout("", "3[center] 20 [center]3", "6[center]5"));
        
        
       
       /**
        * Begin: group Thông tin khách hàng
        */        
        JPanel pnlThongTinKH = new JPanel();
        pnlThongTinKH.setOpaque(false);
        pnlThongTinKH.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Thông tin khách hàng", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), Color.gray));
        pnlThongTinKH.setLayout(new MigLayout("", "10[center]10[center] 10 [center][center]10", "[center]10[center]10[center]10[center]10[center] 20[center]"));
        pnlTop.add(pnlThongTinKH, "w 65%, h 250!");
        
        //Mã khách hàng
        JLabel lblMaKH = new JLabel("Mã khách hàng:");
        lblMaKH.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblMaKH, "align right");
        
        JTextField txtMaKH = new MyTextField();
        txtMaKH.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(txtMaKH, "w 80%, h 36!");
        
        //Tên khách hàng
        JLabel lblTenKH = new JLabel("Tên khách hàng:");
        lblTenKH.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblTenKH, "align right");
        
        JTextField txtTenKH = new MyTextField();
        txtTenKH.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(txtTenKH, "w 80%, h 36!, wrap");
        
        //Căn cước công dân của khách hàng
        JLabel lblCCCD = new JLabel("CCCD:");
        lblCCCD.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblCCCD, "align right");
        
        JTextField txtCCCD = new MyTextField();
        txtCCCD.setFont(new Font("sansserif", Font.PLAIN, 12));
        pnlThongTinKH.add(txtCCCD, "w 80%, h 36!");
        
        //Số điện thoại của khách hàng
        JLabel lblSDT = new JLabel("Số điện thoại:");
        lblSDT.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblSDT, "align right");
        
        JTextField txtSDT = new MyTextField();
        txtSDT.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(txtSDT, "w 80%, h 36!, wrap");
        
        //Địa chỉ của khách hàng
        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        lblDiaChi.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblDiaChi, "align right");
        
        JTextField txtDiaChi = new MyTextField();
        txtDiaChi.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(txtDiaChi, "w 80%, h 36!, wrap");
        
        //Panel nút chức năng
        JPanel pnlButton = new JPanel();
        pnlButton.setOpaque(false);
        pnlButton.setLayout(new MigLayout("", "push[]20[]20[]20[]0", "push[]push"));
        pnlThongTinKH.add(pnlButton, "span , w 100%, h 36!");

        // Nút Xóa
        Button btnXoaKH = new Button("Xóa");
        btnXoaKH.setFont(new Font(fontName, fontStyle, fontSize));
        btnXoaKH.setBackground(colorBtn);
        pnlButton.add(btnXoaKH, "w 100!, h 36!,growx");

        // Nút Sửa
        Button btnSuaKH = new Button("Sửa");
        btnSuaKH.setFont(new Font(fontName, fontStyle, fontSize));
        btnSuaKH.setBackground(colorBtn);
        pnlButton.add(btnSuaKH, "w 100!, h 36!");

        // Nút Làm mới
        Button btnLamMoi = new Button("Làm mới");
        btnLamMoi.setFont(new Font(fontName, fontStyle, fontSize));
        btnLamMoi.setBackground(colorBtn);
        pnlButton.add(btnLamMoi, "w 100!, h 36!");
        /**
         * End: group thông tin khách hàng
         */
        
        /**
         * Begin: Tìm kiếm khách hàng
         */
        JPanel pnlTimKiemKH = new JPanel();
        pnlTimKiemKH.setOpaque(false);
        pnlTimKiemKH.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Tìm kiếm", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), Color.gray));
        pnlTimKiemKH.setLayout(new MigLayout("", "[center][center]", "[]10[]10[]10[]10[]20[]"));
        pnlTop.add(pnlTimKiemKH, "w 35%, h 250!");
      
        /*Ô nhập thông tin tìm kiếm*/
        JTextField txtTimKiem = new MyTextField();
        txtTimKiem.setFont(new Font(fontName, fontStyle, fontSize));
        pnlTimKiemKH.add(txtTimKiem, "w 50%, h 36!");
       
        /*Chọn thông thêm tiêu chí để lọc thông tin*/
        JComboBox<String> cmbTimKiem = new JComboBox<>();
        cmbTimKiem.setFont(new Font(fontName, fontStyle, fontSize));
        cmbTimKiem.addItem("--Chọn--");
        pnlTimKiemKH.add(cmbTimKiem, "w 40%,h 36!, wrap");
        
        /*Click vào button để tìm kiếm*/
        Button btnTimKiem = new Button("Tìm kiếm");
        btnTimKiem.setFont(new Font(fontName, fontStyle, fontSize));
        btnTimKiem.setBackground(colorBtn);
        pnlTimKiemKH.add(btnTimKiem," pos 0.9al 0.7al n n,w 100!, h 36!");
        /**
         * End: Group tìm kiếm khách hàng
         */
        
        /*Begin: group danh sách Khách Hàng*/
        pnlBottom.setLayout(new MigLayout());

        pnlBottom.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 2), "Danh sách khách hàng", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), Color.gray));

        pnlBottom.setPreferredSize(new Dimension(1119, 1110));
        /*End: group danh sách Khách Hàng */

        
        setPreferredSize(new Dimension(1119, 1500));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new gui.panel.PanelShadow();
        pnlBottom = new gui.panel.PanelShadow();
        lblTitle = new javax.swing.JLabel();

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setShadowOpacity(0.3F);
        pnlTop.setShadowSize(3);

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 231, Short.MAX_VALUE)
        );

        pnlBottom.setBackground(new java.awt.Color(255, 255, 255));
        pnlBottom.setShadowOpacity(0.3F);
        pnlBottom.setShadowSize(3);
        pnlBottom.setShadowType(gui.dropshadow.ShadowType.TOP);

        javax.swing.GroupLayout pnlBottomLayout = new javax.swing.GroupLayout(pnlBottom);
        pnlBottom.setLayout(pnlBottomLayout);
        pnlBottomLayout.setHorizontalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1102, Short.MAX_VALUE)
        );
        pnlBottomLayout.setVerticalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );

        lblTitle.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(4, 72, 210));
        lblTitle.setText("Quản Lý Khách Hàng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitle;
    private gui.panel.PanelShadow pnlBottom;
    private gui.panel.PanelShadow pnlTop;
    // End of variables declaration//GEN-END:variables
}
