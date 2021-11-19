package com.cajapaita.cambista.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cajapaita.cambista.models.entity.TipoCambio;
import com.cajapaita.cambista.models.services.ITipoCambioService;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TipoCambioRestController {

	@Autowired
	ITipoCambioService tipoCambioService;
	
	@GetMapping("/tipocambio")
	public List<TipoCambio> index(){
		return tipoCambioService.findAll();
	}
	
	@GetMapping("/tipocambio/page/{page}/{size}")
	public Page<TipoCambio> index(@PathVariable Integer page,@PathVariable Integer size){
		return tipoCambioService.findAll(PageRequest.of(page, size,Sort.by("id").descending()));
	}
	
	@GetMapping("/tipocambio/{id}")
	public ResponseEntity<?> getTipoCambio(@PathVariable Long  id) {
		
		TipoCambio tipoCambio = null;
		Map<String, Object> response = new HashMap<>();
		try {
			tipoCambio = tipoCambioService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la BD!");
			response.put("mensaje", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (tipoCambio == null) {
			response.put("mensaje", "El ID de tipo de cambio:".concat(id.toString()).concat(" no existe en la BD!"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TipoCambio>(tipoCambio, HttpStatus.OK);
		
	}
	
	@PostMapping("/tipocambio")
	public ResponseEntity<?> saveTipoCambio(@Valid @RequestBody TipoCambio tipocambio, BindingResult result) {
		
		TipoCambio tipoCambioNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			tipoCambioNew = tipoCambioService.save(tipocambio);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar el insert a la BD!");
			response.put("Error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El tipo de cambio ha sido creado con éxito para la fecha");
		response.put("tipoDeCambio", tipoCambioNew);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}

	@PutMapping("/tipocambio/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody TipoCambio tipocambio,@PathVariable Long id, BindingResult result) {

		TipoCambio tipoCambioActual = tipoCambioService.findById(id);
		TipoCambio tipoCambioUpdated = null;

		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (tipoCambioActual == null) {
			response.put("mensaje",
					"Error no se pudo encontrar el tipo de cambio ID:".concat(id.toString()).concat(" no existe en la BD!"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			tipoCambioActual.setMoneda(tipocambio.getMoneda());
			tipoCambioActual.setTccompra(tipocambio.getTccompra());
			tipoCambioActual.setTcventa(tipocambio.getTcventa());
			tipoCambioActual.setIdusuario(tipocambio.getIdusuario());
			tipoCambioActual.setEstado(tipocambio.getEstado());

			tipoCambioUpdated = tipoCambioService.save(tipoCambioActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar tipo de cambio en la BD!");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El tipo de cambio ha sido actualizado con éxito para la fecha");
		response.put("tipoDeCambio", tipoCambioUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
	
	
}
