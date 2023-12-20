package com.aranda.denunciaservices.validator;

import com.aranda.denunciaservices.exceptions.ValidateServiceException;
import com.aranda.denunciaservices.entity.Denuncia;

public class DenunciaValidator {
	public static void  save(Denuncia denuncia) {
		if(denuncia.getNombre()==null || denuncia.getNombre().isEmpty()) {
			throw new RuntimeException("El nombre es requerido");
		}
		if(denuncia.getNombre().length()>100) {
			throw new ValidateServiceException("El nombre es muy largo");
		}
		
		
    }

}
