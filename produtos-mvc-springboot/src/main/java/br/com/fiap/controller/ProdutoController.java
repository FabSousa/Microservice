package br.com.fiap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.model.ProdutoModel;
import br.com.fiap.repository.ProdutoRepository;
import jakarta.validation.Valid;

@Controller
public class ProdutoController {

	ProdutoRepository repository = ProdutoRepository.getInstance();

	@RequestMapping("/produtos")
	public String findAll(Model model) {

		model.addAttribute("produtos", repository.findAll());

		return "produtos";
	}
	
	@RequestMapping(value = "/produto/{id}", method = RequestMethod.GET)
	public String findById(@PathVariable("id") long id, Model model) {
		
		model.addAttribute("produto", repository.findById(id));
		
		return "produto-detalhe";
	}
	
	@RequestMapping(value = "/produto/new", method = RequestMethod.GET)
	public String openSave(@ModelAttribute("produtoModel") ProdutoModel produtoModel) {
		
		return "produto-novo";
	}
	

	@PostMapping("/produto/new")
	public String save(@Valid ProdutoModel produto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors())
			return "produto-novo";
		
		repository.save(produto);
		
		redirectAttributes.addFlashAttribute("messages", "Produto cadastrado com sucesso!");
		
		return "redirect:/produtos";
	}
	
	@GetMapping("/produto/update/{id}")
	public String openUpdate(@PathVariable("id") long id, @ModelAttribute("produtoModel") ProdutoModel produtoModel, Model model) {
		
		model.addAttribute("produto", repository.findById(id));
		
		return "produto-editar";
	}
	
	@PostMapping("/produto/update")
	public String update(Model model, @Valid ProdutoModel produtoModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors())
			return "produto-editar";
		
		repository.update(produtoModel);
		
		redirectAttributes.addFlashAttribute("messages", "Produto atualizado com sucesso!");
		
		model.addAttribute("produtos", repository.findAll());
		
		return "redirect:/produtos";
	}

	@DeleteMapping(value = "/produto/delete/{id}")
	public String delete(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {
	
		repository.deleteById(id);
		
		redirectAttributes.addFlashAttribute("messages", "Produto excluido com sucesso!");
		
		model.addAttribute("produtos", repository.findAll());
		
		return "redirect:/produtos";
	}
}
