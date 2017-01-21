package org.ftc6210.main;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author tae
 */
public class RobotHardware {
    private String[] leftMotors;
    private String[] rightMotors;
    private ArrayList<String> servos;
    private int wheelD;
    
    
    public RobotHardware(int wheelDiam, String[] left, String[] right) {
        leftMotors = left;
        rightMotors = right;
        servos = new ArrayList();
        wheelD = wheelDiam;
    }
     
    
    public String drive(Point p1, Point p2, double speed) {        
        String output = "";
        output += "        encoderDrive(" + String.format("%.2f", getDistance(p1, p2)) + "," + speed + ", getDriveMotors());\n";
        return output;
    }
    
    public String turn(Point p1, Point p2, Point p3, double speed) {
        double a = getDistance(p1,p2);
        double b = getDistance(p2,p3);
        double c = getDistance(p1,p3);
        //middle angle = arccos((a^2 + b^2 - c^2) / 2ab)
        return "        encoderTurn(" + String.format("%.2f",(180 - (Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b)) * (180/Math.PI)))) + "," + speed + " ,getDriveMotors());\n";
    }
    
    
    public String setDriveSpeed(double speed) {
        String output = "";
        for (String m : leftMotors) {
            output += m + ".setPower(Range.clip(" + speed + ", -1, 1);";
        }
        for (String m : rightMotors) {
            output += m + ".setPower(Range.clip(" + speed + ", -1, 1);";
        }
        return output;
    }
    
    
    private double toInches(double p1, double p2) { 
        return (p2 - p1) * 1.75;
    }
    
    private double getDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2)) * 1.75;
    }
    
    public String setDriveMode(String mode) {
        String output = "";
        for (String m : leftMotors)
            output += m + ".setMode(" + mode + ");\n";
        for (String m : rightMotors)
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
        String output = "\n\n}\n\n";
        output +=   "public void encoderDrive(double inches, double speed, DcMotor... motors) throws InterruptedException {\n" +
                    "    int pulses = (int) ((inches / (6 * Math.PI) * 280) * 1.6);\n" +
                    "    resetMotorEncoders();\n" +
                    "    while(getAverageEncoderPosition(motors) <= pulses) {\n" +
                    "        setDriveSpeed(speed, -speed);\n" +
                    "        telemetry.addData(\"Target\", pulses);\n" +
                    "        telemetry.addData(\"Current\", getAverageEncoderPosition(motors));\n" +
                    "        telemetry.update();\n" +
                    "        idle();\n" +
                    "     }\n" +
                    "     setDriveSpeed(0, 0);\n" +
                    " }\n" +
                    " \npublic void setDriveSpeed(double speedLeft, double speedRight) {\n" +
                    "     setLeftDriveSpeed(speedLeft);\n" +
                    "     setRightDriveSpeed(speedRight);\n" +
                    "}\n" +
                    "\npublic void setRightDriveSpeed(double speed) {\n" +
                    "    setMotorSpeeds(speed, " + Arrays.toString(rightMotors).replace("[", "").replace("]", "") + ");\n" +
                    "}\n" +
                    "\npublic void setLeftDriveSpeed(double speed) {\n" +
                    "    setMotorSpeeds(speed, " + Arrays.toString(leftMotors).replace("[", "").replace("]", "") + ");\n" +
                    "}\n" +
                    "\npublic void setMotorSpeeds(double speed, DcMotor... motors) {\n" +
                    "    for (DcMotor motor : motors)\n" +
                    "        motor.setPower(Range.clip(speed, -1, 1));\n" +
                    "}\n" + 
                    "public int getAverageEncoderPosition(DcMotor... motors) {\n" +
                    "    int total = 0;\n" +
                    "    int numMotors = motors.length;\n" +
                    "    for(DcMotor motor : motors){\n" +
                    "        if(motor.getCurrentPosition() == -1) {\n" +
                    "            numMotors --;\n" +
                    "        }\n" +
                    "        else\n" +
                    "            total += motor.getCurrentPosition();\n" +
                    "        }\n" +
                    "        return total/numMotors;\n" +
                    "}\n" + 
                    "\npublic DcMotor[] getDriveMotors(){\n" +
                    "    return new DcMotor[]{" + Arrays.toString(leftMotors).replace("[", "").replace("]", "") + Arrays.toString(rightMotors).replace("[", "").replace("]", "") + "};\n" +
                    "}\n\n" + 
                    "public void encoderTurn(double deg, double speed, DcMotor... motors) throws InterruptedException {\n" +
                    "    int pulses = (int) ((((deg/360) * (18 * Math.PI) / (6 * Math.PI) * 280) * 1.6) * 1.55);\n" +
                    "    resetMotorEncoders();\n" +
                    "    setMotorRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER, motors);\n" +
                    "    while(getAverageEncoderPosition(motors) <= pulses) {\n" +
                    "        setDriveSpeed(speed, speed);\n" +
                    "        telemetry.addData(\"Target\", pulses);\n" +
                    "        telemetry.addData(\"Current\", getAverageEncoderPosition(motors));\n" +
                    "        telemetry.update();\n" +
                    "        idle();\n" +
                    "    }\n" +
                    "\nsetDriveSpeed(0, 0);\n" +
                    "\n" +
                    "}" +
                    "\n\npublic void resetMotorEncoders(){\n" +
                    "    setMotorRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER, getDriveMotors());\n" +
                    "}\n" +
                    "\n" +
                    "public void setMotorRunMode(DcMotor.RunMode runMode, DcMotor... motors){\n" +
                    "     for(DcMotor motor : motors) {\n" +
                    "        motor.setMode(runMode);\n" +
                    "     }\n" +
                    "}";
        return output;
    }
    
    public String initMotors() {
        String output = "";
        for (String m : leftMotors)
            output += "        public DcMotor " + m + " = hardwareMap.dcMotor.get(\"" + m + "\")\n";
        for (String m : rightMotors)
            output += "        public DcMotor " + m + " = hardwareMap.dcMotor.get(\"" + m + "\")\n";
        return output;
    }
    
    
}
