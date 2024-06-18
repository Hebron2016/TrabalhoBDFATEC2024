package control;


import daoConsulta.ConsultaDao;
import entity.Autor;
import entity.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CTRConsulta {
	private ConsultaDao dao = new ConsultaDao();
	
	private ObservableList<Autor> listaAutorSemLiv = FXCollections.observableArrayList();
	private ObservableList<Livro> listaLivroSemRes = FXCollections.observableArrayList();
	
	public void autorSemLivro() {
		listaAutorSemLiv.clear();
		listaAutorSemLiv.addAll(dao.autorSemLivro());
	}
	
	public void livroSemRes() {
		listaLivroSemRes.clear();
		listaLivroSemRes.addAll(dao.livroSemRes());
	}
	
	public ObservableList<Autor> getListaAutorSemLiv(){
		return listaAutorSemLiv;
	}
	
	public ObservableList<Livro> getListaLivroSemRes(){
		return listaLivroSemRes;
	}
}
