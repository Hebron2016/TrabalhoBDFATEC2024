package boundary;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.Map;

import boundaryConsulta.FRMConsulta;

import java.util.HashMap;


public class TelaInicial extends Application implements Executor{
	
	private Boundary telaAutor = new FRMManterAutor();
	private Boundary telaAluno = new FRMManterAluno();
	private Boundary telaLivro = new FRMManterLivro();
	private Boundary telaProfessor = new FRMManterProfessor();
	private Boundary telaReserva = new FRMManterReserva();
	private Boundary cadAutor = new FRMCadAutor();
	private Boundary cadAluno = new FRMCadAluno();
	private Boundary cadLivro = new FRMCadLivro();
	private Boundary cadProfessor = new FRMCadProfessor();
	private Boundary cadReserva = new FRMCadReserva();
	private Boundary creditos = new Creditos();
	private Boundary consulta = new FRMConsulta();
	
	private MenuBar menu = new MenuBar();
    private BorderPane bp = new BorderPane();
	private Map<String, Boundary> telas = new HashMap<>();
	
	public TelaInicial() { 
		telaAutor.setExecutor(this);
		telaAluno.setExecutor(this);
		telaLivro.setExecutor(this);
		telaProfessor.setExecutor(this);
		telaReserva.setExecutor(this);
		cadAutor.setExecutor(this);
		cadAluno.setExecutor(this);
		cadLivro.setExecutor(this);
		cadProfessor.setExecutor(this);
		cadReserva.setExecutor(this);
		creditos.setExecutor(this);
		consulta.setExecutor(this);
        telas.put("AUTOR", telaAutor);
        telas.put("ALUNO", telaAluno);
        telas.put("LIVRO", telaLivro);
        telas.put("PROFESSOR", telaProfessor);
        telas.put("RESERVA", telaReserva);
        telas.put("CADAUTOR", cadAutor);
        telas.put("CADALUNO", cadAluno);
        telas.put("CADLIVRO", cadLivro);
        telas.put("CADPROFESSOR", cadProfessor);
        telas.put("CADRESERVA", cadReserva);
        telas.put("CREDITOS", creditos);
        telas.put("CONSULTA", consulta);
    }
	
	@Override
	public void start(Stage stage) throws Exception {
		
		//Configurando a pane principal
		bp.setTop(menu);
		bp.setCenter(telaReserva.render());
		
		//Configurando o menu
		Menu menuGerenciar = new Menu("Gerenciar");
		Menu menuOpcao = new Menu("Opções");
		Menu menuAjuda = new Menu("Ajuda");
		Menu menuConsulta = new Menu("Consulta");
		
		MenuItem creditos = new MenuItem("Créditos");
		MenuItem sair = new MenuItem("Sair");
		MenuItem aluno = new MenuItem("Alunos");
		MenuItem autor = new MenuItem("Autores");
		MenuItem livro = new MenuItem("Livros");
		MenuItem professor = new MenuItem("Professores");
		MenuItem reserva = new MenuItem("Reservas");
		MenuItem consultar = new MenuItem("Consultas");
		
		menuAjuda.getItems().add(creditos);
		menuConsulta.getItems().add(consultar);
		menuGerenciar.getItems().addAll(aluno, autor, livro, professor, reserva);
		menuOpcao.getItems().add(sair);
		menu.getMenus().addAll(menuOpcao,menuGerenciar, menuConsulta, menuAjuda);
		
		sair.setOnAction(e -> executar("SAIR"));
		
		consultar.setOnAction(e -> executar("ABRIR CONSULTA"));
		creditos.setOnAction(e -> executar("ABRIR CREDITOS"));
		reserva.setOnAction(e -> executar("ABRIR RESERVA"));
		aluno.setOnAction(e -> executar("ABRIR ALUNO"));
		autor.setOnAction(e -> executar("ABRIR AUTOR"));
		livro.setOnAction(e -> executar("ABRIR LIVRO"));
		professor.setOnAction(e -> executar("ABRIR PROFESSOR"));
		
        Scene scn = new Scene(bp, 800, 600);
        
        stage.setTitle("Gerenciamento de biblioteca");
        stage.setScene(scn);
		stage.show();
	}
	
	@Override
	public void executar(String comando) {
		System.out.println("Executando o comando: " + comando);
		String[] sentenca = comando.split(" ");
		if(sentenca[0].equals("SAIR")) {
			Platform.exit();
			System.exit(0);
		}
		else if(sentenca[0].equals("ABRIR")) {
			Boundary tela = telas.get(sentenca[1]);
			bp.setCenter(tela.render());
		}
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
