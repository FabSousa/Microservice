package br.com.fiap.controller;

import javax.validation.Valid;

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
import br.com.fiap.repository.ProdutoRepository;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoRepository repository;
	
	private static final String PRODUTO_FOLDER = "produto/";

	@GetMapping
	public String findAll(Model model) {
		model.addAttribute("produtos", repository.findAll());
		return PRODUTO_FOLDER+"produtos";
	}

	@GetMapping("/form")
	public String open(@RequestParam("page") String page, @RequestParam(required = false) Long id,
			@ModelAttribute("produtoModel") ProdutoModel produtoModel, Model model) {

		if ("produto-editar".equals(page)) {
			model.addAttribute("produtoModel", repository.findById(id));
		}

		return PRODUTO_FOLDER+page;
	}

	@GetMapping("/{id}")
	public String findById(@PathVariable("id") long id, Model model) {
		model.addAttribute("produto", repository.findById(id));
		return PRODUTO_FOLDER+"produto-detalhe";
	}

	@PostMapping
	public String save(@Valid ProdutoModel produto, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return PRODUTO_FOLDER+"produto-novo";
		}

		repository.save(produto);
		redirectAttributes.addFlashAttribute("messages", "Produto cadastrado com sucesso!");
		return "redirect:/produtos";
	}
	
	@PostMapping("/{id}")
	public String update(@PathVariable("id") long id, Model model, @Valid ProdutoModel produtoModel,
			RedirectAttributes redirectAttributes) {

		produtoModel.setId(id);
		repository.update(produtoModel);
		redirectAttributes.addFlashAttribute("messages", "Produto atualizado com sucesso!");
		model.addAttribute("produtos", repository.findAll());
		return "redirect:/produtos";

	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {
		repository.deleteById(id);
		redirectAttributes.addFlashAttribute("messages", "Produto excluído com sucesso!");
		model.addAttribute("produtos", repository.findAll());
		return "redirect:/produtos";
	}

}
