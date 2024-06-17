package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	protected static final String JDBC_URL = 
    "jdbc:sqlserver://DESKTOP-3D7FB7H;Database=biblioteca;encrypt=true;TrustServerCertificate=true;IntegratedSecurity=true";
	protected static final String JDBC_USER = ""; //Com o IntegratedSecurity não precisa disso.
	protected static final String JDBC_PASS = "";
	protected Connection con;
	
	public DBConnection(){ 
        try { 
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver carregado");
            con = 
            DriverManager.getConnection(JDBC_URL);
            System.out.println("Conectado ao banco de dados com sucesso");
        } catch (ClassNotFoundException e) { 
            System.out.println("Classe do database não encontrada");
            e.printStackTrace();
        } catch (SQLException e) { 
            System.out.println("Erro de conexão ao banco de dados");
            e.printStackTrace();
        }
    }
}
