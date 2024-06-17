package control;

import entity.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import dao.ProfessorDAO;

public class CTRManterProfessor {
	
	private ProfessorDAO dao;
	
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty email = new SimpleStringProperty("");
	private StringProperty telefone = new SimpleStringProperty("");
	private StringProperty registro = new SimpleStringProperty("");
	
	private ObservableList<Professor> lista =  FXCollections.observableArrayList();
	
	
	 public void limparCampos() { 
	        nome.set("");
	        telefone.set("");
	        email.set("");
	        registro.set("");
	    }

	    public void toEntity() { 
	        Professor p = new Professor();
	        p.setNome( nome.get() );
	        p.setTelefone( telefone.get() );
	        p.setEmail( email.get() );
	        p.setRegistro( registro.get() );
	        lista.add(p);
	        System.out.println("Professor cadastrado");
	    }

	    public void fromEntity(Professor p) { 
	        telefone.set(p.getTelefone());
	        nome.set(p.getNome());
	        email.set(p.getEmail());
	        registro.set(p.getRegistro());
	    }

	    

	    public void pesquisar() { 
	        for (Professor p : lista) { 
	            if (p.getNome().contains( nome.get() )) { 
	                fromEntity(p);
	            }
	        }
	    }
	    
	    
	    
	    public void excluir(Professor p) {
	    	lista.remove(p);
	    }

		public ObservableList<Professor> getLista() {
			return this.lista;
		}

		public void setLista(ObservableList<Professor> lista) {
			this.lista = lista;
		}
		
		public StringProperty telefoneProperty() { 
	        return this.telefone;
	    }

	    public StringProperty nomeProperty() { 
	        return this.nome;
	    }

	    public StringProperty emailProperty() { 
	        return this.email;
	    }
	    
	    public StringProperty registroProperty() {
	    	return this.registro;
	    }
}
