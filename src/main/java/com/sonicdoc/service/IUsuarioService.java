package com.sonicdoc.service;

import com.sonicdoc.entities.Usuario;

public interface IUsuarioService extends ICrudService<Usuario>{
	public Usuario findByDni(String dni) throws Exception;
}
