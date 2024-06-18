package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entity.Aluno;
import entity.Reserva;

public class ReservaDaoImpl extends DBConnection implements ReservaDAO {

	public ReservaDaoImpl() {
		super();
	}

	@Override
	public void adicionar(Reserva res) throws ReservaException {
		try {
			String sql = "INSERT INTO reserva (dataDevolucao, dataRetirada, status, identRa, nomeLivro, identReg) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, res.getDataDevolucao().toString());
			stmt.setString(2, res.getDataRetirada().toString());
			stmt.setString(3, res.getStatus());
			stmt.setString(4, res.getIdentRa());
			stmt.setString(5, res.getNomeLivro());
			stmt.setString(6, res.getIdentReg());
			int linhas = stmt.executeUpdate();
			System.out.println("Insert executado com sucesso, foram " + " afetadas " + linhas + " linhas");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Reserva> pesquisarTodos() throws ReservaException {
		List<Reserva> lista = new ArrayList<>();

		try {
			String sql = "SELECT * FROM reserva ";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			System.out.println("Select executado com sucesso");

			while (rs.next()) {
				Reserva r = new Reserva();
				r.setId(Integer.parseInt(rs.getString("id")));
				r.setDataDevolucao(LocalDate.parse(rs.getString("dataDevolucao")));
				r.setDataRetirada(LocalDate.parse(rs.getString("dataRetirada")));
				r.setStatus(rs.getString("status"));
				r.setIdentRa(rs.getString("identRa"));
				r.setIdentReg(rs.getString("identReg"));
				lista.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Reserva> pesquisarPorData(LocalDate dataDevolucao) throws ReservaException {
		List<Reserva> lista = new ArrayList<>();
		try {
			String sql = "SELECT * FROM reserva " + "WHERE dataDevolucao=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, dataDevolucao.toString());
			ResultSet rs = stmt.executeQuery();
			System.out.println("Select por DataDevolucao executado com sucesso");

			while (rs.next()) {
				Reserva r = new Reserva();
				r.setId(Integer.parseInt(rs.getString("id")));
				r.setDataDevolucao(LocalDate.parse(rs.getString("dataDevolucao")));
				r.setDataRetirada(LocalDate.parse(rs.getString("dataRetirada")));
				r.setStatus(rs.getString("status"));
				r.setIdentRa(rs.getString("identRa"));
				r.setIdentReg(rs.getString("identReg"));
				lista.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public void remover(int id) throws ReservaException {
		try {
			String sql = "DELETE FROM reserva " + "WHERE id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, String.valueOf(id));
			int linhas = stmt.executeUpdate();
			System.out.println("Remoção executada com sucesso, foram " + " afetadas " + linhas + " linhas");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void atualizar(Reserva res) throws ReservaException {
		try {
			String sql = "UPDATE reserva SET dataDevolucao=?, dataRetirada=?, status=?, identRa=?, nomeLivro=?, "
					+ "identReg=? " + "WHERE id=?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, res.getDataDevolucao().toString());
			stmt.setString(2, res.getDataRetirada().toString());
			stmt.setString(3, res.getStatus());
			stmt.setString(4, res.getIdentRa());
			stmt.setString(5, res.getNomeLivro());
			stmt.setString(6, res.getIdentReg());
			stmt.setString(7, String.valueOf(res.getId()));
			int linhas = stmt.executeUpdate();
			System.out.println("Atualização executada com sucesso, foram " + " afetadas " + linhas + " linhas");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
