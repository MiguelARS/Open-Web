package pe.edu.upc.puffleshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.puffleshop.model.Tienda;
import pe.edu.upc.puffleshop.service.TiendaService;

@Controller
@RequestMapping("/tienda")
@SessionAttributes("tienda")
public class TiendaController {

	@Autowired
	private TiendaService tiendaService;
	
	@GetMapping("inicio")
	public String inicio()
	{
		return "tienda/inicio";
	}
	
	
	@GetMapping("nosotros")
	public String nosotros()
	{
		return "tienda/nosotros";
	}
	
	@GetMapping("listado")
	public String listar(Model model) {
		try {
			List<Tienda> tiendas = tiendaService.findAll();
			model.addAttribute("tienda", tiendas);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "tienda/listado";
	}
	
	@GetMapping("nuevo")
	public String nuevo(Model model) {
		Tienda tienda = new Tienda();
		model.addAttribute("tienda", tienda);
		return "/tienda/nuevo";
	}
	@PostMapping("grabar")
	public String grabar(@ModelAttribute("tienda") Tienda tienda, Model model, 
			SessionStatus status) {
		try {
			tiendaService.save(tienda);
			status.setComplete();
		} catch (Exception e) {
			model.addAttribute("error", "No se guardo la tienda");
		}		
		
		return "redirect:/tienda/listado";
	}
	
	@GetMapping("delete/{id}")
	public String remover( @PathVariable("id") Integer id, Model model  ) {
		try {
			Optional<Tienda> tienda = tiendaService.findById(id);
			// Se verifica que el Optional contiene el objeto
			if( tienda.isPresent() ) {
				tiendaService.deleteById(id);
			} else {
				model.addAttribute("error", "No existe el Id");				
			}
			
		} catch (Exception e) {
			model.addAttribute("error", "Error 500");
		}		
		return "redirect:/tienda/listado";
	}
}
