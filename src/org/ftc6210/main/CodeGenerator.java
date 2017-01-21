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

    public String getCode() {
        String output = "";
        output += robot.initClass("RedAutonomous") + 
            robot.initMotors() +
            "        waitForStart();\n";
        for(int i = 0; i < points.size() -2; i++){
            output += robot.drive(points.get(i), points.get(i+1), points.get(i).getSpeed()/100.0);
            output += robot.turn(points.get(i), points.get(i+1), points.get(i+2), points.get(i).getSpeed()/100.0);
        }
        output += robot.drive(points.get(points.size() - 2), points.get(points.size() - 1), points.get(points.size() - 2).getSpeed()/100.0);
        output += robot.endClass();
        return output;
        
    }
    
}
