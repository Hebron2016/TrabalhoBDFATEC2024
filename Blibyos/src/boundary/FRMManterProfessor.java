package boundary;

import control.CTRManterProfessor;
import entity.Professor;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

public class FRMManterProfessor implements Boundary{

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

		
		private Label nomeProfessor = new Label("Nome do professor:");
		private TextField txtNmProf = new TextField();
		private Button pesquisar = new Button("Pesquisar");
		private Button cadastrar = new Button("Cadastrar Professor");
		
		
		
		private TableView<Professor> table = new TableView<>();
		
		private CTRManterProfessor control = new CTRManterProfessor();
		
		
		//Gerando a TableView
		public void generateTable() { 
			
			//Adicionando colunas
	        TableColumn<Professor, String> colProfessor = new TableColumn<>("Professores");
	        colProfessor.setCellValueFactory( data -> new SimpleStringProperty (data.getValue().getNome()));
	        
	        TableColumn<Professor, String> colRegistro = new TableColumn<>("Registro");
	        colRegistro.setCellValueFactory( data -> new SimpleStringProperty (data.getValue().getRegistro()));

	        table
	        .getSelectionModel()
	        .selectedItemProperty().addListener(
	            (obs, oldSelection, newSelection) -> {
	                if (newSelection != null) {
	                    System.out.println("Selecionado: " + newSelection);
	                    control.fromEntity(newSelection);
	                }
	            });

	        TableColumn<Professor, Void> colAcoes = new 
			TableColumn<>("Operações");
	        Callback<TableColumn<Professor, Void>, TableCell<Professor, Void>>
	        callback = new Callback<>() { 
	            public TableCell<Professor, Void> call(TableColumn<Professor, Void> coluna) {
	                TableCell<Professor, Void> tc = new TableCell<>() {
	                    final Button btnDeletar = new Button("Deletar");
	                    {
	                        btnDeletar.setOnAction(event -> { 
	                            Professor p1 = table.getItems().get(getIndex());
	                            control.excluir(p1);
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
	        table.getColumns().addAll(colProfessor , colRegistro, colAcoes);
	        table.setItems( control.getLista() );
	        colAcoes.setCellFactory( callback );
		}
		
		
		@Override
		public Pane render() {
			
			HBox panePrincipal = new HBox();
	        Scene scn = new Scene( panePrincipal, 500, 300);
	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER);
	        grid.setHgap(60);
	        grid.setVgap(10);
	        grid.add(nomeProfessor, 2, 0);
	        grid.add(txtNmProf, 2, 1);
	        grid.add(pesquisar, 2, 2);
	        grid.add(cadastrar, 3, 3);
	        generateTable();
	        
	        cadastrar.setOnAction(e -> executarComando("ABRIR CADPROFESSOR"));
	        
	        panePrincipal.getChildren().add(table);
	        panePrincipal.getChildren().add(grid);
			return panePrincipal;
		}
	  
}


