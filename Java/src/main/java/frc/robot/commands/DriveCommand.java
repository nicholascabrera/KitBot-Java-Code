package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.PWMDrivetrain;

public class DriveCommand extends Command {
    private PWMDrivetrain m_drivetrain;

    public DriveCommand() {
        m_drivetrain = RobotContainer.m_drivetrain;
        addRequirements(m_drivetrain);
    }

    // Called once when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        Double turning_power = -1 * RobotContainer.m_driverController.getLeftY();
        Double drive_power = -1 * RobotContainer.m_driverController.getRightX();
        m_drivetrain.drive(drive_power - turning_power, drive_power + turning_power);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_drivetrain.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false; // Command will never finish (we don't want it to)
    }
}
