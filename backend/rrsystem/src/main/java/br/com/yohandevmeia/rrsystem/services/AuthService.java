package br.com.yohandevmeia.rrsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.yohandevmeia.rrsystem.repositories.ClientRepository;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return clientRepository.findByEmail(username);
	}
}