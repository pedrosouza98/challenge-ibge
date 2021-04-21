package br.com.challengeibge.exception;

public class CsvException extends RuntimeException{
    public CsvException() {
    }

    public CsvException(String s) {
        super(s);
    }
}