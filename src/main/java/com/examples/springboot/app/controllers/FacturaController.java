package com.examples.springboot.app.controllers;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.examples.springboot.app.models.entity.Cliente;
import com.examples.springboot.app.models.entity.Factura;
import com.examples.springboot.app.models.entity.Producto;
import com.examples.springboot.app.models.entity.itemFactura;
import com.examples.springboot.app.models.service.IClienteService;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

	@Autowired
	private IClienteService clienteService;
	
	private final Logger log =LoggerFactory.getLogger(getClass());
	
	
	@GetMapping("/form/{clienteId}")
	public String crear(@PathVariable(value="clienteId") Long clienteId, 
			Map<String, Object> model, 
			RedirectAttributes flash) {
		
		Cliente cliente = clienteService.finOne(clienteId);
		
		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}
		
		Factura factura =new Factura ();
		factura.setCliente(cliente);
		
		model.put("factura", factura);
		model.put("titulo", "Crear Factura");
		
		
		
		return "factura/form";
		
	}
	@GetMapping(value="/cargar-productos/{term}",produces= {"application/json"})
	public @ResponseBody List<Producto> cargarProductos(@PathVariable String term){
		return clienteService.finByNombre(term);
	}
	
	@PostMapping("/form")
	public String guardar(Factura factura,
			@RequestParam(name="item_id[]",required=false) Long[] itemId,
			@RequestParam(name="cantidad[]", required=false) Integer[] cantidad,
			RedirectAttributes flash,
			SessionStatus status) {
		
		for(int i = 0; i < itemId.length; i++) {
			Producto producto = clienteService.finProductoById(itemId[i]);
			
			itemFactura linea = new itemFactura();
			linea.setCantidad(cantidad[i]);
			linea.setProducto(producto);
			factura.addItemFactura(linea);
			
			log.info("ID: " + itemId[i].toString() + ", cantidad: " + cantidad[i].toString());
			
			
		}
		
		clienteService.saveFactura(factura);
		status.setComplete();
		
		flash.addFlashAttribute("success", "Factura creada con exito! ");
		
		return "redirect:/ver/" + factura.getCliente().getId();
		
	}
}
