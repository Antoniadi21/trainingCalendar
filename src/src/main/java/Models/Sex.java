package Models;

import java.util.Objects;

public class Sex {
    private int sexID;
    private char sex;

    public Sex(int sexID, char sex) {
        this.sexID = sexID;
        this.sex = sex;
    }

    public int getSexID() {
        return sexID;
    }

    public void setSexID(int sexID) {
        this.sexID = sexID;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sex sex1 = (Sex) o;
        return sexID == sex1.sexID && sex == sex1.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sexID, sex);
    }
}
