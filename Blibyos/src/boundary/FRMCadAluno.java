package boundary;

import java.util.Optional;

import control.CTRManterAluno;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class FRMCadAluno implements Boundary {

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

	private boolean gridErro = false;

	private ObservableList<String> itensStat = FXCollections.observableArrayList();
	private Label lblErro = new Label("O campo RA deve ser preenchido.");

	private CTRManterAluno control = new CTRManterAluno();

	private TextField txtNome = new TextField();
	private Label lblNome = new Label("Nome:");

	private TextField txtRa = new TextField();
	private Label lblRa = new Label("Ra:");

	private TextField txtEmail = new TextField();
	private Label lblEmail = new Label("Email:");

	private TextField txtTelefone = new TextField();
	private Label lblTelefone = new Label("Telefone:");

	private ComboBox<String> status = new ComboBox<>();
	private Label lblStatus = new Label("Status: ");

	private Button btCancelar = new Button("Cancelar");
	private Button btCadastrar = new Button("Cadastrar");

	public void bind() {
		Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
		Bindings.bindBidirectional(txtTelefone.textProperty(), control.telefoneProperty());
		Bindings.bindBidirectional(txtEmail.textProperty(), control.emailProperty());
		Bindings.bindBidirectional(txtRa.textProperty(), control.raProperty());
		status.valueProperty().bindBidirectional(control.statusProperty());

	}

	@Override
	public Pane render() {
		control.atualizarLista();
		gridErro = false;
		GridPane grid = new GridPane();
		itensStat.clear();
		itensStat.addAll("APTO", "NÃO APTO");
		status.setItems(itensStat);

		grid.setHgap(20);
		grid.setVgap(10);

		grid.add(lblRa, 0, 0);
		grid.add(txtRa, 1, 0);

		grid.add(lblNome, 0, 1);
		grid.add(txtNome, 1, 1);

		grid.add(lblEmail, 0, 2);
		grid.add(txtEmail, 1, 2);

		grid.add(lblTelefone, 0, 3);
		grid.add(txtTelefone, 1, 3);

		grid.add(status, 1, 4);
		grid.add(lblStatus, 0, 4);

		grid.add(btCadastrar, 1, 5);
		grid.add(btCancelar, 0, 5);
		bind();
		status.setOnAction(e -> System.out.println(status.getValue()));
		btCadastrar.setOnAction(e -> {
			if (control.pesquisarExistencia() && !txtRa.textProperty().get().isBlank()) {
				Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
				alerta.setTitle("Mensagem");
				alerta.setHeaderText("Aluno já existe, deseja atualizar seus dados?");
				Optional<ButtonType> result = alerta.showAndWait();
				if (result.get().equals(ButtonType.OK)) {
					control.gravar();
					executarComando("ABRIR ALUNO");
					control.limparCampos();
					;
				} else if (result.get().equals(ButtonType.NO)) {
					alerta.close();
				}
			} else if (!control.pesquisarExistencia() && !txtRa.textProperty().get().isBlank()) {
				control.gravar();
				executarComando("ABRIR ALUNO");
				control.limparCampos();
			} else if (txtRa.textProperty().get().isBlank()) {
				if (!gridErro) {
					grid.add(lblErro, 5, 4);
					gridErro = true;
				}
			}
		});
		btCancelar.setOnAction(e -> {
			executarComando("ABRIR ALUNO");
			control.limparCampos();
		});

		return grid;
	}
}
