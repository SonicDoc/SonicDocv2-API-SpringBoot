package com.sonicdoc.service;

import java.util.List;

import com.sonicdoc.entities.Resena;

public interface IResenaService extends ICrudService<Resena> {
	public List<Resena> findByReservaId(int reservaId) throws Exception;
}
