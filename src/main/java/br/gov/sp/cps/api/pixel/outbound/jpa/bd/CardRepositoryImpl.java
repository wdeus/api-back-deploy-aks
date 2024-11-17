package br.gov.sp.cps.api.pixel.outbound.jpa.bd;

import br.gov.sp.cps.api.pixel.core.domain.dto.Filtro;
import br.gov.sp.cps.api.pixel.core.domain.dto.command.VisualizarCardCommand;
import br.gov.sp.cps.api.pixel.core.domain.repository.CardRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CardRepositoryImpl implements CardRepository {
    private final EntityManager entityManager;

    @Override
    public List<?> getCard(VisualizarCardCommand command) {
        StringBuilder queryBuilder = new StringBuilder("SELECT ");
        queryBuilder.append(command.getEixoX().getCampo())
                .append(" FROM ")
                .append(command.getEixoX().getNome());

        for (Filtro filtro : command.getFiltros()) {
            queryBuilder.append(" JOIN ")
                    .append(filtro.getNome())
                    .append(" ON ")
                    .append(command.getEixoX().getNome())
                    .append(".id_")
                    .append(filtro.getNome())
                    .append(" = ")
                    .append(filtro.getNome())
                    .append(".id_")
                    .append(filtro.getNome());
        }

        if (!command.getFiltros().isEmpty()) {
            queryBuilder.append(" WHERE ");
            for (int i = 0; i < command.getFiltros().size(); i++) {
                Filtro filtro = command.getFiltros().get(i);
                queryBuilder.append(filtro.getNome())
                        .append(".")
                        .append(filtro.getCampo())
                        .append(" ")
                        .append(filtro.getComparador())
                        .append(" '")
                        .append(filtro.getValor())
                        .append("'");
                if (i < command.getFiltros().size() - 1) {
                    queryBuilder.append(" AND ");
                }
            }
        }
        System.out.println("Query gerada: " + queryBuilder.toString());

        Query query = entityManager.createNativeQuery(queryBuilder.toString());
        return query.getResultList();
    }
}
