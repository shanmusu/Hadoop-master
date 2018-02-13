import java.io.*;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;

public class DataTransfer extends Connector{
	static FileInputStream file = null;
	static BufferedReader filebr= null;
	static String lineValue = "";
	static ArrayList<ArrayList<String>> totalList = new ArrayList<ArrayList<String>>();
	static ArrayList<String> lineList = new ArrayList<String>();
	static Connection conn = null;
	static PreparedStatement ps = null;
	static String mysql = "";
	static ResultSet rs = null;
	static java.sql.Date weblogDate= null;
	static int row_number = 0;
	static int line_Val = 0;
	public static void insertRecords(ArrayList<ArrayList<String>> inputRecords) throws SQLException{
		mysql = "INSERT INTO WEBLOGS.SMALL_LOGS(Log_Date, Log_Time, Log_Path, Log_Type, Log_Webfile, Log_Number1, Log_IPAddress, Log_Number2, Log_Number3, Log_Systembit, Log_Number4) VALUES " + "(?,?,?,?,?,?,?,?,?,?,?)";
		try{
			conn = Connector.getconnection();
			ps = conn.prepareStatement(mysql);
			System.out.println("Inserting " + inputRecords.size() + " values into the table");
			for(int i=0; i<inputRecords.size();i++){
				for(int j=0; j<inputRecords.get(i).size();j++){
					if(j==0){
						weblogDate = new java.sql.Date(0000-00-00);
						ps.setDate(1, weblogDate.valueOf(inputRecords.get(i).get(j)));
					}
					
					if(j==6)
						ps.setInt(6, Integer.parseInt(inputRecords.get(i).get(j)));
					
					if(j==10)
						ps.setInt(8, Integer.parseInt(inputRecords.get(i).get(j)));			
					if(j==11)
						ps.setInt(9, Integer.parseInt(inputRecords.get(i).get(j)));
			
					if(j==12)
						ps.setInt(10, Integer.parseInt(inputRecords.get(i).get(j)));
						
					if(j==13)
						ps.setInt(11, Integer.parseInt(inputRecords.get(i).get(j)));
						
					if(j==1)
						ps.setString(2, inputRecords.get(i).get(j));
					
					if(j==2)
						ps.setString(3, inputRecords.get(i).get(j));
					if(j==3)
						ps.setString(4, inputRecords.get(i).get(j));
					if(j==4)
						ps.setString(5, inputRecords.get(i).get(j));
					if(j==8)
						ps.setString(7, inputRecords.get(i).get(j));
				}
				System.out.println("Executing");
				ps.execute();
				row_number = row_number + 1;
				System.out.println("Inserting row number: " + row_number);
			}
		} catch (Exception exp){
			exp.printStackTrace();
		}
	}	

	public static void main(String[] args) throws IOException{
		try{
			file = new FileInputStream("/home/vagrant/finalproject_files/smalllogs.log");
			filebr = new BufferedReader(new InputStreamReader(file));
			while((lineValue = filebr.readLine()) != null){
				if(!lineValue.substring(0,1).equals("#")){
					lineList.addAll(Arrays.asList(lineValue.split(" ")));
					totalList.add(new ArrayList<String>(lineList));
					line_Val+=1;
					lineList.clear();
					if(line_Val == 1000000){
						try{
							System.out.println("Adding " + line_Val + " line to the list");
							insertRecords(totalList);
							line_Val = 0;
							totalList.clear();
						} catch(Exception ep){
							ep.printStackTrace();
						}
					}
				}
			}
			try{
				System.out.println("Adding last set of records into the table");
				insertRecords(totalList);
				totalList.clear();
			} catch(Exception ep){
				ep.printStackTrace();
			}
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
}