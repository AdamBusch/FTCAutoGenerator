/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ftc6210.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author Busch_902818
 */
public class FieldView extends JPanel{
    
    private MainWindow windowRef;
    
    public FieldView() {
        
    }
    
    public FieldView(MainWindow window) {
        windowRef = window;
    }
    
    public void setMainWindow(MainWindow window) {
        this.windowRef = window;
    }
    
    @Override
    public void paintComponents(Graphics g) {
        
        Image offscreen = createImage(360,360);
        Graphics offGraphics = offscreen.getGraphics();
        
        offGraphics.clearRect(0,0,360,360);
        offGraphics.drawImage(windowRef.getFieldImage(), 0, 0, 360, 360, this);
        drawPoints(offGraphics, windowRef.getRedPoints(), Color.RED);
        drawPoints(offGraphics, windowRef.getBluePoints(), Color.BLUE);
        
        g.drawImage(offscreen, 0,0,360,360, null);
    }
    
    public void drawPoints(Graphics g, PointModel model, Color pointColor) {
        g.setColor(Color.BLACK);
        for(int i = 0; i <  model.getPoints().size(); i ++){
            Point p = model.getPoints().get(i);
            if(i > 0) {
                Point last = model.getPoint(i - 1);
                
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(last.getX(), last.getY(), p.getX(), p.getY());
            }            
        }
        g.setColor(Color.GREEN);
        for(int i = 0; i < model.getPoints().size(); i ++) {
            Point p = model.getPoint(i);
            
            
            
            if(i == model.getPoints().size()-1)
                g.setColor(Color.YELLOW);
 
            if(p.equals(windowRef.getSelectedPoint()))
                g.fillOval(p.getX() - 7, p.getY() - 7, 14, 14);
            else
                g.fillOval(p.getX() - 5, p.getY() - 5, 10, 10);
            g.setColor(pointColor);
        }
    }
}