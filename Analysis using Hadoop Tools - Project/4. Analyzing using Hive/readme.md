# Analysis in HIVE - ITEM FOUR

In this item, I first created the table using sqoop create-hive-table command to connect to the jdbc driver and populate them into the hive table having consistency with the table name. I then used LOAD DATA INPATH  to load data into the table on hive. After loading the data, I used the following hive queries to calculate the most frequently used url per month and also per day. 


```
ANALYSIS MADE:

Find the most visited URL per month and per day using HIVE

```

STEP 1: install hive and set path in ~/.bashrc

STEP 2: start hive and using the command HIVE
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-04/images/hive.PNG "Optional title")

STEP 3: create a database in hive where you will store the data imported from  the sql


- CREATE DATABASES PRADEEP;

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-04/images/create_db.PNG "Optional title")

STEP 4: use the following command to import into the hive databse

### LARGE DATA
```
sqoop create-hive-table \
--connect jdbc:mysql://localhost/pradeep \
--username vagrant \
-P
--table large_log \
--hive-import \
--hive-table large_log \
--hive-database pradeep \
-m 1


```

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-04/images/sqoop-hive- import-create tabl-large.PNG "Optional title")

#### showing count of records in hive

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-04/images/large_imported.PNG "Optional title")

STEP 4: similarly use the above command to import the small log also.

STEP 5: NOW enter into hive run the perday large, perday small and permonth large and permonth small code to find the answers.

#small log- per month 
```

SELECT each_year, each_month, most_visited_url, MAX(number_of_visitors) AS number_of_visitors
FROM (SELECT year(dates) AS each_year, month(dates) AS each_month, cs_uri_stem AS most_visited_url, count(cs_uri_stem) AS number_of_visitors
FROM large_log
where cs_uri_stem NOT LIKE '/' and cs_uri_stem NOT LIKE '%index%' and sc_status like '200'
) AS inner
GROUP BY each_year, each_month;
```
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-04/images/1.PNG "Optional title)
#large log- per month 
```

SELECT each_year, each_month, most_visited_url, MAX(number_of_visitors) AS number_of_visitors
FROM (SELECT year(dates) AS each_year, month(dates) AS each_month, cs_uri_stem AS most_visited_url, count(cs_uri_stem) AS number_of_visitors
FROM large_log
where cs_uri_stem NOT LIKE '/' and cs_uri_stem NOT LIKE '%index%' and sc_status like '200'
) AS inner
GROUP BY each_year, each_month;
```
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-04/images/2.PNG "Optional title)

#max visited url per day  large
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-04/images/3.PNG "Optional title)

#max visited url per day  small
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-13/item-04/images/4.PNG "Optional title)


