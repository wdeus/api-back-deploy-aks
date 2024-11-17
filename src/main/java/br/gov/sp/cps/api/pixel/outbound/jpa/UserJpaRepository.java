package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.dto.projection.PermissaoGrupoProjection;
import br.gov.sp.cps.api.pixel.core.domain.entity.User;
import br.gov.sp.cps.api.pixel.core.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<User, Long>, UserRepository {

    default UserDetails buscarPorLogin(String login) {
        return findByLogin(login);
    }

    UserDetails findByLogin(String login);

    default User salvar(User user) {
        return save(user);
    }

    @Query(value = "SELECT DISTINCT p.id_permissao AS idPermissao, p.descricao AS descricao, " +
            "g.nome AS nomeGrupo " +
            "FROM grupo g " +
            "JOIN grupo_permissao gp ON gp.grupo_id = g.id " +
            "JOIN permissao p ON gp.permissao_id = p.id_permissao " +
            "WHERE g.id = ?",
            nativeQuery = true)
    List<PermissaoGrupoProjection> buscarPermissoesPorGrupoId(Long grupoId);
}
