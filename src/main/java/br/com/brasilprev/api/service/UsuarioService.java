package br.com.brasilprev.api.service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasilprev.api.entity.Usuario;
import br.com.brasilprev.api.entity.dto.UsuarioCreateDTO;
import br.com.brasilprev.api.entity.dto.UsuarioUpdateDTO;
import br.com.brasilprev.api.entity.enums.ProfileEnum;
import br.com.brasilprev.api.exceptions.AuthorizationException;
import br.com.brasilprev.api.exceptions.DataBindingViolationException;
import br.com.brasilprev.api.exceptions.ObjectNotFoundException;
import br.com.brasilprev.api.repository.UsuarioRepository;
import br.com.brasilprev.api.security.UserSpringSecurity;
import jakarta.validation.Valid;

@Service
public class UsuarioService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findById(Long id) {
        UserSpringSecurity userSpringSecurity = authenticated();
        if (!Objects.nonNull(userSpringSecurity)
                || !userSpringSecurity.hasRole(ProfileEnum.ADMIN) && !id.equals(userSpringSecurity.getId()))
            throw new AuthorizationException("Acesso negado!");

        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new ObjectNotFoundException(
                "Usuário não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
    }

    @Transactional
    public Usuario create(Usuario obj) {
        obj.setId(null);
        obj.setSenha(this.bCryptPasswordEncoder.encode(obj.getSenha()));
        obj.setPerfis(Stream.of(ProfileEnum.USER.getCode()).collect(Collectors.toSet()));
        obj = this.usuarioRepository.save(obj);
        return obj;
    }

    @Transactional
    public Usuario update(Usuario obj) {
        Usuario newObj = findById(obj.getId());
        newObj.setSenha(obj.getSenha());
        newObj.setSenha(this.bCryptPasswordEncoder.encode(obj.getSenha()));
        return this.usuarioRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataBindingViolationException("Não é possível excluir pois há entidades relacionadas!");
        }
    }

    public static UserSpringSecurity authenticated() {
        try {
            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    public Usuario fromDTO(@Valid UsuarioCreateDTO obj) {
        Usuario usuario = new Usuario();
        usuario.setLogin(obj.getLogin());
        usuario.setSenha(obj.getSenha());
        return usuario;
    }

    public Usuario fromDTO(@Valid UsuarioUpdateDTO obj) {
        Usuario usuario = new Usuario();
        usuario.setId(obj.getId());
        usuario.setSenha(obj.getSenha());
        return usuario;
    }

}
