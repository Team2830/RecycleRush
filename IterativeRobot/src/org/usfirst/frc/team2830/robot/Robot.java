
package org.usfirst.frc.team2830.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	
    RobotDrive robotDrive;
    Joystick stick;

    // Channels for the wheels e e
    final int rearRightChannel	= 3;
    final int frontRightChannel	= 1;
    final int frontLeftChannel	= 0;
    final int rearLeftChannel	= 2;


    
    // The channel on the driver station that the joystick is connected to
    final int joystickChannel	= 0;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    
    	try {

			robotDrive = new RobotDrive(frontLeftChannel, rearLeftChannel, frontRightChannel, rearRightChannel);
			robotDrive.setExpiration(0.1);
			robotDrive.setInvertedMotor(MotorType.kFrontRight, true);	// invert the left side motors
			robotDrive.setInvertedMotor(MotorType.kRearRight, true);		// you may need to change or remove this to match your robot

			stick = new Joystick(joystickChannel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	//TODO
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        robotDrive.mecanumDrive_Cartesian(stick.getX(), stick.getY(), stick.getZ(), 0);
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    //TODO
    }
    
}

