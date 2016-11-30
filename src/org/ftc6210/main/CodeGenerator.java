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
public class CodeGenerator {
    private ArrayList<Point> points;
    private RobotHardware robot;
    private String code;

    
    public CodeGenerator(PointModel model, RobotHardware rh) {
        points = model.getPoints();
        robot = rh;
    }
    
    
    
    private void calibrate(Point p1, Point p2) {
        
    }
    
    
    public String getBlueAllianceCode() {
        return null;
    }
    public String getRedAllianceCode() {
        String output = "";
        output += robot.initClass("Red Autonomous") + 
            robot.initMotors() +
            "waitForStart();\n";
        for(int i = 0; i < points.size() -1; i++){
            output += robot.turn(points.get(i), points.get(i+1));
            output += robot.drive(points.get(i), points.get(i+1), 0.5);
        }
        
        output += robot.endClass();
        return output;
        
    }
    
}
