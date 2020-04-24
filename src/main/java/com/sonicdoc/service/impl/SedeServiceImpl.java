package com.sonicdoc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sonicdoc.entities.Sede;
import com.sonicdoc.repository.ISedeRepository;
import com.sonicdoc.service.ISedeService;

@Service
@Transactional(readOnly=true)
public class SedeServiceImpl implements ISedeService{

	@Autowired
	private ISedeRepository sedeRepository;
	
	@Override
	@Transactional
	public Sede save(Sede t) throws Exception {
		return sedeRepository.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) throws Exception {
		sedeRepository.deleteById(id);
	}

	@Override
	public Optional<Sede> findById(Integer id) throws Exception {
		return sedeRepository.findById(id);
	}

	@Override
	public List<Sede> findAll() throws Exception {
		return sedeRepository.findAll();
	}

}
