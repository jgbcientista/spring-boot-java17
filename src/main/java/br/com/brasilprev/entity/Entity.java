package br.com.brasilprev.entity;

import jakarta.validation.constraints.NotBlank;

public class Entity {

    @NotBlank(message = "Name is mandatory")
    private String name;
    
    @NotBlank(message = "Email is mandatory")
    private String email;
}
