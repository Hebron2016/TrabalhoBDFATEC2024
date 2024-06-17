package entity;

import java.time.LocalDate;

public class Reserva {
	
	
	private int id;
	private LocalDate dataDevolucao;
	private LocalDate dataRetirada;
	private String status;
	private String identificacao;
	private String nomeLivro;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
	public LocalDate getDataRetirada() {
		return dataRetirada;
	}
	public void setDataRetirada(LocalDate dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getnomeLivro() {
		return nomeLivro;
	}
	public void setnomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}
	public String getIdentificacao() {
		return identificacao;
	}
	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}
	
}
