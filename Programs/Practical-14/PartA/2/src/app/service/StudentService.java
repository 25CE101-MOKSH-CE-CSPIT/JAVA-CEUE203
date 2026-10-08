package app.service;

import app.model.Student;
import java.io.*;
import java.util.ArrayList;

public class StudentService {
    private ArrayList<Student> ls = new ArrayList<>();

    public void add(Student s) {
        ls.add(s);
    }

    public int count() {
        return ls.size();
    }

    public Student get(int i) {
        if (i < 0 || i >= ls.size())
            throw new IndexOutOfBoundsException("Invalid index");
        return ls.get(i);
    }

    public void remove(int i) {
        if (i < 0 || i >= ls.size())
            throw new IndexOutOfBoundsException("Invalid index");
        ls.remove(i);
    }

    public void display() {
        for (Student s : ls)
            System.out.println(s);
    }

    public void save(String f) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
            oos.writeObject(ls);
        }
    }

    @SuppressWarnings("unchecked")
    public void load(String f) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            ls = (ArrayList<Student>) ois.readObject();
        }
    }
}
