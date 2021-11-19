package com.cajapaita.cambista.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cajapaita.cambista.models.entity.TipoCambio;

public interface ITipoCambioService {

	public List<TipoCambio> findAll();
	
	public Page<TipoCambio> findAll(Pageable pageable);
	
	public TipoCambio findById(Long id);
	
	public TipoCambio save(TipoCambio tipocambio);

	
}
	