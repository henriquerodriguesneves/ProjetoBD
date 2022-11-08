package persistence;

import java.util.List;

import entity.DiscosEntity;

public interface DiscosDAO {
	
	void inserir(DiscosEntity f);
	List<DiscosEntity> consultar(String album);

}
