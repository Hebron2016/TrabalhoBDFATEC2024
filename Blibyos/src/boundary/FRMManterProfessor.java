package boundary;

import control.CTRManterProfessor;
import entity.Aluno;
import entity.Professor;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

		
		private Label nomeProfessor = new Label("RA do professor:");
		private TextField txtNmProf = new TextField();
		private Button pesquisar = new Button("Pesquisar");
		private Button cadastrar = new Button("Cadastrar/Alterar Professor");
		private Button redefinir = new Button("Redefinir");
		
		
		
		private TableView<Professor> table = new TableView<>();
		
		private CTRManterProfessor control = new CTRManterProfessor();
		
		
		//Gerando a TableView
		public void generateTable(ObservableList<Professor> lista) { 
			
			//Adicionando colunas
	        TableColumn<Professor, String> colProfessor = new TableColumn<>("Professores");
	        colProfessor.setCellValueFactory( new PropertyValueFactory<>("nome"));
	        
	        TableColumn<Professor, String> colRegistro = new TableColumn<>("Registro");
	        colRegistro.setCellValueFactory( new PropertyValueFactory<>("registro"));

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
	        table.setItems( lista );
	        colAcoes.setCellFactory( callback );
		}
		
		public void bind() { 
	        Bindings.bindBidirectional(txtNmProf.textProperty(), control.pesquisaProperty());
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
	        grid.add(nomeProfessor, 2, 0);
	        grid.add(txtNmProf, 2, 1);
	        grid.add(pesquisar, 2, 2);
	        grid.add(cadastrar, 3, 3);
	        grid.add(redefinir, 2, 3);
	        bind();
	        generateTable(control.getLista());
	        
	        cadastrar.setOnAction(e -> executarComando("ABRIR CADPROFESSOR"));
	        pesquisar.setOnAction( e ->
	        {
	        	generateTable(control.pesquisarProfessor());
	        });
	        redefinir.setOnAction( e-> generateTable(control.getLista()));
	        
	        panePrincipal.getChildren().add(table);
	        panePrincipal.getChildren().add(grid);
			return panePrincipal;
		}
	  
}


