Procedure to run the project:

1. download the temperature data files from the given link :  https://drive.google.com/open?id=0Bys2c__9q7eBTUsyYnpMc01GcEk
2. place the files in a folder in your vagrant folder under the data folder 
3. get the sample code files  for reference from the   https://github.com/tomwhite/hadoop-book/
4. we need to create 3 schemas such as 1990, 1990 and 1992 and 1990,91,92,93
5. max_temperature_all.sh  : It is used find the max temperature  out of all the 4 years
6. max_temperature_all1.sh : It is used to find the max temperature from the 1990.gz file
7. max_temperature_all2.sh : It is used to find the max temperature from the 1990.gz and 1992.gz

We have to note down the time taken for each shell script by using the time LINUX command
eg: time ./max_temperature_all.sh


Steps to gunzip the gzip file
1. start your vagrant
2. change the directory to all folder 
3. type gunzip 1990.gz > 1990
4. repeat the command to all other files in all


Step to create the database and Its tables
1. locate the createdb.sh 
2. In your vagrant shell  run the script by using the command ./createdb.sh : Running this file will create a new database and three tables into the database.

Run the Java file named: substring.java
1. Substring.java is used to insert the 1990.gz file into all the three tables.
2. Substring1.java is used to insert the 1992.gz file into year 2 and year 3 table
3. Substring2.java is used to insert the 1991 and 1993 into year3 table.

Analysis file:
run the analysis.java is used to find the max temp from each table.

change your directory to the place where the analysis.java file is present 
run the java file by using the command :
	javac analysis.java 


 