package br.gov.sp.cps.api.pixel.outbound.jpa;

import br.gov.sp.cps.api.pixel.core.domain.entity.Feedback;
import br.gov.sp.cps.api.pixel.core.domain.repository.FeedbackRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackJpaRepository extends JpaRepository<Feedback, Integer>, FeedbackRepository {

    default List<Feedback> salvar(List<Feedback> feedbacks) {
        return saveAll(feedbacks);
    }
}
