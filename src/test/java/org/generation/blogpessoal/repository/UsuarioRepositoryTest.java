package org.generation.blogpessoal.repository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.generation.blogpessoal.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void stard() {

		usuarioRepository.save(new Usuario(0L, "Jorge Tevez", "Jorgin21@gmail.com", "miranha123"));
		usuarioRepository.save(new Usuario(0L, "Jennifer Silva", "Jeni30@gmail.com", "Levi123"));
		usuarioRepository.save(new Usuario(0L, "Kaique Lopes", "Kaique56@gmail.com", "RM123"));
		usuarioRepository.save(new Usuario(0L, "Lucas Santos", "Lucas45@gmail.com", "Osu123"));
	}

	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {

		Optional<Usuario> usuario = usuarioRepository.findByUsuario("Jorgin21@gmail.com");
		assertTrue(usuario.get().getUsuario().equals("Jorgin21@gmail.com"));

	}

	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuario() {

		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("e");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Jorge Tevez"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Jennifer Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Kaique Lopes"));
		
	}
	
	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}

}