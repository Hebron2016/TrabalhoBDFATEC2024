package control;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.LivroDAO;
import dao.LivroDaoImpl;
import dao.LivroException;
import entity.Livro;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CTRManterLivro {

	private LivroDAO dao = new LivroDaoImpl();

	private CTRManterAutor ctrlAutor = new CTRManterAutor();
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty nomeAutor = new SimpleStringProperty("");
	private StringProperty descricao = new SimpleStringProperty("");
	private StringProperty status = new SimpleStringProperty("DISPONÍVEL");
	private IntegerProperty anoEscrito = new SimpleIntegerProperty();
	private StringProperty genero = new SimpleStringProperty("");
	private StringProperty isbn = new SimpleStringProperty("");
	private StringProperty pesquisa = new SimpleStringProperty("");

	private ObservableList<Livro> lista = FXCollections.observableArrayList();

	public void limparCampos() {
		nome.set("");
		nomeAutor.set("");
		descricao.set("");
		status.set("");
		genero.set("");
		isbn.set("");
	}

	public Livro toEntity() {
		Livro l = new Livro();
		l.setNome(nome.get());
		System.out.println(nome.get());
		System.out.println(nomeAutor.get());
		l.setIdAutor(ctrlAutor.getFirstID(nomeAutor.get()));
		l.setDescricao(descricao.get());
		l.setStatus(status.get());
		l.setAnoEscrito(anoEscrito.get());
		l.setGenero(genero.get());
		l.setIsbn(isbn.get());
		
		return l;
	}

	public boolean gravar() {
		Livro l = toEntity();
		if (l.getIdAutor() == -1) {
			return false;
		} else {
			try {
				if (pesquisarExistencia()) {
					dao.atualizar(l);
				} else {
					dao.adicionar(l);
				}
				atualizarLista();
				limparCampos();
				return true;
			} catch (LivroException e) {
				e.printStackTrace();
			}
		}return false;
	}

	public ObservableList<Livro> pesquisarLivro() {

		ObservableList<Livro> l = FXCollections.observableArrayList();
		try {
			l.addAll(dao.pesquisarPorIsbn(pesquisa.get()));
			return l;
		} catch (LivroException e) {
			e.printStackTrace();
		}
		return l;
	}

	public boolean pesquisarExistencia() {
		atualizarLista();
		for (Livro l : lista) {
			if (l.getIsbn().equals(isbn.get())) {

				return true;
			}
		}
		return false;
	}
	
	public boolean pesquisarExistencia(String nome) {
		atualizarLista();
		for (Livro l : lista) {
			if (l.getNome().equals(nome)) {

				return true;
			}
		}
		return false;
	}
	
	
	public void atualizarLista() { 
        List<Livro> tempLista = new ArrayList<>();
        try {
			tempLista = dao.pesquisarTodos();
			lista.clear();
	        lista.addAll( tempLista );
	        System.out.println("Lista atualizada com sucesso");
		} catch (LivroException e) {
			e.printStackTrace();
		}
        
    }
	
	 public void excluir(Livro l) {
	    	try {
				dao.remover(l.getIsbn());
				atualizarLista();
			} catch (LivroException e) {
				e.printStackTrace();
			}
	    }

	public void pesquisar() {
		for (Livro a : lista) {
			if (a.getNome().contains(nome.get())) {
				// fromEntity(a);
			}
		}
	}


	public ObservableList<Livro> getLista() {
		return this.lista;
	}

	public void setLista(ObservableList<Livro> lista) {
		this.lista = lista;
	}

	public StringProperty nomeAutorProperty() {
		return this.nomeAutor;
	}

	public StringProperty nomeProperty() {
		return this.nome;
	}

	public StringProperty isbnProperty() {
		return this.isbn;
	}

	public StringProperty descricaoProperty() {
		return this.descricao;
	}

	public StringProperty statusProperty() {
		return this.status;
	}

	public IntegerProperty anoEscritoProperty() {
		return this.anoEscrito;
	}

	public StringProperty generoProperty() {
		return this.genero;
	}

	public StringProperty pesquisaProperty() {
		return this.pesquisa;
	}
}
