# ITEM FOUR

```
Running the MapReduce program to find the MaxTemperature on the following data sets with the Gzip compression 	.


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


### Commands to run the job in the cluster

```

gzipwithout combiner

time nohup hadoop jar mt.jar MaxTemperaturegzip /user/root/ncdc/1990/1990.txt /user/$USER/output/week007/item4/1990txt/gzip >> log/19.txt    				-- 0349  - 12mins, 40sec                
time nohup hadoop jar mt.jar MaxTemperaturegzip /user/root/ncdc/1990/1990.txt.gz /user/$USER/output/week007/item4/1990gz/gzip >> log/20.txt   				-- 0350  - 18mins, 28sec   
time nohup hadoop jar mt.jar MaxTemperaturegzip /user/root/ncdc/1990/1990.txt.bz2 /user/$USER/output/week007/item4/1990bz/gzip >> log/21.txt  				-- 0352  - 7mins, 0sec      

time nohup hadoop jar mt.jar MaxTemperaturegzip /user/root/ncdc/90and92/1990and1992.txt /user/$USER/output/week007/item4/90and92txt/gzip >> log/22.txt      -- 0354 - 46mins, 11sec
time nohup hadoop jar mt.jar MaxTemperaturegzip /user/root/ncdc/90and92/1990and1992.txt.gz /user/$USER/output/week007/item4/90and92gz/gzip >> log/23.txt    -- 0355 - 18mins, 0sec 
time nohup hadoop jar mt.jar MaxTemperaturegzip /user/root/ncdc/90and92/1990and1992.txt.bz2 /user/$USER/output/week007/item4/90and92bz/gzip >> log/24.txt   -- 0357 - 9mins, 28sec
 																																					
time nohup hadoop jar mt.jar MaxTemperaturegzip /user/root/ncdc/90-93/90919293.txt  /user/$USER/output/week007/item4/90-93txt/gzip >> log/25.txt         	-- 0364  - 27mins, 43sec  
time nohup hadoop jar mt.jar MaxTemperaturegzip /user/root/ncdc/90-93/90919293.txt.gz  /user/$USER/output/week007/item4/90-93gz/gzip >> log/26.txt       	-- 0360  - 33mins, 6sec
time nohup hadoop jar mt.jar MaxTemperaturegzip /user/root/ncdc/90-93/90919293.txt.bz2 /user/$USER/output/week007/item4/90-93bz/gzip >> log/27.txt       	-- 0361  - 25mins, 54sec


gzip withCombiner
time nohup hadoop jar mt.jar MaxTemperaturegzipWithCombiner /user/root/ncdc/1990/1990.txt /user/$USER/output/week007/item4c/1990txt/gzip >> log/19.txt  		 -- 0375 - 10mins, 46sec                 
time nohup hadoop jar mt.jar MaxTemperaturegzipWithCombiner /user/root/ncdc/1990/1990.txt.gz /user/$USER/output/week007/item4c/1990gz/gzip >> log/20.txt  		 -- 0396 - 4mins, 37sec        
time nohup hadoop jar mt.jar MaxTemperaturegzipWithCombiner /user/root/ncdc/1990/1990.txt.bz2 /user/$USER/output/week007/item4c/1990bz/gzip >> log/21.txt 		 -- 0397 - 6mins, 23sec             

time nohup hadoop jar mt.jar MaxTemperaturegzipWithCombiner /user/root/ncdc/90and92/1990and1992.txt /user/$USER/output/week007/item4c/90and92txt/gzip >> log/22.txt  	-- 0398 - 20mins, 58sec      
time nohup hadoop jar mt.jar MaxTemperaturegzipWithCombiner /user/root/ncdc/90and92/1990and1992.txt.gz /user/$USER/output/week007/item4c/90and92gz/gzip >> log/23.txt   -- 0400 - 35mins, 31sec
time nohup hadoop jar mt.jar MaxTemperaturegzipWithCombiner /user/root/ncdc/90and92/1990and1992.txt.bz2 /user/$USER/output/week007/item4c/90and92bz/gzip >> log/24.txt  -- 0402 - 4mins, 51sec

time nohup hadoop jar mt.jar MaxTemperaturegzipWithCombiner /user/root/ncdc/90-93/90919293.txt  /user/$USER/output/week007/item4c/90-93txt/gzip >> log/25.txt    		-- 0403 - 32mins, 40sec         
time nohup hadoop jar mt.jar MaxTemperaturegzipWithCombiner /user/root/ncdc/90-93/90919293.txt.gz  /user/$USER/output/week007/item4c/90-93gz/gzip >> log/26.txt   		-- 0405 - 46mins, 49sec  
time nohup hadoop jar mt.jar MaxTemperaturegzipWithCombiner /user/root/ncdc/90-93/90919293.txt.bz2 /user/$USER/output/week007/item4c/90-93bz/gzip >> log/27.txt   		-- 0406 - 40mins, 31sec 



```
## TIMING FOR RUNNING THE MAXTEMPERATURE JOB WITHOUT COMBINER ENABLING GZIP COMPRESSION


| DATA          	   | MAPREDUCE PROPERTY		 | TIME          |	JOB ID (Last 4 digit)  	|
| -------------------- |:-----------------------:| -------------:| ------------------------:|
| 1990.txt      	   | WithOut Combiner	     | 12mins, 40sec |  		0349  			|
| 1990.txt.gz   	   | WithOut Combiner		 | 18mins, 28sec |			0350			|	
| 1990.txt.bz  		   | WithOut Combiner      	 | 7mins,  0sec	 |			0352			|
| 1990and1992.txt      | WithOut Combiner		 | 46mins, 11sec |			0354			|
| 1990and1992.txt.gz   | WithOut Combiner		 | 18mins, 0sec  |			0355			|
| 1990and1992.txt.bz   | WithOut Combiner		 | 9mins, 28sec  |			0357			|
| 90919293.txt         | WithOut Combiner		 | 27mins, 43sec |			0364			|
| 90919293.txt.gz      | WithOut Combiner		 | 33mins, 06sec |			0360			|
| 90919293.txt.bz      | WithOut Combiner		 | 25mins, 54sec |			0361			|

MAXTEMPERATURE  WITHOUT COMBINER
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-07/item-four/WITHOUT%20COMBINER.PNG "Optional title")


## TIMING FOR RUNNING THE MAXTEMPERATURE JOB WITH  COMBINER ENABLING GZIP
					    	   
| DATA          	   | MAPREDUCE PROPERTY      | TIME          |  JOB ID (Last 4 digit)  	|
| -------------------- |:-----------------------:| -------------:| ------------------------:|
| 1990.txt      	   | With Combiner			 | 10mins, 46sec | 			0375			|
| 1990.txt.gz   	   | With Combiner		     | 4mins, 37sec	 |			0396			|
| 1990.txt.bz  		   | With Combiner           | 6mins, 23sec	 |			0397			|
| 1990and1992.txt      | With Combiner		     | 20mins, 58sec |			0398			|
| 1990and1992.txt.gz   | With Combiner			 | 35mins, 31sec |			0400			|
| 1990and1992.txt.bz   | With Combiner		     | 4mins, 51sec  |			0402			|
| 90919293.txt         | With Combiner		     | 32mins, 40sec |			0403			|
| 90919293.txt.gz      | With Combiner			 | 46mins, 49sec |			0405			|
| 90919293.txt.bz      | With Combiner		     | 20mins, 31sec |			0406			|

MAXTEMPERATURE  WITH COMBINER
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-07/item-four/WITH%20COMBINER.PNG "Optional title")

-Text based input has taken 16gb memory and 18 cores. - there is a slight reduce is time using a combiner.
-gzip based input has taken 16gb memoryb and 22 cores. from the above comaprison it is clear that it has faced some resource probelm 
so the results are quite disturbed.
-bzip the result are clear there is great difference in using a combiner when compared to jobs without a combiner.


![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-07/item-four/book.png "Optional title")

- intermediate compression uses  fast compressor such as LZO or LZ4  which has a increase the performance
- When running hadoop job the Map output is written to the disk once the mapping phase is done and it is 
  then transferred across network to reducers for the reduce phase.

- When there is huge amount of data has to be transferred from once hase to another phase it reduces the throughput. Hence it is advantageous to perform a intermeidate compression.
- since it reduces the amount of data that has to  be written to the  disk and transferred across the network it increases the throughput and data transfer happens at a faster rate.