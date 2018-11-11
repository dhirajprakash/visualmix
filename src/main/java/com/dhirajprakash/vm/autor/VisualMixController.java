package com.dhirajprakash.vm.autor;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VisualMixController {

	private VisualMixService visualMixService;

	@Autowired
	public void setVisualMaxService(VisualMixService visualMixService) {
		this.visualMixService = visualMixService;
	}

	@GetMapping("/vmax/test")
	@CrossOrigin
	public ResponseEntity<?> test() {

		return visualMixService.test();
	}

	@PostMapping("/vmax/operation")
	@CrossOrigin
	public ResponseEntity<?> testJDBC(@RequestBody VisualMixSQLFilterParameters vmp) {
		return visualMixService.testjdbc(vmp);
	}

	@PostMapping("/vmax/incluir-via-json")
	@CrossOrigin
	public ResponseEntity<?> createUser(@RequestBody Autor autor) {
		return visualMixService.incluirAutor(autor.getNome(), autor.getCpf());
	}

	@PostMapping("/vmax/incluir")
	@CrossOrigin
	public ResponseEntity<?> incluirAutor(@RequestHeader("nome") String nome, @RequestHeader("cpf") String cpf) {
		// return visualMaxService.createUser(user);
		return visualMixService.incluirAutor(nome, cpf);
	}

	@PostMapping("/vmax/alterar")
	@CrossOrigin
	public ResponseEntity<?> alterarAutor(@RequestHeader("id") String id, @RequestHeader("nome") String nome) {
		// return visualMaxService.createUser(user);
		return visualMixService.alterarAutor(Integer.valueOf(id), nome);
	}

	@PostMapping("/vmax/excluir")
	@CrossOrigin
	public ResponseEntity<?> excluirAutor(@RequestHeader("id") String id) {
		// return visualMaxService.createUser(user);
		return visualMixService.excluirAutor(Integer.valueOf(id));
	}

	@PostMapping("/vmax/listar")
	@CrossOrigin
	public ResponseEntity<?> listarAutor(@RequestHeader("id") String id) {
		// return visualMaxService.createUser(user);
		return visualMixService.listarAutor(Integer.valueOf(id));
	}

	@PostMapping("/vmax/listartodos")
	@CrossOrigin
	public ResponseEntity<?> listarTodosAutor() {
		// return visualMaxService.createUser(user);
		return visualMixService.listarTodosAutor();
	}

}
