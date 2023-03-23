package Models;

import java.util.Objects;

public class Training_type {
    private int trainingId;
    private String type;

    public Training_type(int trainingId, String type) {
        this.trainingId = trainingId;
        this.type = type;
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training_type that = (Training_type) o;
        return trainingId == that.trainingId && type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainingId, type);
    }
}
