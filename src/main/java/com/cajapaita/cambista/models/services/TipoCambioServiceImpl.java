package com.cajapaita.cambista.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cajapaita.cambista.models.dao.ITipoCambioDao;
import com.cajapaita.cambista.models.entity.TipoCambio;

@Service
public class TipoCambioServiceImpl implements ITipoCambioService{

	@Autowired 
	private ITipoCambioDao tipoCambioDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<TipoCambio> findAll() {
		
		return (List<TipoCambio>) tipoCambioDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<TipoCambio> findAll(Pageable pageable) {
	
		return  tipoCambioDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public TipoCambio findById(Long id) {

		return tipoCambioDao.findById(id).orElse(null);
	}

	@Override
	public TipoCambio save(TipoCambio tipocambio) {

		return tipoCambioDao.save(tipocambio);
	}




}
