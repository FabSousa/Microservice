package br.com.fiap.mspedidos.dto.errors;

import br.com.fiap.mspedidos.dto.errors.FieldMessageDTO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationCustomErrorDTO {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
    private List<FieldMessageDTO> listFieldError = new ArrayList<>();

    public ValidationCustomErrorDTO(Instant timestamp, Integer status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public void addError(String fieldName, String message){
        listFieldError.add(new FieldMessageDTO(fieldName, message));
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }

    public List<FieldMessageDTO> getListFieldError() {
        return listFieldError;
    }
}
