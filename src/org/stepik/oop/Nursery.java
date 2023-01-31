package org.stepik.oop;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Nursery {

    public static final String fileArrayJSON = "fileAnimalJSON.json";

    public static Scanner s;
    public static void addNewDog (int i, Dog[] dogs) {
//        s = new Scanner(System.in);
        String breed = "", size = "", color = "";
        int age = 0;
        try {
            System.out.println("Enter breed:");
            breed = s.nextLine();
            System.out.println("Enter size:");
            size = s.nextLine();
            System.out.println("Enter color:");
            color = s.nextLine();
            System.out.println("Enter age:");
            age = Integer.parseInt(s.nextLine());
            if (age < 0) {
                throw new NegativeAgeException(age);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Error: NumberFormatException");
        } catch (NegativeAgeException e) {
            e.printStackTrace();
            System.out.println("Error: ArithmeticException occurred");
            age = 0;
        } catch (Exception e) {
            System.out.println("Error: Exception occurred");
        }

        dogs[i] = new Dog(breed, size, age, color, "Dog");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "dog");
        jsonObject.put("breed", breed);
        jsonObject.put("size", size);
        jsonObject.put("age", age);
        jsonObject.put("color", color);

        File file = new File(fileArrayJSON);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        JSONArray animalList;
        Object obj;
        JSONParser jsonParser =new JSONParser();
        try (FileReader reader =new FileReader(fileArrayJSON)) {
            if (file.length() > 2) {
                obj = jsonParser.parse(reader);
                animalList = (JSONArray) obj;
            } else {
                animalList = new JSONArray();
            }
            animalList.add(jsonObject);

            try (FileWriter writer = new FileWriter(fileArrayJSON)) {
                writer.write(animalList.toJSONString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showAllDog(int size, Dog[] dogs) {
        for (int i = 0; i < size; i++) {
            System.out.printf("%d) %s %s %d %s\n",(i + 1), dogs[i].getBreed(), dogs[i].getSize(), dogs[i].getAge(), dogs[i].getColor());
            System.out.printf("%d) count of days (seconds) sit in nursery %d\n",(i + 1), dogs[i].sit());
        }
    }

    private static void editDogFields(int count, Dog[] dogs) {
        System.out.println("Enter number of dog:");
        int i = Integer.parseInt(s.nextLine()) - 1;
        if (i >= 0 && i < count) {
            System.out.printf("Old data: %d) %s %s %d %s\n",(i + 1), dogs[i].getBreed(), dogs[i].getSize(), dogs[i].getAge(), dogs[i].getColor());
            String size;
            int age;
            System.out.println("Enter size:");
            size = s.nextLine();
            dogs[i].setSize(size);

            try {
                System.out.println("Enter age:");
                age = Integer.parseInt(s.nextLine());
                if (age < 0) {
                    throw new NegativeAgeException(age);
                }
            } catch (NumberFormatException e) {
//                e.printStackTrace();
                System.out.println("Error: NumberFormatException");
                age = 0;
            } catch (NegativeAgeException e) {
                e.printStackTrace();
                System.out.println("Error: Arithmetics");
                age = 1;
            }
            dogs[i].setAge(age);

        } else {
            System.out.println("error : number of dog incorrect");
        }

    }

    private static int readJSONFromFile(Dog[] dogs) {

        int count = 0;
        File file = new File(fileArrayJSON);
        if (!file.exists() || file.length() < 2) {
           return 0;
        }
        JSONArray animalList;
        Object obj;
        JSONParser jsonParser =new JSONParser();
        try (FileReader reader =new FileReader(fileArrayJSON)) {
            obj = jsonParser.parse(reader);
            animalList = (JSONArray) obj;
            count = animalList.size();
            if (count >= 10) {
                count = 10;
            }


            for (int i = 0; i < count; i++) {
                JSONObject objJSON = (JSONObject) animalList.get(i);
                dogs[i] = new Dog((String) objJSON.get("breed"), (String) objJSON.get("size"), (int)(long) objJSON.get("age"), (String)objJSON.get("color"), (String)objJSON.get("type"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println("Nursery project!");
        s = new Scanner(System.in);
        String command;
        Dog[] dogs = new Dog[10];
        int count = 0;
        count = readJSONFromFile(dogs);



        // add, list, exit
        while (true) {
            System.out.println("Enter command: add/list/edit/exit");
            command = s.nextLine();
            switch (command) {
                case "add":
                    System.out.println("Add new dog");
                    if (count < 10){
                        addNewDog(count, dogs);
                        count++;
                        System.out.println("New dog was added");
                    } else {
                        System.out.println("Nursery is full!");
                    }
                    break;
                case "list":
                    System.out.println("Show all dog");
                    if (count > 0) {
                        showAllDog(count, dogs);
                    } else {
                        System.out.println("Nursery is empty");
                    }
                    break;
                case "edit":
                    System.out.println("Edit data for some dog");
                    if (count > 0) {
                        editDogFields(count, dogs);
                    } else {
                        System.out.println("Nursery is empty");
                    }
                    break;
                case "del":
                    System.out.println("Delete data for some dog");
                    if (count > 0) {
//                        delDogFields(count, dogs);
                    } else {
                        System.out.println("Nursery is empty");
                    }
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Unknown command");

            }
        }
    }



}
/* Dog mastiff = new Dog();
        mastiff.breed = "Neapolitan mastiff";
        mastiff.size = "large";
        mastiff.age = 5;
        mastiff.color = "black";
        System.out.printf("First dog: %s, %d, %s.",mastiff.breed, mastiff.age, mastiff.color); */