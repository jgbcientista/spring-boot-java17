package br.com.brasilprev.api.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioUpdateDTO {

    private Long id;

    @NotBlank
    @Size(min = 8, max = 60)
    private String senha;

}
