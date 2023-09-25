package br.com.brasilprev.api.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.brasilprev.api.entity.enums.ProfileEnum;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Usuario.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {

    public static final String TABLE_NAME = "usuario";

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", length = 100, nullable = false, unique = true)
    @Size(min = 2, max = 100)
    @NotBlank
    private String login;

    @Column(name = "senha", length = 60, nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    @Size(min = 8, max = 60)
    @NotBlank
    private String senha;

    @OneToMany(mappedBy = "usuario")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Tarefa> tarefas = new ArrayList<Tarefa>();

    @Column(name = "profile", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_profile")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Integer> perfis = new HashSet<>();

    public Set<ProfileEnum> getProfiles() {
        return this.perfis.stream().map(x -> ProfileEnum.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(ProfileEnum profileEnum) {
        this.perfis.add(profileEnum.getCode());
    }

}
