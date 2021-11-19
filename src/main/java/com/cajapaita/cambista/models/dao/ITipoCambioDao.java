package com.cajapaita.cambista.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cajapaita.cambista.models.entity.TipoCambio;

public interface ITipoCambioDao extends JpaRepository<TipoCambio, Long>{

}
