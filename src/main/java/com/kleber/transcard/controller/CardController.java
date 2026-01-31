package com.kleber.transcard.controller;

import com.kleber.transcard.dto.page.PageCardDTO;
import com.kleber.transcard.dto.request.CreateCardDTO;
import com.kleber.transcard.dto.response.CardDTO;
import com.kleber.transcard.infra.RestErrorMessage;
import com.kleber.transcard.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cards")
@Tag(name = "Cards", description = "Endpoints para gerenciamento dos Cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    // GET CARDS
    @Operation(
            summary = "Listar cartões",
            description = "Retorna uma página de cartões com filtros opcionais por nome, tipo e status. " +
                    "Exemplo: ?name=Visa&typeCard=CREDIT&status=true&page=0&size=5"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cartões retornada com sucesso", content = @Content(schema = @Schema(implementation = PageCardDTO.class))),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido",
                    content = @Content(schema = @Schema(
                            implementation = RestErrorMessage.class,
                            example = """
                             {
                               "status": "401",
                               "message": "Token ausente ou inválido"
                             }
                             """
                    ))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(schema = @Schema(
                            implementation = RestErrorMessage.class,
                            example = """
                     {
                       "status": "500 INTERNAL_SERVER_ERROR",
                       "message": "Erro interno no servidor"
                     }
                     """
                    )))
    })
    @GetMapping
    public Page<CardDTO> getCards(
            @Parameter(description = "Nome para filtrar os cartões", required = false)
            @RequestParam(required = false) String name,

            @Parameter(description = "Tipo do cartão", required = false)
            @RequestParam(required = false) String typeCard,

            @Parameter(description = "Status do cartão", required = false)
            @RequestParam(required = false) Boolean status,

            @Parameter(description = "Número da página (opcional, padrão 0)", required = false, example = "0")
            @RequestParam(required = false, defaultValue = "0") Integer page,

            @Parameter(description = "Quantidade de usuários por página (opcional, padrão 5)", required = false, example = "5")
            @RequestParam(required = false, defaultValue = "20") Integer size
//            Pageable pageable
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return cardService.getCards(name, typeCard, status, pageable);
    }

    // CREATE CARD FOR USER
    @Operation(summary = "Criar cartão para usuário", description = "Cria um novo cartão associado a um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cartão criado com sucesso", content = @Content(schema = @Schema(implementation = CardDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(
                            implementation = RestErrorMessage.class,
                            example = """
                         {
                           "status": "404 NOT_FOUND",
                           "message": "Usuário não encontrado"
                         }
                         """
                    ))),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido",
                    content = @Content(schema = @Schema(
                            implementation = RestErrorMessage.class,
                            example = """
                             {
                               "status": "401",
                               "message": "Token ausente ou inválido"
                             }
                             """
                    ))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(schema = @Schema(
                            implementation = RestErrorMessage.class,
                            example = """
                         {
                           "status": "500 INTERNAL_SERVER_ERROR",
                           "message": "Erro interno no servidor"
                         }
                         """
                    )))
    })
    @PostMapping("/{userId}")
    public ResponseEntity<CardDTO> createForUser(
            @PathVariable Long userId,
            @RequestBody( required = true ) CreateCardDTO dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cardService.createCardForUser(userId, dto));
    }

    // TOGGLE CARD STATUS
    @Operation(
            summary = "Ativar/Inativar cartão",
            description = "Alterna o status (ativo/inativo) de um cartão pelo ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status do cartão alterado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cartão não encontrado",
                    content = @Content(schema = @Schema(
                            implementation = RestErrorMessage.class,
                            example = """
                         {
                           "status": "404 NOT_FOUND",
                           "message": "Cartão não encontrado"
                         }
                         """
                    ))),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido",
                    content = @Content(schema = @Schema(
                            implementation = RestErrorMessage.class,
                            example = """
                             {
                               "status": "401",
                               "message": "Token ausente ou inválido"
                             }
                             """
                    ))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(schema = @Schema(
                            implementation = RestErrorMessage.class,
                            example = """
                         {
                           "status": "500 INTERNAL_SERVER_ERROR",
                           "message": "Erro interno no servidor"
                         }
                         """
                    )))
    })
    @PatchMapping("/{cardId}/toggle")
    public ResponseEntity<CardDTO> toggle(
            @PathVariable Long cardId
    ) {
        return ResponseEntity.ok(
                cardService.toggleStatus(cardId)
        );
    }

    // DELETE CARD
    @Operation(summary = "Excluir cartão", description = "Remove um cartão pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cartão removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cartão não encontrado",
                    content = @Content(schema = @Schema(
                            implementation = RestErrorMessage.class,
                            example = """
                         {
                           "status": "404 NOT_FOUND",
                           "message": "Cartão não encontrado"
                         }
                         """
                    ))),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido",
                    content = @Content(schema = @Schema(
                            implementation = RestErrorMessage.class,
                            example = """
                             {
                               "status": "401",
                               "message": "Token ausente ou inválido"
                             }
                             """
                    ))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                    content = @Content(schema = @Schema(
                            implementation = RestErrorMessage.class,
                            example = """
                         {
                           "status": "500 INTERNAL_SERVER_ERROR",
                           "message": "Erro interno no servidor"
                         }
                         """
                    )))
    })
    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> deleteCard(
            @PathVariable Long cardId
    ) {
        cardService.deleteCard(cardId);
        return ResponseEntity.ok().build();
    }
}

