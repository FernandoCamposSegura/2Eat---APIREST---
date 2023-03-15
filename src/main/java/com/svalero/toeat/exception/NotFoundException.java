package com.svalero.toeat.exception;

public class NotFoundException extends Exception {
    public NotFoundException(Object o) { super(o.getClass().getSimpleName() + " not found"); }
}
