package model;

import java.time.LocalDate;
import java.util.List;

public interface ReservaDAO {
	public void adicionar (Reserva res) throws ReservaException;
	public List<Reserva>  pesquisarTodos() throws ReservaException;
	public List<Reserva>  pesquisarPorData(LocalDate dataDevolucao ) throws ReservaException;
	public void remover(String id) throws ReservaException;
	public void atualizar(String id) throws ReservaException;
}
