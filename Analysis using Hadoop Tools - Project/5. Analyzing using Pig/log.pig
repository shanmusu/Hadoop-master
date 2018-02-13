#### script to find the most visited url per day in large log

data = LOAD '/user/input/large.txt' USING PigStorage(' ') AS (year:chararray, time:chararray, ip:chararray,cs_method:chararray, visited_url:chararray, cs_uri_query:chararray, s_port:int, username:chararray, cip:chararray, cs_user_agent:chararray, sc_status:chararray, time_taken:chararray);
layer_one_filter = FILTER data BY year != '*#*';
layer_two_filter= FILTER layer_one_filter BY sc_status == '200'  AND visited_url != '*index.*' AND visited_url != '/';
layer_three_filter= GROUP layer_two_filter BY  (visited_url,year);
get_val = FOREACH layer_three_filter GENERATE group , COUNT(layer_two_filter.visited_url) as counts;
url_count = ORDER get_val BY counts DESC;
layer_four_filter = GROUP url_count BY group.year;
data =  foreach  layer_four_filter generate  MAX(url_count.counts) as large;
temp = JOIN url_count BY counts , data BY large;
result = foreach temp generate $0 , $1;


#### script to find the most visited url per day in small log

data = LOAD '/user/input/small.txt' USING PigStorage(' ') AS (year:chararray, time:chararray, ip:chararray,cs_method:chararray, visited_url:chararray, cs_uri_query:chararray, s_port:int, username:chararray, cip:chararray, cs_user_agent:chararray, sc_status:chararray, time_taken:chararray);
layer_one_filter = FILTER data BY year != '*#*';
layer_two_filter= FILTER layer_one_filter BY visited_url != '/' AND visited_url != '*index.*' AND sc_status == '200';
layer_three_filter= GROUP layer_two_filter BY  (visited_url,year);
get_val = FOREACH layer_three_filter GENERATE group , COUNT(layer_two_filter.visited_url) as counts;
url_count = ORDER get_val BY counts DESC;
layer_four_filter = GROUP url_count BY group.year;
data =  foreach  layer_four_filter generate  MAX(url_count.counts) as large;
temp = JOIN url_count BY counts , data BY large;
result = foreach temp generate $0 , $1;


#### script to find the most visited url per month in large log

data = LOAD '/user/input/large.txt' USING PigStorage(' ') AS (year:chararray, time:chararray, ip:chararray,cs_method:chararray, visited_url:chararray, cs_uri_query:chararray, s_port:int, username:chararray, cip:chararray, cs_user_agent:chararray, sc_status:chararray, time_taken:chararray);
filtered_data = FILTER data BY year != '*#*';
layer_two_filter= FILTER filtered_data BY visited_url != '/' AND visited_url != '*index.*' AND sc_status == '200';
test = FOREACH layer_two_filter generate SUBSTRING(year,0,7) as month , visited_url;
layer_three_filter= GROUP test BY  (visited_url,month);
get_val = FOREACH layer_three_filter GENERATE group , COUNT(test.visited_url) as counts;
url_count = ORDER get_val BY counts DESC;
layer_four_filter = GROUP url_count BY group.month;
data =  foreach  layer_four_filter generate  MAX(url_count.counts) as large;
temp = JOIN url_count BY counts , data BY large;
result = foreach temp generate $0 , $1;



#### script to find the most visited url per month in small log

data = LOAD '/user/input/small.txt' USING PigStorage(' ') AS (year:chararray, time:chararray, ip:chararray,cs_method:chararray, visited_url:chararray, cs_uri_query:chararray, s_port:int, username:chararray, cip:chararray, cs_user_agent:chararray, sc_status:chararray, time_taken:chararray);
filtered_data = FILTER data BY year != '*#*';
layer_two_filter= FILTER filtered_data BY visited_url != '/' AND visited_url != '*index.*' AND sc_status == '200';
test = FOREACH layer_two_filter generate SUBSTRING(year,0,7) as month , visited_url;
layer_three_filter= GROUP test BY  (visited_url,month);
get_val = FOREACH layer_three_filter GENERATE group , COUNT(test.visited_url) as counts;
url_count = ORDER get_val BY counts DESC;
layer_four_filter = GROUP url_count BY group.month;
data =  foreach  layer_four_filter generate  MAX(url_count.counts) as large;
temp = JOIN url_count BY counts , data BY large;
result = foreach temp generate $0 , $1;
