package Models;

import java.time.LocalDate;
import java.util.Objects;

public class Training {
    private int trainingID;
    private int userID;
    private int durationInMinutes;
    private LocalDate date;

    public Training(int trainingID, int userID, int durationInMinutes, LocalDate date) {
        this.trainingID = trainingID;
        this.userID = userID;
        this.durationInMinutes = durationInMinutes;
        this.date = date;
    }

    public int getTrainingID() {
        return trainingID;
    }

    public void setTrainingID(int trainingID) {
        this.trainingID = trainingID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
        return trainingID == training.trainingID && userID == training.userID && durationInMinutes == training.durationInMinutes && date.equals(training.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainingID, userID, durationInMinutes, date);
    }

}
