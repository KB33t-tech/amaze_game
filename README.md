Commands for building, running, and testing the game  

1. Compile the program and run the game:  
	Step 1:	mvn clean install  
	Step 2:	java -cp target/project-0.0.1-SNAPSHOT.jar panel.Frame  

2. Compile the program and run the game without running the tests:  
	Step 1:	mvn install -DskipTests  
	Step 2:	java -cp target/project-0.0.1-SNAPSHOT.jar panel.Frame  
	
3. Run all the tests in all classes:  
	Step 1:	mvn clean test  
	
4. Run all the tests in a specific class:  
	Step 1:	mvn -Dtest=xxxxxx test	(xxxxxx is class name)  
	Example:  mvn -Dtest=TestWallDetection test  
	
5. Run an individual test(method) in a specific class:  
	Step 1:	mvn -Dtest=xxxxxx#testA test	(xxxxxx is class name, testA is method name)  
	Example:  mvn -Dtest=TestWallDetection#testRightWall test  

6. To remove all previous build and test:  
	Step 1:	mvn clean  