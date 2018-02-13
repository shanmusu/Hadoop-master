# ITEM ONE

```
Running the MapReduce program to find the MaxTemperature without combiner on the text files of each data set.


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
time nohup hadoop jar mt.jar MaxTemperature /user/root/ncdc/1990/1990.txt /user/$USER/output/week007/1990 >> log/1.txt
time nohup hadoop jar mt.jar MaxTemperature /user/root/ncdc/90and92/1990and1992.txt /user/$USER/output/week007/90and92 >> log/7.txt
time nohup hadoop jar mt.jar MaxTemperature /user/root/ncdc/90-93/90919293.txt /user/$USER/output/week007/90-93 >> log/13.txt 
```

### TIME TAKEN FOR EACH DATA SET WITHOUT COMBINER


| DATA          	   |  TIME (WithOut Combine) |JOB ID (Last 4 digit)  	|
| -------------------- |------------------------:|-------------------------:|
| 1990.txt      	   |  10mins, 13sec		     | job_14889988448355_1905  |  
| 1990and1992.txt      |  12mins, 12sec          |			1914			|
| 90919293.txt         |  26mins, 46sec 		 |			1921			|

### ANALYSIS:

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-07/item-one/GRAPH.PNG "Optional title")

```
1. In this scnenario we are running all the three data set that vary in size and its corresponding text file
2. Here the file sizes for the text files for each data sets are in a increasing sequence like 7.84gb, 16.47gb and 33.59gb
3. The run time for each data set without combiner is shown above  where the small data set takes 10.13min, 
   medium takes 12.12min and larger data set takes 26.46 min
4. Difference is time is evident, where the small data set takes lesser time and the larger data set takes more,
   this is because by default we are running the job without the combiner and based on the resources that are 
   available at the time of running the job. 
5. No. of splits small data set is 63 and for medium its 132 and for larger dataset its 269 
   Since the files size increases the number of split in data node also increases but for smaller files
   the number of blocks are lesser so the processing is also takes faster.

```
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-07/item-one/BOOK.png  "Optional title")
### How MR takes advantage of the distributed cluster 

In Distributed system, in our scenario we have 6 nodes so the jobs can be processed in a faster manner.
This is because in distributed system it supports parallel processing.
In case of any data loss, or a node failure, here we have a replication factor of 3 where data is available 
all the time even in case of loss and high availability.



