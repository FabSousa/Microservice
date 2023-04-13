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

import br.com.fiap.model.MarcaModel;
import br.com.fiap.repository.MarcaRepository;

@Controller
@RequestMapping("/marcas")
public class MarcaController {

	private static final String MARCA_FOLDER = "marca/";

	@Autowired
	public MarcaRepository repository;

	@GetMapping
	public String findAll(Model model) {
		model.addAttribute("marcas", repository.findAll());
		return MARCA_FOLDER+"marcas";
	}

	@GetMapping("/form")
	public String open(@RequestParam("page") String page, @RequestParam(required = false) Long id,
			@ModelAttribute("marcaModel") MarcaModel marcaModel, Model model) {
		if ("marca-editar".equals(page)) 
			model.addAttribute("marcaModel", repository.findById(id));

		return MARCA_FOLDER+page;
	}

	@GetMapping("/{id}")
	public String findById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("marca", repository.findById(id));
		return MARCA_FOLDER+"marca-detalhe";
	}

	@PostMapping
	public String save(@Valid MarcaModel marcaModel, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) 
			return MARCA_FOLDER+"marca-novo";

		repository.save(marcaModel);
		redirectAttributes.addFlashAttribute("messages", "Marca cadastrada com sucesso!");
		return "redirect:/marcas";
	}

	@PutMapping("/{id}")
	public String update(@PathVariable("id") Long id, @Valid MarcaModel marcaModel, Model model,
			RedirectAttributes redirectAttributes) {
		marcaModel.setIdMarca(id);
		repository.update(marcaModel);
		redirectAttributes.addFlashAttribute("messages", "Marca atualizada com sucesso!");
		model.addAttribute("marcas", repository.findAll());
		return "redirect:/marcas";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
		repository.deleteById(id);
		redirectAttributes.addFlashAttribute("messages", "Marca excluída com sucesso!");
		model.addAttribute("marcas", repository.findAll());
		return "redirect:/marcas";
	}
}
