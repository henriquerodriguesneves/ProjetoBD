package boundary;

import java.time.format.DateTimeFormatter;

import control.DiscosControl;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class DiscosBoundary extends Application{
	private TextField txtNomeArtista = new TextField();
	private TextField txtNomeAlbum = new TextField();
	private TextField txtAnoLancamento = new TextField();
	private TextField txtEstadoConservacao = new TextField();
//	private TextField txtPreco = new TextField();
	private Button btnAdd = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnSair = new Button("Sair");
	private Button btnLimpar = new Button("Limpar");
	private DiscosControl control = new DiscosControl();
	
	@Override
	public void start(Stage stage) throws Exception {
		BorderPane principal = new BorderPane();
		GridPane grid = new GridPane();
		principal.setCenter(grid);
		
		this.txtNomeArtista.setPrefWidth(250);
		grid.add(new Label("Nome Artista: "), 0, 0);
		grid.add(txtNomeArtista, 1, 0);
		grid.add(new Label("Nome Album: "), 0, 1);
		grid.add(txtNomeAlbum, 1, 1);
		grid.add(new Label("Data de Lancamento: "), 0, 2);
		grid.add(txtAnoLancamento, 1, 2);
		grid.add(new Label("Estado de conservacao: "), 0, 3);
		grid.add(txtEstadoConservacao, 1, 3);
//		grid.add(new Label("Preco: "), 0, 4);
//		grid.add(txtPreco, 1, 4);
		grid.add(btnAdd, 0, 5);
		grid.add(btnPesquisar, 1, 5);
		grid.add(btnLimpar, 2, 5);
		grid.add(btnSair, 3, 5);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateStringConverter ldc = new LocalDateStringConverter(formatter, null);
		
		Bindings.bindBidirectional(control.nomeArtistaProperty(), txtNomeArtista.textProperty());
		Bindings.bindBidirectional(control.nomeAlbumProperty(), txtNomeAlbum.textProperty());
		Bindings.bindBidirectional(
				txtAnoLancamento.textProperty(), control.anoLancamentoProperty(), ldc);
		Bindings.bindBidirectional(control.estadoConservacaoProperty(), txtEstadoConservacao.textProperty());
//		Bindings.unbindBidirectional(control.precoProperty(), txtPreco.textProperty());
		
		principal.setBottom(control.getTable());
		
		btnAdd.setOnAction(e -> control.adicionar());
		btnLimpar.setOnAction( e -> control.limparCampos());
		btnPesquisar.setOnAction(e -> control.pesquisar());
		btnSair.setOnAction(e -> {
			System.exit(0);
		});
		
		Scene scn = new Scene(principal, 600, 400);
		stage.setScene(scn);
		stage.setTitle("Cadastro de Discos");
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(DiscosBoundary.class, args);
	}

}
