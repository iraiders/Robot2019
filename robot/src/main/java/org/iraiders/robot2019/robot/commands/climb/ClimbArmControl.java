package org.iraiders.robot2019.robot.commands.climb;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.PIDCommand;
import org.iraiders.robot2019.robot.OI;
import org.iraiders.robot2019.robot.RobotMap;
import org.iraiders.robot2019.robot.subsystems.ClimbSubsystem;

public class ClimbArmControl extends PIDCommand {
  private final ClimbSubsystem cs;

  public ClimbArmControl(ClimbSubsystem climbSubsystem) {
    super(.5, 0, 0); // TODO Tune this and max & min range
    this.cs = climbSubsystem;
    this.setInputRange(0, 1000);
    this.getPIDController().setPercentTolerance(5);
  }

  @Override
  protected double returnPIDInput() {
    DriverStation.reportWarning(String.valueOf(cs.leftLArm.getEncoder().getPosition()), false);
    this.setSetpoint(OI.arcadeController.getZ());
    return cs.leftLArm.getEncoder().getPosition();
  }

  @Override
  protected void usePIDOutput(double output) {
    if (!RobotMap.climberArmButton.get()) return;

    cs.leftLArm.set(output);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}