import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class substring {
	public static Connection conn = null;
	public static Statement stmt = null;

	public static void db_conn()
	{
		
    	try { 
            String url = "jdbc:mysql://localhost/temperature_year";
            String user = "root";
            String password = "safestsystemever";
 
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connected to the database temperature");
            }          
        } catch (SQLException ex) {
            System.out.println("An error occurred: username/password is invalid");
            ex.printStackTrace();
        }
	}

	public static void insert(String line) throws SQLException{
		
		PreparedStatement sqlquery=null,sqlquery2=null,sqlquery3=null;


	    String sql="insert into year1 values (?,?,?,?,?,?,?,?,?,?,?,?)";
	    sqlquery=conn.prepareStatement(sql);
		sqlquery=conn.prepareStatement(sql);
				
		sqlquery.setInt(1, Integer.parseInt(line.substring(4, 10)));
		sqlquery.setInt(2,  Integer.parseInt(line.substring(10,15)));
		sqlquery.setInt(3, Integer.parseInt(line.substring(23, 27)));
		sqlquery.setString(4, line.substring(28, 34));
		sqlquery.setString(5, line.substring(34, 41));
		sqlquery.setString(6, line.substring(46, 51));
		sqlquery.setInt(7, Integer.parseInt(line.substring(60, 63)));
		sqlquery.setInt(8, Integer.parseInt(String.valueOf(line.charAt(63))));
		sqlquery.setInt(9, Integer.parseInt(line.substring(78, 84)));
		sqlquery.setInt(10, Integer.parseInt(line.substring(99, 104)));
		sqlquery.setInt(11, Integer.parseInt(line.substring(15, 19)));
		sqlquery.setInt(12, Integer.parseInt(line.substring(87, 92)));
	   // sqlquery.executeUpdate();
	
		sqlquery.close();

		 String sql2="insert into year2 values (?,?,?,?,?,?,?,?,?,?,?,?)";
	    sqlquery2=conn.prepareStatement(sql2);
		sqlquery2=conn.prepareStatement(sql2);
				
		sqlquery2.setInt(1, Integer.parseInt(line.substring(4, 10)));
		sqlquery2.setInt(2,  Integer.parseInt(line.substring(10,15)));
		sqlquery2.setInt(3, Integer.parseInt(line.substring(23, 27)));
		sqlquery2.setString(4, line.substring(28, 34));
		sqlquery2.setString(5, line.substring(34, 41));
		sqlquery2.setString(6, line.substring(46, 51));
		sqlquery2.setInt(7, Integer.parseInt(line.substring(60, 63)));
		sqlquery2.setInt(8, Integer.parseInt(String.valueOf(line.charAt(63))));
		sqlquery2.setInt(9, Integer.parseInt(line.substring(78, 84)));
		sqlquery2.setInt(10, Integer.parseInt(line.substring(99, 104)));
		sqlquery2.setInt(11, Integer.parseInt(line.substring(15, 19)));
		sqlquery2.setInt(12, Integer.parseInt(line.substring(87, 92)));
	   // sqlquery2.executeUpdate();
		
		sqlquery2.close();
		String sql3="insert into year3 values (?,?,?,?,?,?,?,?,?,?,?,?)";
	    sqlquery3=conn.prepareStatement(sql3);
		sqlquery3=conn.prepareStatement(sql3);
				
		sqlquery3.setInt(1, Integer.parseInt(line.substring(4, 10)));
		sqlquery3.setInt(2,  Integer.parseInt(line.substring(10,15)));
		sqlquery3.setInt(3, Integer.parseInt(line.substring(23, 27)));
		sqlquery3.setString(4, line.substring(28, 34));
		sqlquery3.setString(5, line.substring(34, 41));
		sqlquery3.setString(6, line.substring(46, 51));
		sqlquery3.setInt(7, Integer.parseInt(line.substring(60, 63)));
		sqlquery3.setInt(8, Integer.parseInt(String.valueOf(line.charAt(63))));
		sqlquery3.setInt(9, Integer.parseInt(line.substring(78, 84)));
		sqlquery3.setInt(10, Integer.parseInt(line.substring(99, 104)));
		sqlquery3.setInt(11, Integer.parseInt(line.substring(15, 19)));
		sqlquery3.setInt(12, Integer.parseInt(line.substring(87, 92)));
	    sqlquery3.executeUpdate();
		System.out.println("data inserted into year1 table ");
		sqlquery3.close();
     
	} 
	public static void main(String[] args) throws IOException, NumberFormatException, SQLException {
		
		substring.db_conn();

		File fin = new File("1993");
		BufferedReader br = new BufferedReader(new FileReader(fin)); 
	
		try {
				int i=0;
			    String line;
			    while ((line = br.readLine()) != null) {
			    	System.out.println(line);
			    	String temp = line.substring(88,92);
			    	if(!(line.substring(15,19).contains("9999")) && !(line.substring(87,92).contains("99999")) && !(line.substring(88,92).contains("9999"))){
			    	insert(line);
			    	System.out.println(i++);
			    	}
			  }
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	     
	}

}
