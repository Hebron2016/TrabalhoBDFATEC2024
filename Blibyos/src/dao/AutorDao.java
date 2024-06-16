package dao;

import java.util.List;

import entity.Autor;

public interface AutorDao {
	public void adicionar (Autor aut) throws AutorException;
	public List<Autor>  pesquisarTodos() throws AutorException;
	public List<Autor>  pesquisarPorReg(String id) throws AutorException;
	public void remover(String id) throws AutorException;
	public void atualizar(String id) throws AutorException;
}
