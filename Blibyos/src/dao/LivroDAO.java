package dao;

import java.util.List;

import entity.Livro;

public interface LivroDAO {
	public void adicionar (Livro liv) throws LivroException;
	public List<Livro>  pesquisarTodos() throws LivroException;
	public List<Livro>  pesquisarPorIsbn(String isbn) throws LivroException;
	public void remover(String isbn) throws LivroException;
	public void atualizar(Livro l) throws LivroException;
}
