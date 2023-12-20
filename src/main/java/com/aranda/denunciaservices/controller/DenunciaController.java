package com.aranda.denunciaservices.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aranda.denunciaservices.converter.DenunciaConverter;
import com.aranda.denunciaservices.dto.DenunciaDTO;
import com.aranda.denunciaservices.entity.Denuncia;
import com.aranda.denunciaservices.service.DenunciaService;
import com.aranda.denunciaservices.utils.WrapperResponse;



@RestController
@RequestMapping("/v1/denuncias")
public class DenunciaController {
	@Autowired
	private DenunciaService service;
	
	@Autowired
	private DenunciaConverter converter;
	
	@GetMapping()
	public ResponseEntity<List<DenunciaDTO>> finAll(
			@RequestParam(value = "nombre", required = false, defaultValue = "") String nombre,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
			){
		Pageable page= PageRequest.of(pageNumber, pageSize);
		List<Denuncia> denuncias;
		if(nombre==null) {
			denuncias=service.findAll(page);		
		}else {
			denuncias=service.findByNombre(nombre, page);	
		}
		
		List<DenunciaDTO> denunciasDTO=converter.fromEntity(denuncias);
		return new WrapperResponse(true,"success",denunciasDTO).createResponse(HttpStatus.OK);
     }
	
	@GetMapping(value="/{id}")
	public ResponseEntity<WrapperResponse<DenunciaDTO>> findById(@PathVariable("id") int id){
		Denuncia denuncia = service.findById(id);
		
		DenunciaDTO denunciaDTO=converter.fromEntity(denuncia);
		return new WrapperResponse<DenunciaDTO>(true,"success",denunciaDTO).createResponse(HttpStatus.OK);
	}
	@PostMapping()
	public ResponseEntity<DenunciaDTO> create(@RequestBody DenunciaDTO denunciaDTO){
		Denuncia registro=service.save(converter.fromDTO(denunciaDTO));
		DenunciaDTO registroDTO=converter.fromEntity(registro);
		return new WrapperResponse(true,"success",registroDTO).createResponse(HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<DenunciaDTO> update(@PathVariable("id") int id,@RequestBody DenunciaDTO denunciaDTO){
		Denuncia registro = service.update(converter.fromDTO(denunciaDTO));
		
		DenunciaDTO registroDTO=converter.fromEntity(registro);
		return new WrapperResponse(true,"success",registroDTO).createResponse(HttpStatus.OK);
	}
	@DeleteMapping(value="/{id}")
	public ResponseEntity<DenunciaDTO> delete(@PathVariable("id") int id){
		service.delete(id);
		return new WrapperResponse(true,"success",null).createResponse(HttpStatus.OK);
	}
}
