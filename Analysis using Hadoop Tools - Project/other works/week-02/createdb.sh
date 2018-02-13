#!/bin/bash
mysql -u root -p -e "create database temperature_year";
mysql --user='root' --password='safestsystemever' temperature_year << EOF

create table year1 (station int (21), weather_stn INT(20), obshr INT(20), lat VARCHAR(20), longi VARCHAR(20), elevation VARCHAR(20), w_direct INT (20), q_code INT(20), v_dist INT(20), atm_pressure INT(20), year INT(25), temp INT(10));

create table year2 (station int (21), weather_stn INT(20), obshr INT(20), lat VARCHAR(20), longi VARCHAR(20), elevation VARCHAR(20), w_direct INT (20), q_code INT(20), v_dist INT(20), atm_pressure INT(20), year INT(25), temp INT(10));

create table year3 (station int (21), weather_stn INT(20), obshr INT(20), lat VARCHAR(20), longi VARCHAR(20), elevation VARCHAR(20), w_direct INT (20), q_code INT(20), v_dist INT(20), atm_pressure INT(20), year INT(25), temp INT(10));

commit;

EOF

