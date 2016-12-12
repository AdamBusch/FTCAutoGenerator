/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ftc6210.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Busch_902818
 */
public class PointModel implements Iterable<Point>, Serializable{
    
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
    
    public Point getPoint(int index) {
        return index >= 0 ? index < points.size() ? points.get(index) : null : null;
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

    @Override
    public Iterator<Point> iterator() {
        return points.iterator();
    }
    
    
    
    
    
}
