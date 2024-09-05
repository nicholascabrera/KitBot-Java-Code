// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.DrivetrainConstants.*;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/* This class declares the subsystem for the robot drivetrain if controllers are connected via PWM. If using SPARK MAX
 * controllers connected to CAN, go to RobotContainer and comment out the line declaring this subsystem and uncomment
 * the line for the CANDrivetrain.
 *
 * The subsystem contains the objects for the hardware contained in the mechanism and handles low level logic
 * for control. Subsystems are a mechanism that, when used in conjuction with command "Requirements", ensure
 * that hardware is only being used by 1 command at a time.
 */
public class PWMDrivetrain extends SubsystemBase {
  private PWMSparkMax m_leftRear;
  private PWMSparkMax m_leftFront;
  private PWMSparkMax m_rightRear;
  private PWMSparkMax m_rightFront;

  SlewRateLimiter rightFilter;
  SlewRateLimiter leftFilter;


  /*Constructor. This method is called when an instance of the class is created. This should generally be used to set up
   * member variables and perform any configuration or set up necessary on hardware.
   */
  public PWMDrivetrain() {
    /*Create MotorControllerGroups for each side of the drivetrain. These are declared here, and not at the class level
     * as we will not need to reference them directly anymore after we put them into a DifferentialDrive.
     */
    m_leftRear = new PWMSparkMax(kLeftRearID);
    m_leftFront = new PWMSparkMax(kLeftFrontID);
    m_rightRear = new PWMSparkMax(kRightRearID);
    m_rightFront = new PWMSparkMax(kRightFrontID);

    m_rightFront.addFollower(m_rightRear);
    m_leftFront.addFollower(m_leftRear);

    // Invert left side motors so both sides drive forward with positive output values
    m_leftFront.setInverted(true);
    m_rightFront.setInverted(false);

    rightFilter = new SlewRateLimiter(5);
    leftFilter = new SlewRateLimiter(5);

  }

  /* Set power to the drivetrain motors */
  public void drive(double leftPercentPower, double rightPercentPower) {
    leftPercentPower = leftFilter.calculate(leftPercentPower);
    rightPercentPower = rightFilter.calculate(rightPercentPower);

    m_leftFront.set(leftPercentPower);
    m_leftRear.set(leftPercentPower);
    m_rightFront.set(rightPercentPower);
    m_rightRear.set(rightPercentPower);
  }

  public void stop(){
    drive(0, 0);
  }

  @Override
  public void periodic() {
    /*This method will be called once per scheduler run. It can be used for running tasks we know we want to update each
     * loop such as processing sensor data. Our drivetrain is simple so we don't have anything to put here */
  }
}
