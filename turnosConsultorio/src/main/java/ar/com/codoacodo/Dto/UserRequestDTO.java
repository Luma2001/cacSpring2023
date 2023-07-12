package ar.com.codoacodo.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {
	private String username;
	private String password;
	private List<String> roles;
}
