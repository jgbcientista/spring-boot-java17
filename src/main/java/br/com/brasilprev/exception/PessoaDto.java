package br.com.brasilprev.exception;

import jakarta.validation.constraints.NotBlank;

public class PessoaDto {

    @NotBlank(message = "Name is mandatory")
    private String name;
    
    @NotBlank(message = "Email is mandatory")
    private String email;
}
