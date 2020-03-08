package ua.kpi.tef.websecuritytest.dto;

import lombok.*;
import ua.kpi.tef.websecuritytest.entity.User;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserListDTO {
	private List<User> users;
}
