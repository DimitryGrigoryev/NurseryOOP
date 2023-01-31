package org.stepik.oop;

import java.util.Date;

public abstract class Animal implements Alertable, ConvertAlertMessages{

    private String type;
    private String size;
    private int age;
    private String color;
    private final Date dateArrived;

    public Animal(String type) {
        this.type = type;
        this.dateArrived = new Date();
        this.size = "medium";
        this.age = 0;
    }

    public Animal(String size, int age, String  color, String type ) {
        this.dateArrived = new Date();
        this.type = type;
        this.size = size;
        this.age = age;
        if (color != null) {
            this.color = color;
        }
    }

    // методы класса
    abstract boolean eat (float weight, String food);

    abstract int sleep ();

    public final int sit () {
        // Возвращает сколько дней животное находится в питомнике
        Date currentDate = new Date();
//        int days = (int)(currentDate.getTime() - this.dateArrived.getTime())/(60*60*24*1000);
        int seconds = (int)(currentDate.getTime() - this.dateArrived.getTime())/(1000); // временная заглушка для наглядности работы
//        return days;

        return seconds;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void alertError(int num) {
        System.out.println("Error occurred: " + this.convertAlertErrorMessage(num));
    }

    @Override
    public void alertWarning(int num) {
        System.out.println("Warning occurred: " + this.convertAlertWarningMessage(num));

    }

    @Override
    public String convertAlertErrorMessage(int num) {
        return ListErrorWarning.listError[num];
    }

    @Override
    public String convertAlertWarningMessage(int num) {
        return ListErrorWarning.listWarning[num];
    }

















}
