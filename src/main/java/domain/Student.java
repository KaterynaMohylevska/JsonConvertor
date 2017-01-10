package domain;

import json.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    protected Tuple<String, Integer>[] exam;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exam = new Tuple[exams.length];
        int counter = 0;
        for (Tuple<String, Integer> ex : exams) {
            exam[counter] = ex;
            counter++;
        }
    }

    @Override
    public JsonObject toJsonObject() {
        JsonPair name = new JsonPair("name", new JsonString(this.name));
        JsonPair surname = new JsonPair("surname", new JsonString(this.surname));
        JsonPair year = new JsonPair("year", new JsonNumber(this.year));
        JsonPair ex;
        if (this.exam.length == 0) {
            ex = new JsonPair("exams", new JsonArray());
        } else {
            JsonObject[] obj = new JsonObject[this.exam.length];
            int count = 0;
            for (Tuple<String, Integer> e : this.exam) {
                JsonPair course = new JsonPair("course", new JsonString(e.key));
                JsonPair mark = new JsonPair("mark", new JsonNumber(e.value));
                JsonPair passed;
                if (e.value > 2) {
                    passed = new JsonPair("passed", new JsonBoolean(true));
                } else {
                    passed = new JsonPair("passed", new JsonBoolean(false));
                }
                JsonObject jsonExam = new JsonObject(course, mark, passed);
                obj[count] = jsonExam;
                count++;
            }

            ex = new JsonPair("exams", new JsonArray(obj));
        }
        JsonObject jsonObj = new JsonObject(name, surname, year, ex);
        return jsonObj;
    }
}