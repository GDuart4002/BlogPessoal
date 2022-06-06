package com.generation.blogpessoal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		//Busca pelo usuario
		Optional<Usuario> usuario = userRepository.findByUsuario(userName);
		//Caso o usuario nao for encontrado, retorna not found
		usuario.orElseThrow(()-> new UsernameNotFoundException(userName + "Usuário não encontrado!"));
		
		return usuario.map(UserDetailsImpl::new).get();
	}

}
