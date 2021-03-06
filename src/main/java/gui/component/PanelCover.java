package gui.component;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;

import gui.swing.image.BackgroundImage;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import net.miginfocom.swing.MigLayout;

public class PanelCover extends BackgroundImage {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private DecimalFormat df = new DecimalFormat("##0.###");
    private DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    private MigLayout layout;
    private JLabel title;
    private JLabel slogan;
    private JLabel slogan1;

    public PanelCover(Icon icon) {
        super(icon);
        initComponents();
        buildPanelCover();
    }

    public PanelCover(Icon icon, Color ftColor) {
        super(icon, ftColor);
        initComponents();
        buildPanelCover();
    }
    /**
     * Xây dựng pane cover
     */
    private void buildPanelCover() {
        
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        layout = new MigLayout("wrap", "push[center]push", "push[]10[]10[]100");
        setLayout(layout);
        title = new JLabel("KARAOKE FOURH");
        title.setFont(new Font("sansserif", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        add(title);

        slogan = new JLabel("Enjoy as you never did before");
        slogan.setFont(new Font("sansserif", Font.ITALIC|Font.BOLD, 15));
        slogan.setForeground(Color.WHITE);
        add(slogan);

        slogan1 = new JLabel("Music brings joys to life");
        slogan1.setFont(new Font("sansserif", Font.ITALIC|Font.BOLD, 15));
        slogan1.setForeground(Color.WHITE);
        add(slogan1);
        
    }

    private void initComponents() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );
    }
    
    public void forgotPassLeft(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0"); // Đặt phần đệm cho thành phần theo pixel tuyệt đối, thành phần sẽ không bị ảnh hưởng đến layout
        layout.setComponentConstraints(slogan, "pad 0 -" + v + "% 0 0"); // pad = trên trái dưới phải
        layout.setComponentConstraints(slogan1, "pad 0 -" + v + "% 0 0");
    }
    
    public void forgotPassRight(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(slogan, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(slogan1, "pad 0 -" + v + "% 0 0");
        
    }
    
    public void loginLeft(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 "+ v + "% 0 " + v + "%");
        layout.setComponentConstraints(slogan, "pad 0 "+ v + "% 0 " + v + "%");
        layout.setComponentConstraints(slogan1, "pad 0 "+ v + "% 0 " + v + "%");
    }
    
    public void loginRight(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 "+ v + "% 0 " + v + "%");
        layout.setComponentConstraints(slogan, "pad 0 "+ v + "% 0 " + v + "%");
        layout.setComponentConstraints(slogan1, "pad 0 "+ v + "% 0 " + v + "%");
    }
    
    private void login(boolean isLoginLeft) {
    }
}
