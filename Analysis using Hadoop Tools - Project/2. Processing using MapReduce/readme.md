# Processing using MapReduce - ITEM One

In this item, I wrote MapReduce programs for processing the data present in the small-logs-08-09-2016.zip and logs-2014-2015.zip files to find the most frequently visited URL per month and per day based on the conditions that the URL is not of the type index.* and has a return code of 200.

After moving the data on to the hdfs platform, I coded the MaxVisitedurl.java, MaxVisitedurlMapper.java and MaxVisitedurReducer.java  for both day and month and compiled them to get their corresponding classes. After successful compilation, I created the jar file to execute them using hadoop command.

## INPUT FILE DETAILS


```
## FILE 1: Large.txt - which is a concated file of all log files present in the large logs
## FILE 2: Small.txt - which is a concated file of all log files present in the small logs


```
### STEP 1- HOW CONCATENATION IS DONE
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-01/image/concatenate_files.PNG "Optional title")

### STEP 2 - Start all hadoop  services
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-01/image/jps.PNG "Optional title")

### STEP 3 - Move both the data to hadoop file system
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-01/image/move_data.PNG "Optional title")


# MOST VISITED URL PER-DAY

Source code: Check inside perday folder
```
1. MaxVisitedurlPerday.java - driver class
2. MaxVisitedurlMapper.java - Mapper class
3. MaxVisitedurlReducer.java - Reducer class
```

### STEPS INVOLVED TO FIND MOST VISITED URL FOR EACH DAY

```
1. complie all you java by using the command
	- hadoop com.sun.tools.javac.Main *.java
	- jar cf url.jar *.class
2. Run the jar file with both the small and larger log files

```
Running Large log Input File:
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-01/image/perday_large.PNG "Optional title")

##### OUTPUT (PerDay Large Logs): 

###COMPLETE OUTPUT: [https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-01/perday/perday_larger_file.txt].

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-01/image/output1-perday_L.PNG "Optional title")

Running Small log Input File:

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-01/image/perday_small.PNG "Optional title")

##### OUTPUT (PerDay Small Logs): 

###COMPLETE OUTPUT: [https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-01/perday/perday_small_file.txt].

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-01/image/output2-perday_S.PNG "Optional title")

COMPLETE OUTPUT: [https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-01/perday/perday_small_file.txt].
# MOST VISITED URL PER-MONTH

Source code: Check inside permonth folder
```
1. MaxVisitedurlPerMonth.java - driver class
2. MaxVisitedurlMapper.java - Mapper class
3. MaxVisitedurlReducer.java - Reducer class
```
### STEPS INVOLVED TO FIND MOST VISITED URL FOR EACH MONTH

```
1. complie all you java by using the command
	- hadoop com.sun.tools.javac.Main *.java
	- jar cf url.jar *.class
2. Run the jar file with both the small and larger log files

```
Running Large log Input File:
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-01/image/permonth_large.PNG "Optional title")

##### OUTPUT (PerDay Large Logs): 

###COMPLETE OUTPUT: [https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-01/permonth/permonth_large.txt].


![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-01/image/output3-permonth_large.PNG "Optional title")

Running Small log Input File:

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-01/image/permonth_small.PNG "Optional title")

##### OUTPUT (PerDay Small Logs): 

###COMPLETE OUTPUT: [https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-01/permonth/permonth_small.txt].

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-01/image/output4-permonth_small.PNG "Optional title")

## ASSUMPTIONS :

1. "/ " in URL is considered to null and avoided for calculation.
2. Consider only url having a return status 200
3. Index.* url's are avoided
4. If two URL's have same number of count for most visited per day or per month, In MAP REDUCE the order in which the URL appears in log is considered to be the most visited URL. 
