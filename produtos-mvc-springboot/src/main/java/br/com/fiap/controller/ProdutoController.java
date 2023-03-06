package br.com.fiap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.fiap.model.ProdutoModel;
import br.com.fiap.repository.ProdutoRepository;

@Controller
public class ProdutoController {
	
	ProdutoRepository repository = new ProdutoRepository();
	
	@RequestMapping("/produtos")
	public String findAll(Model model) {
		model.addAttribute("produtos", repository.findAll());
		return "produtos";
	}
	
	@RequestMapping(value="/produto/{id}", method=RequestMethod.GET)
	public String findById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("produto", repository.findById(id));
		return "produto-detalhe";
	}

	@RequestMapping(value="/produto/new", method=RequestMethod.GET)
	public String openSave() {
		return "produto-novo";
	}
	
	@PostMapping("/produto/new")
	public String save(ProdutoModel produto) {
		repository.save(produto);
		return "produto-novo-sucesso";
	}
	
	@GetMapping(value="/produto/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		repository.delete(id);
		model.addAttribute("produtos", repository.findAll());
		return "redirect:/produtos";
	}
	
	@RequestMapping(value="/produto/edit/{id}", method=RequestMethod.GET)
	public String openEdit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("produto", repository.findById(id));
		return "produto-edit";
	}
	
	@PostMapping("/produto/edit/{id}")
	public String edit(@PathVariable("id") Long id, ProdutoModel produto) {
		repository.edit(produto, id);
		return "produto-edit-sucesso";
	}
}
