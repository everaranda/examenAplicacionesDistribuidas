package com.aranda.denunciaservices.converter;

import org.springframework.stereotype.Component;

import com.aranda.denunciaservices.dto.DenunciaDTO;
import com.aranda.denunciaservices.entity.Denuncia;


@Component
public class DenunciaConverter extends AbstractConverter<Denuncia, DenunciaDTO>{

	@Override
	public DenunciaDTO fromEntity(Denuncia entity) {
		if (entity==null)return null;
		return DenunciaDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.build();
				
	}

	@Override
	public Denuncia fromDTO(DenunciaDTO dto) {
		if (dto==null)return null;
		return Denuncia.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.build();
	}
	
	

}
