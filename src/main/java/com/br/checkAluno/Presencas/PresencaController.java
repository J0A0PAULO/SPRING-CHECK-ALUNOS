    package com.br.checkAluno.Presencas;

    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.responses.ApiResponse;
    import io.swagger.v3.oas.annotations.responses.ApiResponses;
    import io.swagger.v3.oas.annotations.tags.Tag;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @Tag(name = "Presenças", description = "EndPoints gerencimento de presenças")
    @RestController
    @RequestMapping("presenca")
    public class PresencaController {

        private PresencaService presencaService;

        public PresencaController(PresencaService presencaService) {
            this.presencaService = presencaService;
        }

        @Operation(summary = "Listar todas Presenças", description = "Essa rota fica responsavel por listar todas presenças")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Sucesso")
        })
        @GetMapping("/listar")
        public ResponseEntity<List<PresencaDTO>> listar(){
            List<PresencaDTO> Presencas = presencaService.listarPresenca();
            return ResponseEntity.ok(Presencas);
        }

        @Operation(summary = "Listar presença por id", description = "Essa rota fica responsavel por listar presenca por id")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Presença encontrada"),
                @ApiResponse(responseCode = "404", description = "Presença não encontrada")
        })
        @GetMapping("/list/{id}")
        public ResponseEntity<PresencaDTO> listarPorID(@PathVariable Long id) {
            PresencaDTO presenca = presencaService.listarPorId(id);
            if (presenca != null) {
                return ResponseEntity.ok(presenca);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(presenca);
        }

        @Operation(summary = "Criar presença", description = "Essa rota cria uma presença")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "Presenca criada com sucesso"),
                @ApiResponse(responseCode = "400", description = "Dados de presença invalido")
        })
        @PostMapping("/criar")
        public ResponseEntity<PresencaDTO> criar(@RequestBody PresencaDTO presencaDTO) {
            PresencaDTO presenca = presencaService.criar(presencaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(presenca);
        }

        @Operation(summary = "Atualizar Presença", description = "Essa rota atualiza presenca")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
                @ApiResponse(responseCode = "400", description = "Dados de presença invalidos"),
                @ApiResponse(responseCode = "404", description = "Presença não encontrada")
        })
        @PutMapping("/atualizar/{id}")
        public ResponseEntity<PresencaDTO> atualizar(@PathVariable Long id, @RequestBody PresencaDTO presencaDTO) {
            PresencaDTO presenca =  presencaService.atualizar(id, presencaDTO);
            if (presenca != null) {
                return ResponseEntity.ok(presenca);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


        @Operation(summary = "Deletar presença", description = "Essa rota deleta uma presença")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "204", description = "Presença deletada com sucesso"),
                @ApiResponse(responseCode = "404", description = "Presença não encontrada")
        })
        @DeleteMapping("/deletar/{id}")
        public ResponseEntity<Void> deletar(@PathVariable Long id) {
            presencaService.deletar(id);
            return ResponseEntity.noContent().build();
        }

    }
