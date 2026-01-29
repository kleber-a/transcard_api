package com.kleber.transcard.dto.page;

import com.kleber.transcard.dto.response.CardDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "PageCardDTO")
public class PageCardDTO {

    @Schema(description = "Lista de cartões")
    public List<CardDTO> content;

    @Schema(description = "Indica se a página está vazia", example = "false")
    public boolean empty;

    @Schema(example = "true")
    public boolean first;

    @Schema(example = "true")
    public boolean last;

    @Schema(example = "0")
    public int number;

    @Schema(example = "3")
    public int numberOfElements;

    @Schema(example = "20")
    public int size;

    @Schema(example = "3")
    public long totalElements;

    @Schema(example = "1")
    public int totalPages;

}
