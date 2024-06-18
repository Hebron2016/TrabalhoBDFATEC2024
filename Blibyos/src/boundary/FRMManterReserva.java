package boundary;


import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	
	private Label data = new Label("Filtrar por data de entrega: ");
	private DatePicker dtDev = new DatePicker();
	
	private Button pesquisar = new Button("Pesquisar");
	private Button redefinir = new Button("Redefinir");
	private Button cadastrar = new Button("Cadastrar Reserva");
	
	private TableView<Reserva> table = new TableView<>();
	
	private CTRManterReserva control = new CTRManterReserva();
	
	
	//Gerando a TableView
	public void generateTable(ObservableList<Reserva> lista) { 
		
		//Adicionando colunas
       
        
        TableColumn<Reserva, String> colLivro = new TableColumn<>("Livro");
        colLivro.setCellValueFactory( new PropertyValueFactory<>("nomeLivro"));
        
        TableColumn<Reserva, String> colStatus = new TableColumn<>("Status Reserva");
        colStatus.setCellValueFactory( new PropertyValueFactory<>("status"));
        
        TableColumn<Reserva, String> colRa = new TableColumn<>("RA");
        colRa.setCellValueFactory( new PropertyValueFactory<>("identRa"));
        
        TableColumn<Reserva, String> colReg = new TableColumn<>("Registro");
        colReg.setCellValueFactory( new PropertyValueFactory<>("identReg"));
        
        TableColumn<Reserva, String> colDataRet = new TableColumn<>("Data de Retirada");
        colDataRet.setCellValueFactory( new PropertyValueFactory<>("dataRetirada"));
        
        TableColumn<Reserva, String> colDataDev = new TableColumn<>("Data de Devolução");
        colDataDev.setCellValueFactory( new PropertyValueFactory<>("dataDevolucao"));



        
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
        table.getColumns().addAll(colRa, colReg, colLivro, colStatus, colDataRet, colDataDev, colAcoes);
        table.setItems( lista );
        colAcoes.setCellFactory( callback );
    }
	public void bind() { 
		dtDev.valueProperty().bindBidirectional(control.pesquisaProperty());
	}
	@Override
	public Pane render() {
		control.atualizarLista();
		
		BorderPane panePrincipal = new BorderPane();
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(data, 2, 0);
        grid.add(dtDev, 2, 1);
        grid.add(pesquisar, 2, 2);
        grid.add(cadastrar, 3, 2);
        grid.add(redefinir, 4, 2);
        bind();
        generateTable(control.getLista());
        
        cadastrar.setOnAction(e -> executarComando("ABRIR CADRESERVA"));
        pesquisar.setOnAction( e ->
        {
        	generateTable(control.pesquisarReserva());
        });
        
        redefinir.setOnAction( e-> generateTable(control.getLista()));
        
        panePrincipal.setTop(grid);
        panePrincipal.setCenter( table );
        
		return panePrincipal;
	}

}
