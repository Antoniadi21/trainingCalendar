package Models;

import java.util.Objects;

public class Sex {
    private int sexId;
    private char sex;

    public Sex(int sexId, char sex) {
        this.sexId = sexId;
        this.sex = sex;
    }

    public int getSexId() {
        return sexId;
    }

    public void setSexId(int sexId) {
        this.sexId = sexId;
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
        return sexId == sex1.sexId && sex == sex1.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sexId, sex);
    }
}
