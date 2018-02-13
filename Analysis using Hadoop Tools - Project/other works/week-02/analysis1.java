
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class analysis {
	public static Connection conn = null;
	public static Statement stmt = null;
	static String url = "jdbc:mysql://localhost/temperature_year";
    static String user = "root";
    static String password = "safestsystemever";
	public static void db_conn()
	{
    	try { 
            
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connected to the database temperature");
            }
 
        } catch (SQLException ex) {
            System.out.println("An error occurred username/password is invalid");
            ex.printStackTrace();
        }
			
	}
	public static void fin_dmax()

	{
		try {
			String sql = "SELECT max(temp),year from year2 group by year;";
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			stmt.execute(sql);
			ResultSet rs = stmt.getResultSet();
			System.out.println("Maximum temperature \n");
			while(rs.next()) {
				int a = rs.getInt(1);
				int  b = rs.getInt(2);
				
				
				
				System.out.println(b +" : "+a );
			
				
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		db_conn();
		long startTime = System.currentTimeMillis();
		fin_dmax();
		long endTime = System.currentTimeMillis();
		long duration = (endTime - startTime);
		
		System.out.println("\n \nTime taken :\t"+ duration * 100 +" ms");
		
		
	}

}
