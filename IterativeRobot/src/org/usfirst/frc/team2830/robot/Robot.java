
package org.usfirst.frc.team2830.robot;

import edu.wpi.first.wpilibj.Gyro;
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
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */	
    RobotDrive robotDrive;
    Joystick stick;
    Joystick stickTwo;
    Gyro strafingGyro;
    // Channels for the wheels e e
    final int rearRightChannel	= 3;
    final int frontRightChannel	= 1;
    final int frontLeftChannel	= 0;
    final int rearLeftChannel	= 2;
  
    final int gyro = 1;
    
    
    
    


    
    // The channel on the driver station that the joystick is connected to
    final int joystickChannel	= 0;
    final int joystickChannel2  = 1;
    
    public void robotInit() {
    	try {

			robotDrive = new RobotDrive(frontLeftChannel, rearLeftChannel, frontRightChannel, rearRightChannel);
			robotDrive.setExpiration(0.1);
			robotDrive.setInvertedMotor(MotorType.kFrontRight, true);	// invert the left side motors
			robotDrive.setInvertedMotor(MotorType.kRearRight, true);		// you may need to change or remove this to match your robot

			stick = new Joystick(joystickChannel);
			stickTwo = new Joystick(joystickChannel2);
			
			strafingGyro = new Gyro(gyro);
			
			strafingGyro.setSensitivity(.007);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    
    boolean lastIsTurning = true;
    final double CORRECTION_RATE = .1;
    final double DEADBAND = .1;
    double rotatingSpeed;
    final double GYRO_DEADBAND = 2;
    
    public void teleopInit()
    {
    	strafingGyro.reset();
    }
    
    public void teleopPeriodic() 
    {
        boolean isTurning = Math.abs(stick.getTwist()) > DEADBAND;
        
        if (isTurning && !lastIsTurning){
        	strafingGyro.reset();
        	rotatingSpeed=0;
        
        }else if(isTurning)
        {
        	rotatingSpeed=stick.getTwist();
        	
        }else
        {
        	if (Math.abs(strafingGyro.getAngle()) < GYRO_DEADBAND)
        	{
        		rotatingSpeed = 0;
        	}
        	else 
        	{
        	
        		if (strafingGyro.getAngle() < 0)
        		{rotatingSpeed = .5;
        				
        		}
        		else
        		{rotatingSpeed = -.5;
        			
        		}	
        		}
        	{
        		// if (stickTwo.getAxis(Joystick.AxisType.kX > 0);
        		
        		
        	}
        	
        	}
        robotDrive.mecanumDrive_Cartesian( stick.getAxis(Joystick.AxisType.kX),stick.getAxis(Joystick.AxisType.kY),rotatingSpeed,0);
        
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
