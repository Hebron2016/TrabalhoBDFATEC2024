package boundary;

import java.util.Optional;

import control.CTRManterAutor;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class FRMCadAutor implements Boundary {

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

	private CTRManterAutor control = new CTRManterAutor();

	private TextField txtNome = new TextField();
	private Label lblNome = new Label("Nome:");

	private TextField txtBio = new TextField();
	private Label lblBio = new Label("Biografia:");

	private boolean gridErro = false;
	private Label lblErro = new Label("O campo Nome deve ser preenchido.");

	private Button btCancelar = new Button("Cancelar");
	private Button btCadastrar = new Button("Cadastrar");

	public void bind() {
		Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
		Bindings.bindBidirectional(txtBio.textProperty(), control.biografiaProperty());
	}

	@Override
	public Pane render() {

		control.atualizarLista();
		gridErro = false;
		GridPane grid = new GridPane();

		grid.setHgap(20);
		grid.setVgap(10);

		grid.add(lblNome, 0, 1);
		grid.add(txtNome, 1, 1);

		grid.add(lblBio, 0, 2);
		grid.add(txtBio, 1, 2);

		grid.add(btCadastrar, 1, 4);
		grid.add(btCancelar, 0, 4);
		bind();
		btCadastrar.setOnAction(e -> {

			if (control.pesquisarExistencia() && !txtNome.textProperty().get().isBlank()) {
				Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
				alerta.setTitle("Mensagem");
				alerta.setHeaderText(
						"Autor já existe, deseja atualizar seus dados, ou adicionar outro com o mesmo nome?");
				ButtonType buttonAtt = new ButtonType("Atualizar");
				ButtonType buttonAdd = new ButtonType("Adicionar");
				ButtonType buttonCancel = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
				alerta.getButtonTypes().setAll(buttonAdd, buttonAtt, buttonCancel);
				Optional<ButtonType> result = alerta.showAndWait();
				if (result.get() == buttonAtt) {
					control.atualizar();
					executarComando("ABRIR AUTOR");
					control.limparCampos();
					;
				} else if (result.get() == buttonAdd) {
					control.gravar();
					executarComando("ABRIR AUTOR");
					control.limparCampos();
				}
			} else if (!control.pesquisarExistencia() && !txtNome.textProperty().get().isBlank()) {
				control.gravar();
				executarComando("ABRIR AUTOR");
				control.limparCampos();
			} else if (txtNome.textProperty().get().isBlank()) {
				if (!gridErro) {
					grid.add(lblErro, 5, 4);
					gridErro = true;
				}
			}
		});

		btCancelar.setOnAction(e -> {
			executarComando("ABRIR AUTOR");
			control.limparCampos();
		});

		return grid;
	}

}
