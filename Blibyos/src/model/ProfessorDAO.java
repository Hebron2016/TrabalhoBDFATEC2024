package model;

import java.util.List;


public interface ProfessorDAO {
	public void adicionar (Professor pro) throws ProfessorException;
	public List<Professor>  pesquisarTodos() throws ProfessorException;
	public List<Professor>  pesquisarPorReg(String registro) throws ProfessorException;
	public void remover(String registro) throws ProfessorException;
	public void atualizar(String registro) throws ProfessorException;
	
}
