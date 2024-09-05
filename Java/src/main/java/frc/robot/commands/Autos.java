// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.PWMDrivetrain;

// import frc.robot.subsystems.CANDrivetrain;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static Command exampleAuto(PWMDrivetrain m_drivetrain) {
    /**
     * RunCommand is a helper class that creates a command from a single method, in this case we
     * pass it the arcadeDrive method to drive straight back at half power. We modify that command
     * with the .withTimeout(1) decorator to timeout after 1 second, and use the .andThen decorator
     * to stop the drivetrain after the first command times out
     */
    return new RunCommand(() -> m_drivetrain.drive(-.5, -.5), m_drivetrain)
        .withTimeout(1)
        .andThen(new RunCommand(() -> m_drivetrain.drive(0, 0), m_drivetrain));
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
