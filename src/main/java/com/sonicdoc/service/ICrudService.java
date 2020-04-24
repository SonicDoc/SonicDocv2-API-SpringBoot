package com.sonicdoc.service;

import java.util.List;
import java.util.Optional;

public interface ICrudService<T> {
	T save (T t)throws Exception;
	void deleteById(Integer id)throws Exception;
	Optional<T> findById(Integer id)throws Exception;
	List<T> findAll()throws Exception;
}
