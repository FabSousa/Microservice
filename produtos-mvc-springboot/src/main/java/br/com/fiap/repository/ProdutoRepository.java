package br.com.fiap.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.com.fiap.model.ProdutoModel;

public class ProdutoRepository {
	
	private static Map<Long, ProdutoModel> produtos;
	private static ProdutoRepository instance;

	private ProdutoRepository() {
		produtos = new HashMap<Long, ProdutoModel>();
		
		produtos.put(1L, new ProdutoModel(1L, 100.00, "Nome Produto 1", "sku1", "desc prod 1", "carac prod 1"));
		produtos.put(2L, new ProdutoModel(2L, 200.00, "Nome Produto 2", "sku2", "desc prod 2", "carac prod 2"));
		produtos.put(3L, new ProdutoModel(3L, 300.00, "Nome Produto 3", "sku3", "desc prod 3", "carac prod 3"));
		produtos.put(4L, new ProdutoModel(4L, 400.00, "Nome Produto 4", "sku4", "desc prod 4", "carac prod 4"));
	}
	
	public static ProdutoRepository getInstance() {
		if(instance == null)
			instance = new ProdutoRepository();
		return instance;
	}
	
	public ArrayList<ProdutoModel> findAll(){
		return new ArrayList<ProdutoModel>(produtos.values());
	}
	
	public ProdutoModel findById(Long id) {
		return produtos.get(id);
	}
	
	public void save(ProdutoModel produto) {
		Long newId = (long) (produtos.size()+1);
		produto.setId(newId);
		produtos.put(newId, produto);
	}
	
	public void delete(Long id) {
		produtos.remove(id);
	}
	
	public void edit(ProdutoModel produto, Long id) {
		produtos.put(id, produto);
	}
}
