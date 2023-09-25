package br.com.brasilprev.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brasilprev.api.entity.Tarefa;
import br.com.brasilprev.api.entity.projection.TarefaProjection;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<TarefaProjection> findByUsuario_Id(Long id);

    // @Query(value = "SELECT t FROM Tarefa t WHERE t.user.id = :id")
    // List<Tarefa> findByUser_Id(@Param("id") Long id);

    // @Query(value = "SELECT * FROM task t WHERE t.user_id = :id", nativeQuery =
    // true)
    // List<Tarefa> findByUser_Id(@Param("id") Long id);

}
