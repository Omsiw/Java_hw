package Generics_and_exception;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Scanner;

@AllArgsConstructor
public class Array <T extends Number> {
    private T[] data;

    public Array(int size) { data = (T[]) new Object[size]; }

    public void Set(int index, T value) {
        if (index >= 0 && index < data.length) {
            data[index] = value;
        } else {
            throw new IndexOutOfBoundsException("Index out of Bounds");
        }
    }

    public T Get(int index) { return data[index];}

    public void fillByKeyboard() {

        for (int i = 0; i < data.length; i++) {

            Scanner in = new Scanner(System.in);
//            try {
//                data[i] = (T) in.nextLine();
//            } catch (ClassCastException e) {
//                throw new IllegalArgumentException("u fucked up");
//            }
        }

    }

}
