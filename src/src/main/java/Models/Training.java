package Models;

import java.time.LocalDate;
import java.util.Objects;

public class Training {
    private int trainingId;
    private int userId;
    private int durationInMinutes;
    private LocalDate date;

    public Training(int trainingId, int userId, int durationInMinutes, LocalDate date) {
        this.trainingId = trainingId;
        this.userId = userId;
        this.durationInMinutes = durationInMinutes;
        this.date = date;
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return trainingId == training.trainingId && userId == training.userId && durationInMinutes == training.durationInMinutes && date.equals(training.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainingId, userId, durationInMinutes, date);
    }

}
