package boundary;

import control.CTRManterAluno;
import entity.Aluno;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class FRMManterAluno implements Boundary{

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

		
		private Label nomeAluno = new Label("RA do aluno:");
		private TextField txtNmAluno = new TextField();
		private Button pesquisar = new Button("Pesquisar");
		private Button cadastrar = new Button("Cadastrar/Alterar Aluno");
		private Button redefinir = new Button("Redefinir");
		
		
		
		private TableView<Aluno> table = new TableView<>();
		
		private CTRManterAluno control = new CTRManterAluno();
		
		
		//Gerando a TableView
		public void generateTable(ObservableList<Aluno> lista) { 
			
			//Adicionando colunas
	        TableColumn<Aluno, String> colAluno = new TableColumn<>("Alunos");
	        colAluno.setCellValueFactory( new PropertyValueFactory<>("nome"));
	        
	        TableColumn<Aluno, String> colRegistro = new TableColumn<>("RA");
	        colRegistro.setCellValueFactory( new PropertyValueFactory<>("ra"));
	        
	        TableColumn<Aluno, String> colStatus = new TableColumn<>("Status");
	        colStatus.setCellValueFactory( new PropertyValueFactory<>("status"));

	        table
	        .getSelectionModel()
	        .selectedItemProperty().addListener(
	            (obs, oldSelection, newSelection) -> {
	                if (newSelection != null) {
	                    System.out.println("Selecionado: " + newSelection);
	                    control.fromEntity(newSelection);
	                }
	            });

	        TableColumn<Aluno, Void> colAcoes = new 
			TableColumn<>("Operações");
	        Callback<TableColumn<Aluno, Void>, TableCell<Aluno, Void>>
	        callback = new Callback<>() { 
	            public TableCell<Aluno, Void> call(TableColumn<Aluno, Void> coluna) {
	                TableCell<Aluno, Void> tc = new TableCell<>() {
	                    final Button btnDeletar = new Button("Deletar");
	                    {
	                        btnDeletar.setOnAction(event -> { 
	                        	Aluno a1 = table.getItems().get(getIndex());
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
	        table.getColumns().addAll(colAluno , colRegistro, colStatus, colAcoes);
	        table.setItems( lista );
	        colAcoes.setCellFactory( callback );
		}
		
		public void bind() { 
	        Bindings.bindBidirectional(txtNmAluno.textProperty(), control.pesquisaProperty());
		}
		
		@Override
		public Pane render() {
			control.atualizarLista();
			HBox panePrincipal = new HBox();
	        Scene scn = new Scene( panePrincipal, 500, 300);
	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER);
	        grid.setHgap(60);
	        grid.setVgap(10);
	        grid.add(nomeAluno, 2, 0);
	        grid.add(txtNmAluno, 2, 1);
	        grid.add(pesquisar, 2, 2);
	        grid.add(cadastrar, 3, 3);
	        grid.add(redefinir, 2, 3);
	        bind();
	        generateTable(control.getLista());
	        
	        cadastrar.setOnAction(e -> executarComando("ABRIR CADALUNO"));
	        pesquisar.setOnAction( e ->
	        {
	        	generateTable(control.pesquisarAluno());
	        });
	        redefinir.setOnAction( e-> generateTable(control.getLista()));

	        panePrincipal.getChildren().add(table);
	        panePrincipal.getChildren().add(grid);
			return panePrincipal;
		}

}
