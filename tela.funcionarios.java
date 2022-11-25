package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class funcionariosDao {

	Connection conn;
	Statement st;
	
	public boolean conectar() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/controle","root","");
			st = conn.createStatement();
			
			return true;
		} catch (ClassNotFoundException | SQLException ex) {
			return false;
		}
	}
	
	public int salvar(funcionarios fun) {
		String sql;
		int status;
		 sql = "INSERT INTO funcionarios values('" +fun.getMatricula()+ "','" +fun.getNome()+"','"+ fun.getCargo() +"', "+ fun.getSalario()+") ";
		try {
			status =st.executeUpdate(sql);
			return status;
		} catch (SQLException ex) {
			return ex.getErrorCode();
		}
		
	}
	
	public void desconectar() {
		try {
			conn.close();
		} catch (SQLException e) {
		}
	}
	
}
