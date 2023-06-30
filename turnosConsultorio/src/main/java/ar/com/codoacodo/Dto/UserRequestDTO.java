package ar.com.codoacodo.Dto;

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
}
