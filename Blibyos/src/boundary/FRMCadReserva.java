package boundary;

import java.util.Optional;

import control.CTRManterAluno;
import control.CTRManterLivro;
import control.CTRManterProfessor;
import control.CTRManterReserva;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class FRMCadReserva implements Boundary {

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

	private CTRManterReserva control = new CTRManterReserva();

	private CTRManterAluno ctrlAluno = new CTRManterAluno();
	private CTRManterProfessor ctrlProf = new CTRManterProfessor();
	private CTRManterLivro ctrlLivro = new CTRManterLivro();

	private TextField txtLivro = new TextField();
	private Label lblLivro = new Label("*Nome do Livro:");

	private Label lblErro = new Label("Os campos com * devem ser preenchidos.");
	private boolean gridErro;

	private TextField txtIdentificacao = new TextField();
	private Label lblIdentificacao = new Label("*RA/Registro:");

	private RadioButton rdAluno = new RadioButton("Aluno");
	private RadioButton rdProf = new RadioButton("Professor");

	private ToggleGroup group = new ToggleGroup();

	private DatePicker txtDataDev = new DatePicker();
	private Label lblDataDev = new Label("*Data de devolução:");

	private DatePicker txtDataRet = new DatePicker();
	private Label lblDataRet = new Label("*Data de retirada:");

	private Button btCancelar = new Button("Cancelar");
	private Button btCadastrar = new Button("Cadastrar");

	public void bind() {
		Bindings.bindBidirectional(txtLivro.textProperty(), control.nomeLivroProperty());
		Bindings.bindBidirectional(txtIdentificacao.textProperty(), control.identificacaoProperty());
		txtDataDev.valueProperty().bindBidirectional(control.dataDevoluProperty());
		txtDataRet.valueProperty().bindBidirectional(control.dataRetiradaProperty());
	}

	@Override
	public Pane render() {
		GridPane grid = new GridPane();

		control.atualizarLista();
		gridErro = false;
		rdAluno.setToggleGroup(group);
		rdProf.setToggleGroup(group);

		rdAluno.setUserData("ra");
		rdProf.setUserData("reg");

		grid.setHgap(20);
		grid.setVgap(10);

		grid.add(txtLivro, 1, 0);
		grid.add(lblLivro, 0, 0);

		grid.add(txtIdentificacao, 1, 1);
		grid.add(lblIdentificacao, 0, 1);

		grid.add(rdAluno, 0, 2);
		grid.add(rdProf, 1, 2);

		grid.add(txtDataRet, 3, 0);
		grid.add(lblDataRet, 2, 0);

		grid.add(txtDataDev, 3, 1);
		grid.add(lblDataDev, 2, 1);

		grid.add(btCadastrar, 1, 4);
		grid.add(btCancelar, 0, 4);
		bind();
		btCadastrar.setOnAction(e -> {

			if (txtLivro.textProperty().get().isBlank() || txtIdentificacao.textProperty().get().isBlank()) {
				if (!gridErro) {
					grid.add(lblErro, 5, 4);
					gridErro = true;
				}
			} else if (!ctrlLivro.pesquisarExistencia(txtLivro.textProperty().get())) {
				Alert alerta = new Alert(Alert.AlertType.INFORMATION);
				alerta.setTitle("Mensagem");
				alerta.setHeaderText("Livro não cadastrado no sistema.");
				alerta.showAndWait();
			} else if (rdAluno.isSelected()) {

				if (!ctrlAluno.pesquisarExistencia(txtIdentificacao.textProperty().get())) {
					Alert alerta = new Alert(Alert.AlertType.INFORMATION);
					alerta.setTitle("Mensagem");
					alerta.setHeaderText("Aluno não cadastrado no sistema.");
					alerta.showAndWait();
				} else {
					control.gravar("Aluno");
					executarComando("ABRIR RESERVA");
					control.limparCampos();
				}
			} else if (rdProf.isSelected()) {

				if (!ctrlProf.pesquisarExistencia(txtIdentificacao.textProperty().get())) {
					Alert alerta = new Alert(Alert.AlertType.INFORMATION);
					alerta.setTitle("Mensagem");
					alerta.setHeaderText("Professor não cadastrado no sistema.");
					alerta.showAndWait();
				} else {
					control.gravar("Professor");
					executarComando("ABRIR RESERVA");
					control.limparCampos();
				}

			}
		});
		btCancelar.setOnAction(e -> {
			executarComando("ABRIR RESERVA");
			control.limparCampos();
		});

		return grid;
	}

}
