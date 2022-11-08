package entity;

import java.time.LocalDate;

public class DiscosEntity {
	
	private String nomeArtista;
	private String nomeAlbum;
	private LocalDate anoLancamento;
	private String estadoConservacao;
	//private double preco;
	public String getNomeArtista() {
		return nomeArtista;
	}
	public void setNomeArtista(String nomeArtista) {
		this.nomeArtista = nomeArtista;
	}
	public String getNomeAlbum() {
		return nomeAlbum;
	}
	public void setNomeAlbum(String nomeAlbum) {
		this.nomeAlbum = nomeAlbum;
	}
	public LocalDate getAnoLancamento() {
		return anoLancamento;
	}
	public void setAnoLancamento(LocalDate anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	public String getEstadoConservacao() {
		return estadoConservacao;
	}
	public void setEstadoConservacao(String estadoConservacao) {
		this.estadoConservacao = estadoConservacao;
	}
//	public double getPreco() {
//		return preco;
//	}
//	public void setPreco(double preco) {
//		this.preco = preco;
//	}
//	
	

}
