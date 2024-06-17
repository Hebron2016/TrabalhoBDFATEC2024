package control;

import java.util.ArrayList;
import java.util.List;

import dao.AlunoException;
import dao.AutorDao;
import dao.AutorDaoImpl;
import dao.AutorException;
import entity.Aluno;
import entity.Autor;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CTRManterAutor {

	private AutorDao dao = new AutorDaoImpl();

	private StringProperty nome = new SimpleStringProperty("");
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty biografia = new SimpleStringProperty("");
	private StringProperty pesquisa = new SimpleStringProperty("");

	private ObservableList<Autor> lista = FXCollections.observableArrayList();

	public void limparCampos() {
		nome.set("");
		id.set(0);
		biografia.set("");
	}

	public Autor toEntity() {
		Autor a = new Autor();
		a.setNome(nome.get());
		a.setId(id.get());
		a.setBiografia(biografia.get());
		return a;
	}

	public void gravar() {
		Autor a = toEntity();
		try {

			dao.adicionar(a);
			atualizarLista();
			limparCampos();
		} catch (AutorException e) {
			e.printStackTrace();
		}

	}

	public void atualizar() {
		Autor a = toEntity();
		try {
			dao.atualizar(a);
			atualizarLista();
			limparCampos();
		} catch (AutorException e) {
			e.printStackTrace();
		}
	}

	public boolean pesquisarExistencia() {
		atualizarLista();
		for (Autor a : lista) {
			if (a.getNome().equals(nome.get())) {

				return true;
			}
		}
		return false;
	}

	public void fromEntity(Autor a) {
		biografia.set(a.getBiografia());
		nome.set(a.getNome());
		id.set(a.getId());
	}


	public ObservableList<Autor> pesquisarAutor() {

		ObservableList<Autor> aut = FXCollections.observableArrayList();
		try {
			aut.addAll(dao.pesquisarPorNome(pesquisa.get()));
			return aut;
		} catch (AutorException e) {
			e.printStackTrace();
		}
		return aut;
	}

	public void atualizarLista() {
		List<Autor> tempLista = new ArrayList<>();
		try {
			tempLista = dao.pesquisarTodos();
			lista.clear();
			lista.addAll(tempLista);
			System.out.println("Lista atualizada com sucesso");
		} catch (AutorException e) {
			e.printStackTrace();
		}

	}

	public void excluir(Autor a) {
		try {
			dao.remover(a.getId());
			atualizarLista();
		} catch (AutorException e) {
			e.printStackTrace();
		}
	}

	

	public ObservableList<Autor> getLista() {
		return this.lista;
	}

	public void setLista(ObservableList<Autor> lista) {
		this.lista = lista;
	}

	public StringProperty biografiaProperty() {
		return this.biografia;
	}

	public StringProperty nomeProperty() {
		return this.nome;
	}

	public IntegerProperty idProperty() {
		return this.id;
	}
	
	public StringProperty pesquisaProperty() {
    	return this.pesquisa;
    }
}
