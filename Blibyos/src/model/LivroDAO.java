package model;

import java.util.List;

public interface LivroDAO {
	public void adicionar (Livro liv) throws LivroException;
	public List<Livro>  pesquisarTodos() throws LivroException;
	public List<Livro>  pesquisarPorReg(String isbn) throws LivroException;
	public void remover(String isbn) throws LivroException;
	public void atualizar(String isbn) throws LivroException;
}
