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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.model.LojaModel;
import br.com.fiap.repository.LojaRepository;

@Controller
@RequestMapping("/lojas")
public class LojaController {
	private static final String LOJA_FOLDER = "loja/";
	
	@Autowired
	public LojaRepository repository;
	
	@GetMapping
	public String findAll(Model model) {
		model.addAttribute("lojas", repository.findAll());
		return LOJA_FOLDER+"lojas";
	}
	
	@GetMapping("/form")
	public String open(@RequestParam("page") String page, @RequestParam(required = false) Long id,
			@ModelAttribute("lojaModel") LojaModel lojaModel, Model model) {
		if ("loja-editar".equals(page)) 
			model.addAttribute("lojaModel", repository.findById(id).get());

		return LOJA_FOLDER+page;
	}
	
	@GetMapping("/{id}")
	public String findById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("loja", repository.findById(id).get());
		return LOJA_FOLDER+"loja-detalhe";
	}
	
	@PostMapping
	public String save(@Valid LojaModel lojaModel, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) 
			return LOJA_FOLDER+"loja-novo";

		repository.save(lojaModel);
		redirectAttributes.addFlashAttribute("messages", "Loja cadastrada com sucesso!");
		return "redirect:/lojas";
	}

	@PutMapping("/{id}")
	public String update(@PathVariable("id") Long id, @Valid LojaModel lojaModel, Model model,
			RedirectAttributes redirectAttributes) {
		lojaModel.setIdLoja(id);
		repository.save(lojaModel);
		redirectAttributes.addFlashAttribute("messages", "Loja atualizada com sucesso!");
		model.addAttribute("lojas", repository.findAll());
		return "redirect:/lojas";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
		repository.deleteById(id);
		redirectAttributes.addFlashAttribute("messages", "Loja excluída com sucesso!");
		model.addAttribute("lojas", repository.findAll());
		return "redirect:/lojas";
	}
}
