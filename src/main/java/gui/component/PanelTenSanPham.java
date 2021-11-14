/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.component;

import dao.NhaCungCapVaNhapHang_DAO;
import entity.LoaiDichVu;
import entity.MatHang;
import gui.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import objectcombobox.ObjectComboBox;

/**
 *
 * @author 84975
 */
public class PanelTenSanPham extends javax.swing.JPanel {
    private NhaCungCapVaNhapHang_DAO nhaCungCapVaNhapHang_DAO ;
    /**
     * Creates new form PanelTenSanPham
     */
    public PanelTenSanPham() {
        initComponents();
        config();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSPChuaCo = new gui.swing.textfield.MyTextField();
        cbSPDaCo = new gui.swing.textfield.MyComboBox();

        setLayout(new java.awt.CardLayout());

        txtSPChuaCo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSPChuaCoActionPerformed(evt);
            }
        });
        add(txtSPChuaCo, "card2");
        add(cbSPDaCo, "card3");
    }// </editor-fold>//GEN-END:initComponents

    private void txtSPChuaCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSPChuaCoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSPChuaCoActionPerformed
    
    public void config(){ 
        this.setOpaque(false);
        txtSPChuaCo.setBorderLine(true);

        cbSPDaCo.setBorderLine(true);
        cbSPDaCo.setBorderRadius(10);
        
        cbSPDaCo.addItem("Chọn sản phẩm");

        nhaCungCapVaNhapHang_DAO = new NhaCungCapVaNhapHang_DAO();
    }
    
    public void setTypeSP(boolean b){
        if(b){
            cbSPDaCo.setVisible(false);
            txtSPChuaCo.setVisible(true);
        }else{
            txtSPChuaCo.setVisible(false);
            cbSPDaCo.setVisible(true);
        }
    }
    
    public void setComboboxItem(String id){
        cbSPDaCo.removeAllItems();
        cbSPDaCo.addItem("Chọn sản phẩm");
        if(!id.equalsIgnoreCase("")){
            List<MatHang> listMH = nhaCungCapVaNhapHang_DAO.getDanhSachMatHangByLoaiDichVu(id);
            for (int i = 0; i < listMH.size(); i++) {
                MatHang dv = listMH.get(i);
                cbSPDaCo.addItem(new ObjectComboBox(dv.getTenMatHang(),dv.getMaMatHang()));  
            } 
        } 
    }
    
    public String getTenSanPhamMoi(){
        return txtSPChuaCo.getText();
    }
    
    public int getSelectedIndex(){
        return cbSPDaCo.getSelectedIndex();
    }
    public String getTenSanPhamCu(){
        return cbSPDaCo.getSelectedItem().toString();
    }
    public String getMaSanPhamCu(){
        ObjectComboBox dv = (ObjectComboBox)cbSPDaCo.getSelectedItem();
        return dv.getMa();
    }
    
    public void requestFocus(){
        txtSPChuaCo.requestFocus();
        txtSPChuaCo.selectAll();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.textfield.MyComboBox cbSPDaCo;
    private gui.swing.textfield.MyTextField txtSPChuaCo;
    // End of variables declaration//GEN-END:variables
}
