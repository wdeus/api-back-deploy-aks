package br.gov.sp.cps.api.pixel.core.domain.repository;

import br.gov.sp.cps.api.pixel.core.domain.entity.Feedback;

import java.util.List;

public interface FeedbackRepository {
    List<Feedback> salvar(List<Feedback> feedbacks);
}
