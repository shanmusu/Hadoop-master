# ITEM TWO

```
Running the MapReduce program to find the MaxTemperature withcombiner on the text file of each data sets.

- Following are the available data sets and their data size.

	1. 1990 /user/root/ncdc/1990/ - SMALL DATA SET
		- text size : 7.84 GB
		- bzip size : 593.14 MB
		- gzip size : 1.02 GB
	2. 1990 & 1992 /user/root/ncdc/1990and1992/  - MEDIUM DATA SET
		- text size : 16.47GB
		- bzip size : 1.22 GB
		- gzip size : 2.13 GB
	3. 90-93 /user/root/ncdc/90-93/
		- text size : 33.59 GB
		- bzip size : 2.51 GB
		- gzip size : 4.36 GB
```

## CLUSTER CONFIGURATION 
	Number of  Live nodes: 6
	Node Capacity: 492.03GB X 6
	Node Memory : 16GB
	Total VCores used: 46
	Average number of block in nodes :8500
	Memory available: 16GB
	
	

### Command to  compile the java files and create a jar file

```
hadoop com.sun.tools.javac.Main MaxTemperature*.java
jar cf mt.jar MaxTemperature*.class

```	

### Commands to run the job in the cluster

```
time nohup hadoop jar mt.jar MaxTemperatureWithCombiner /user/root/ncdc/1990/1990.txt /user/$USER/output/week007/1990c >> log/2.txt
time nohup hadoop jar mt.jar MaxTemperatureWithCombiner /user/root/ncdc/90and92/1990and1992.txt /user/$USER/output/week007/90and92c >> log/10.txt
time nohup hadoop jar mt.jar MaxTemperatureWithCombiner /user/root/ncdc/90-93/90919293.txt /user/$USER/output/week007/90-93c >> log/16.txt   
```

### TIME TAKEN FOR EACH DATA SET WITH OUT COMBINER



| DATA          	   |  TIME (With Combine)    |JOB ID (Last 4 digit)  	|
| -------------------- |------------------------:|-------------------------:|
| 1990.txt      	   |  3mins, 9sec		     | 			1909			|
| 1990and1992.txt      |  7mins, 48sec           |			1917			|
| 90919293.txt         |  10mins, 45sec 		 |			1927			|

### ANALYSIS: 

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-07/item-two/GRAPH.PNG "Optional title")
```
1. In this scnenario we are running all the three data set that vary in size
   and its corresponding text file with the combiner.
2. Using a combiner reduces the  time taken in by half when compared with the time taken to 
   run the same job without combiner.
3. Here the file sizes for the text files for each data sets are in a increasing sequence like 7.84gb, 16.47gb and 33.59gb
4. The run time for each data set without combiner is shown above  where the small data set 
   takes 3.9min, medium takes 7.48min and larger data set takes 10.45 min
5. Difference is time is evident, but it is less than item 1, where the small data set takes lesser time and 
   the larger data set takes more this is because by default we are running the job  based on the 
   resources that are available at the time of running the job. 
6. No. of splits small data set is 63 and for medium its 132 and for larger dataset its 269 
   Since the files size increases the number of block size in data node also increased but for smaller files 
   the number of blocks are lesser so the processing is also takes faster.
7. Using a Combiner will reduce the amount processing to be done in reducer which will be done by a combiner 
   which work with the intermidiate output given by the mapper.
```