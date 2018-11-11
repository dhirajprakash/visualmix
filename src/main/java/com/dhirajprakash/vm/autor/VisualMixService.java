package com.dhirajprakash.vm.autor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class VisualMixService {

	@Autowired
	private AutorRepository vmaxAutor;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	VisualMixResponse vm;

	ObjectMapper mapper = new ObjectMapper();
	Map<String, String> returnString = new HashMap<>();

	private Logger logger = LoggerFactory.getLogger(VisualMixService.class);

	public ResponseEntity<?> incluirAutor(String nome, String cpf) {

		Autor autor = new Autor();
		autor.setId(vmaxAutor.findMaxId() + 1);
		autor.setNome(nome);
		autor.setCpf(cpf);
		vmaxAutor.save(autor);
		vm.setDescricao("SALVO COM SUCESSO");
		vm.setLogicJson(autor);

		return new ResponseEntity(vm, HttpStatus.OK);
	}

	public ResponseEntity<?> alterarAutor(Integer id, String nome) {

		Autor autor = vmaxAutor.findOne(id);
		autor.setNome(nome);
		vmaxAutor.save(autor);
		vm.setDescricao("ALTERADO COM SUCESSO");
		vm.setLogicJson(autor);

		return new ResponseEntity(vm, HttpStatus.OK);
	}

	public ResponseEntity<?> excluirAutor(Integer id) {

		Autor autor = vmaxAutor.findOne(id);
		vmaxAutor.delete(autor);
		vm.setDescricao("EXCLUIDO COM SUCESSO");
		vm.setLogicJson(autor);

		return new ResponseEntity(vm, HttpStatus.OK);
	}

	public ResponseEntity<?> listarAutor(Integer id) {
		Autor autor = vmaxAutor.findOne(id);
		vm.setDescricao("Listar COM SUCESSO");
		vm.setLogicJson(autor);

		return new ResponseEntity(vm, HttpStatus.OK);

	}

	public ResponseEntity<?> listarTodosAutor() {

		vm.setDescricao("Liste todos");
		vm.setLogicJson(vmaxAutor.findAll());
		return new ResponseEntity(vm, HttpStatus.OK);
	}

	public ResponseEntity<?> test() {
		vm.setDescricao("Test com sucesso");
		vm.setLogicJson("");

		return new ResponseEntity(vm, HttpStatus.OK);
	}

	public ResponseEntity<?> testjdbc(VisualMixSQLFilterParameters vmp) {

		ArrayList<Relatorios> mp = new ArrayList<Relatorios>();
		try {
			if (vmp.getLogicName().equalsIgnoreCase("first"))
				mp = runFirstTask(vmp.getVisualMixKeySet());
			else if (vmp.getLogicName().equalsIgnoreCase("second"))
				mp = runSecondTask(vmp.getVisualMixKeySet());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vm.setDescricao("CUSTOM SQL QUERY");
		vm.setLogicJson(mp);

		return new ResponseEntity(vm, HttpStatus.OK);
	}

	public ArrayList<Relatorios> runFirstTask(String... strings) throws Exception {
		ArrayList<Relatorios> alist = new ArrayList<Relatorios>();
		jdbcTemplate.query("SELECT id, numero_de_os, user FROM relatorios WHERE user = ?", new Object[] { strings[0] },
				(rs, rowNum) -> new Relatorios(rs.getInt("id"), rs.getString("numero_de_os"), rs.getString("user")))
				.forEach(relatorios -> {
					// logger.info(customer.toString()
					alist.add(relatorios);

				});

		return alist;
	}

	public ArrayList<Relatorios> runSecondTask(String... strings) throws Exception {
		ArrayList<Relatorios> alist = new ArrayList<Relatorios>();
		jdbcTemplate.query(
				"SELECT rl.id,rl.numero_de_os as no,rl.user,udos.full_name FROM relatorios2.relatorios as rl \n"
						+ "LEFT JOIN relatorios2.user_details_os udos on udos.username=rl.user where rl.user = ?",
				new Object[] { strings[0] }, (rs, rowNum) -> new Relatorios(rs.getInt("id"), rs.getString("no"),
						rs.getString("user"), rs.getString("full_name")))
				.forEach(relatorios -> {

					alist.add(relatorios);

				});

		return alist;
	}

	/*
	 * 
	 * public void runTasks(String... strings) throws Exception {
	 * 
	 * logger.info("Creating tables");
	 * 
	 * jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
	 * jdbcTemplate.execute("CREATE TABLE customers(" +
	 * "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
	 * 
	 * // Split up the array of whole names into an array of first/last names
	 * List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean",
	 * "Josh Bloch", "Josh Long").stream() .map(name -> name.split(" "))
	 * .collect(Collectors.toList());
	 * 
	 * // Use a Java 8 stream to print out each tuple of the list
	 * splitUpNames.forEach(name ->
	 * logger.info(String.format("Inserting customer record for %s %s", name[0],
	 * name[1])));
	 * 
	 * // Uses JdbcTemplate's batchUpdate operation to bulk load data jdbcTemplate.
	 * batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)",
	 * splitUpNames);
	 * 
	 * log.info("Querying for customer records where first_name = 'Josh':");
	 * jdbcTemplate.query(
	 * "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new
	 * Object[] { "Josh" }, (rs, rowNum) -> new Customer(rs.getLong("id"),
	 * rs.getString("first_name"), rs.getString("last_name")) ).forEach(customer ->
	 * logger.info(customer.toString())); }
	 * 
	 */

}
