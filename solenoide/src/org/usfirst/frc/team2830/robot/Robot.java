
package org.usfirst.frc.team2830.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

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
	DoubleSolenoid solenoid;
	Joystick javion;
	final int joystickChannel	= 0;
    public void robotInit() {
    	solenoid = new DoubleSolenoid( 0, 1);
    	javion = new Joystick (joystickChannel);
    	
    	
    	
    	
    	
    	
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	if (javion.getRawButton(6))
    	{
    		solenoid.set(DoubleSolenoid.Value.kForward);
    		
    	}else if(javion.getRawButton(4))
    	{ 
    		solenoid.set(DoubleSolenoid.Value.kReverse);
    		
    	}
    	
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
