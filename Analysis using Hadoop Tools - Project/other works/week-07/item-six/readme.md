# ITEM SIX

```
Running the MapReduce program to find the MaxTemperature on the following data sets.

	* 1990 /user/root/ncdc/1990/
	* 1990 & 1992 /user/root/ncdc/1990and1992/
	* 90-93 /user/root/ncdc/90-93/
```
 Following are the available data sets and their data size.

	1. 90-93 /user/root/ncdc/90-93/
		- text size : 33.59 GB
		- bzip size : 2.51 GB
		- gzip size : 4.36 GB
## CLUSTER CONFIGURATION 
	Number of  Live nodes: 6
	Node Capacity: 492.03 GB X 6
	Node Memory : 16GB
	Total VCores used: 46
	Average number of block in nodes :8500
	Memory available: 16GB

### Command to  compile the java files and create a jar file

```
hadoop com.sun.tools.javac.Main MaxTemperature*.java
jar cf mt.jar MaxTemperature*.class

```


chainning 1:

The code to run this with two discrete MapReduce jobs 

16 GB memory and 21 crores whwere used during the execution.

### Commands to run the job in the cluster

```
#chaining 1

time nohup hadoop jar mt.jar MaxTemperature /user/root/ncdc/90-93/90919293.txt /user/$USER/output/week007/c1/90-93 >> log/1.txt  					1309			
time nohup hadoop jar mt.jar MaxTemperature /user/root/ncdc/90-93/90919293.txt.bz2 /user/$USER/output/week007/c1/90-93bz >> log/2.txt  				1310	
time nohup hadoop jar mt.jar MaxTemperature /user/root/ncdc/90-93/90919293.txt.gz /user/$USER/output/week007/c1/90-93gz >> log/3.txt   		         1311

wait for above  jobs to complete and then run the following code!!

time nohup hadoop jar mt.jar MaxTemperature1 /user/$USER/output/week007/c1/90-93/part-r-00000 /user/$USER/output/week007/c1/90-93c >> log/4.txt  			
time nohup hadoop jar mt.jar MaxTemperature1 /user/$USER/output/week007/c1/90-93bz/part-r-00000  /user/$USER/output/week007/c1/90-93bzc >> log/5.txt 		 
time nohup hadoop jar mt.jar MaxTemperature1 /user/$USER/output/week007/c1/90-93gz/part-r-00000  /user/$USER/output/week007/c1/90-93gzc >> log/6.txt     
```


Dataset  | Total run time job W |
-------  | -------------------- | 
Text     | 15.55                | 
gz       | 16.55                | 
bz2      | 9.58                  | 

#Chainning 2: 
 
  Re-writen code of the MapReduce job to use Chaining 
  16 GB memory and 18 crores whwere used during the execution.


```
time nohup hadoop jar mt.jar MaxTemperature /user/root/ncdc/90-93/90919293.txt /user/$USER/output/week007/chaining/90-93 >> log/1.txt  	      1312			
time nohup hadoop jar mt.jar MaxTemperature /user/root/ncdc/90-93/90919293.txt.bz2 /user/$USER/output/week007/chaining/90-93bz >> log/2.txt   1313 		
time nohup hadoop jar mt.jar MaxTemperature /user/root/ncdc/90-93/90919293.txt.gz /user/$USER/output/week007/chaining/90-93gz >> log/3.txt     1314		

```
Dataset  | Total time
-------  | ----------
Text     | 25.59
gz       | 32.24
bz2      | 26.21

Comparision of execution time and other output data.


-- In Discrete Jobs,  first  the initial job output is saved to disk and then it is given as input to Second job.
-- During the inintial phase that is the first job the Maximum temperature is calculated in the first job and then the mean is calculated in the second job. 
-- This job is not efficient as the first job output is written to disk and then it is read as input to second job. 

  In chaining2, first job are completed and then the second job is executed from the output of the first job. 
  There is no intermediate data that is written into the disk from job1 instead it is directly feed for job 2.
  Execution of reduce phase takes place from the second map operation which should be more efficient in real-life and less time for the large data.

 when compared to normal real time jobs, one additional read and write operation is performed in the disk which is much costly and not an efficient usage of storage. but Its more faster than chainning when comparing the result of the run time of job discrete jobs are more faster. In discreate job the output is written in the same server and This wont be effecient for real time networks

