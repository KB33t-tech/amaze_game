**Instructions and commands for creating the artifacts of the program:  **
    
cd into the base directory called "project", and perform the following commands:  
  
1. Create the artifacts, including JAR file of the program and JavaDocs:  
	Step1:	mvn clean install
  	Step2: 	from there, one can find the artifacts inside $basedir/target/
  
  
**Instructions and commands for building, running, and testing the game  **
  
cd into the base directory called "project", and perform the following commands:  
  
1. Compile the program and run the game:  
	Step 1:	mvn clean install  
	Step 2:	java -cp target/project-0.0.1-SNAPSHOT.jar panel.Frame  

2. Compile the program and run the game without running the tests:  
	Step 1:	mvn install -DskipTests  
	Step 2:	java -cp target/project-0.0.1-SNAPSHOT.jar panel.Frame  
	
3. Run all the unit tests in all classes:  
	Step 1:	mvn clean test  
	
4. Run all the unit tests in a specific class:  
	Step 1:	mvn -Dtest=xxxxxx test	(xxxxxx is class name)  
	Example:  mvn -Dtest=TestWallDetection test  
	
5. Run an individual unit test(method) in a specific class:  
	Step 1:	mvn -Dtest=xxxxxx#testA test	(xxxxxx is class name, testA is method name)  
	Example:  mvn -Dtest=TestWallDetection#testRightWall test  
	
6. Run all the integration tests in all classes:  
	Step 1:	mvn verify  
  
7. To remove all previous build and test:  
	Step 1:	mvn clean  