package boundary;

import control.CTRManterAutor;
import entity.Autor;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class FRMManterAutor implements Boundary{

	Executor executor;
	
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
			executor.executar(comando);
		}
		
	}

		
		private Label nomeAutor = new Label("Nome do autor:");
		private TextField txtNmAutor = new TextField();
		private Button pesquisar = new Button("Pesquisar");
		private Button cadastrar = new Button("Cadastrar/Atualizar Autor");
		private Button redefinir = new Button("Redefinir");
		
		
		private TableView<Autor> table = new TableView<>();
		
		private CTRManterAutor control = new CTRManterAutor();
		
		
		//Gerando a TableView
		public void generateTable(ObservableList<Autor> lista) { 
			
			//Adicionando colunas
			TableColumn<Autor, String> colId = new TableColumn<>("ID");
	        colId.setCellValueFactory( new PropertyValueFactory<>("id"));
	        
	        TableColumn<Autor, String> colAutor = new TableColumn<>("Autores");
	        colAutor.setCellValueFactory( new PropertyValueFactory<>("nome"));
	        
	        TableColumn<Autor, String> colBio = new TableColumn<>("Biografia");
	        colBio.setCellValueFactory( new PropertyValueFactory<>("biografia"));
	        
	        table
	        .getSelectionModel()
	        .selectedItemProperty().addListener(
	            (obs, oldSelection, newSelection) -> {
	                if (newSelection != null) {
	                    System.out.println("Selecionado: " + newSelection);
	                    control.fromEntity(newSelection);
	                }
	            });

	        TableColumn<Autor, Void> colAcoes = new 
			TableColumn<>("Operações");
	        Callback<TableColumn<Autor, Void>, TableCell<Autor, Void>>
	        callback = new Callback<>() { 
	            public TableCell<Autor, Void> call(TableColumn<Autor, Void> coluna) {
	                TableCell<Autor, Void> tc = new TableCell<>() {
	                    final Button btnDeletar = new Button("Deletar");
	                    {
	                        btnDeletar.setOnAction(event -> { 
	                        	Autor a1 = table.getItems().get(getIndex());
	                            control.excluir(a1);
	                        });
	                    }
	                    public void updateItem(Void item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (empty) {
	                            setGraphic(null);
	                        } else {
	                            setGraphic(btnDeletar);
	                        }
	                    }
	                };
	                return tc;
	            }
	        };

	        table.getColumns().clear();
	        table.getColumns().addAll(colId, colAutor, colBio, colAcoes);
	        table.setItems( lista );
	        colAcoes.setCellFactory( callback );
		}
		
		public void bind() { 
	        Bindings.bindBidirectional(txtNmAutor.textProperty(), control.pesquisaProperty());
		}
		
		@Override
		public Pane render() {
			control.atualizarLista();
			BorderPane panePrincipal = new BorderPane();
	        GridPane grid = new GridPane();
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.add(nomeAutor, 2, 0);
	        grid.add(txtNmAutor, 2, 1);
	        grid.add(pesquisar, 2, 2);
	        grid.add(cadastrar, 3, 2);
	        grid.add(redefinir, 4, 2);
	        bind();
	        generateTable(control.getLista());
	        
	        cadastrar.setOnAction(e -> executarComando("ABRIR CADAUTOR"));
	        pesquisar.setOnAction( e ->
	        {
	        	generateTable(control.pesquisarAutor());
	        });
	        
	        redefinir.setOnAction( e-> generateTable(control.getLista()));
	        
	        panePrincipal.setTop(grid);
	        panePrincipal.setCenter(table);
			return panePrincipal;
		}

}

