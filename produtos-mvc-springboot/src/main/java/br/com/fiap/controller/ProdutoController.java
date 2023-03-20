package br.com.fiap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.model.ProdutoModel;
import br.com.fiap.repository.ProdutoRepository;

@Controller
public class ProdutoController {
	
	private ProdutoRepository repository = ProdutoRepository.getInstance();
	
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
	public String save(ProdutoModel produto, RedirectAttributes redirectAttributes) {
		repository.save(produto);
		redirectAttributes.addFlashAttribute("message", "produto cadastrado com sucesso");
		return "redirect:/produtos";
	}
	
	@GetMapping(value="/produto/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
		repository.delete(id);
		model.addAttribute("produtos", repository.findAll());
		redirectAttributes.addFlashAttribute("message", "produto deletado com sucesso");
		return "redirect:/produtos";
	}
	
	@RequestMapping(value="/produto/edit/{id}", method=RequestMethod.GET)
	public String openEdit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("produto", repository.findById(id));
		return "produto-edit";
	}
	
	@PostMapping("/produto/edit/{id}")
	public String edit(@PathVariable("id") Long id, ProdutoModel produto, RedirectAttributes redirectAttributes) {
		repository.edit(produto, id);
		redirectAttributes.addFlashAttribute("message", "produto editado com sucesso");
		return "redirect:/produtos";
	}
}
