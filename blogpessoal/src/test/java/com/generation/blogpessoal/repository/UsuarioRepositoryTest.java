package com.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessoal.model.Usuario;

//indica que a classe UsuarioRepositoryTest é uma classe de test, que vai rodar em uma porta aleatoria a cada teste realizado
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//cria uma instancia de testes, que define que o ciclo de vida do teste vai respeitar o ciclo de vida da classe(será executado e resetado após o uso)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repository;

	@BeforeAll
	void start() {

		repository.save(new Usuario(0L, "Gabriel", "gabriel@gmail.com", "1010",
				"https://upload.wikimedia.org/wikipedia/pt/9/93/Uncharted_Golden_Abyss_capa.png"));

		repository.save(new Usuario(0L, "Lucas Silva", "lucas@gmail.com", "2020", "https://i.imgur.com/BA2hH3f.jpeg"));

		repository.save(new Usuario(0L, "Camila Silva", "camila@gmail.com", "3030", "https://i.imgur.com/fhintxd.jpeg"));

		repository.save(new Usuario(0L, "Maria Silva", "maria@gmail.com", "4040", "https://i.imgur.com/QNVeh7E.jpeg"));

	}

	@Test
	@DisplayName("Retorna 1 usuario")
	public void retornaUmUsuario() {

		Optional<Usuario> usuario = repository.findByUsuario("gabriel@gmail.com");
		assertTrue(usuario.get().getUsuario().equals("gabriel@gmail.com"));

	}
	
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void retornaTresUsuarios() {
		
		List<Usuario> listaDeUsuarios = repository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Lucas Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Camila Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Maria Silva"));
		
	}
	
	@AfterAll
	public void end() {
		repository.deleteAll();
	}

}
