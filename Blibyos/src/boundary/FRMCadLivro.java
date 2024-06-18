package boundary;

import java.util.Optional;

import control.CTRManterAutor;
import control.CTRManterLivro;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
	private CTRManterAutor ctrlAutor = new CTRManterAutor();
	private ObservableList<String> itensStat = FXCollections.observableArrayList();
	
	private boolean gridErro = false;
	private Label lblErro = new Label("O campo ISBN deve ser preenchido.");
	
	private TextField txtNome = new TextField();
	private Label lblNome = new Label("Título:");
	
	private TextField txtAutor = new TextField();
	private Label lblAutor = new Label("Autor:");
	
	private TextField txtDescricao = new TextField();
	private Label lblDescricao = new Label("Descrição:");
	
	private TextField txtISBN = new TextField();
	private Label lblISBN = new Label("ISBN:");
	
	private TextField txtAno = new TextField();
	private Label lblAno = new Label("Ano:");
	
	private TextField txtGenero = new TextField();
	private Label lblGenero = new Label("Gênero:");
	
	private ComboBox<String> status = new ComboBox<>();
	private Label lblStatus = new Label("Status:");

	private Button btCancelar = new Button("Cancelar");
	private Button btCadastrar = new Button("Cadastrar");
	
	public void bind() { 
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtAutor.textProperty(), control.nomeAutorProperty());
        Bindings.bindBidirectional(txtDescricao.textProperty(), control.descricaoProperty());
        Bindings.bindBidirectional(txtAno.textProperty(), control.anoEscritoProperty(), (StringConverter<Number>) converterNumber);
        Bindings.bindBidirectional(txtISBN.textProperty(), control.isbnProperty());
        Bindings.bindBidirectional(txtGenero.textProperty(), control.generoProperty());
        status.valueProperty().bindBidirectional(control.statusProperty());
        
        
    }
	
	@Override
	public Pane render() {
		control.atualizarLista();
		GridPane grid = new GridPane();
		gridErro = false;
		itensStat.clear();
		itensStat.addAll("DISPONÍVEL", "NÃO DISPONÍVEL");
		status.setItems(itensStat);
		
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
	      grid.add(status, 4, 1);
	      
	      grid.add(lblAno, 3, 2);
	      grid.add(txtAno, 4, 2);
	      
	      grid.add(lblDescricao, 0, 3);
	      grid.add(txtDescricao, 1, 3);
	      
	      grid.add(btCadastrar, 1, 4);
	      grid.add(btCancelar, 0, 4);
	      bind();
	      
	      btCadastrar.setOnAction(e -> {
	    	  if(ctrlAutor.getFirstID(txtAutor.textProperty().get()) == -1) {
	    		  Alert alerta = new Alert(Alert.AlertType.INFORMATION);
	    		  	alerta.setTitle("Mensagem");
					alerta.setHeaderText("Autor não cadastrado no sistema.");
					alerta.showAndWait();
	    	  }
	    	  else if (control.pesquisarExistencia() && !txtISBN.textProperty().get().isBlank()) {
					Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
					alerta.setTitle("Mensagem");
					alerta.setHeaderText("Livro já existe, deseja atualizar seus dados?");
					Optional<ButtonType> result = alerta.showAndWait();
					if (result.get().equals(ButtonType.OK)) {
						control.gravar();
						executarComando("ABRIR LIVRO");
						control.limparCampos();
						;
					} else if (result.get().equals(ButtonType.NO)) {
						alerta.close();
					}
				} else if (!control.pesquisarExistencia() && !txtISBN.textProperty().get().isBlank()) {
					control.gravar();
					executarComando("ABRIR LIVRO");
					control.limparCampos();
				} else if (txtISBN.textProperty().get().isBlank()) {
					if (!gridErro) {
						grid.add(lblErro, 5, 4);
						gridErro = true;
					}
				}});
	      
	      btCancelar.setOnAction(e -> {
				executarComando("ABRIR LIVRO");
				control.limparCampos();
			});
	      
	      return grid;
	}

}
