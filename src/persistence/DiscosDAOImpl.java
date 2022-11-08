package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.DiscosEntity;

public class DiscosDAOImpl implements DiscosDAO {
	private final static String JDBC_CLASS = "org.mariadb.jdbc.Driver";
	private final static String JDBC_URL = "jdbc:mariadb://localhost:3306/projetoBD?allowMultiQueries=true";
	private final static String JDBC_USER = "root";
	private final static String JDBC_PASS = "";
	private Connection con;
	
	public DiscosDAOImpl() {
		try {
			Class.forName(JDBC_CLASS);
			con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
			System.out.println("Biblioteca importada");
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	@Override
	public void inserir(DiscosEntity f) {
		 String sql = "INSERT INTO discos (artista, album, ano_lancamento, estado_conservacao) ";
		 sql += " VALUES (?, ?, ?, ?) ";
		 try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, f.getNomeArtista());
			stmt.setString(2, f.getNomeAlbum());
			stmt.setDate(3, java.sql.Date.valueOf(f.getAnoLancamento()));
			stmt.setString(4, f.getEstadoConservacao());
//			stmt.setDouble(5, f.getPreco());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			 ex.printStackTrace();
		}
		
	}

	@Override
	public List<DiscosEntity> consultar(String album) {
		 List<DiscosEntity> lista = new ArrayList<>();
		 String sql = "SELECT * FROM discos WHERE album LIKE '%" + album +"%'";
		 try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				DiscosEntity discos = new DiscosEntity();
				discos.setNomeArtista(rs.getString("artista"));
				discos.setNomeAlbum(rs.getString("album"));
				discos.setAnoLancamento(rs.getDate("ano_lancamento").toLocalDate());
				discos.setEstadoConservacao(rs.getString("estado_conservacao"));
//				discos.setPreco(rs.getDouble("preco"));
				lista.add(discos);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lista;
	}

}
