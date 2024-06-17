package control;

import java.util.ArrayList;
import java.util.List;

import dao.AlunoDAO;
import dao.AlunoDaoImpl;
import dao.AlunoException;
import entity.Aluno;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CTRManterAluno {
	
	private AlunoDAO dao = new AlunoDaoImpl();
	
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty email = new SimpleStringProperty("");
	private StringProperty telefone = new SimpleStringProperty("");
	private StringProperty ra = new SimpleStringProperty("");
	private StringProperty status = new SimpleStringProperty("APTO");
	private StringProperty pesquisa = new SimpleStringProperty("");
	
	private ObservableList<Aluno> lista =  FXCollections.observableArrayList();
	
	
	 public void limparCampos() { 
	        nome.set("");
	        telefone.set("");
	        email.set("");
	        ra.set("");
	    }

	    public Aluno toEntity() { 
	        Aluno a = new Aluno();
	        a.setStatus(status.get());
	        a.setNome( nome.get() );
	        a.setTelefone( telefone.get() );
	        a.setEmail( email.get() );
	        a.setRa( ra.get() );
	        return a;
	    }
	    
	    public void gravar() { 
	        Aluno a = toEntity();
	            try {
	              if (pesquisarExistencia()) { 
					dao.atualizar(a);
	             } else { 
	 	            dao.adicionar(a);
	 	        }
	              atualizarLista();
	              limparCampos();
				} catch (AlunoException e) {
					e.printStackTrace();
				}
	        
	    }

	    public void fromEntity(Aluno a) { 
	        telefone.set(a.getTelefone());
	        nome.set(a.getNome());
	        email.set(a.getEmail());
	        ra.set(a.getRa());
	    }

	    public ObservableList<Aluno> pesquisarAluno(){
	    	
	    	ObservableList<Aluno> al = FXCollections.observableArrayList();
	    	try {
				al.addAll(dao.pesquisarPorRa(pesquisa.get()));
				return al;
			} catch (AlunoException e) {
				e.printStackTrace();
			}
	    	return al;
	    }

	    public boolean pesquisarExistencia() { 
	    	atualizarLista();
	        for (Aluno a : lista) { 
	            if (a.getRa().contains( ra.get() )) { 
	                
	                return true;
	            }
	        }
	       return false;
	    }
	    
	    public void atualizarLista() { 
	        List<Aluno> tempLista = new ArrayList<>();
	        try {
				tempLista = dao.pesquisarTodos();
				lista.clear();
		        lista.addAll( tempLista );
		        System.out.println("Lista atualizada com sucesso");
			} catch (AlunoException e) {
				e.printStackTrace();
			}
	        
	    }
	    
	    
	    
	    public void excluir(Aluno p) {
	    	try {
				dao.remover(p.getRa());
				atualizarLista();
			} catch (AlunoException e) {
				e.printStackTrace();
			}
	    }

		public ObservableList<Aluno> getLista() {
			return this.lista;
		}

		public void setLista(ObservableList<Aluno> lista) {
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
	    
	    public StringProperty raProperty() {
	    	return this.ra;
	    }
	    public StringProperty statusProperty() {
	    	return this.status;
	    }
	    public StringProperty pesquisaProperty() {
	    	return this.pesquisa;
	    }
}


