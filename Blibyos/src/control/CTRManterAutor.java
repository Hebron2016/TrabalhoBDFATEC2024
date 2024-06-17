package control;

import dao.AutorDao;
import entity.Autor;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CTRManterAutor {
		
		private AutorDao dao;
		
		private StringProperty nome = new SimpleStringProperty("");
		private IntegerProperty id = new SimpleIntegerProperty(0);
		private StringProperty biografia = new SimpleStringProperty("");
		
		private ObservableList<Autor> lista =  FXCollections.observableArrayList();
		
		
		 public void limparCampos() { 
		        nome.set("");
		        id.set(0);
		        biografia.set("");
		    }

		    public void toEntity() { 
		        Autor a = new Autor();
		        a.setNome( nome.get() );
		        a.setId( id.get() );
		        a.setBiografia( biografia.get() );
		        lista.add(a);
		        System.out.println("Autor cadastrado");
		    }

		    public void fromEntity(Autor a) { 
		        biografia.set(a.getBiografia());
		        nome.set(a.getNome());
		        id.set(a.getId());
		    }

		    
		    public void pesquisar() { 
		        for (Autor a : lista) { 
		            if (a.getNome().contains( nome.get() )) { 
		                fromEntity(a);
		            }
		        }
		    }
		    
		    
		    
		    public void excluir(Autor p) {
		    	lista.remove(p);
		    }
		    
		    public int getIDAutor(String nome) {
		    	for (Autor a : lista) {
		    		if (a.getNome().contains(nome)) {
		    			return a.getId();
		    		}
		    	}
		    	return -1;
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
}



