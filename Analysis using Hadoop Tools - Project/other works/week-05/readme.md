# week 05

Step 1: download text files into vagrant data
Step 2: concatenate all the files into a single file using the command
```
		cat *.txt >all-sotu.txt
```
Step 3: Starting Hadoop Services:
```
		Use the following commands 
			start-dfs.sh
			start-yarn-sh
			mr-jobhistory-daemon.sh start historyserver
```
Step 4: create a path for the input file using the command
```
		hadoop fs -mkdir -p /user/$USER/week05/input
```
Step 5: move the data files into the HDFS using the command
```
		hadoop fs -copyFromLocal ./all-sotu.txt /user/$USER/week05/input/  
```
Step 6: navigate into wordcout1  directory to run the wordcount v1 
```
		compile the java files using the command: 
			hadoop com.sun.tools.javac.Main *.java
```
Step 7: create a jar file Using the command: 
```
		jar cf wc.jar WordCount*.class
```
Step 8: Run the Java files using the following command
``` 
		 hadoop jar wc.jar WordCount /user/$USER/week05/input/all-sotu.txt /user/$USER/output1
```
Step 9: To display the top 10 most occured word use the command
```
			hadoop fs -cat /user/$USER/output/part-r-00000 | sort -rm -k2 | head -n10
```
Step 10: navigate into wordcout2 directory to run the wordcount v2 and repeat the steps with the following commands to create jar files
```
		-to compile
			hadoop com.sun.tools.javac.Main *.java
		-to create jar file
			jar cf wc.jar WordCount2*.class		

		- To run the jar in hadoop 
			hadoop jar wc.jar WordCount2 /user/$USER/week05/input/all-sotu.txt /user/$USER/output2

		- To list the top 10 most occured words 	
			hadoop fs -cat /user/$USER/output2/part-r-00000 | sort -rm -k2 | head -n10
```
step 11: navigate to modified wordcount1 directory 
```
		- It has the same wordcount v1 java file
		- Modifications are made to list only the words that occurs more than 4 times
			at line 44 the following modifications are made
			if(sum >= 5 ) {
      			result.set(sum);
      			context.write(key, result);
  	  		}

  	  	-now repeat the steps from 6 - 9 to get the list of words that has occured more than 4 times.
 ```
 Step 12: navigate to modified wordcount2 directory
 ``` 
		- It has the same wordcount v2 java file
		- Modifications are made to list the words with out case sensitivity
			at line 44 the following modifications are made
			caseSensitive =  false;

		- pattern.txt file is created and all the prepositions and punctuations are manually entered so that these words dont appear in the final list
		- move the pattern.txt file into HDFS using the command
			hadoop fs -copyFromLocal ./pattern.txt /user/$USER/week05/input/  
		- compile the java file and create jar file using the step 10
		-To run the modified version use the following command:
			hadoop jar wc.jar WordCount2 /user/$USER/week05/input/all-sotu.txt /user/$USER/output4 -skip /user/$USER/input/pattern.txt
		- TO display the top 10 words using the modified version
			hadoop fs -cat /user/$USER/output4/part-r-00000 | sort -rm -k2 | head -n10
		
INFO:  output folder contains all the following versions of output which is stored using the following command
			hadoop fs -cat /user/$USER/output4/part-r-00000> filename.txt

		To get the number of word in each output use the command:
			wc -l filename.txt

```



