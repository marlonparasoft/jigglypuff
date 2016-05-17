Descriptions of Ant, Maven and Gradle integrations are available in the DTP_Engines_for_Java_User_Manual.pdf file and in the plugins-manual.html documentation. Both resources are in the "manuals" directory.

The following instructions describe how to run Static Analysis Engine (SAE) and Unit Tests Connector (UTC) with Code Coverage Engine (CCE) on the Demo project. 

Please note that Demo-oriented settings are provided in the demo.properties file located in the [INSTALL]/examples/demo directory. The demo.properties file is used by Ant, Maven, and Gradle build scripts. The following command line examples are intended to be executed from Demo project directory.


Prerequisites
-------------------------------------------------
1. Setup Jtest DTP Engine license in jtestcli.properties placed at installation directory.


Jtest DTP Engine data file
-------------------------------------------------
1. Run "Recommended Rules" for Static Analysis (SAE) directly using command:
   Windows:
     ..\..\jtestcli.exe -config "builtin://Recommended Rules" -data demo.data.json -report report
   UNIX:
     ../../jtestcli -config "builtin://Recommended Rules" -data demo.data.json -report report


Ant
-------------------------------------------------
1. Make sure that you have "ant" available on your path.

2. Run "Demo Configuration" for Static Analysis (SAE) and Unit Tests (UTC) report with coverage (CCE):
     
     ant -file jtest.xml
     (in case of Ant 1.6 add cmdline option " -lib lib/junit-4.11.jar" otherwise tests will not run)

   Demo project will be built and unit tests will run. Ant plugin collects source code compilation data,
   and unit test results with coverage to perform analysis and generate report.

   Note:
   
   To run Static Analysis only (SAE) use following command:
     
     ant -file jtest.xml jtest-sae
   
   To run Unit Tests only (UTC) use following command:
     
     ant -file jtest.xml jtest-utc
     (in case of Ant 1.6 add cmdline option " -lib lib/junit-4.11.jar" otherwise tests will not run)
       
   Configurations are specified in jtest.xml - see "jtest", "jtest-sae", or "jtest-utc" targets. 


Maven
-------------------------------------------------
1. Make sure that you have "mvn" available on your path.
2. Configure your Maven setup by following guide from
   manuals/plugins-manual.html: Jtest Maven Plugin > Usage > Initial Setup.

3. Run "Demo Configuration" for Static Analysis (SAE) and Unit Tests (UTC) report with coverage:

     mvn clean test-compile jtest:agent test jtest:jtest -Djtest.config="builtin://Demo Configuration"
     (or mvn clean test-compile jtest:instrument test jtest:jtest -Djtest.config="builtin://Demo Configuration")
  
   Example project will be built and unit tests will run. Maven plugin will collect source code compilation data,
   and test results with coverage to perform analysis and generate report. 

   Note:
   
   To run Static Analysis only (SAE) use following command:
   
     mvn jtest:jtest
   
   "Recommended Rules" configuration is used by default.

   To run Unit Tests only (UTC) use following command:
   
     mvn clean test-compile jtest:agent test jtest:jtest -Djtest.config="builtin://Unit Tests"
     (or mvn clean test-compile jtest:instrument test jtest:jtest -Djtest.config="builtin://Unit Tests")


Gradle
-------------------------------------------------
1. Make sure that you have "gradle" available on your path
2. Configure installed Jtest DTP Engine package or add desired settings into "jtest" block of buildscript

3. Run "Demo Configuration" for Static Analysis (SAE) and Unit Tests (UTC) report:

     gradle clean jtest-agent test jtest -Djtest.config="builtin://Demo Configuration"
     (or gradle clean jtest-instrument test jtest -Djtest.config="builtin://Demo Configuration")

   Example project will be built and Junit tests will run. Gradle plugin will collect 
   source code compilation data, and test results to perform analysis and generate report.

   Note:
   
   To run Static Analysis only (SAE) use following command:
     
     gradle clean build jtest
   
   "Recommended Rules" configuration is used by default.

   To run Unit Tests only (UTC) use following command:
   
     gradle clean jtest-agent test jtest -Djtest.config="builtin://Unit Tests"
     (or gradle clean jtest-instrument test jtest -Djtest.config="builtin://Unit Tests")


=================================================

Collecting application coverage

1. Make sure that you have "mvn" and java available on your path.
2. Configure your Maven setup by following guide from
   manuals/plugins-manual.html: Jtest Maven Plugin > Usage > Initial Setup.

3. Build application and collect data necessary for monitoring
     mvn clean package jtest:monitor
   
   Note: As a result you should get monitor.zip file.

4. Run application and collect coverage data
   
   Windows:
     
     a) Unpack monitor.zip archive into demo directory (subdirectory monitor will be created)
        Archive path: target\jtest\monitor\monitor.zip
     
     b) Run agent.bat
        cd monitor        
        agent.bat
        cd ..
      
     c) Run application using Java VM argument generated in point b)
        java -cp target\Demo.jar [paste argument generated in point b] examples.stackmachine.RunnableStackMachine

     d) Perform few actions using "Stack Machine Example" application
        - Insert 123 number into "Input" field
        - press "push" button 5 times
        - press "+", "-", "x" and "/" buttons
        - exit application

   UNIX:

     a) Unpack monitor.zip archive into demo directory (subdirectory monitor will be created)
        unzip ./target/jtest/monitor/monitor.zip
     
     b) Run agent.sh 
        ./monitor/agent.sh
      
     c) Run application using Java VM argument generated in point b)
        java -cp ./target/Demo.jar [paste argument generated in point b] examples.stackmachine.RunnableStackMachine

     d) Perform few actions using "Stack Machine Example" application
        - Insert 123 number into "Input" field
        - press "push" button 5 times
        - press "+", "-", "x" and "/" buttons
        - exit application

5. Generate coverage report

     Windows:  
       ..\..\jtestcli.exe -config "builtin://Calculate Application Coverage" -staticcoverage monitor\static_coverage.xml -runtimecoverage monitor\runtime_coverage
     UNIX
       ../../jtestcli -config "builtin://Calculate Application Coverage" -staticcoverage ./monitor/static_coverage.xml -runtimecoverage ./monitor/runtime_coverage

     Coverage details are available in report.html
