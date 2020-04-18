#### Instructions and commands for creating and using the artifacts of the program: 
    
cd into this project's base directory called **project** and issue the following commands:  
  
1. Create the artifacts, including JAR file of the program and Javadoc:  
	Step1:	mvn clean install  
  	Step2: 	from there, one can find the artifacts inside $project/target/  
  
2. View the contents of the Javadoc HTML without extracting it:  
	Step 1:	mvn clean install  
	Step 1:	jar -tvf target/project-0.0.1-SNAPSHOT-javadoc.jar  
  
3. View the Javadoc HTML files from Jar:  
	Step 1:	mvn clean install  
	Step 2:	cd into the folder named "target"  
	Step 3:	cd into the folder named "apidocs" and all the Javadoc HTML files are inside it  
  
4. Run the game using the JAR file:    
	Step 1:	mvn clean install  
	Step 2:	java -cp target/project-0.0.1-SNAPSHOT.jar panel.Frame    
  
#### Instructions and commands for building, running, and testing the game:  
  
cd into this project's base directory called **project** and issue the following commands:  
  
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
  

