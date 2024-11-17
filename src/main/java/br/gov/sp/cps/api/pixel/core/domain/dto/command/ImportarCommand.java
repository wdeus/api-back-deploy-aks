package br.gov.sp.cps.api.pixel.core.domain.dto.command;

import lombok.Data;

@Data
public class ImportarCommand {
    private byte[] document;
}
