package com.sonicdoc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sonicdoc.entities.Usuario;
import com.sonicdoc.repository.IUsuarioRepository;
import com.sonicdoc.service.IUsuarioService;

@Service
@Transactional(readOnly=true)
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Override
	@Transactional
	public Usuario save(Usuario t) throws Exception {
		return usuarioRepository.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) throws Exception {
		usuarioRepository.deleteById(id);
	}

	@Override
	public Optional<Usuario> findById(Integer id) throws Exception {
		return usuarioRepository.findById(id);
	}

	@Override
	public List<Usuario> findAll() throws Exception {
		return usuarioRepository.findAll();
	}
	
	@Override
	public Usuario findByDni(String dni) throws Exception {
		return usuarioRepository.findByDni(dni);
	}

}
