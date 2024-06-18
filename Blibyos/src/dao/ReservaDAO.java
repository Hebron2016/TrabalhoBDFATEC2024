package dao;

import java.time.LocalDate;
import java.util.List;

import entity.Reserva;

public interface ReservaDAO {
	public void adicionar (Reserva res) throws ReservaException;
	public List<Reserva>  pesquisarTodos() throws ReservaException;
	public List<Reserva>  pesquisarPorData(LocalDate dataDevolucao ) throws ReservaException;
	public void remover(int id) throws ReservaException;
	public void atualizar(Reserva res) throws ReservaException;
}
