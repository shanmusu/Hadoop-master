# ITEM TWO


```
Running the sample code job provided by the book and insert three addition records 
```

### Step to involved in setting up mysql

```
1. install mysql using the command
	- sudo apt-get install mysql-server
2. login into mysql -u root -p specifying the password

3. create a database named hadoopguide and use the database
4. create a table widget using the command
```
```
	CREATE TABLE widgets(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	widget_name VARCHAR(64) NOT NULL,
	price DECIMAL(10,2),
	design_date DATE,
	version INT, 
	design_comment VARCHAR(100));
```
```
5. Insert the data into the table using the following command
```
```
INSERT INTO widgets VALUES(NULL,'sprocket',0.25,'2010-02-10',1,'Connectstwogizmos');
INSERT INTO widgets VALUES(NULL,'gizmo',4.00,'2009-11-30',4,NULL);
INSERT INTO widgets VALUES(NULL,'gadget',99.99,'1983-08-13',13,'Ourflagshipproduct');
INSERT INTO widgets VALUES(NULL,'seeker',0.84,'1972-01-10',1,''Connects Gizmos additional1');
INSERT INTO widgets VALUES(NULL,'ghostinashell',0.84,'1992-02-10',1,''Connects Gizmos additional2');
INSERT INTO widgets VALUES(NULL,'ghostinashell',0.84,'1992-02-10',1,''Connects Gizmos additional3');
```

#### SELECT * FROM widgets:

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-11/item-02/inser.PNG "Optional title")

```
6. Grant privileges for the hadoopguide databases.
```

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-11/item-02/grant.PNG "Optional title")



### Step to involved in sqoop import
```
1. add the mysql-connectorj jar file to the sqoop library.
2. Use the following command to import the data from the sql
```
```
sqoop import --connect jdbc:mysql://localhost/hadoopguide --username ubuntu --password password --table widgets -m 1

```
#### Sqoop import 
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-11/item-02/import.PNG "Optional title")

#### imported data from MySQl database
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-11/item-02/importeddata.PNG "Optional title")


### TO generate codegen using sqoop use the following command 

```
sqoop codegen --connect jdbc:mysql://localhost/HADOOPGUIDE --username ubuntu -P --table widgets --class-name Widget
```
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-11/item-02/codegen.PNG "Optional title")


### use the following command to install maven

```
sudo apt-add-repository ppa:andrei-pozolotin/maven3 
sudo apt-get update 
sudo apt-get install maven3

```


### TO FIND THE MAXWIDGET ID  USE THE FOLLOWING COMMAND

```
hadoop jar sqoop-examples.jar MaxWidgetId -libjars /home/vagrant/sqoop/sqoop-1.4.6.jar
```
![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-11/item-02/maxID.PNG "Optional title")

### SOLUTION: The new answer that is the maximum widget ID is show below

![image](https://github.com/illinoistech-itm/pmohan3/blob/master/itmd521/week-11/item-02/MaxID_3.PNG "Optional title")
