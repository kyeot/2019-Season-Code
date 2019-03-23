package frc.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Date;

import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Robot;

/**
 * Used for logging important messages, text written to file found 
 * on roborio at /home/lvuser/log.txt
 * 
 * @author 2783
 */
public class Logger {

	static DecimalFormat voltageFormat = new DecimalFormat("##.###");	
	static DecimalFormat percentFormat = new DecimalFormat("#.####");

	public static void logTalon(TalonSRX motor, String id){
		double voltage = motor.getMotorOutputVoltage();
		double percentOutput = motor.getMotorOutputPercent();
		
		info(id + " - " + "Voltage:" + voltageFormat.format(voltage) + " " + "PercentOutput: " + percentFormat.format(percentOutput));
	}

	public static void logVictor(VictorSPX motor, String id){

		double voltage = motor.getMotorOutputVoltage();
		double percentOutput = motor.getMotorOutputPercent();
		
		info(id + " - " + "Voltage:" + voltageFormat.format(voltage) + " " + "PercentOutput: " + percentFormat.format(percentOutput));
	}
	
	public static void debug(String msg) {
		log("DEBUG", msg);
	}
	
	public static void error(String msg) {
		log("ERROR", msg);
	}
	
	public static void warn(String msg) {
		log("WARN", msg);
	}
	
	public static void info(String msg) {
		log("INFO", msg);
	}

	public static void fatal(String msg) {
		log("FATAL", msg);
	}
	
	public static void log(String level, String msg) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(Robot.fileName, true))) {
			writer.print(level + ", ");
			writer.print(new Date().toString() + ", ");
			writer.print(Robot.parseMatchTime() + ", ");
			writer.print(msg);

			/*writer.print("[" + level + "]");
			writer.print(" ");
			writer.print("[" + new Date().toString() + "]");
			writer.print(": ");
            writer.print(msg);
            writer.print(", ");
            writer.print("[" + Robot.parseMatchTime() + "]");*/
            writer.println();
            
            System.out.println("[" + level + "] " + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}