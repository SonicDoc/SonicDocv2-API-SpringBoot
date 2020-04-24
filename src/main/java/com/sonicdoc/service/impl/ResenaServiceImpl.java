package com.sonicdoc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sonicdoc.entities.Resena;
import com.sonicdoc.repository.IResenaRepository;
import com.sonicdoc.service.IResenaService;

@Service
@Transactional(readOnly=true)
public class ResenaServiceImpl implements IResenaService {

	@Autowired
	private IResenaRepository resenaRepository;

	@Override
	@Transactional
	public Resena save(Resena t) throws Exception {
		return  resenaRepository.save(t) ;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		resenaRepository.deleteById(id);;
	}

	@Override
	public Optional<Resena> findById(Integer id) throws Exception {
		return resenaRepository.findById(id);
	}

	@Override
	public List<Resena> findAll() throws Exception {
		return resenaRepository.findAll();
	}

	@Override
	public List<Resena> findByReservaId(int reservaId) throws Exception {
		return resenaRepository.findByReservaId(reservaId);
	}

}
