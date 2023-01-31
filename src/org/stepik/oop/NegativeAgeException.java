package org.stepik.oop;

public class NegativeAgeException extends Exception{

    private int value;

    public NegativeAgeException(int value) {
        this.value = value;
    }

    @Override
    public String toString() {

        String msg = "EXCEPTION: value of age: " + value + " is negative";
        return msg;
    }










}
