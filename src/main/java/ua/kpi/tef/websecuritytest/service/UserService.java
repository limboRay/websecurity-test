package ua.kpi.tef.websecuritytest.service;

import com.sun.javafx.collections.ImmutableObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.kpi.tef.websecuritytest.dto.UserListDTO;
import ua.kpi.tef.websecuritytest.entity.RoleType;
import ua.kpi.tef.websecuritytest.entity.User;
import ua.kpi.tef.websecuritytest.dto.UserDTO;
import ua.kpi.tef.websecuritytest.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserListDTO getAllUsers() {
		//TODO checking for an empty user list
		return new UserListDTO(userRepository.findAll());
	}

	public Optional<User> findByUsername (UserDTO userDTO){
		//TODO check for user availability. password check
		return userRepository.findByUsername(userDTO.getUsername());
	}

	public void saveNewUser (User user){
		//TODO inform the user about the replay email
		// TODO exception to endpoint
		try {
			userRepository.save(user);
		} catch (Exception ex){
			System.out.println("Error: duplicate user email");
		}

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return new UserDTO(userRepository.findByUsername(username).orElseThrow(() ->
				new UsernameNotFoundException("login " + username + " not found.")));


	}
}
