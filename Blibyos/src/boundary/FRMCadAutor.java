package boundary;

import control.CTRManterAutor;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class FRMCadAutor implements Boundary{

	private Executor executor;
	private StringConverter<? extends Number>converterNumber = new NumberStringConverter();
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
	
	private CTRManterAutor control = new CTRManterAutor();
	
	private TextField txtNome = new TextField();
	private Label lblNome = new Label("Nome:");
	
	private TextField txtBio = new TextField();
	private Label lblBio = new Label("Biografia:");
	
	private TextField txtId = new TextField();
	private Label lblId = new Label("ID:");

	
	private Button btCancelar = new Button("Cancelar");
	private Button btCadastrar = new Button("Cadastrar");
	
	public void bind() { 
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtBio.textProperty(), control.biografiaProperty());
        Bindings.bindBidirectional(txtId.textProperty(), control.idProperty(), (StringConverter<Number>) converterNumber);
    }
	
	@Override
	public Pane render() {
		
		//control.atualizar();
		GridPane grid = new GridPane();
		
		
		  grid.setHgap(20);
	      grid.setVgap(10);
	      
	      grid.add(lblId, 0, 0);
	      grid.add(txtId, 1, 0);
	      
	      grid.add(lblNome, 0, 1);
	      grid.add(txtNome, 1, 1);
	      
	      grid.add(lblBio, 0, 2);
	      grid.add(txtBio, 1, 2);
	      
	      grid.add(btCadastrar, 1, 4);
	      grid.add(btCancelar, 0, 4);
	      bind();
	      btCadastrar.setOnAction(e -> {control.toEntity(); executarComando("ABRIR AUTOR");}
	    		  );
		  btCancelar.setOnAction(e -> executarComando("ABRIR AUTOR"));
	      
	      return grid;
	}

}
