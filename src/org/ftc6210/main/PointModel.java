/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ftc6210.main;

import java.util.ArrayList;

/**
 *
 * @author Busch_902818
 */
public class PointModel {
    
    private ArrayList<Point> points;
    private boolean isBlueAlliance;
    
    public PointModel(boolean isBlueAlliance) {
        points = new ArrayList<>();
        this.isBlueAlliance = isBlueAlliance;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }
    
    public Point addPoint(Point p) {
        p.setIsBlueAlliance(isBlueAlliance);
        if(!points.contains(p)) points.add(p);
        return p;
    }
    
    public Point addPoint(int x, int y, String name) {
        Point p = new Point(x, y);
        p.setIsBlueAlliance(isBlueAlliance);
        p.setName(name);
        points.add(p);
        return p;
    }
    
    public boolean removePoint(Point p) {
        return points.remove(p);
    }

    public boolean isBlueAlliance() {
        return isBlueAlliance;
    }

    public void setIsBlueAlliance(boolean isBlueAlliance) {
        this.isBlueAlliance = isBlueAlliance;
    }
    
    
    
    
    
}
