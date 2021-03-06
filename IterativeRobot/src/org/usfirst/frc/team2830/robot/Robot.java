
package org.usfirst.frc.team2830.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    DoubleSolenoid solenoid;
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
			
			new SmartDashboard();
			
			SmartDashboard.putNumber("Gyro Correction", 0.15);
			
			strafingGyro.setSensitivity(.007);
			
			solenoid = new DoubleSolenoid( 0, 1);
			
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
    final double DEADBAND = .2;
    double rotatingSpeed;
    final double GYRO_DEADBAND = 2;
    double setPoint = 0;
    double gyroCorrection = .15;
    
    public void teleopInit()
    {
    	strafingGyro.reset();
    }
    
    public void teleopPeriodic() 
    {
        boolean isTurning = Math.abs(stick.getTwist()) > DEADBAND;
        gyroCorrection = SmartDashboard.getNumber("Gyro Correction");
        
        if (!isTurning && lastIsTurning){
        	setPoint = strafingGyro.getAngle();
        	rotatingSpeed=0;
        	System.out.println("A");
        	lastIsTurning = false;
        
        }else if(isTurning)
        {
        	rotatingSpeed=stick.getTwist();
        	System.out.println("B");
        	lastIsTurning = true;
        	
        }else
        {lastIsTurning = false;
        	if (Math.abs(strafingGyro.getAngle() - setPoint) < GYRO_DEADBAND)
        		
        	{
        		rotatingSpeed = 0;
        		System.out.println("C");
        		
        	}
        	else 
        	{
        	
        		if (strafingGyro.getAngle() - setPoint < 0)
        		{rotatingSpeed = gyroCorrection;
        		System.out.println("D");
        				
        		}
        		else
        		{rotatingSpeed = -gyroCorrection;
        		System.out.println("E");
        			
        		}	
        		}
        	
        	{
        		if (stickTwo.getRawButton(6))
            	{
            		solenoid.set(DoubleSolenoid.Value.kForward);
            		
            	}else if(stickTwo.getRawButton(4))
            	{ 
            		solenoid.set(DoubleSolenoid.Value.kReverse);
            		
            	}
        	}
        	
        	
        	
        	{
        		// if (stickTwo.getAxis(Joystick.AxisType.kX > 0);
        		
        		
        	}
        	
        	}
        robotDrive.mecanumDrive_Cartesian( stick.getAxis(Joystick.AxisType.kX),stick.getAxis(Joystick.AxisType.kY),rotatingSpeed, strafingGyro.getAngle());
        
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
