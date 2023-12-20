package com.aranda.denunciaservices.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aranda.denunciaservices.entity.Denuncia;
import com.aranda.denunciaservices.exceptions.GeneralServiceException;
import com.aranda.denunciaservices.exceptions.NoDataFoundException;
import com.aranda.denunciaservices.exceptions.ValidateServiceException;
import com.aranda.denunciaservices.repository.DenunciaRepository;
import com.aranda.denunciaservices.service.DenunciaService;
import com.aranda.denunciaservices.validator.DenunciaValidator;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class DenunciaServiceImpl implements DenunciaService{

	@Autowired
	private DenunciaRepository repository;
	@Override
	@Transactional(readOnly=true)
	public List<Denuncia> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		}catch (NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Denuncia> findByNombre(String nombre, Pageable page) {
		try {
			return repository.findByNombreContaining(nombre, page);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Denuncia findById(int id) {
		try {
			Denuncia registro= repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID."));
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Denuncia save(Denuncia denuncia) {
		try {
			DenunciaValidator.save(denuncia);
			if (repository.findByNombre(denuncia.getNombre())!=null) {
				throw new ValidateServiceException("ya existe un registro con el nombre:"+denuncia.getNombre());
				
			}
			denuncia.setActivo(true);
			Denuncia registro=repository.save(denuncia);
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Denuncia update(Denuncia denuncia) {
		try {
			DenunciaValidator.save(denuncia);
			Denuncia registro = repository.findById(denuncia.getId()).orElseThrow(()-> new NoDataFoundException("No existe un registro con ese ID"));
			Denuncia registroD = repository.findByNombre(denuncia.getNombre());
			if (registroD!=null && registroD.getId()!= denuncia.getId()) {
				throw new ValidateServiceException("ya existe un registro con el nombre:"+denuncia.getNombre());
				
			}
			registro.setNombre(denuncia.getNombre());
			repository.save(registro);
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			Denuncia registro=repository.findById(id).orElseThrow(()-> new NoDataFoundException("No existe un registro con ese ID"));
			repository.delete(registro);
			
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
		
	}

}

