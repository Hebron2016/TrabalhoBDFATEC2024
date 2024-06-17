package entity;

import java.time.LocalDate;

public class Livro {
	private String nome;
	private String descricao;
	private String status;
	private int anoEscrito;
	private String genero;
	private String isbn;
	private int idAutor;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAnoEscrito() {
		return anoEscrito;
	}
	public void setAnoEscrito(int anoEscrito) {
		this.anoEscrito = anoEscrito;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getIdAutor() {
		return idAutor;
	}
	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}
	
}
