# ITEM FIVE

```
Running the MapReduce program to find the MaxTemperature on the following data sets with the Native compression.

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
```
	Number of  Live nodes: 6
	Node Capacity: 492.03 GB X 6
	Node Memory : 16GB
	Total VCores used: 46
	Average number of block in nodes :8500
	Memory available: 16GB
```
### Command to  compile the java files and create a jar file


```
hadoop com.sun.tools.javac.Main MaxTemperature*.java
jar cf mt1.jar MaxTemperature*.class

```


### Commands to run the job in the cluster



```

Mapoutputcompression without combiner
time nohup hadoop jar mt1.jar MaxTemperatureWithMapOutputCompression /user/root/ncdc/1990/1990.txt /user/$USER/output/week007/item5-1/1990txt/gzip >> log/1.txt                   
time nohup hadoop jar mt1.jar MaxTemperatureWithMapOutputCompression /user/root/ncdc/1990/1990.txt.gz /user/$USER/output/week007/item5-1/1990gz/gzip >> log/2.txt             
time nohup hadoop jar mt1.jar MaxTemperatureWithMapOutputCompression /user/root/ncdc/1990/1990.txt.bz2 /user/$USER/output/week007/item5-1/1990bz/gzip >> log/3.txt                 

time nohup hadoop jar mt1.jar MaxTemperatureWithMapOutputCompression /user/root/ncdc/90and92/1990and1992.txt /user/$USER/output/week007/item5-1/90and92txt/gzip >> log/4.txt   
time nohup hadoop jar mt1.jar MaxTemperatureWithMapOutputCompression /user/root/ncdc/90and92/1990and1992.txt.gz /user/$USER/output/week007/item5-1/90and92gz/gzip >> log/5.txt
time nohup hadoop jar mt1.jar MaxTemperatureWithMapOutputCompression /user/root/ncdc/90and92/1990and1992.txt.bz2 /user/$USER/output/week007/item5-1/90and92bz/gzip >> log/6.txt   

time nohup hadoop jar mt1.jar MaxTemperatureWithMapOutputCompression /user/root/ncdc/90-93/90919293.txt  /user/$USER/output/week007/item5-1/90-93txt/gzip >> log/7.txt                
time nohup hadoop jar mt1.jar MaxTemperatureWithMapOutputCompression /user/root/ncdc/90-93/90919293.txt.gz  /user/$USER/output/week007/item5-1/90-93gz/gzip >> log/8.txt 
time nohup hadoop jar mt1.jar MaxTemperatureWithMapOutputCompression /user/root/ncdc/90-93/90919293.txt.bz2 /user/$USER/output/week007/item5-1/90-93bz/gzip >> log/9.txt     


Mapoutputcompression with combiner

time nohup hadoop jar mt1.jar MaxTemperatureWithMapOutputCompressionWithCombiner /user/root/ncdc/1990/1990.txt /user/$USER/output/week007/item5c-1/1990txt/gzip >> log/11.txt                         
time nohup hadoop jar mt1.jar MaxTemperatureWithMapOutputCompressionWithCombiner /user/root/ncdc/1990/1990.txt.gz /user/$USER/output/week007/item5c-1/1990gz/gzip >> log/12.txt       
time nohup hadoop jar mt1.jar MaxTemperatureWithMapOutputCompressionWithCombiner /user/root/ncdc/1990/1990.txt.bz2 /user/$USER/output/week007/item5c-1/1990bz/gzip >> log/13.txt            

time nohup hadoop jar mt1.jar MaxTemperatureWithMapOutputCompressionWithCombiner /user/root/ncdc/90and92/1990and1992.txt /user/$USER/output/week007/item5c-1/90and92txt/gzip >> log/14.txt       
time nohup hadoop jar mt1.jar MaxTemperatureWithMapOutputCompressionWithCombiner /user/root/ncdc/90and92/1990and1992.txt.gz /user/$USER/output/week007/item5c-1/90and92gz/gzip >> log/15.txt   
time nohup hadoop jar mt1.jar MaxTemperatureWithMapOutputCompressionWithCombiner /user/root/ncdc/90and92/1990and1992.txt.bz2 /user/$USER/output/week007/item5c-1/90and92bz/gzip >> log/16.txt   

time nohup hadoop jar mt1.jar MaxTemperatureWithMapOutputCompressionWithCombiner /user/root/ncdc/90-93/90919293.txt  /user/$USER/output/week007/item5c-1/90-93txt/gzip >> log/17.txt                
time nohup hadoop jar mt1.jar MaxTemperatureWithMapOutputCompressionWithCombiner /user/root/ncdc/90-93/90919293.txt.gz  /user/$USER/output/week007/item5c-1/90-93gz/gzip >> log/18.txt
time nohup hadoop jar mt1.jar MaxTemperatureWithMapOutputCompressionWithCombiner /user/root/ncdc/90-93/90919293.txt.bz2 /user/$USER/output/week007/item5c-1/90-93bz/gzip >> log/19.txt       

```

## TIMING FOR RUNNING THE MAXTEMPERATURE JOB WITHOUT COMBINER ENABLING NATIVE HADOOP COMPRESSION CODECS


| DATA          	   | MAPREDUCE PROPERTY		 | TIME          |	JOB ID (Last 4 digit)  	|
| -------------------- |:-----------------------:| -------------:| ------------------------:|
| 1990.txt      	   | WithOut Combiner	     | 4mins, 52sec  |  		1508  			|
| 1990.txt.gz   	   | WithOut Combiner		 | 24mins, 41sec |			1509			|	
| 1990.txt.bz  		   | WithOut Combiner      	 | 16mins, 47sec |			1510			|
| 1990and1992.txt      | WithOut Combiner		 | 12mins, 3sec  |			1511			|
| 1990and1992.txt.gz   | WithOut Combiner		 | 13mins, 35sec |			1512			|
| 1990and1992.txt.bz   | WithOut Combiner		 | 11mins, 19sec |			1513			|
| 90919293.txt         | WithOut Combiner		 | 30mins, 24sec |			1514			|
| 90919293.txt.gz      | WithOut Combiner		 | 49mins, 51sec |			1515			|
| 90919293.txt.bz      | WithOut Combiner		 | 13mins, 54sec |			1516			|

MAXTEMPERATURE  WITHOUT COMBINER
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-07/item-five/without%20combiner.PNG "Optional title")


## TIMING FOR RUNNING THE MAXTEMPERATURE JOB WITH  COMBINER ENABLING  NATIVE HADOOP COMPRESSION CODECS
					    	   
| DATA          	   | MAPREDUCE PROPERTY      | TIME          |  JOB ID (Last 4 digit)  	|
| -------------------- |:-----------------------:| -------------:| ------------------------:|
| 1990.txt      	   | With Combiner			 | 24mins, 27sec | 			1517			|
| 1990.txt.gz   	   | With Combiner		     | 7mins, 21sec	 |			1519			|
| 1990.txt.bz  		   | With Combiner           | 13mins, 39sec |			1520			|
| 1990and1992.txt      | With Combiner		     | 12mins, 14sec |			1522			|
| 1990and1992.txt.gz   | With Combiner			 | 5mins, 5sec   |			1524			|
| 1990and1992.txt.bz   | With Combiner		     | 7mins, 37sec  |			1525			|
| 90919293.txt         | With Combiner		     | 30mins, 11sec |			1526			|
| 90919293.txt.gz      | With Combiner			 | 15mins, 58sec |			1527			|
| 90919293.txt.bz      | With Combiner		     | 13mins, 35sec |			1528			|

MAXTEMPERATURE  WITH COMBINER
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-07/item-five/with%20combiner.PNG "Optional title")
