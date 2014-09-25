package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUser {
	
	private Connection cnn = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt;
	
	public DAOUser(){
		try {
			Class.forName("org.postgresql.Driver");
			cnn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "123456");
			System.out.println("Connection Successfully!!!");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public DTOUser getAuthenticationUser(String email, String password){
		DTOUser user = new DTOUser();
		String sql = "SELECT * FROM \"tbUser\" WHERE \"Email\"=? AND \"Password\"=?";
		try{
			pstmt = cnn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()){
				user.setId(rs.getInt(1));
				user.setEmail(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setPosition(rs.getString(4));
				System.out.println("RETURN USER");
				return user;
			}	
		}catch(SQLException e){
			System.out.println("Get Authentication User error: "+e);
		}
		return null;		
	}
}
