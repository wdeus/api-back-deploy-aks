package br.gov.sp.cps.api.pixel.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Combobox {

    private String nome;
    private String alias;
    private List<String> campos = new ArrayList<>();
}
