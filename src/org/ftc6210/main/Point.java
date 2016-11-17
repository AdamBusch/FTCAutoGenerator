/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ftc6210.main;

/**
 *
 * @author Busch_902818
 */
public class Point {
    private int speed;
    private String name;
    private String notes;
    private int x, y;
    private boolean isBlueAlliance;

    public Point(int x, int y, String name, int speed, String notes, boolean isBlueAlliance) {
        this.speed = speed;
        this.name = name;
        this.notes = notes;
        this.x = x;
        this.y = y;
        this.isBlueAlliance = isBlueAlliance;
    }
    
    public Point(int x, int y) {
        this(x, y, "Point", 100, "", true);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isBlueAlliance() {
        return isBlueAlliance;
    }

    public void setIsBlueAlliance(boolean isBlueAlliance) {
        this.isBlueAlliance = isBlueAlliance;
    }
    
    
    
    
    
    
}
