package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.talon.Gains;
import frc.robot.talon.TalonInitializer;

public class TalonSrxMotorTestCommand extends Command {

  private WPI_TalonSRX talonSr;

  public TalonSrxMotorTestCommand(int canChannelId) {
    SmartDashboard.putData("Talon SRX Test", this);
    talonSr = new WPI_TalonSRX(canChannelId);

    /**
     * Gains used in Positon Closed Loop, to be adjusted accordingly
     * Gains(kp, ki, kd, kf, izone, peak output);
     */
    Gains kGains = new Gains(0.15, 0.0, 1.0, 0.0, 0, 1.0);
    TalonInitializer.initTalon(talonSr, kGains);
  }

  @Override
  protected void initialize() {
    super.initialize();

    double rotations = 5.0;
    double targetPositionRotations = rotations * 4096;
    talonSr.set(ControlMode.Position, targetPositionRotations);
  }

  @Override
  protected void execute() {
    log();
    super.execute();
  }

  @Override
  public synchronized void cancel() {
    talonSr.set(0);
    super.cancel();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  private void log() {
    SmartDashboard.putNumber("TalonSRXTestMotor raw", talonSr.get());
    SmartDashboard.putNumber("TalonSRXTestMotor raw", talonSr.getIntegralAccumulator());
    SmartDashboard.putNumber("TalonSRXTestMotor voltage", talonSr.getBusVoltage());
    SmartDashboard.putNumber("TalonSRXTestMotor sensor value", talonSr.getSelectedSensorPosition());
  }
}
