package pt.com.batista.temperatureservice.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando ocorre uma tentativa de criar um sensor de temperatura com um UID que já existe.
 * Esta exceção indica um conflito de UID, impedindo a criação de um novo sensor com um UID já existente.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateUidException extends RuntimeException {
    /**
     * Construtor para a exceção DuplicateUidException.
     *
     * @param message Mensagem descritiva que detalha a razão da exceção.
     */
    public DuplicateUidException(String message) {
        super(message);
    }
}
