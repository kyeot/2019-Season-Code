package frc.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import frc.robot.Robot;

/**
 * Used for logging important messages, text written to file found 
 * on roborio at /home/lvuser/log.txt
 * 
 * @author 2783
 */
public class Logger {
	
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
		try (PrintWriter writer = new PrintWriter(new FileWriter("/home/lvuser/log.txt", true))) {
			writer.print("[" + level + "]");
			writer.print(" ");
			writer.print("[" + new Date().toString() + "]");
			writer.print(": ");
            writer.print(msg);
            writer.print(", ");
            writer.print("[" + Robot.parseMatchTime() + "]");
            writer.println();
            
            System.out.println("[" + level + "] " + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}