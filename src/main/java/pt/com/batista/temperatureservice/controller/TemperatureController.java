package pt.com.batista.temperatureservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pt.com.batista.temperatureservice.model.TemperatureRequest;
import pt.com.batista.temperatureservice.model.TemperatureResponse;
import pt.com.batista.temperatureservice.service.TemperatureService;
import pt.com.batista.temperatureservice.service.TemperatureServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/v1/temperature")
public class TemperatureController {
    @Autowired
    private TemperatureService service;
    @PostMapping("/add")
    public ResponseEntity<TemperatureResponse> create(@RequestBody  TemperatureRequest request){
        return ResponseEntity.ok(service.create(request));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<TemperatureResponse>> getAll(){
            return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id){
        TemperatureResponse response  = service.delete(id);
        if(response!=null){
            return new ResponseEntity<>("Sensor de temperatura excluído com sucesso.", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Não foi possível encontrar o sensor de temperatura para exclusão.", HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody TemperatureRequest request, @PathVariable String id){
        TemperatureResponse response  = service.update(request, id);
        if(response != null){
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o sensor de temperatura para atualização.");
        }
    }

}
