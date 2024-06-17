package boundary;

import control.CTRManterProfessor;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class FRMCadProfessor implements Boundary{

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
	
	private CTRManterProfessor control = new CTRManterProfessor();
	
	private TextField txtNome = new TextField();
	private Label lblNome = new Label("Nome:");
	
	private TextField txtRegistro = new TextField();
	private Label lblRegistro = new Label("Registro:");
	
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
        Bindings.bindBidirectional(txtRegistro.textProperty(), control.registroProperty());
    }
	
	@Override
	public Pane render() {
		GridPane grid = new GridPane();
		
		
		  grid.setHgap(20);
	      grid.setVgap(10);
	      
	      grid.add(lblRegistro, 0, 0);
	      grid.add(txtRegistro, 1, 0);
	      
	      grid.add(lblNome, 0, 1);
	      grid.add(txtNome, 1, 1);
	      
	      grid.add(lblEmail, 0, 2);
	      grid.add(txtEmail, 1, 2);
	      
	      grid.add(lblTelefone, 0, 3);
	      grid.add(txtTelefone, 1, 3);
	      
	      grid.add(btCadastrar, 1, 4);
	      grid.add(btCancelar, 0, 4);
	      bind();
	      btCadastrar.setOnAction(e ->  {control.toEntity(); executarComando("ABRIR PROFESSOR");}
	    		  );
		  btCancelar.setOnAction(e -> executarComando("ABRIR PROFESSOR"));
	      
	      return grid;
	}

}
