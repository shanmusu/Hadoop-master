# ITEM THREE

```
Running the MapReduce program to find the MaxTemperature on the following data sets.


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
time nohup hadoop jar mt.jar MaxTemperature /user/root/ncdc/1990/1990.txt.bz2 /user/$USER/output/week007/1990bz >> log/3.txt		     		
time nohup hadoop jar mt.jar MaxTemperature /user/root/ncdc/1990/1990.txt.gz /user/$USER/output/week007/1990gz >> log/5.txt               		
time nohup hadoop jar mt.jar MaxTemperatureWithCombiner /user/root/ncdc/1990/1990.txt /user/$USER/output/week007/1990c >> log/2.txt    		 	
time nohup hadoop jar mt.jar MaxTemperatureWithCombiner /user/root/ncdc/1990/1990.txt.bz2 /user/$USER/output/week007/1990bzc >> log/4.txt 		
time nohup hadoop jar mt.jar MaxTemperatureWithCombiner /user/root/ncdc/1990/1990.txt.gz /user/$USER/output/week007/1990gzc >> log/6.txt   		
	
time nohup hadoop jar mt.jar MaxTemperature /user/root/ncdc/90and92/1990and1992.txt /user/$USER/output/week007/90and92 >> log/7.txt      		          
time nohup hadoop jar mt.jar MaxTemperature /user/root/ncdc/90and92/1990and1992.txt.gz /user/$USER/output/week007/90and92gz >> log/8.txt        		  
time nohup hadoop jar mt.jar MaxTemperature /user/root/ncdc/90and92/1990and1992.txt.bz2 /user/$USER/output/week007/90and92bz >> log/9.txt        		  
time nohup hadoop jar mt.jar MaxTemperatureWithCombiner /user/root/ncdc/90and92/1990and1992.txt /user/$USER/output/week007/90and92c >> log/10.txt		  
time nohup hadoop jar mt.jar MaxTemperatureWithCombiner /user/root/ncdc/90and92/1990and1992.txt.gz /user/$USER/output/week007/90and92gzc >> log/11.txt	  
time nohup hadoop jar mt.jar MaxTemperatureWithCombiner /user/root/ncdc/90and92/1990and1992.txt.bz2 /user/$USER/output/week007/90and92bzc >> log/12.txt   

time nohup hadoop jar mt.jar MaxTemperature /user/root/ncdc/90-93/90919293.txt /user/$USER/output/week007/90-93 >> log/13.txt  							  
time nohup hadoop jar mt.jar MaxTemperature /user/root/ncdc/90-93/90919293.txt.bz2 /user/$USER/output/week007/90-93bz >> log/14.txt  					  
time nohup hadoop jar mt.jar MaxTemperature /user/root/ncdc/90-93/90919293.txt.gz /user/$USER/output/week007/90-93gz >> log/15.txt   				  	  
time nohup hadoop jar mt.jar MaxTemperatureWithCombiner /user/root/ncdc/90-93/90919293.txt /user/$USER/output/week007/90-93c >> log/16.txt    			  
time nohup hadoop jar mt.jar MaxTemperatureWithCombiner /user/root/ncdc/90-93/90919293.txt.bz2 /user/$USER/output/week007/90-93bzc >> log/17.txt 		  
time nohup hadoop jar mt.jar MaxTemperatureWithCombiner /user/root/ncdc/90-93/90919293.txt.gz /user/$USER/output/week007/90-93gzc >> log/18.txt  
```
## JOB HISTORY FOR MAXTEMPERATURE JOB 
![image]( https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-07/item-three/job%20history.PNG "JOB HISTORY")

## TIMING FOR RUNNING THE MAXTEMPERATURE JOB WITHOUT COMBINER


| DATA          	   | MAPREDUCE PROPERTY		 | TIME          |	JOB ID (Last 4 digit)  	|
| -------------------- |:-----------------------:| -------------:| ------------------------:|
| 1990.txt      	   | WithOut Combiner	     | 10mins, 13sec | job_14889988448355_1905 	|
| 1990.txt.gz   	   | WithOut Combiner		 | 4mins, 13sec  |			1912			|	
| 1990.txt.bz  		   | WithOut Combiner      	 | 6mins, 44sec	 |			1910			|
| 1990and1992.txt      | WithOut Combiner		 | 12mins, 12sec |			1914			|
| 1990and1992.txt.gz   | WithOut Combiner		 | 20mins, 7sec  |			1915			|
| 1990and1992.txt.bz   | WithOut Combiner		 | 15mins, 50sec |			1916			|
| 90919293.txt         | WithOut Combiner		 | 26mins, 46sec |			1921			|
| 90919293.txt.gz      | WithOut Combiner		 | 32mins, 48sec |			1926			|
| 90919293.txt.bz      | WithOut Combiner		 | 13mins, 3sec  |			1925			|

MAXTEMPERATURE  WITHOUT COMBINER
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-07/item-three/WITHOUT%20COMBINER.PNG "Optional title")


## TIMING FOR RUNNING THE MAXTEMPERATURE JOB WITH  COMBINER
					    	   
| DATA          	   | MAPREDUCE PROPERTY      | TIME          |  JOB ID (Last 4 digit)  	|
| -------------------- |:-----------------------:| -------------:| ------------------------:|
| 1990.txt      	   | With Combiner			 | 3mins, 9sec   | 			1909			|
| 1990.txt.gz   	   | With Combiner		     | 2mins, 15sec	 |			1913			|
| 1990.txt.bz  		   | With Combiner           | 4mins, 16sec	 |			1911			|
| 1990and1992.txt      | With Combiner		     | 7mins, 48sec  |			1917			|
| 1990and1992.txt.gz   | With Combiner			 | 16mins, 35sec |			1918			|
| 1990and1992.txt.bz   | With Combiner		     | 11mins, 36sec |			1919			|
| 90919293.txt         | With Combiner		     | 10mins, 45sec |			1927			|
| 90919293.txt.gz      | With Combiner			 | 20mins, 35sec |			1930			|
| 90919293.txt.bz      | With Combiner		     | 7mins, 38sec  |			1928			|

MAXTEMPERATURE  WITH COMBINER
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-07/item-three/WITHOUT%20COMBINER.PNG "Optional title")

ANALYSIS: 


Note: Time may vary depending on the resources say the number of container available at the time of each job.

### Without combiner
Running the job without combiner  for text file is same as item 1.

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


### With Combiner
Running the job with combiner for text file is same as item 2.

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

### Compression files

1. There are two different types of compression one is GZIP and BZIP.
2. In Gzip compression type, it does not support splitting hence the number of map is alwasys 1.
   so generally for all the three data sets, the gzip takes more time when compared to the bzip file type.
3. In bzip compression type file can be splitted and at the same time after compressing the data the size is very less 
   than the other two data type available. After splitting the data it can be processed quick.
4. As the file size increases it is clear that bzip type has lesser running time for the jobs. 

Bzip-  (Vcores used -14)
       all of its jobs it is shuffled and reduced into a single file. Here the shuffling time is more as the map outputs is compressed files 
       it needs to be sorted before they are  sent to reducer phase.
       Using a Combiner takes more time to execute as the output of each map has to be sent to combiner for shuffling and output is also not sorted.
Gzip - (Vcores used -14)
       It is built by DEFLATE Algorithim which uses snappy, so it doesn’t support splits
       It is effective when compared to normal text files.