package dao;

import java.util.List;

import entity.Professor;


public interface ProfessorDAO {
	public void adicionar (Professor pro) throws ProfessorException;
	public List<Professor>  pesquisarTodos() throws ProfessorException;
	public List<Professor>  pesquisarPorReg(String registro) throws ProfessorException;
	public void remover(String registro) throws ProfessorException;
	public void atualizar(Professor pro) throws ProfessorException;
	
}
