package boundaryConsulta;

import boundary.Boundary;
import boundary.Executor;
import control.CTRConsulta;
import entity.Aluno;
import entity.Autor;
import entity.Livro;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class FRMConsulta implements Boundary {

	private Executor executor;

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

	}{

}
	private CTRConsulta consulta = new CTRConsulta();
	private TableView<Autor> tableAut = new TableView<>();
	private TableView<Livro> tableLiv = new TableView<>();
	private Button autSemLivro = new Button("Autores sem livro");
	private Button livroSemRes = new Button("Livros não reservados");
	public void gerarAutorSemLivro(){
		
		 	TableColumn<Autor, String> colAutor = new TableColumn<>("Autores sem Livro registrado");
	        colAutor.setCellValueFactory( new PropertyValueFactory<>("nome"));
	        tableAut.getColumns().clear();
	        tableAut.getColumns().addAll(colAutor);
	        tableAut.setItems( consulta.getListaAutorSemLiv() );
	}
	
	public void gerarLivroSemReserva() {
		TableColumn<Livro, String> colLivro = new TableColumn<>("Livros não reservados");
        colLivro.setCellValueFactory( new PropertyValueFactory<>("nome"));
        tableLiv.getColumns().clear();
        tableLiv.getColumns().addAll(colLivro);
        tableLiv.setItems( consulta.getListaLivroSemRes() );
	}
	@Override
	public Pane render() {
		BorderPane paneprincipal = new BorderPane();
		GridPane grid = new GridPane();
		grid.setVgap(20);
		grid.setHgap(20);
		grid.add(autSemLivro, 0, 0);
		grid.add(livroSemRes, 0, 1);
		
		livroSemRes.setOnAction(e -> {
			consulta.livroSemRes();
			gerarLivroSemReserva();
			paneprincipal.setBottom(tableLiv);
		});
		
		autSemLivro.setOnAction(e -> {
			consulta.autorSemLivro();
			gerarAutorSemLivro();
			paneprincipal.setBottom(tableAut);
		});
		
		
		paneprincipal.setTop(grid);
		return paneprincipal;
	}
}
