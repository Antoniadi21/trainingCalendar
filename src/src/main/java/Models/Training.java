package Models;

import java.time.LocalDate;
import java.util.Objects;

public class Training {
    private final int trainingID;
    private final int userID;
    private final int durationInMinutes;
    private final LocalDate date;

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

    public Training(int trainingID, int userID, int durationInMinutes, LocalDate date) {
        this.trainingID = trainingID;
        this.userID = userID;
        this.durationInMinutes = durationInMinutes;
        this.date = date;
    }
}
