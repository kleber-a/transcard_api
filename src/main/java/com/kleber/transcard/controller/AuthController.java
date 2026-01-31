package com.kleber.transcard.controller;

import com.kleber.transcard.dto.request.AuthDTO;
import com.kleber.transcard.dto.response.LoginResponseDTO;
import com.kleber.transcard.dto.request.RegisterDTO;
import com.kleber.transcard.infra.RestErrorMessage;
import com.kleber.transcard.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Endpoints de autenticação e registro de usuários")
public class AuthController {

    @Autowired
    private AuthService authService;

    //LOGIN
    @Operation(
            summary = "Login do usuário",
            description = "Autentica o usuário e retorna o token JWT e informações básicas do usuário",
            method = "POST",
            operationId = "authLogin",
            tags = {"Auth"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido", content = @Content(schema = @Schema(implementation = LoginResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "Email ou senha inválidos",
                    content = @Content(schema = @Schema(
                            implementation = RestErrorMessage.class,
                            example = """
                             {
                               "status": "401 UNAUTHORIZED",
                               "message": "Email ou senha inválidos"
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
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Validated @RequestBody AuthDTO data
    ) {
        return ResponseEntity.ok(authService.login(data));
    }

    //REGISTER
    @Operation(
            summary = "Cadastro de usuário",
            description = "Registra um novo usuário no sistema",
            method = "POST",
            operationId = "authRegister",
            tags = {"Auth"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Email já está em uso",
                    content = @Content(schema = @Schema(
                            implementation = RestErrorMessage.class,
                            example = """
                         {
                           "status": "400 BAD_REQUEST",
                           "message": "Email já cadastrado"
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
    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @RequestBody( required = true ) RegisterDTO data
    ) {
        authService.register(data);
        return ResponseEntity.ok().build();
    }
}
