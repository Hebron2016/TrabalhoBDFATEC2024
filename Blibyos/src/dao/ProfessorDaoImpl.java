package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Professor;

public class ProfessorDaoImpl extends DBConnection implements ProfessorDAO{
	
	@Override
	public void adicionar(Professor po) throws ProfessorException {
		 try {
	            String sql = "INSERT INTO professor (registro, nome, email, telefone) "+
	            "VALUES (?, ?, ?, ?, ?)";

	            PreparedStatement stmt = con.prepareStatement(sql);
	            stmt.setString(1, po.getRegistro());
	            stmt.setString(2, po.getNome());
	            stmt.setString(3, po.getEmail());
	            stmt.setString(4, po.getTelefone());
	            int linhas = stmt.executeUpdate();
	            System.out.println("Insert executado com sucesso, foram " + 
	            " afetadas " + linhas + " linhas");
	        } catch(SQLException e) { 
	            e.printStackTrace();
	        }
	}

	@Override
	public List<Professor> pesquisarTodos() throws ProfessorException {
		 List<Professor> lista = new ArrayList<>();

	        try {
	            String sql = "SELECT * FROM professor ";
	            PreparedStatement stmt = con.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            System.out.println("Select executado com sucesso");

	            while (rs.next()) { 
	                Professor c = new Professor();
	                c.setNome(rs.getString("nome"));
	                c.setTelefone(rs.getString("telefone"));
	                c.setEmail(rs.getString("email"));
	                c.setRegistro(rs.getString("registro"));
	                lista.add(c);
	            }
	        } catch(SQLException e) { 
	            e.printStackTrace();
	        }
	        return lista;
		
	}

	@Override
	public List<Professor> pesquisarPorReg(String reg) throws ProfessorException {
		List<Professor> lista = new ArrayList<>();
		try {
            String sql = "SELECT * FROM professor " +
            				 "WHERE registro=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, reg);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Select por Registro executado com sucesso");

            while (rs.next()) { 
                Professor p = new Professor();
                p.setNome(rs.getString("nome"));
                p.setTelefone(rs.getString("telefone"));
                p.setEmail(rs.getString("email"));
                p.setRegistro(rs.getString("registro"));
                lista.add(p);
            }
        } catch(SQLException e) { 
            e.printStackTrace();
        }
        return lista;
	}

	@Override
	public void remover(String reg) throws ProfessorException {
	  try {
		String sql = "DELETE FROM professor "+
	            "WHERE registro=?";
		PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, reg);
        int linhas = stmt.executeUpdate();
        System.out.println("Remoção executada com sucesso, foram " + 
                " afetadas " + linhas + " linhas");
	  } catch(SQLException e) {
		  e.printStackTrace();
	  }

	}

	@Override
	public void atualizar(Professor pro) throws ProfessorException {
		try {
            String sql = "UPDATE professor SET nome=?, email=?, telefone=? "+
            "WHERE id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, pro.getNome());
            stmt.setString(2, pro.getEmail());
            stmt.setString(3, pro.getTelefone());
            stmt.setString(4, pro.getRegistro());
            int linhas = stmt.executeUpdate();
            System.out.println("Atualização executada com sucesso, foram " + 
            " afetadas " + linhas + " linhas");
        } catch(SQLException e) { 
            e.printStackTrace();
        }
		
	}

}
