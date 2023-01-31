package org.stepik.oop;

public class Cat extends Animal {

    public Cat(String type) {
        super(type);
    }

    @Override
    boolean eat(float weight, String food) {
        // ...
        return true;
    }

    @Override
    int sleep() {
        // ...
        return 6;
    }
}
