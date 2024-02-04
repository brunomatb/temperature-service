package pt.com.batista.temperatureservice.service;

import org.springframework.stereotype.Service;
import pt.com.batista.temperatureservice.model.TemperatureRequest;
import pt.com.batista.temperatureservice.model.TemperatureResponse;

import java.util.List;
@Service
public interface TemperatureService {

    TemperatureResponse create(TemperatureRequest request);
    List<TemperatureResponse> getAll();

    TemperatureResponse delete(String Id);
    TemperatureResponse update(TemperatureRequest request, String id);
}
