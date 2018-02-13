# Processing using MySQL - ITEM TWO

 I created a MySQL Database named "WEBLOGS" and a set of two tables - "SMALL_LOGS" and "LARGE_LOGS" for insertion of raw data from the log files present in logs-2015-2014.zip and small-logs-08-09-2016.zip files respectively. For this purpose, I used the shell scripts createtable.sh to create the SMALL_LOGS table and the createtable.sh to create the LARGE_LOGS table.

Both logs-2015-2014.zip and small-logs-08-09-2016.zip are uncompressed first. The resulting log files are merged together into a single log file using CAT command. The resulting files are named as "smalllogs.log" and "logs-2015.log". Then DataTransfer.java or using the load data infile is used to  write the data line by line and populate them inside the SMALL_LOGS and LARGE_LOGS table.

## INPUT FILE DETAILS


```
## FILE 1: Large.txt - which is a concated file of all log files present in the large logs
## FILE 2: Small.txt - which is a concated file of all log files present in the small logs


```
### STEP 1- HOW CONCATENATION IS DONE
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-01/image/concatenate_files.PNG "Optional title")

### STEP 2 - Login into MySql using local-Infile option
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-02/images/mysql-inline-load.PNG "Optional title")

### STEP 3 - Create tables for large Logs

```
create table large_log ( date varchar(20), time varchar(20), s_ip varchar(20),cs_method varchar(20), cs_uri_stem varchar(350), cs_uri_query varchar(10), s_port int, cs_username varchar(10), c_ip varchar(50), cs_user_agent varchar(300), sc_status varchar(10), sc_substatus varchar(10), sc_win32_status varchar(10), time_taken varchar(10) );
```
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-02/images/large_log_table_create.PNG "Optional title")


### STEP 4 - Create tables for small Logs
```
create table small_log ( date varchar(20), time varchar(20), s_ip varchar(20),cs_method varchar(20), cs_uri_stem varchar(350), cs_uri_query varchar(10), s_port int, cs_username varchar(10), c_ip varchar(50), cs_user_agent varchar(300), sc_status varchar(10), sc_substatus varchar(10), sc_win32_status varchar(10), time_taken varchar(10) );
```
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-02/images/small_log_table_create.PNG "Optional title")

### STEP 4 - Load  data into the table using the following command

```
LOAD FROM LARGE.TXT

load data local infile '//vagrant_data/data/large/large.txt' into table large_log columns terminated by ' ' lines terminated by '\n';

LOAD FROM SMALL.TXT

load data local infile '//vagrant_data/data/small/small.txt' into table small_log columns terminated by ' ' lines terminated by '\n';
```
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-02/images/insert.PNG "Optional title")

### MySql Configuration to Disable ONLY_FULL_GROUP_BY

```
SET sql_mode = ''
```

## QUERY FOR FINDING MOST VISITED URL PER MONTH

### FOR LARGE_LOG TABLE
```
SELECT each_year, each_month, most_visited_url, MAX(number_of_visitors) AS number_of_visitors
FROM (SELECT year(date) AS each_year, month(date) AS each_month, cs_uri_stem AS most_visited_url, count(cs_uri_stem) AS number_of_visitors
FROM large_log
where cs_uri_stem NOT LIKE '/' and cs_uri_stem NOT LIKE '%index%' and sc_status like '200'
GROUP BY cs_uri_stem, year(date),month(date)
order by count(cs_uri_stem) DESC) AS abc
GROUP BY each_year, each_month
order by each_year, each_month;

```
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-02/images/permonth_large.PNG "Optional title")


### FOR SMALL_LOG TABLE
```
SELECT each_year, each_month,most_visited_url ,MAX(number_of_visitors) AS number_of_visitors
FROM (SELECT year(date) AS each_year, month(date) AS each_month, cs_uri_stem AS most_visited_url, count(cs_uri_stem) AS number_of_visitors
FROM small_log where cs_uri_stem NOT LIKE '/' and cs_uri_stem NOT LIKE '%index%' and sc_status like '200'
GROUP BY cs_uri_stem, year(date),month(date)
order by count(cs_uri_stem) DESC) AS sub_query
GROUP BY each_year, each_month
order by each_year, each_month;

```

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-02/images/permonth_small.PNG "Optional title")


## QUERY FOR FINDING MOST VISITED URL PER DAY

### FOR LARGE_LOG TABLE

```
SELECT MAX(sub_query.number_of_visitors) AS number_of_visitors, sub_query.most_visited_url AS most_visited_url , sub_query.date AS each_day FROM (
SELECT date, count(cs_uri_stem) AS number_of_visitors, cs_uri_stem AS most_visited_url FROM large_log where cs_uri_stem NOT LIKE '%index%' AND sc_status = '200' AND  cs_uri_stem NOT LIKE '/'
GROUP BY cs_uri_stem,date
order by number_of_visitors DESC
) sub_query
GROUP BY each_day;

```
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-02/images/perday_large.PNG "Optional title")
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-02/images/perday_large_1.PNG "Optional title")
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-02/images/perday_large_2.PNG "Optional title")
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-02/images/perday_large_3.PNG "Optional title")
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-02/images/perday_large_4.PNG "Optional title")

### FOR SMALL_LOG TABLE

```
SELECT MAX(sub_query.number_of_visitors) AS number_of_visitors, sub_query.most_visited_url AS most_visited_url , sub_query.date AS each_day FROM (
SELECT date, count(cs_uri_stem) AS number_of_visitors,cs_uri_stem AS most_visited_url FROM small_log where sc_status = '200' AND cs_uri_stem NOT LIKE '%index%' AND cs_uri_stem NOT LIKE '/'
GROUP BY cs_uri_stem,date
order by number_of_visitors DESC
) sub_query
GROUP BY each_day;
```
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-02/images/perday_small.PNG "Optional title")

## ASSUMPTIONS :

1. "/ " in URL is considered to null and avoided for calculation.
2. Consider only url having a return status 200
3. Index.* url's are avoided
4. If two URL's have same number of count for most visited per day or per month, In SQL alphabetical order is considered for final top url where as in MAP REDUCE, the order in which the URL appears in log is considered. 

## POBLEM FACED:

1. Initially, My vagrant box was ubuntu/xenial which has a total memory around 10GGB due to which, I was unable to load the log files into the table which throwed table full error.
2. To solve this probelm, Reload my vagrant with custom Xenial box which is scaled to a total memory of 60GB 


 


