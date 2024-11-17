package br.gov.sp.cps.api.pixel.outbound.jpa.bd;

import br.gov.sp.cps.api.pixel.core.domain.entity.Indicador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class IndicadorRepositoryImpl {

    private final EntityManager entityManager;

    public List<?> executarConsultaIndicador(Indicador indicador) {
        StringBuilder queryBuilder = new StringBuilder("SELECT SUM(f.");

        queryBuilder.append(indicador.getIndicadorCampo()).append(") ");

        queryBuilder.append("FROM ").append(indicador.getIndicadorNome()).append(" f ");

        boolean hasFiltro = indicador.getFiltroCampo() != null && !indicador.getFiltroCampo().isEmpty();

        if (hasFiltro) {
            queryBuilder.append("JOIN ").append(indicador.getFiltroNome()).append(" d ")
                    .append("ON f.id_").append(indicador.getFiltroNome())
                    .append(" = d.id_").append(indicador.getFiltroNome()).append(" ");
        }

        if (hasFiltro) {
            queryBuilder.append("WHERE d.")
                .append(indicador.getFiltroCampo()).append(" ")
                .append(indicador.getFiltroComparador()).append(" :filtro_valor ");

        }

        queryBuilder.append("HAVING SUM(f.")
                .append(indicador.getIndicadorCampo()).append(") ")
                .append(indicador.getIndicadorComparador()).append(" :indicador_valor ");

        System.out.println(queryBuilder.toString());

        Query query = entityManager.createNativeQuery(queryBuilder.toString());

        int valorConvertido = Integer.parseInt(indicador.getIndicadorValor());
        query.setParameter("indicador_valor", valorConvertido);

        if (hasFiltro) {
            query.setParameter("filtro_valor", indicador.getFiltroValor());
        }

        return query.getResultList();
    }
}
