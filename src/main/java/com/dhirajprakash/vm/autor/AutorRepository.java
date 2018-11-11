package com.dhirajprakash.vm.autor;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface AutorRepository extends CrudRepository<Autor, Integer> {

	@Query("select max(autor.id) from Autor autor")
	Integer findMaxId();
	
	@Query("select autor from Autor autor where autor.id = :id")
	Autor findOneById(@Param("id") Integer id);
	
	@Query("select autor from Autor autor where autor.nome like %:name%")
	List<Autor> findOneByName(@Param("name") String name);

}
