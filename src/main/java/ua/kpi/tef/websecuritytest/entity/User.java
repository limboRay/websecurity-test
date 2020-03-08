package ua.kpi.tef.websecuritytest.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
@Entity
@Table( name="user",
		uniqueConstraints={@UniqueConstraint(columnNames={"username"})})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "first_name_cyr", nullable = false)
	private String firstNameCyr;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "last_name_cyr", nullable = false)
	private String lastNameCyr;
	@Column(nullable = false)
	private String username;
	@Column(name = "password", nullable = false)
	private String password;
	@Column
	private String email;
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private RoleType role;



	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;



}

