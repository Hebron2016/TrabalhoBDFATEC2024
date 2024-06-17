package boundary;

import control.CTRManterLivro;
import entity.Livro;
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

public class FRMManterLivro implements Boundary{

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

		
		private Label nomeLivro = new Label("Nome do Livro:");
		private TextField txtNmLivro = new TextField();
		private Button pesquisar = new Button("Pesquisar");
		private Button cadastrar = new Button("Cadastrar Livro");
		
		
		
		private TableView<Livro> table = new TableView<>();
		
		private CTRManterLivro control = new CTRManterLivro();
		
		
		//Gerando a TableView
		public void generateTable() { 
			
			//Adicionando colunas
	        TableColumn<Livro, String> colLivro = new TableColumn<>("Livros");
	        colLivro.setCellValueFactory( new PropertyValueFactory<Livro, String>("nome"));
	        
	        TableColumn<Livro, String> colISBM = new TableColumn<>("Livros");
	        colISBM.setCellValueFactory( new PropertyValueFactory<Livro, String>("isbm"));
	        
	        TableColumn<Livro, String> colDescricao = new TableColumn<>("Descrição");
	        colDescricao.setCellValueFactory( new PropertyValueFactory<Livro, String>("descricao"));
	        
	        TableColumn<Livro, String> colStatus = new TableColumn<>("Status");
	        colStatus.setCellValueFactory( new PropertyValueFactory<Livro, String>("status"));
	        
	        

	        TableColumn<Livro, Void> colAcoes = new 
			TableColumn<>("Operações");
	        Callback<TableColumn<Livro, Void>, TableCell<Livro, Void>>
	        callback = new Callback<>() { 
	            public TableCell<Livro, Void> call(TableColumn<Livro, Void> coluna) {
	                TableCell<Livro, Void> tc = new TableCell<>() {
	                    final Button btnDeletar = new Button("Deletar");
	                    {
	                        btnDeletar.setOnAction(event -> { 
	                        	Livro l1 = table.getItems().get(getIndex());
	                            control.excluir(l1);
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
	        table.getColumns().addAll(colLivro, colISBM, colDescricao, colStatus, colAcoes);
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
	        grid.add(nomeLivro, 2, 0);
	        grid.add(txtNmLivro, 2, 1);
	        grid.add(pesquisar, 2, 2);
	        grid.add(cadastrar, 3, 2);
	        generateTable();
	        
	        cadastrar.setOnAction(e -> executarComando("ABRIR CADLIVRO"));

	        panePrincipal.setTop(grid);
	        panePrincipal.setCenter(table);
			return panePrincipal;
		}

}
