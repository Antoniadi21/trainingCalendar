package Models;

import java.time.LocalDate;
import java.util.Objects;

public class Training {
    private int trainingId;
    private int userId;
    private int durationInMinutes;
    private LocalDate date;
    private TrainingType trainingType;

    public Training(int trainingId, int userId, int durationInMinutes, LocalDate date, TrainingType trainingType) {
        this.trainingId = trainingId;
        this.userId = userId;
        this.durationInMinutes = durationInMinutes;
        this.date = date;
        this.trainingType = trainingType;
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
    public String toString() {
        return "Продолжительность: " + durationInMinutes + " Дата: " + date + " Тип: " + trainingType.getType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Training training = (Training) o;

        if (trainingId != training.trainingId) return false;
        if (userId != training.userId) return false;
        if (durationInMinutes != training.durationInMinutes) return false;
        if (!date.equals(training.date)) return false;
        return trainingType.equals(training.trainingType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainingId, userId, durationInMinutes, date);
    }

}
