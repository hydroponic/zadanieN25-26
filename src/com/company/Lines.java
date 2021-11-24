package com.company;
import java.util.Objects;

public class Lines {
    private String number;
    private String name;

    public Lines(String id, String name) {
        this.number = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lines line = (Lines) o;
        return Objects.equals(number, line.number) &&
                Objects.equals(name, line.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name);
    }

    public String getId() {
        return number;
    }

    public String getName() {
        return name;
    }
}