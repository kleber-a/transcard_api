package com.kleber.transcard.controller;

import com.kleber.transcard.dto.page.PageUserDTO;
import com.kleber.transcard.dto.request.CreateUserDTO;
import com.kleber.transcard.dto.request.UserUpdateDTO;
import com.kleber.transcard.dto.response.UserDTO;
import com.kleber.transcard.infra.RestErrorMessage;
import com.kleber.transcard.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Endpoints para gerenciamento de usuários")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET ALL USERS
    @Operation(
            summary = "Listar usuários",
            description = "Retorna uma página de usuários filtrados por nome (opcional)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PageUserDTO.class)
                    )),
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
    public Page<UserDTO> getAll(
            @Parameter(description = "Nome para filtrar usuários", required = false)
            @RequestParam(required = false) String name,

            @Parameter(description = "Número da página (opcional, padrão 0)", required = false, example = "0")
            @RequestParam(required = false, defaultValue = "0") Integer page,

            @Parameter(description = "Quantidade de usuários por página (opcional, padrão 8)", required = false, example = "5")
            @RequestParam(required = false, defaultValue = "20") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.getAllUsers(name, pageable);
    }

    // ========================= CREATE USER =========================
    @Operation(
            summary = "Criar usuário",
            description = "Cria um novo usuário com nome, email, senha"
    )
    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso", content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Email já cadastrado",
                    content = @Content(schema = @Schema(
                            implementation = RestErrorMessage.class,
                            example = """
                             {
                               "status": "400 BAD_REQUEST",
                               "message": "Email já cadastrado"
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
    @PostMapping
    public UserDTO create(
            @RequestBody(
                    description = "Dados do usuário para criação",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CreateUserDTO.class))
            )
            @org.springframework.web.bind.annotation.RequestBody CreateUserDTO createUserDTO
    ) {
        return userService.save(createUserDTO);
    }

    // ========================= UPDATE USER =========================
    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso", content = @Content(schema = @Schema(implementation = UserDTO.class))),
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
    @PatchMapping("/{id}")
    public UserDTO updateUser(
            @PathVariable Long id,
            @RequestBody(
                    description = "Dados do usuário para atualização",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UserUpdateDTO.class))
            )
            @org.springframework.web.bind.annotation.RequestBody UserUpdateDTO userDTO
    ) {
        return userService.updateUser(id, userDTO);
    }

    // ========================= DELETE USER =========================
    @Operation(summary = "Excluir usuário", description = "Remove um usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário removido com sucesso"),
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
            @ApiResponse(responseCode = "404 NOT_FOUND", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(
                            implementation = RestErrorMessage.class,
                            example = """
                             {
                               "status": "404 NOT_FOUND",
                               "message": "Usuário não encontrado"
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}












//package com.kleber.transcard.controller;
//
//import com.kleber.transcard.dto.request.CreateUserDTO;
//import com.kleber.transcard.dto.response.UserDTO;
//import com.kleber.transcard.entity.User;
//import com.kleber.transcard.service.UserService;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//    private final UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//
//    @GetMapping
//    public Page<UserDTO> getAll(
//            @RequestParam(required = false) String name,
//            Pageable pageable
//    ) {
//        return userService.getAllUsers(name, pageable);
//    }
//
////    @GetMapping("/me")
////    public UserDTO me(@AuthenticationPrincipal User loggedUser) {
////        return userService.getMeWithCards(loggedUser);
////    }
//
//    @PostMapping
//    public UserDTO create(
//        @RequestBody CreateUserDTO createUserDTO
//    ) {
//        return userService.save(createUserDTO);
//    }
//
//
//    @PatchMapping("/{id}")
//    public UserDTO updateUser(
//            @PathVariable Long id,
//            @RequestBody UserDTO userDTO
//    ) {
//        return userService.updateUser(id, userDTO);
//    }
//
//
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Long id){
//        userService.deleteUser(id);
//    }
//
//
//}
