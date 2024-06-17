package boundary;

import control.CTRManterAluno;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class FRMCadAluno implements Boundary {

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
	
	private CTRManterAluno control = new CTRManterAluno();
	
	private TextField txtNome = new TextField();
	private Label lblNome = new Label("Nome:");
	
	private TextField txtRa = new TextField();
	private Label lblRa = new Label("Ra:");
	
	private TextField txtEmail = new TextField();
	private Label lblEmail = new Label("Email:");
	
	private TextField txtTelefone = new TextField();
	private Label lblTelefone = new Label("Telefone:");
	
	private Button btCancelar = new Button("Cancelar");
	private Button btCadastrar = new Button("Cadastrar");
	
	public void bind() { 
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtTelefone.textProperty(), control.telefoneProperty());
        Bindings.bindBidirectional(txtEmail.textProperty(), control.emailProperty());
        Bindings.bindBidirectional(txtRa.textProperty(), control.raProperty());
    }
	
	@Override
	public Pane render() {
		GridPane grid = new GridPane();
		
		
		  grid.setHgap(20);
	      grid.setVgap(10);
	      
	      grid.add(lblRa, 0, 0);
	      grid.add(txtRa, 1, 0);
	      
	      grid.add(lblNome, 0, 1);
	      grid.add(txtNome, 1, 1);
	      
	      grid.add(lblEmail, 0, 2);
	      grid.add(txtEmail, 1, 2);
	      
	      grid.add(lblTelefone, 0, 3);
	      grid.add(txtTelefone, 1, 3);
	      
	      grid.add(btCadastrar, 1, 4);
	      grid.add(btCancelar, 0, 4);
	      bind();
	      btCadastrar.setOnAction(e -> {control.gravar(); executarComando("ABRIR ALUNO");}
	    		  );
		  btCancelar.setOnAction(e -> executarComando("ABRIR ALUNO"));
	      
	      return grid;
	}
}
