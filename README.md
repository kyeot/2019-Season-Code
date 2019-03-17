# Team 2783
This is the code for FRC Team 2783's 2019 robot. The robot code is written in Java and the vision processing code on the Pi is written in Python.

## Code Features:
* Field-Oriented Swerve Drive Base  
  
     Uses a Nav-X Gyroscope to measure the angle of the robot, and factors it into the calculation of angles of each individual swerve module. 4 individual PID loops are then used in conjunction with absolute encoders to point each of the modules in the correct direction. The result is a very clean, easy to drive and extremely maneuverable robot. Most of this code is contained in [SwerveDriveBase.java](https://github.com/kyeot/2019-Season-Code/blob/master/src/main/java/frc/robot/subsystems/SwerveDriveBase.java).

* Driver Vision with Raspberry Pi  

    Our robot contains a Raspberry Pi with WPILib's [Official Image](https://github.com/wpilibsuite/FRCVision-pi-gen/releases). We then use a modified version of Team 3997's [ChickenVision](https://github.com/team3997/ChickenVision) (thanks guys!) uploaded on the Pi to stream a processed camera feed. In the future this will use OpenCV to process a camera feed and calculate a 3d vector for alignment use in the robot code, however this feature is still being worked on. This code is at [ChickenVision.py](https://github.com/kyeot/2019-Season-Code/blob/master/vision_app/ChickenVision.py).

* Logging Important Data  
  
  Our code contains some custom functionality that will check for important events like a brown-out on a separate thread, and log it for us in a text file on the roborio. We have the logger also log the time in the match, allowing us to diagnose something accurately even when the robot was on the field. You can find this code in [Logger.java](https://github.com/kyeot/2019-Season-Code/blob/master/src/main/java/frc/util/Logger.java), [EventLogger.java](https://github.com/kyeot/2019-Season-Code/blob/master/src/main/java/frc/util/EventLogger.java), and [LogData.java](https://github.com/kyeot/2019-Season-Code/blob/master/src/main/java/frc/loops/LogData.java).

* 'Action' System

    A replacement for the Command system in place by default for command-based robots. Slightly more sophisticated Actions and Action Groups which are run through a queue system in another thread by a scheduler. Put in place for simple creation of complex strings of subsystem functionality, especially for autonomous control. Works in tandem with our logging system to log when Actions are completed successfully, or whether they fail. This is all contained in [autonomous/](https://github.com/kyeot/2019-Season-Code/tree/master/src/main/java/frc/autonomous).

* Comprehensive Constants Class

    [Constants.java](https://github.com/kyeot/2019-Season-Code/blob/master/src/main/java/frc/robot/Constants.java) is a class containing a lot of constant variables which control important parts of our robot. The existence of this class allows us to quickly change various parts of our robots control system, from motor Id's, to PID's, to controls of subsystems, or even ramp rates.
  
## Trying our Code:

 Our team uses VSCode to program our robot, and we recommend doing the same.
 To view in VSCode clone the repository with:  
        
        git clone https://github.com/kyeot/2019-Season-Code.git

 into the desired directory. Then in VSCode, open the new "2019-Season-Code" folder with "File>Open Folder..."  
 If you wish to deploy and use this code, make sure you edit the [Constants.java]() class, changing motor ID's and controller mappings to your setup.