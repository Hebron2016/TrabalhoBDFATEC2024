package model;

import java.util.List;

public interface AlunoDAO {
	public void adicionar(Aluno al) throws AlunoException;
	public List<Aluno> pesquisarTodos() throws AlunoException;
	public List<Aluno> pesquisarPorRa() throws AlunoException;
	public void remover(String ra) throws AlunoException;
	public void atualizar(String ra, Aluno al) throws AlunoException;
}
