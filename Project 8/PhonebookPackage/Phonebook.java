package PhonebookPackage;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.Serializable;

public class Phonebook implements Serializable{
    StringProperty firstName = new SimpleStringProperty();
    StringProperty lastName = new SimpleStringProperty();
    StringProperty number = new SimpleStringProperty();
    StringProperty notes = new SimpleStringProperty();
    StringProperty time = new SimpleStringProperty();

    public final StringProperty firstNameProperty() {
        return this.firstName;
    }

    public final java.lang.String getFirstName() {
        return this.firstNameProperty().get();
    }

    public final void setFirstName(final java.lang.String name) {
        this.firstNameProperty().set(name);
    }

    public final StringProperty lastNameProperty() {
        return this.lastName;
    }

    public final java.lang.String getLastName() {
        return this.lastNameProperty().get();
    }

    public final void setLastName(final java.lang.String name) {
        this.lastNameProperty().set(name);
    }

    public final StringProperty numberProperty() {
        return this.number;
    }

    public final java.lang.String getNumber() {
        return this.numberProperty().get();
    }

    public final void setNumber(final java.lang.String number) {
        this.numberProperty().set(number);
    }

    public final StringProperty notesProperty() {
        return this.notes;
    }

    public final java.lang.String getNotes() {
        return this.notesProperty().get();
    }

    public final void setNotes(final java.lang.String notes) {
        this.notesProperty().set(notes);
    }

    public final StringProperty timeProperty() {
        return this.time;
    }

    public final java.lang.String getTime() {
        return this.timeProperty().get();
    }

    public final void setTime(final java.lang.String time) {
        this.timeProperty().set(time);
    }

    public String toString(){
        return getFirstName() + "," + getLastName() + "," + getNumber() + "," + getNotes() + "," + getTime();
    }
}