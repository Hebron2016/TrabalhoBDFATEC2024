package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Autor;

public class AutorDaoImpl extends DBConnection implements AutorDao {

	public AutorDaoImpl() {
		super();
	}

	@Override
	public void adicionar(Autor aut) throws AutorException {
		try {
			String sql = "INSERT INTO autor (nome, biografia) " + "VALUES (?, ?)";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, aut.getNome());
			stmt.setString(2, aut.getBiografia());
			int linhas = stmt.executeUpdate();
			System.out.println("Insert executado com sucesso, foram " + " afetadas " + linhas + " linhas");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Autor> pesquisarTodos() throws AutorException {
		List<Autor> lista = new ArrayList<>();

		try {
			String sql = "SELECT * FROM autor ";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			System.out.println("Select executado com sucesso");

			while (rs.next()) {
				Autor c = new Autor();
				c.setId(Integer.parseInt(rs.getString("id")));
				c.setNome(rs.getString("nome"));
				c.setBiografia(rs.getString("biografia"));
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;

	}

	@Override
	public List<Autor> pesquisarPorNome(String nome) throws AutorException {
		List<Autor> lista = new ArrayList<>();
		try {
			String sql = "SELECT * FROM autor " + "WHERE nome LIKE ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();
			System.out.println("Select por NomeAutor executado com sucesso");

			while (rs.next()) {
				Autor c = new Autor();
				c.setId(Integer.parseInt(rs.getString("id")));
				c.setNome(rs.getString("nome"));
				c.setBiografia(rs.getString("biografia"));
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void remover(int id) throws AutorException {
		try {
			String sql = "DELETE FROM autor " + "WHERE id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, String.valueOf(id));
			int linhas = stmt.executeUpdate();
			System.out.println("Remoção executada com sucesso, foram " + " afetadas " + linhas + " linhas");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void atualizar(Autor aut) throws AutorException {
		try {
			String sql = "UPDATE autor SET nome=?, biografia=?" + "WHERE id=?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, aut.getNome());
			stmt.setString(2, aut.getBiografia());
			stmt.setString(3, String.valueOf(aut.getId()));
			
			int linhas = stmt.executeUpdate();
			System.out.println("Atualização executada com sucesso, foram " + " afetadas " + linhas + " linhas");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
