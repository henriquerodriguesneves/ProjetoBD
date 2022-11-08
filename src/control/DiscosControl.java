package control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import entity.DiscosEntity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import persistence.DiscosDAO;
import persistence.DiscosDAOImpl;

public class DiscosControl {
	
	private ObservableList<DiscosEntity> discos = FXCollections.observableArrayList();
	
	private StringProperty nomeArtista = new SimpleStringProperty("");
	private StringProperty nomeAlbum = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> anoLancamento = new SimpleObjectProperty<>();
	private StringProperty estadoConservacao = new SimpleStringProperty("");
//	private ObjectProperty<Double> preco = new SimpleObjectProperty<>();
	
	private DiscosDAO dao = new DiscosDAOImpl();
	
	private TableView<DiscosEntity> table = new TableView<>();
	
	public StringProperty nomeArtistaProperty() {
		return nomeArtista;
	}
	public StringProperty nomeAlbumProperty() {
		return nomeAlbum;
	}
	public ObjectProperty<LocalDate> anoLancamentoProperty(){
		return anoLancamento;
	}
	public StringProperty estadoConservacaoProperty() {
		return estadoConservacao;
	}
//	public ObjectProperty<Double> precoProperty() {
//		return preco;
//	}
	
	@SuppressWarnings("unchecked")
	public DiscosControl() {
		TableColumn<DiscosEntity, String> col1 = new TableColumn<>("Nome_Artista");
		col1.setCellValueFactory(new PropertyValueFactory<>("nomeArtista"));
		TableColumn<DiscosEntity, String> col2 = new TableColumn<>("Nome_Album");
		col2.setCellValueFactory(new PropertyValueFactory<>("nomeAlbum"));
		TableColumn<DiscosEntity, String> col3 = new TableColumn<>("Ano_Lancamento");
		col3.setCellValueFactory((itemData) -> {
			LocalDate dt = itemData.getValue().getAnoLancamento();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return new ReadOnlyStringWrapper(dt.format(formatter));
		});
		TableColumn<DiscosEntity, String> col4 = new TableColumn<>("Estado_Conservacao");
		col4.setCellValueFactory(new PropertyValueFactory<>("estadoConservacao"));
//		TableColumn<DiscosEntity, String> col5 = new TableColumn<>("Preco");
//		col5.setCellValueFactory(new PropertyValueFactory<>("preco"));

		
		table.getColumns().addAll(col1, col2, col3, col4);
		table.setItems(discos);
	}
	
	public void adicionar() {
		DiscosEntity f = new DiscosEntity();
		f.setNomeArtista(nomeArtista.get());
		f.setNomeAlbum(nomeAlbum.get());
		f.setAnoLancamento(anoLancamento.get());
		f.setEstadoConservacao(estadoConservacao.get());
//		f.setPreco(preco.get());
		discos.add(f);
		dao.inserir(f);
		
	}
	
	public void pesquisar() {
		List<DiscosEntity> lista = dao.consultar(nomeAlbum.get());
		discos.clear();
		discos.addAll(lista);
		
		}
	
	public TableView getTable() {
		return table;
	}
	
	public void limparCampos() {
		this.nomeArtistaProperty().setValue("");
		this.nomeAlbumProperty().setValue("");
		this.anoLancamentoProperty().setValue(null);
		this.estadoConservacaoProperty().setValue("");
//		this.precoProperty().setValue(null);
	}

}
