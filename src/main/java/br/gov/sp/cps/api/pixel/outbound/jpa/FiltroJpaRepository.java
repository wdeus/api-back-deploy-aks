package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.dto.projection.ComboboxProjection;
import br.gov.sp.cps.api.pixel.core.domain.entity.FatoEntrevista;
import br.gov.sp.cps.api.pixel.core.domain.repository.FiltroRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FiltroJpaRepository extends JpaRepository<FatoEntrevista, Long>, FiltroRepository {

    @Query(value = "SELECT table_name AS nome, " +
            "CONCAT('Gerenciamento ', REPLACE(table_name, 'fato_', '')) AS alias, " +
            "array_to_string(array_agg(column_name), ',') AS campos " +
            "FROM information_schema.columns " +
            "WHERE table_schema = 'public' " +
            "AND table_name LIKE 'fato%' " +
            "AND column_name NOT LIKE 'id%' " +
            "GROUP BY table_name " +
            "ORDER BY table_name",
            nativeQuery = true)
    List<ComboboxProjection> buscarFatos();

    @Query(value = "SELECT dim.table_name AS nome, " +
            "CONCAT('Gerenciamento ', REPLACE(dim.table_name, 'dim_', '')) AS alias, " +
            "array_to_string(array_agg(dim.column_name), ',') AS campos " +
            "FROM information_schema.columns dim " +
            "WHERE dim.table_schema = 'public' " +
            "AND (" +
            "  (:fato = 'fato_vaga' AND dim.table_name IN ('dim_vaga', 'dim_processo_seletivo', 'dim_periodo')) OR " +
            "  (:fato = 'fato_entrevista' AND dim.table_name IN ('dim_entrevista', 'dim_vaga', 'dim_feedback', 'dim_acao_seletiva', 'dim_participante_rh', 'dim_contratacao'))" +
            ") " +
            "AND dim.column_name NOT LIKE 'id%' " +
            "GROUP BY dim.table_name " +
            "ORDER BY dim.table_name",
            nativeQuery = true)
    List<ComboboxProjection> buscarDimensoesPorFato(@Param("fato") String fato);

}
