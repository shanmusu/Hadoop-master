# ITEM SIX


comparison and contrasting of all of these 5 elements in two categories:

## Ease of gathering, processing, and wrtting code to retrieve data

### 1. MAPREDUCE

#### Gathering Data: 
1. In mapreduce it is just a raw data which is directly fed to the MapReduce program.
2. In our case since it was log data, the field name where bit clear so getting column information where easy.
   but if the data is unstructured completely, where the user must indentify the field details and process it, in such cases it becomes difficult.
3. Here, we concatenated all the log files into one and moved to HDFS for input which was easy.  

#### Processing:

1. The data has few line starting with '#' makes the program to malfunction. 
2. Indentified such cases and added them as a constrain in code, and avoided such lines.
3. Having a decent memory made the processing fast (16 GB memory and Xenial custom vagrant 64gb ), Initially, trying with 4gb and 8gb of ram made the process to take long time.

#### Wrtting code: 


 1. identify the key and value field is and checking for all constrains such as no index.* and sc_status should be 200, only then the context.write should be done in mapper class.
 2. actual logic stays in reducer classs, where the count of each url from a iteratable list of URL for each date is identified, then the most high count url is written as output.
 3. Hash Map is used to store the url count here. 


### 2. SQL

#### Gathering Data:

1. using the same  log data for SQL, importing into SQL.
2. concatenated all the files into one txt file for small and large dataset.
3. we can push the data directly using inline load method using delimiters or a java program can be written to push the data into mysql
4. Get the meta data for the field of the data log.
#### Processing:

1. Going with JAva is time consuming and there is chance of memory problem may occur. instead inline-load method is faster and prefered.  
2. Intially, my vagrant box size was too small due to which I was getting error like table is full befor inserting all the line of log
3. Custome xenial box is installed to increase the size of the box and then the insertion is done. 

#### Wrtting code: 
1. SQL usually works on FULL GROUP BY MODE which is again disturbed the output.
2. check the sql mode by using the command
 - select @@sql_mode;
3. set the sql_mode to be null
4. create the table structure and insert the data
5. using the sub query select method it is possibe to get the indended output.
- subquery get the count of each url grouped by url and date ordered by the count of url
- outter query get the  max count of URL grouped by date

### 3. SQOOP

#### Gathering Data:

1. Indentify which database has the data that has to be moved to the HDFS
2. make sure the database has all permission given for the user of the host
3. identify all the columns needed 
4. or you can also import all the data into HDFS

#### Processing:
1. If your data has primary key you can increase the number of map in import command
2. Since the log table is not designed in such model we are moving with 1 M option - one map
3. again, here for sqoop job set the sql mode to disabled.
4. remove the line starting with the '#' from mysql which are unwanted lines for processing

#### Wrtting code: 
  
- MAPREDUCE: 
        1. once the import is done with a sqoop import statement and java file is generated 
        2. use the same logic of mapreduce again to find the most visted URL for day and month.
        3. IT is again similar to Item 1
- SQOOP JOB
        1. create a job in sqoop using sqoop job 
        2. give the connection string with username and password 
        3. using the sQL query with the job, creates a job 
        4. sqoop --exec is used to execute the job

### 4.HIVE

#### Gathering Data:

1. using the data already present in the mysql 
2. identify the fields that are nessesaary for the processing    

#### Processing:

1. Using the sqoop we can import the sql table into the hive maintaining the same structure 
2. since hive is schema on read the reading process is much more faster than any other process.
3. since we have already removed all the '#' line in mysql there is no more preprocessing required.

#### Wrtting code:   

modify the query that is used in mysql and sqoop job to find the most visited url per day and the most visited url per month
for both the large and small dataset 

ASSUMPTION: 
If  we have used partations in hive, It would have been the fastest method to retrive the data.

### 5.PIG

#### Gathering Data:

again the data can be load from the local path, where the data has to be concatenated into a single files. 

#### Processing:

It similart process of processing but pig is preferable if the data set which we are using is more of numerical.

#### Wrtting code:   

It is easy to code in PIG. which is one of the biggest advantage.

## Real time experience 

- Two years back, I used hadoop for processing tweets from twitter. For which I have used apache FLUME to contineously stream data into hdfs.
Now for the case of finding  the most retwetted string,  the data is loaded in hdfs we can stag it in a partition table in hive. 
and since the twitter provides data in JSON format it is more better option to store them in hive in a partition table because each.
- sqoop was very useful and sqoop with hive is a great combination for work that I was carrying out as an ETL developer. Using ETL tools to process millions rows of data took me a lot of time. It used to take like more than 6 hours to process the data through the pipeline and sometimes, it become complex as well. Hence, SQOOP with MapReduce on top of it and Hive to process the data is my personal choice.

- Future Learning: It becomes difficult if twitter gives data contineously through their API. in such cases Apache oozie can be used to maintain the job flow  where we can keep schedule job to be runner and alter the table when ever required.  In my scenario I would prefer hive. But it is very clear that it depends for each cases. On the whole the simple MapReduce was more efficient in out final project.    