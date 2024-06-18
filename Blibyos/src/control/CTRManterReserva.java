package control;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import dao.ReservaDAO;
import dao.ReservaDaoImpl;
import dao.ReservaException;
import entity.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CTRManterReserva {
	
	
	
	

	private ReservaDAO dao = new ReservaDaoImpl();

	private StringProperty nomeLivro = new SimpleStringProperty("");
	private StringProperty identificacao = new SimpleStringProperty("");
	private StringProperty statusReserva = new SimpleStringProperty("");
	private StringProperty tipoPessoa = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> dataDevolu = new SimpleObjectProperty<>(LocalDate.now());
	private ObjectProperty<LocalDate> dataRetirada = new SimpleObjectProperty<>(LocalDate.now());
	private ObservableList<Reserva> lista = FXCollections.observableArrayList();
	private ObjectProperty<LocalDate> pesquisa = new SimpleObjectProperty<>(LocalDate.now());

	public void limparCampos() {
		nomeLivro.set("");
		identificacao.set("");
	}

	public Reserva toEntity(String tipo) {
		Reserva r = new Reserva();
		r.setnomeLivro(nomeLivro.get());
		r.setDataDevolucao(dataDevolu.get());
		r.setDataRetirada(dataRetirada.get());
		r.setStatus(statusReserva.get());
		if (tipo.equals("Aluno")) {
			r.setIdentRa(identificacao.get());
		} else if (tipo.equals("Professor")) {
			r.setIdentReg(identificacao.get());
		}
		return r;
	}

	public void attStatus() {
		
		for (Reserva r : lista) {

			if (r.getDataDevolucao().isBefore(LocalDate.now())) {
				r.setStatus("ATRASADO");
				
			} else {
				r.setStatus("NO PRAZO");
				
			}
			atualizar(r);
		}
		
	}

	public void gravar(String tipo) {
		Reserva r = toEntity(tipo);
		try {
			dao.adicionar(r);
			atualizarLista();
			limparCampos();
		} catch (ReservaException e) {
			e.printStackTrace();
		}

	}

	public void atualizar(Reserva r) {
		try {
			dao.atualizar(r);
			atualizarLista();
		} catch (ReservaException e) {
			e.printStackTrace();
		}
	}

	public void atualizarLista() {
		List<Reserva> tempLista = new ArrayList<>();
		try {
			tempLista = dao.pesquisarTodos();
			lista.clear();
			lista.addAll(tempLista);
			System.out.println("Lista atualizada com sucesso");
		} catch (ReservaException e) {
			e.printStackTrace();
		}

	}

	public void excluir(Reserva r) {
		try {
			dao.remover(r.getId());
			atualizarLista();
		} catch (ReservaException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Reserva> pesquisarReserva() {

		ObservableList<Reserva> r = FXCollections.observableArrayList();
		try {
			r.addAll(dao.pesquisarPorData(pesquisa.get()));
			return r;
		} catch (ReservaException e) {
			e.printStackTrace();
		}
		return r;
	}

	public ObservableList<Reserva> getLista() {
		return this.lista;
	}

	public void setLista(ObservableList<Reserva> lista) {
		this.lista = lista;
	}

	public StringProperty nomeLivroProperty() {
		return this.nomeLivro;
	}

	public StringProperty identificacaoProperty() {
		return this.identificacao;
	}

	public StringProperty statusReservaProperty() {
		return this.statusReserva;
	}

	public StringProperty tipoPessoaProperty() {
		return this.tipoPessoa;
	}

	public ObjectProperty<LocalDate> dataDevoluProperty() {
		return this.dataDevolu;
	}

	public ObjectProperty<LocalDate> dataRetiradaProperty() {
		return this.dataRetirada;
	}

	public ObjectProperty<LocalDate> pesquisaProperty() {
		return this.dataRetirada;
	}

}
