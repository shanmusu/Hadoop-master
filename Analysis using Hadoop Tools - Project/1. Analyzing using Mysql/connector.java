
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class connector{
	public static Connection getconnection(){
		Connection connection = null;
		String URL = "jdbc:mysql://localhost:3306/mysql";
		String UName = "root";
		String Pwd = "safestsystemever";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection(URL,UName,Pwd);
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		catch(ClassNotFoundException sqle){
			sqle.printStackTrace();
		}
		return connection;
	}
}