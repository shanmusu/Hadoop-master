STEP 1: Starting Hadoop Services:

	Use the following commands 
	start-dfs.sh
	start-yarn-sh
	mr-jobhistory-daemon.sh start historyserver


STEP 2: To check the services that are running type jps which will list the services that are up and running

	-NameNode
	-ResourceManager
	-NodeManager
	-SecondaryNameNode
	-DataNode
	-JobHistoryServer
	-jps


	Incase if name node is not running or any other services are running then do namenode formart, stop all services and start again.

	hdfs namenode -format
	stop-dfs.sh
	stop-yarn-sh

STEP 3: Once the services are started, copy the dataset into the "vagrant_data" directory.

STEP 4: Use the gunzip command to unzip the input dataset files
		gunzip 1990.gz repeat the process for other files

		Iniially we run only the 1990 dataset. 

STEP 5: Create a temporary directory in HDFS and transfer the input files. 
		- Use to following command to create a directory: 
		  hadoop fs -mkdir -p /user/$USER/pradeep/input

		Check whether the directory is created by running the command
		 hadoop fa -ls /user/$USER/pradeep/

STEP 6: Copy the dataset from local to HDFS using the command:
		hadoop fs -copyFromLocal ./1990 /user/$USER/pradeep/input/

STEP 7: Navigate to the directory: /hadoop-book/ch02-mr-intro/src/main/java

STEP 8: compile the java files using the command: hadoop com.sun.tools.javac.Main *.java

STEP 9: create a jar file Using the command: jar cf mt.jar MaxTemperature*.class

STEP 10: Run the Java files using the following command
		 hadoop jar mt.jar MaxTemperature /user/$USER/pradeep/input/* /user/$USER/ouput

STEP 11: after execution is successful, the output can be checked using the following command:
 		 hadoop fs -ls /user/$USER/output

STEP 12: To display the output: hadoop fs -cat /user/$USER/output/part-r-00000

STEP 13: open localhost:19888 to see the job history and its run time.

STEP 14: Repeat steps for the remaining two combination of runs by creating folder with datasets
		- 1990 & 1992
		- 1990, 1991, 1992, 1993
    

 


