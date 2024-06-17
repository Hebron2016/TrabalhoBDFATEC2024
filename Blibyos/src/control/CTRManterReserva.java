package control;

import java.time.LocalDate;


import dao.ReservaDAO;
import entity.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CTRManterReserva {
	
	  	private CTRManterAluno ctrAluno = new CTRManterAluno();
	  	private CTRManterProfessor ctrProf = new CTRManterProfessor();
	  
		private ReservaDAO dao;
		
		private StringProperty nomeLivro = new SimpleStringProperty("");
		private StringProperty identificacao = new SimpleStringProperty("");
		private StringProperty statusReserva = new SimpleStringProperty("");
		private StringProperty tipoPessoa = new SimpleStringProperty("");
		private ObjectProperty<LocalDate> dataDevolu = new SimpleObjectProperty<>();
		private ObjectProperty<LocalDate> dataRetirada = new SimpleObjectProperty<>();
		private ObservableList<Reserva> lista =  FXCollections.observableArrayList();
		
		
		 public void limparCampos() { 
		        nomeLivro.set("");
		        identificacao.set("");
		    }

		    public Reserva toEntity() { 
		        Reserva r = new Reserva();
		        r.setnomeLivro( nomeLivro.get() );
		        r.setIdentificacao( identificacao.get() );
		        r.setDataDevolucao( dataDevolu.get() );
		        r.setDataRetirada( dataRetirada.get());
		        System.out.println("Reserva cadastrada");
		        return r;
		    }

		    

		    
		    public void pesquisar() { 
		        for (Reserva r : lista) { 
		            }
		        }
		    
		    
		    
		    
		    public void excluir(Reserva r) {
		    	lista.remove(r);
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
			public ObjectProperty<LocalDate> dataRetiradaProperty(){
				return this.dataRetirada;
			}
}
