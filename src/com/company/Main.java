package com.company;

import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
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

        JsonSerializer complaintsSerializer = new JsonSerializer();
        String jsonPeople = complaintsSerializer.deep(true).serialize(peopleHash);
        File com = new File("people.json");
        FileWriter comw = new FileWriter(com);
        comw.write(jsonPeople);
        comw.close();


        System.out.println(peopleHash);
    }
}
