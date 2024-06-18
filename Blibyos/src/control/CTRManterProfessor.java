package control;


import entity.Aluno;
import entity.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

import dao.AlunoException;
import dao.ProfessorDAO;
import dao.ProfessorDaoImpl;
import dao.ProfessorException;

public class CTRManterProfessor {
	
	private ProfessorDAO dao = new ProfessorDaoImpl();
	
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty email = new SimpleStringProperty("");
	private StringProperty telefone = new SimpleStringProperty("");
	private StringProperty registro = new SimpleStringProperty("");
	private StringProperty pesquisa = new SimpleStringProperty("");
	
	private ObservableList<Professor> lista =  FXCollections.observableArrayList();
	
	
	 public void limparCampos() { 
	        nome.set("");
	        telefone.set("");
	        email.set("");
	        registro.set("");
	    }

	    public Professor toEntity() { 
	        Professor p = new Professor();
	        p.setNome( nome.get() );
	        p.setTelefone( telefone.get() );
	        p.setEmail( email.get() );
	        p.setRegistro( registro.get() );
	        return p;
	    }
	    
	    public void gravar() { 
	        Professor p = toEntity();
	            try {
	              if (pesquisarExistencia()) { 
					dao.atualizar(p);
	             } else { 
	 	            dao.adicionar(p);
	 	        }
	              atualizarLista();
	              limparCampos();
				} catch (ProfessorException e) {
					e.printStackTrace();
				}
	        
	    }

	    public void fromEntity(Professor p) { 
	        telefone.set(p.getTelefone());
	        nome.set(p.getNome());
	        email.set(p.getEmail());
	        registro.set(p.getRegistro());
	    }

	    public ObservableList<Professor> pesquisarProfessor(){
	    	
	    	ObservableList<Professor> pr = FXCollections.observableArrayList();
	    	try {
				pr.addAll(dao.pesquisarPorReg(pesquisa.get()));
				return pr;
			} catch (ProfessorException e) {
				e.printStackTrace();
			}
	    	return pr;
	    }

	    

	    public boolean pesquisarExistencia() { 
	    	atualizarLista();
	        for (Professor p : lista) { 
	            if (p.getRegistro().equals( registro.get() )) { 
	                
	                return true;
	            }
	        }
	       return false;
	    }
	    
	    public boolean pesquisarExistencia(String reg) { 
	    	atualizarLista();
	        for (Professor p : lista) { 
	            if (p.getRegistro().equals( reg )) { 
	                
	                return true;
	            }
	        }
	       return false;
	    }
	    
	    public void atualizarLista() { 
	        List<Professor> tempLista = new ArrayList<>();
	        try {
				tempLista = dao.pesquisarTodos();
				lista.clear();
		        lista.addAll( tempLista );
		        System.out.println("Lista atualizada com sucesso");
			} catch (ProfessorException e) {
				e.printStackTrace();
			}
	        
	    }
	    
	    public void excluir(Professor p) {
	    	try {
				dao.remover(p.getRegistro());
				atualizarLista();
			} catch (ProfessorException e) {
				e.printStackTrace();
			}
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
	    
	    public StringProperty pesquisaProperty() {
	    	return this.pesquisa;
	    }
}
