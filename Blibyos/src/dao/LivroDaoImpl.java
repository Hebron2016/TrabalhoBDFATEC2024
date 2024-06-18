package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Livro;

public class LivroDaoImpl extends DBConnection implements LivroDAO{
	
	public LivroDaoImpl() {
		super();
	}
	
	@Override
	public void adicionar(Livro l) throws LivroException {
		 try {
	            String sql = "INSERT INTO livro (isbn, nome, descricao, status, anoEscrito, genero, idAutor) "+
	            "VALUES (?, ?, ?, ?, ?, ?, ?)";

	            PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setString(1, l.getIsbn());
	            stmt.setString(2, l.getNome());
	            stmt.setString(3, l.getDescricao());
	            stmt.setString(4, l.getStatus());
	            stmt.setString(5, String.valueOf(l.getAnoEscrito()));
	            stmt.setString(6, l.getGenero());
	            stmt.setString(7, String.valueOf(l.getIdAutor()));
	            int linhas = stmt.executeUpdate();
	            System.out.println("Insert executado com sucesso, foram " + 
	            " afetadas " + linhas + " linhas");
	        } catch(SQLException e) { 
	            e.printStackTrace();
	        }
	}

	@Override
	public List<Livro> pesquisarTodos() throws LivroException {
		 List<Livro> lista = new ArrayList<>();

	        try {
	            String sql = "SELECT * FROM livro ";
	            PreparedStatement stmt = con.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            System.out.println("Select executado com sucesso");

	            while (rs.next()) { 
	                Livro c = new Livro();
	                c.setNome(rs.getString("nome"));
	                c.setIsbn(rs.getString("isbn"));
	                c.setDescricao(rs.getString("descricao"));
	                c.setStatus(rs.getString("status"));
	                if (rs.getString("anoEscrito") != null) {
	                c.setAnoEscrito(Integer.parseInt(rs.getString("anoEscrito")));}
	                c.setGenero(rs.getString("genero"));
	                if (rs.getString("idAutor") != null) {
	                c.setIdAutor(Integer.parseInt(rs.getString("idAutor")));
	                }
	                lista.add(c);
	            }
	        } catch(SQLException e) { 
	            e.printStackTrace();
	        }
	        return lista;
		
	}

	@Override
	public List<Livro> pesquisarPorIsbn(String isbn) throws LivroException {
		List<Livro> lista = new ArrayList<>();
		try {
            String sql = "SELECT * FROM livro " +
            				 "WHERE isbn=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Select por ISBN executado com sucesso");

            while (rs.next()) { 
            	Livro c = new Livro();
                c.setNome(rs.getString("nome"));
                c.setIsbn(rs.getString("isbn"));
                c.setDescricao(rs.getString("descricao"));
                c.setStatus(rs.getString("status"));
                c.setAnoEscrito(Integer.parseInt(rs.getString("anoEscrito")));
                c.setGenero(rs.getString("genero"));
                c.setIdAutor(Integer.parseInt(rs.getString("idAutor")));
                lista.add(c);
            }
        } catch(SQLException e) { 
            e.printStackTrace();
        }
        return lista;
	}

	@Override
	public void remover(String isbn) throws LivroException {
	  try {
		String sql = "DELETE FROM livro "+
	            "WHERE isbn=?";
		PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, isbn);
        int linhas = stmt.executeUpdate();
        System.out.println("Remoção executada com sucesso, foram " + 
                " afetadas " + linhas + " linhas");
	  } catch(SQLException e) {
		  e.printStackTrace();
	  }

	}

	@Override
	public void atualizar(Livro l) throws LivroException {
		try {
            String sql = "UPDATE livro SET nome=?, descricao=?, status=?, anoEscrito=?, "+
            "genero=?, idAutor=?" +
            "WHERE isbn=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(7, l.getIsbn());
            stmt.setString(1, l.getNome());
            stmt.setString(2, l.getDescricao());
            stmt.setString(3, l.getStatus());
            stmt.setString(4, String.valueOf(l.getAnoEscrito()));
            stmt.setString(5, l.getGenero());
            stmt.setString(6, String.valueOf(l.getIdAutor()));
            int linhas = stmt.executeUpdate();
            System.out.println("Atualização executada com sucesso, foram " + 
            " afetadas " + linhas + " linhas");
        } catch(SQLException e) { 
            e.printStackTrace();
        }
		
	}
}
