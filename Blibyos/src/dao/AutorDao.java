package dao;

import java.util.List;

import entity.Autor;

public interface AutorDao {
	public void adicionar (Autor aut) throws AutorException;
	public List<Autor>  pesquisarTodos() throws AutorException;
	public List<Autor>  pesquisarPorNome(String nome) throws AutorException;
	public void remover(int id) throws AutorException;
	public void atualizar(Autor aut) throws AutorException;
}
