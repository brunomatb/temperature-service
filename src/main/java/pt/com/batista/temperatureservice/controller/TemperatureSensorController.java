package pt.com.batista.temperatureservice.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pt.com.batista.temperatureservice.model.TemperatureSensorRequest;
import pt.com.batista.temperatureservice.model.TemperatureSensorResponse;
import pt.com.batista.temperatureservice.service.TemperatureSensorService;

import java.util.List;

@RestController
@RequestMapping("/v1/temperature")
@Api(value = "Temperature Sensor API", tags = "Temperature Sensor")
public class TemperatureSensorController {
    @Autowired
    private TemperatureSensorService service;
    @ApiOperation(value = "Cria um novo sensor de temperatura", notes = "Adiciona um novo sensor de temperatura ao sistema.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sensor de temperatura criado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 409, message = "Conflito - UID do sensor já existe")
    })
    @PostMapping("/add")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<TemperatureSensorResponse> create(@RequestBody TemperatureSensorRequest request){
        return ResponseEntity.ok(service.create(request));
    }
    @ApiOperation(value = "Obtém todos os sensores de temperatura", notes = "Recupera todos os sensores de temperatura registrados no sistema.")
    @GetMapping("/getAll")
    public ResponseEntity<List<TemperatureSensorResponse>> getAll(){
            return ResponseEntity.ok(service.getAll());
    }
    @ApiOperation(value = "Exclui um sensor de temperatura", notes = "Exclui um sensor de temperatura com base no ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sensor de temperatura excluído com sucesso"),
            @ApiResponse(code = 404, message = "Sensor de temperatura não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id){
        TemperatureSensorResponse response  = service.delete(id);
        if(response!=null){
            return new ResponseEntity<>("Sensor de temperatura excluído com sucesso.", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Não foi possível encontrar o sensor de temperatura para exclusão.", HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation(value = "Atualiza um sensor de temperatura", notes = "Atualiza um sensor de temperatura com base no ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sensor de temperatura atualizado com sucesso"),
            @ApiResponse(code = 404, message = "Sensor de temperatura não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody TemperatureSensorRequest request, @ApiParam(value = "ID do sensor de temperatura a ser atualizado", required = true) @PathVariable String id
    ){

        TemperatureSensorResponse response  = service.update(request, id);
        if(response != null){
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o sensor de temperatura para atualização.");
        }
    }

}
