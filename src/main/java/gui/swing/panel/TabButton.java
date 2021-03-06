/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.swing.panel;

import gui.swing.event.EventTabSelected;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Admin
 */
public class TabButton extends JPanel {
    private EventTabSelected event;
    private List<TabButtonItem> tabButtonItems  = new ArrayList<>();

    public EventTabSelected getEvent() {
        return event;
    }

    public void setEvent(EventTabSelected event) {
        this.event = event;
    }
    
    public TabButton() {
        setOpaque(false);
        setLayout(new MigLayout("insets 0", "0[]0", "0[]0"));
    }
    
    public void addTabButtonItem(String name) {
        TabButtonItem tabButtonItem = new TabButtonItem(name, getComponentCount());
        tabButtonItem.setSelectedTab(getComponentCount() == 0 ? (true):(false));
        tabButtonItem.setEvent(event);
        add(tabButtonItem, "w 100!, h 40!");
        tabButtonItems.add(tabButtonItem);
    }
    
    public void check() {
        tabButtonItems.stream().map(tabButtonItem -> {
            tabButtonItem.setSelectedTab(false);
            return tabButtonItem;
        }).map(tabButtonItem -> {
            tabButtonItem.repaint();
            return tabButtonItem;
        }).forEachOrdered(tabButtonItem -> {
            tabButtonItem.revalidate();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        if(getComponentCount()> 0) {
            g2.setColor(new Color(0, 0, 0, 0.1f));
            g2.fillRect(getComponent(getComponentCount() - 2).getX() + 100, getHeight() - 1, getWidth(), 5);
        }
        super.paintComponent(g);
    }
}
