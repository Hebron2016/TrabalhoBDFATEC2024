package boundary;

import control.CTRManterProfessor;
import control.CTRManterReserva;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class FRMCadReserva implements Boundary{

	private Executor executor;
	
	@Override
	public void setExecutor(Executor a) {
		this.executor = a;
		
	}

	@Override
	public void clearExecutor() {
		this.executor = null;
		
	}

	@Override
	public void executarComando(String comando) {
		if (this.executor != null) {
			this.executor.executar(comando);
		}
		
	}
	
	private CTRManterReserva control = new CTRManterReserva();
	
	private TextField txtLivro = new TextField();
	private Label lblLivro = new Label("Nome do Livro:");
	
	private TextField txtIdentificacao = new TextField();
	private Label lblIdentificacao = new Label("RA/Registro:");
	
	private DatePicker txtDataDev = new DatePicker();
	private Label lblDataDev = new Label("Data de devolução:");
	
	private DatePicker txtDataRet = new DatePicker();
	private Label lblDataRet = new Label("Data de retirada:");
	
	private Button btCancelar = new Button("Cancelar");
	private Button btCadastrar = new Button("Cadastrar");
	
	public void bind() { 
        Bindings.bindBidirectional(txtLivro.textProperty(), control.nomeLivroProperty());
        Bindings.bindBidirectional(txtIdentificacao.textProperty(), control.identificacaoProperty());
        Bindings.bindBidirectional(new SimpleObjectProperty<>(txtDataDev.getValue()), control.dataDevoluProperty());
        Bindings.bindBidirectional(new SimpleObjectProperty<>(txtDataRet.getValue()), control.dataRetiradaProperty());
    }
	
	@Override
	public Pane render() {
		GridPane grid = new GridPane();
		
		
		  grid.setHgap(20);
	      grid.setVgap(10);
	      
	      grid.add(txtLivro, 1, 0);
	      grid.add(lblLivro, 0, 0);
	      
	      grid.add(txtIdentificacao, 1, 1);
	      grid.add(lblIdentificacao, 0, 1);
	      
	      grid.add(txtDataRet, 3, 0);
	      grid.add(lblDataRet, 2, 0);
	      
	      grid.add(txtDataDev, 3, 1);
	      grid.add(lblDataDev, 2, 1);
	      
	      grid.add(btCadastrar, 1, 4);
	      grid.add(btCancelar, 0, 4);
	      bind();
	      btCadastrar.setOnAction(e ->  {control.toEntity(); executarComando("ABRIR RESERVA");}
	    		  );
		  btCancelar.setOnAction(e -> executarComando("ABRIR RESERVA"));
	      
	      return grid;
	}


}
