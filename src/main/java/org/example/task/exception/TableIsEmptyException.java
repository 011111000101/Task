package org.example.task.exception;

public class TableIsEmptyException extends RuntimeException{

    public TableIsEmptyException(String nameOfTable) {
        super("Table: " + nameOfTable);
    }
}