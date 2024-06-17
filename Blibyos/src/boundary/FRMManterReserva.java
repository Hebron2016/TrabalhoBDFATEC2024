package boundary;

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
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import control.CTRManterReserva;
import entity.Reserva;

public class FRMManterReserva implements Boundary{

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
	
	private Label identificacao = new Label("RA/Registro: ");
	private TextField txtIdent = new TextField();
	private Button pesquisar = new Button("Pesquisar");
	private Button cadastrar = new Button("Cadastrar Reserva");
	
	private TableView<Reserva> table = new TableView<>();
	
	private CTRManterReserva control = new CTRManterReserva();
	
	
	//Gerando a TableView
	public void generateTable() { 
		
		//Adicionando colunas
        TableColumn<Reserva, String> colIdentificacao = new TableColumn<>("Identificação (RA/Registro)");
        colIdentificacao.setCellValueFactory( new PropertyValueFactory<>("identificacao"));
        
        TableColumn<Reserva, String> colLivro = new TableColumn<>("Livro");
        colLivro.setCellValueFactory( new PropertyValueFactory<>("livroNome"));
        
        TableColumn<Reserva, String> colStatus = new TableColumn<>("Status Reserva");
        colStatus.setCellValueFactory( new PropertyValueFactory<>("status"));
        
        TableColumn<Reserva, String> colPessoa = new TableColumn<>("Nome");
        colPessoa.setCellValueFactory( new PropertyValueFactory<>("nomePessoa"));


        
        TableColumn<Reserva, Void> colAcoes = new 
		TableColumn<>("Ações");
        Callback<TableColumn<Reserva, Void>, TableCell<Reserva, Void>>
        callback = new Callback<>() { 
            public TableCell<Reserva, Void> call(TableColumn<Reserva, Void> coluna) {
                TableCell<Reserva, Void> tc = new TableCell<>() {
                    final Button btnFinalizar = new Button("Finalizar");
                    {
                        btnFinalizar.setOnAction(event -> { 
                            Reserva r1 = table.getItems().get(getIndex());
                            control.excluir(r1);
                        });
                    }
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnFinalizar);
                        }
                    }
                };
                return tc;
            }
        };

        table.getColumns().clear();
        table.getColumns().addAll(colIdentificacao, colPessoa, colLivro, colStatus, colAcoes);
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
        grid.add(identificacao, 2, 0);
        grid.add(txtIdent, 2, 1);
        grid.add(pesquisar, 2, 2);
        grid.add(cadastrar, 3, 2);
        generateTable();
        
        cadastrar.setOnAction(e -> executarComando("ABRIR CADRESERVA"));
        
        panePrincipal.setTop(grid);
        panePrincipal.setCenter( table );
        
		return panePrincipal;
	}

}
