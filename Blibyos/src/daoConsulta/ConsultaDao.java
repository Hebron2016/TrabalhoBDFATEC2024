package daoConsulta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DBConnection;
import entity.Autor;
import entity.Livro;
import entity.Professor;

public class ConsultaDao extends DBConnection{
	public ConsultaDao() {
		super();
	}
	
	public List<Autor> autorSemLivro() {
		List<Autor> lista = new ArrayList<>();
		try {
		String sql = "SELECT a.nome "
		           +"FROM autor a "
		           +"LEFT OUTER JOIN livro l ON "
		           +"a.id = l.idAutor "
		           +"WHERE l.idAutor IS NULL ";
		 
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Autor a = new Autor();
				a.setNome(rs.getString("nome"));
				lista.add(a);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
		 }
	
	public List<Livro> livroSemRes(){
		List<Livro> lista = new ArrayList<>();
		try {
			String sql = "SELECT l.nome "+
					"FROM livro l "+
					"LEFT OUTER JOIN reserva r ON "+
					"l.nome = r.nomeLivro "+
					"WHERE r.nomeLivro IS NULL ";
					
					
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Livro l = new Livro();
				l.setNome(rs.getString("nome"));
				lista.add(l);
		}}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}
}
