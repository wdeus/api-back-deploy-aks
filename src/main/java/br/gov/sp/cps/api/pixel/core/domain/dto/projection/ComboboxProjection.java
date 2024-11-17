package br.gov.sp.cps.api.pixel.core.domain.dto.projection;

import java.util.List;

public interface ComboboxProjection {

    String getNome();
    String getAlias();
    List<String> getCampos();
}
