package boundary;

import control.CTRManterLivro;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class FRMCadLivro implements Boundary{

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
	
	private CTRManterLivro control = new CTRManterLivro();
	private boolean gridErro = false;
	private TextField txtNome = new TextField();
	private Label lblNome = new Label("Título:");
	
	private TextField txtAutor = new TextField();
	private Label lblAutor = new Label("Autor:");
	
	private TextField txtISBN = new TextField();
	private Label lblISBN = new Label("ISBN:");
	
	private TextField txtAno = new TextField();
	private Label lblAno = new Label("Ano:");
	
	private TextField txtGenero = new TextField();
	private Label lblGenero = new Label("Gênero:");
	
	private TextField txtStatus = new TextField();
	private Label lblStatus = new Label("Status: ");

	private Label lblErro = new Label("Autor não cadastrado no sistema.");
	private Button btCancelar = new Button("Cancelar");
	private Button btCadastrar = new Button("Cadastrar");
	
	public void bind() { 
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtAutor.textProperty(), control.descricaoProperty());
        Bindings.bindBidirectional(txtAno.textProperty(), control.anoEscritoProperty(), (StringConverter<Number>) converterNumber);
        Bindings.bindBidirectional(txtISBN.textProperty(), control.isbnProperty());
        Bindings.bindBidirectional(txtGenero.textProperty(), control.generoProperty());
        Bindings.bindBidirectional(txtStatus.textProperty(), control.statusProperty());
        
        
    }
	
	@Override
	public Pane render() {
		GridPane grid = new GridPane();
		gridErro = false;
		
		  grid.setHgap(20);
	      grid.setVgap(10);
	      
	      grid.add(lblNome, 0, 0);
	      grid.add(txtNome, 1, 0);
	      
	      grid.add(lblISBN, 0, 1);
	      grid.add(txtISBN, 1, 1);
	      
	      grid.add(lblAutor, 0, 2);
	      grid.add(txtAutor, 1, 2);
	      
	      grid.add(lblGenero, 3, 0);
	      grid.add(txtGenero, 4, 0);
	      
	      grid.add(lblStatus, 3, 1);
	      grid.add(txtStatus, 4, 1);
	      
	      grid.add(lblAno, 3, 2);
	      grid.add(txtAno, 4, 2);
	      
	      grid.add(btCadastrar, 1, 4);
	      grid.add(btCancelar, 0, 4);
	      bind();
	      btCadastrar.setOnAction(e -> {
	    	  if(control.toEntity()) {
	    		  executarComando("ABRIR LIVRO");}
	    	  else {
	    		  if (!gridErro) {
	    		  grid.add(lblErro, 5, 4);
	    		  gridErro = true;}
	    	  }
	      });
		  btCancelar.setOnAction(e -> {executarComando("ABRIR LIVRO"); gridErro = false;});
	      
	      return grid;
	}

}
