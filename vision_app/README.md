# Vision App
This is the application that runs on the [WPILib Rasberry Pi Image](https://github.com/wpilibsuite/FRCVision-pi-gen/releases).
It's written in Java, and we used GRIP to generate the Pipeline code.

#### Building (on Desktop)
1. Make sure you're using Java 11 and your JAVA_HOME env variable is set (instructions for that [here](https://docs.oracle.com/cd/E19182-01/821-0917/inst_jdk_javahome_t/index.html))
2. Navigate to the vision_app folder in PowerShell (for Windows) or any Unix-Based shell and run "./gradlew build"

#### Deploying
1. Connect to the robot, and open the Raspberry Pi dashboard by putting the IP of the Pi (Usually "frcvision.local" or "10.TE.AM.77") in Chrome, prefixed with "http://"
2. Under "Application", set the Application to "Uploaded Java Jar"
3. Make sure the Pi is set to readable, then upload the built jar under "vision_app/build/libs" (make sure to choose the "-all" version) and save the change.

