package com.aranda.denunciaservices.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aranda.denunciaservices.entity.Denuncia;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Integer>{
	List<Denuncia> findByNombreContaining(String nombre, Pageable page);
	Denuncia findByNombre(String object);
	

}
