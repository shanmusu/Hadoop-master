# Integrating Data using Sqoop - ITEM THREE

In this item, I wrote a MapReduce programs on top of sqoop for processing the data present in the mysql tables SMALL_LOGS, LARGE_LOGS residing in the database. The processing is carried out to identify the most frequently visited URL per month and per day that is not index.* and that also has a return code of 200.

In this process, I first configured the SQOOP_HOME path in bashrc file and after which I first integrated the data from the mysql table to the hdfs using the sqoop import command.

Once moving the data on top of hdfs, I coded the MaxVisitedUrl(day/month) in java that has both Mapper and Reducer in seperate java class. After successful compilation, I created the MaxVisitedUrl.jar file to execute them using hadoop command.

```
Running the sample code job provided by the book and insert three addition records 
```

STEP 1- login into mysql using the root user and identify the tables in the mysql

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-03/images/sql.PNG "Optional title")

STEP 2- set the grant all option to the pradeep database which is having the large and the small log file. 

```
grant all privileges on pradeep.* TO  ''@localhost identified by 'password';
```
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-03/images/grant.PNG "Optional title")


STEP 3 - run the sqoop import command to start importing the data into hdfs

commands
LARGE TABLE
```
sqoop import --connect jdbc:mysql://localhost/pradeep \
--username vagrant \
--password password \
--table large_log \
-m 1 

```
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-03/images/largre_import1.PNG "Optional title")

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-03/images/largre_import2.PNG "Optional title")
SMALL TABLE

```
sqoop import --connect jdbc:mysql://localhost/pradeep \
--username vagrant \
--password password \
--table small_log \
-m 1 
```
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-03/images/small_import1.PNG "Optional title")

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-03/images/small_import2.PNG "Optional title")

## DATA IN HDFS

### SMALL LOG

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-03/images/cat_large.PNG "Optional title")
### LARGE LOG

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-03/images/cat_small.PNG "Optional title")

# SQOOP JOB


PER MONTH _ LARGE DATA
```
sqoop job --create url_month \
-- eval \
--connect jdbc:mysql://localhost/pradeep \
--username vagrant \
-P \
--query "SELECT each_year, each_month, most_visited_url, MAX(number_of_visitors) AS number_of_visitors
FROM (SELECT year(dates) AS each_year, month(dates) AS each_month, cs_uri_stem AS most_visited_url, count(cs_uri_stem) AS number_of_visitors
FROM large_log
where cs_uri_stem NOT LIKE '/' and cs_uri_stem NOT LIKE '%index%' and sc_status like '200'
GROUP BY cs_uri_stem, year(dates),month(dates)
order by count(cs_uri_stem) DESC) AS abc
GROUP BY each_year, each_month
order by each_year, each_month"
```
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-03/images/permonth_L.PNG "Optional title")



PER MONTH_SMALL DATA
```
sqoop job --create url_month2 \
-- eval \
--connect jdbc:mysql://localhost/pradeep \
--username vagrant \
-P \
--query "SELECT each_year, each_month, most_visited_url, MAX(number_of_visitors) AS number_of_visitors
FROM (SELECT year(date) AS each_year, month(date) AS each_month, cs_uri_stem AS most_visited_url, count(cs_uri_stem) AS number_of_visitors
FROM small_log
where cs_uri_stem NOT LIKE '/' and cs_uri_stem NOT LIKE '%index%' and sc_status like '200'
GROUP BY cs_uri_stem, year(date),month(date)
order by count(cs_uri_stem) DESC) AS abc
GROUP BY each_year, each_month
order by each_year, each_month"
```
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-03/images/permonth_S.PNG "Optional title")


PER DAY _ LARGE DATA
```
sqoop job --create url_day1 \
-- eval \
--connect jdbc:mysql://localhost/pradeep \
--username vagrant \
-P \
--query "SELECT MAX(sub_query.number_of_visitors) AS number_of_visitors, sub_query.most_visited_url AS most_visited_url , sub_query.dates AS each_day FROM (
SELECT dates, count(cs_uri_stem) AS number_of_visitors, cs_uri_stem AS most_visited_url FROM large_log where cs_uri_stem NOT LIKE '%index%' AND sc_status = '200' AND  cs_uri_stem NOT LIKE '/'
GROUP BY cs_uri_stem,dates
order by number_of_visitors DESC
) sub_query
GROUP BY each_day"
```
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-03/images/perday_L.PNG "Optional title")



PER DAY_SMALL DATA
```
sqoop job --create url_days \
-- eval \
--connect jdbc:mysql://localhost/pradeep \
--username vagrant \
-P \
--query "SELECT MAX(sub_query.number_of_visitors) AS number_of_visitors, sub_query.most_visited_url AS most_visited_url , sub_query.date AS each_day FROM (
SELECT date, count(cs_uri_stem) AS number_of_visitors, cs_uri_stem AS most_visited_url FROM small_log where cs_uri_stem NOT LIKE '%index%' AND sc_status = '200' AND  cs_uri_stem NOT LIKE '/'
GROUP BY cs_uri_stem,date
order by number_of_visitors DESC
) sub_query
GROUP BY each_day"
```

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-03/images/perday_S.PNG "Optional title")




## MAPREDUCE PROGRAM

STEP 1: use the location of the file which  is imported using sqoop whihc is the input for the MapReduce program
STEP 2: move the generated java file during the import function into the mapreduce program location 
STEP 3: complie and generate the jar file

```
hadoop com.sun.tools.javac.Main *.java
jar cf url.jar *.class

```

STEP 4: RUN THE PROGRAM FOR ALL 4 COMBINATIONS OF output

```
hadoop jar url.jar MaxVisitedUrlPerday -libjars /home/vagrant/sqoop/sqoop-1.4.6.jar

```
running: 

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-03/images/run1.PNG "Optional title")

# MODIFIED CODES ARE AVAILABLE IN LARGE LOG AND SMALL LOG FOLDER

OUTPUT:

## PER DAY LARGE:
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-03/images/perday_L_op.PNG "Optional title")

## PER DAY SMALL:
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-03/images/perday_S_op.PNG "Optional title")

## PER MONTH LARGE:
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-03/images/permonth_L_op.PNG "Optional title")

## PER MONTH SMALL
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-03/images/permonth_S_op.PNG "Optional title")

ASSUMPTION : 
- since few field which throwed error during the job run those field are avoided while importing the sql table
- delimiter for end of line is made as '|'

