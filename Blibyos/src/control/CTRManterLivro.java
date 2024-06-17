package control;

import java.time.LocalDate;

import dao.LivroDAO;
import entity.Livro;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CTRManterLivro {

	private LivroDAO dao;
	
	private CTRManterAutor ctrlAutor = new CTRManterAutor();
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty nomeAutor = new SimpleStringProperty("");
	private StringProperty descricao = new SimpleStringProperty("");
	private StringProperty status = new SimpleStringProperty("");
	private IntegerProperty anoEscrito = new SimpleIntegerProperty();
	private StringProperty genero = new SimpleStringProperty("");
	private StringProperty isbn = new SimpleStringProperty("");
	
	private ObservableList<Livro> lista =  FXCollections.observableArrayList();
	
	
	 public void limparCampos() { 
	        nome.set("");
	        nomeAutor.set("");
	        descricao.set("");
	        status.set("");
	        genero.set("");
	        isbn.set("");
	    }

	    public boolean toEntity() { 
	    	int idAutor = ctrlAutor.getIDAutor(nomeAutor.get());
	    	if (idAutor != -1) {
	    		
	        Livro a = new Livro();
	        a.setNome( nome.get() );
	        a.setIdAutor( idAutor );
	        a.setDescricao( descricao.get() );
	        a.setStatus( status.get() );
	        a.setAnoEscrito( anoEscrito.get() );
	        a.setGenero(genero.get());
	        a.setIsbn(isbn.get());
	        lista.add(a);
	        System.out.println("Livro cadastrado");
	        return true;
	    }
	    	return false;
	    	}

	    

	    
	    public void pesquisar() { 
	        for (Livro a : lista) { 
	            if (a.getNome().contains( nome.get() )) { 
	                //fromEntity(a);
	            }
	        }
	    }
	    
	    
	    
	    public void excluir(Livro p) {
	    	lista.remove(p);
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
}
