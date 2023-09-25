package br.com.fiap.mspedidos.service.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) { super(message); }
}
