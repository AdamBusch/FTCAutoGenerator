package org.ftc6210.main;

import java.util.ArrayList;

/**
 *
 * @author tae
 */
public class RobotHardware {
    private ArrayList<String> motors;
    private ArrayList<String> servos;
    private int wheelD;
    
    
    public RobotHardware(int wheelDiam, String[] driveMotors) {
        motors = new ArrayList();
        servos = new ArrayList();
        for (String m : driveMotors)
            motors.add(m);
        wheelD = wheelDiam;
    }
     
    public void addMotor(String m){
        motors.add(m);
    }
    
    public String drive(Point p1, Point p2, double speed) {
        String output = "";
        output += setDriveMode("DcMotor.RunMode.STOP_AND_RESET_ENCODER") + setDriveMode("DcMotor.RunMode.RUN_WITHOUT_ENCODER");
        output += "while(getAverageEncoderPosition() < " + motors.get(0) + ".getCurrentPosition() + " + toInches(p1.getX(), p2.getX())
                + ") {\n"
                + setDriveSpeed(speed)
                + "\n}";
        return output;
    }
    
    public String turn(Point p1, Point p2) {
        return null;
    }
    
    
    public String setDriveSpeed(double speed) {
        String output = "";
        for (String m : motors) {
            output += m + ".setPower(Range.clip(" + speed + ", -1, 1);";
        }
        return output;
    }
    
    
    private double toInches(double p1, double p2) { 
        return (p2 - p1) * 1.75;
    }
    
    public String setDriveMode(String mode) {
        String output = "";
        for (String m : motors)
            output += m + ".setMode(" + mode + ");\n";
        return output;
    }
    
    public String initClass(String className) {
        return "import com.qualcomm.robotcore.eventloop.opmode.Autonomous;\n" +
                "import com.qualcomm.robotcore.hardware.Servo;\n" +
                "\n" +
                "\n" +
                "import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;\n" +
                "\n" +
                "@Autonomous(name = \"" + className + "\", group = \"Autonomous\")\n" +
                "public class " + className + " extends StrykeAutonomous {\n" +
                "\n" +
                "    @Override\n" +
                "    public void runOpMode() throws InterruptedException {\n";
    }
    
    public String endClass() {
        String output = "}\n\n";
        output += "public int getAverageEncoderPosition() {\n"
                + "int total = ";
        for (String m : motors) {
            output += m + ".getCurrentPosition() + ";
        } 
        output += " + 0;\n"
                + " return total/" + motors.size() + ";\n}";
        return output;
    }
    
    public String initMotors() {
        String output = "";
        for (String m : motors)
            output += "public DcMotor " + m + " = hardwareMap.dcMotor.get(\"" + m + "\")\n";
        return output;
    }
    
    
}
