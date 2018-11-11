package com.dhirajprakash.vm.autor;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RelatoriosRepository extends CrudRepository<RelatoriosEntity,Integer> {
	
	@Query("select re from RelatoriosEntity re where re.user = :user")
	List<RelatoriosEntity> findByName(@Param("user") String user);
	
//	@Query("SELECT rl.id,rl.numero_de_os,rl.user,udos.full_name FROM relatorios2.relatorios as rl \n" + 
//			"LEFT JOIN relatorios2.user_details_os udos on udos.username=rl.user where rl.user= :user")
//	List<Relatorios> findByName2(@Param("user") String user);

}
