package br.com.fiap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.model.ProdutoModel;
import br.com.fiap.repository.CategoriaRepository;
import br.com.fiap.repository.MarcaRepository;
import br.com.fiap.repository.ProdutoRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	private static final String PRODUTO_FOLDER = "produto/";

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private MarcaRepository marcaRepository;

	@GetMapping()
	public String findAll(Model model) {
		model.addAttribute("produtos", repository.findAll());
		return PRODUTO_FOLDER + "produtos";
	}
	
	@GetMapping("/form")
	public String open(@RequestParam String page, @RequestParam(required = false) Long id, @ModelAttribute("produtoModel") ProdutoModel produtoModel, Model model) {
		if ("produto-editar".equals(page)) {
			model.addAttribute("produtoModel", repository.findById(id));
		}
		
		model.addAttribute("categorias", categoriaRepository.findAll());
		model.addAttribute("marcas", marcaRepository.findAll());
		
		return PRODUTO_FOLDER + page;
	}
	
	@GetMapping("/{id}")
	public String findById(@PathVariable("id") long id, Model model) {
		
		model.addAttribute("produto", repository.findById(id));
		return PRODUTO_FOLDER + "produto-detalhe";
	}
	
//	@GetMapping("/new")
//	public String openSave(@ModelAttribute("produtoModel") ProdutoModel produtoModel) {
//		
//		return "produto-novo";
//	}
	

	@PostMapping()
	public String save(@Valid ProdutoModel produto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors())
			return PRODUTO_FOLDER + "produto-novo";
		
		repository.save(produto);
		redirectAttributes.addFlashAttribute("messages", "Produto cadastrado com sucesso!");
		return "redirect:/produto";
	}
	
//	@GetMapping("/update/{id}")
//	public String openUpdate(@PathVariable("id") long id, @ModelAttribute("produtoModel") ProdutoModel produtoModel, Model model) {
//		
//		model.addAttribute("produto", repository.findById(id));
//		
//		return "produto-editar";
//	}
	
	@PostMapping("/{id}")
	public String update(@PathVariable("id") long id, Model model,@Valid ProdutoModel produtoModel,BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		produtoModel.setId(id);
		repository.update(produtoModel);
		redirectAttributes.addFlashAttribute("messages", "Produto atualizado com sucesso!");
		model.addAttribute("produto", repository.findAll());
		return "redirect:/produto";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {
		repository.deleteById(id);
		redirectAttributes.addFlashAttribute("messages", "Produto excluido com sucesso!");
		model.addAttribute("produtos", repository.findAll());
		return "redirect:/produto";
	}
}
