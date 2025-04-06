package org.example.miniprojectspring.exception;

public class FileNotFoundException extends Exception{
    private String fileName;

    public FileNotFoundException(String fileName) {
        super("File not found: " + fileName);
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
