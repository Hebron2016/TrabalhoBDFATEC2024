package boundary;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Creditos implements Boundary{

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

	}
	
	private Label feito = new Label("Trabalho feito por:");
	private Label cred1 = new Label("Kevin Freitas Mantovani");
	private Label cred2 = new Label("Henrique Hebron");
	private Label cred3 = new Label("Guilherme Frigueiredo");

	@Override
	public Pane render() {
		
		GridPane grid = new GridPane();
		grid.add(feito, 0, 0);
		grid.add(cred1, 0, 1);
		grid.add(cred2, 0, 2);
		grid.add(cred3, 0, 3);
		
		return grid;
	}

}
