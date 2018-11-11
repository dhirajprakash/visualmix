package com.dhirajprakash.vm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.dhirajprakash.vm.autor.AutorRepository;
import com.dhirajprakash.vm.autor.Relatorios;
import com.dhirajprakash.vm.autor.RelatoriosEntity;
import com.dhirajprakash.vm.autor.RelatoriosRepository;
import com.dhirajprakash.vm.autor.VisualMixResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseTests {

	@Autowired
	AutorRepository am;

	@Autowired
	RelatoriosRepository rm;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	VisualMixResponse vm;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	EntityManager em;

	@Autowired
	DataSource ds;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	private Logger logger = LoggerFactory.getLogger(DatabaseTests.class);
	
	@Test
	public void jdbcExample() {

		jdbcTemplate.query(
				"SELECT rl.id,rl.numero_de_os as no,rl.user,udos.full_name FROM relatorios2.relatorios as rl \n"
						+ "LEFT JOIN relatorios2.user_details_os udos on udos.username=rl.user where rl.user = ?",
				new Object[] { "patricia" }, (rs, rowNum) -> new Relatorios(rs.getInt("id"), rs.getString("no"),
						rs.getString("user"), rs.getString("full_name")))
				.forEach(relatorios -> {

					logger.info(relatorios.toString());

				});
	}

	public void otherExample() {

		logger.info(rm.findAll().toString());

	}

	public void myBatisExample() {

		logger.info(rm.findAll().toString());

	}

	
	public void datasourceExample() {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<Relatorios> al = new ArrayList();
		try {
			conn = ds.getConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st = conn.prepareStatement("SELECT id, numero_de_os, user FROM relatorios WHERE user = ?");
			st.setString(1, "admin2");
			rs = st.executeQuery();
			while (rs.next()) {

				Relatorios rl = new Relatorios(0, "", "");
				rl.setId(rs.getInt("id"));
				rl.setNumeroDeOs(rs.getString("numero_de_os"));
				rl.setUser(rs.getString("user"));
				al.add(rl);

			}

			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		vm.setDescricao("DATASOURCE EXAMPLE");
		vm.setLogicJson(al);
		try {
			logger.info(mapper.writeValueAsString(vm));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void entityManagerFactoryExample() {

		/*
		 * Session session =
		 * entityManagerFactory.unwrap(SessionFactory.class).openSession();
		 * CriteriaBuilder builder = session.getCriteriaBuilder(); CriteriaQuery
		 * criteria = builder.createQuery(RelatoriosEntity.class); Root contactRoot =
		 * criteria.from(RelatoriosEntity.class); criteria.select(contactRoot);
		 * session.createQuery(criteria).getResultList();
		 */

	}

	public void entityManagerExample() {

		List<RelatoriosEntity> list = (List<RelatoriosEntity>) em
				.createNativeQuery("SELECT id, numero_de_os, user FROM relatorios WHERE user = :user ",
						RelatoriosEntity.class)
				.setParameter("user", "admin").getResultList();
		vm.setDescricao("ENTITY EXAMPLE");
		vm.setLogicJson(list);
		try {
			logger.info(mapper.writeValueAsString(vm));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void repositoryExample() {

		vm.setDescricao("REPOSITORY FIND BY NAME");
		vm.setLogicJson(rm.findByName("admin2"));
		try {
			logger.info(mapper.writeValueAsString(vm));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RelatoriosEntity re = rm.findOne(1);
		re.setNumero_de_os("OP002/5.17");
		rm.save(re);

		vm.setDescricao("REPOSITORY UPDATE EXAMPLE");
		vm.setLogicJson(rm.findByName("admin2"));
		try {
			logger.info(mapper.writeValueAsString(vm));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
