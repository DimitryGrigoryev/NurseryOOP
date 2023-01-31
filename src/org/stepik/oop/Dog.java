package org.stepik.oop;

public class Dog extends Animal {
    // поля
    private String breed;

    // конструкторы класса
    Dog (String type) {
        super(type);
        this.breed = "unknown";
    }

    Dog (String breed, String size, int age, String  color, String type ) {
        super(size, age, color, type);
        this.breed = breed;
        if(size.equals("big")){
            this.alertWarning(1);
        }
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

/*    public String getSize() {
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
    }*/


    // методы класса
    boolean eat (float weight, String food) {
        // .....
        return true;
    }

    int sleep () {
        // ...
        return 5;
    }


    float run (int task) {
        // выполняет упражнение
        return 0.42f;
    }


}
