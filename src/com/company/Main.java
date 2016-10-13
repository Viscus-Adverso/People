package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        HashMap<String, ArrayList<Person>> peopleHash = new HashMap<>();

        File peoplef = new File("people.csv");

        try {
            Scanner scanner = new Scanner(peoplef);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] columns = line.split(",");
                Person person = new Person(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5]);

                ArrayList<Person> ppl = peopleHash.get(person.country);
                if (ppl == null) {
                    ppl = new ArrayList<>();
                    peopleHash.put(person.country, ppl);
                }
                ppl.add(person);
                Collections.sort(ppl);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(peopleHash);
    }
}
