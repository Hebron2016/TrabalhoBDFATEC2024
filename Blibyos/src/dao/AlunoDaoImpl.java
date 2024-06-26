package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Aluno;

public class AlunoDaoImpl extends DBConnection implements AlunoDAO {
	
	public AlunoDaoImpl() {
		super();
	}
	@Override
	public void adicionar(Aluno al) throws AlunoException {
		 try {
	            String sql = "INSERT INTO aluno (ra, status, nome, email, telefone) "+
	            "VALUES (?, ?, ?, ?, ?)";

	            PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setString(1, al.getRa());
	            stmt.setString(2, al.getStatus());
	            stmt.setString(3, al.getNome());
	            stmt.setString(4, al.getEmail());
	            stmt.setString(5, al.getTelefone());
	            int linhas = stmt.executeUpdate();
	            System.out.println("Insert executado com sucesso, foram " + 
	            " afetadas " + linhas + " linhas");
	        } catch(SQLException e) { 
	            e.printStackTrace();
	        }
	}

	@Override
	public List<Aluno> pesquisarTodos() throws AlunoException {
		 List<Aluno> lista = new ArrayList<>();

	        try {
	            String sql = "SELECT * FROM aluno ";
	            PreparedStatement stmt = con.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            System.out.println("Select executado com sucesso");

	            while (rs.next()) { 
	                Aluno c = new Aluno();
	                c.setNome(rs.getString("nome"));
	                c.setTelefone(rs.getString("telefone"));
	                c.setEmail(rs.getString("email"));
	                c.setRa(rs.getString("ra"));
	                c.setStatus(rs.getString("status"));
	                lista.add(c);
	            }
	        } catch(SQLException e) { 
	            e.printStackTrace();
	        }
	        return lista;
		
	}

	@Override
	public List<Aluno> pesquisarPorRa(String ra) throws AlunoException {
		List<Aluno> lista = new ArrayList<>();
		try {
            String sql = "SELECT * FROM aluno " +
            				 "WHERE ra=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, ra);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Select por RA executado com sucesso");

            while (rs.next()) { 
                Aluno c = new Aluno();
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setRa(rs.getString("ra"));
                c.setStatus(rs.getString("status"));
                lista.add(c);
            }
        } catch(SQLException e) { 
            e.printStackTrace();
        }
        return lista;
	}

	@Override
	public void remover(String ra) throws AlunoException {
	  try {
		String sql = "DELETE FROM aluno "+
	            "WHERE ra=?";
		PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, ra);
        int linhas = stmt.executeUpdate();
        System.out.println("Remoção executada com sucesso, foram " + 
                " afetadas " + linhas + " linhas");
	  } catch(SQLException e) {
		  e.printStackTrace();
	  }

	}

	@Override
	public void atualizar(Aluno al) throws AlunoException {
		try {
            String sql = "UPDATE aluno SET nome=?, telefone=?, email=?, status=? "+
            "WHERE ra=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, al.getNome());
            stmt.setString(2, al.getTelefone());
            stmt.setString(3, al.getEmail());
            stmt.setString(4, al.getStatus());
            stmt.setString(5, al.getRa());
            int linhas = stmt.executeUpdate();
            System.out.println("Atualização executada com sucesso, foram " + 
            " afetadas " + linhas + " linhas");
        } catch(SQLException e) { 
            e.printStackTrace();
        }
		
	}

}
