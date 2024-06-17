package boundary;

import control.CTRManterAutor;
import entity.Autor;
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
		private Button cadastrar = new Button("Cadastrar Autor");
		
		
		
		private TableView<Autor> table = new TableView<>();
		
		private CTRManterAutor control = new CTRManterAutor();
		
		
		//Gerando a TableView
		public void generateTable() { 
			
			//Adicionando colunas
	        TableColumn<Autor, String> colAutor = new TableColumn<>("Autores");
	        colAutor.setCellValueFactory( new PropertyValueFactory<>("nome"));
	        

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
	        table.getColumns().addAll(colAutor, colAcoes);
	        table.setItems( control.getLista() );
	        colAcoes.setCellFactory( callback );
		}
		
		
		@Override
		public Pane render() {
			
			BorderPane panePrincipal = new BorderPane();
	        Scene scn = new Scene( panePrincipal, 500, 300);
	        GridPane grid = new GridPane();
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.add(nomeAutor, 2, 0);
	        grid.add(txtNmAutor, 2, 1);
	        grid.add(pesquisar, 2, 2);
	        grid.add(cadastrar, 3, 3);
	        generateTable();
	        
	        cadastrar.setOnAction(e -> executarComando("ABRIR CADAUTOR"));

	        panePrincipal.setTop(grid);
	        panePrincipal.setCenter(table);
			return panePrincipal;
		}

}

