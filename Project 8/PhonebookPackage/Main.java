package PhonebookPackage;

// Frank Parker & Tony Pellican
// Project 8
// A rework of Project 7 into a GUI;
// Places a user's entry into a table, sorted by
// first and last name, phone number, notes,
// and a time stamp.

import java.io.*;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Date;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application implements Serializable{

    Stage window;
    TableView<Phonebook> table;
    TextField firstNameInput, lastNameInput, numberInput, notesInput;
    MenuBar harold;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Collection<Phonebook> list;
        list = Files.readAllLines(new File("C:\\Users\\Frank Parker\\IdeaProjects\\Project 8\\src\\PhonebookPackage\\contacts.txt").toPath())
                .stream()
                .map(line -> {
                    String[] details = line.split(",");
                    Phonebook cd;
                    cd = new Phonebook();
                    cd.setFirstName(details[0]);
                    cd.setLastName(details[1]);
                    cd.setNumber(details[2]);
                    cd.setNotes(details[3]);
                    cd.setTime(details[4]);
                    return cd;
                })
                .collect(Collectors.toList());

        ObservableList<Phonebook> details;
        details = FXCollections.observableArrayList(list);

        window = primaryStage;
        window.setTitle("Phonebook");

        TableColumn<Phonebook, String> firstNameColumn;
        firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setMinWidth(125);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Phonebook, String> lastNameColumn;
        lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setMinWidth(125);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Phonebook, String> numberColumn;
        numberColumn = new TableColumn<>("Number");
        numberColumn.setMinWidth(150);
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<Phonebook, String> notesColumn;
        notesColumn = new TableColumn<>("Notes");
        notesColumn.setMinWidth(200);
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));

        TableColumn<Phonebook, String> timeColumn;
        timeColumn = new TableColumn<>("Time");
        timeColumn.setMinWidth(225);
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        firstNameInput = new TextField();
        firstNameInput.setPromptText("Enter a first name");

        lastNameInput = new TextField();
        lastNameInput.setPromptText("Enter a last name");

        numberInput = new TextField();
        numberInput.setPromptText("Enter a phone number");

        notesInput = new TextField();
        notesInput.setPromptText("Enter some notes");

        Menu menuFile;
        menuFile = new Menu("File");
        MenuItem save;
        save = new MenuItem("Save");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleSave();
            }
        });
        MenuItem exit;
        exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                closeApp(event);
            }
        });
        Menu menuHelp;
        menuHelp = new Menu("Help");
        MenuItem help;
        help = new MenuItem("Instructions");
        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    sendHelp(event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        menuFile.getItems().addAll(save, exit);
        menuHelp.getItems().add(help);
        harold = new MenuBar();
        harold.getMenus().addAll(menuFile, menuHelp);

        Button addButton;
        addButton = new Button("Add");
        addButton.setOnAction(e -> addButtonClicked(e));
        Button clearButton;
        clearButton = new Button("Clear");
        clearButton.setOnAction(e -> clearButtonClicked());
        Button deleteButton;
        deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());

        HBox hBox;
        hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(firstNameInput, lastNameInput, numberInput, notesInput, addButton, clearButton, deleteButton);

        table = new TableView<>();
        table.setItems(details);
        table.getColumns().add(firstNameColumn);
        table.getColumns().add(lastNameColumn);
        table.getColumns().add(numberColumn);
        table.getColumns().add(notesColumn);
        table.getColumns().add(timeColumn);

        VBox vBox;
        vBox = new VBox();
        vBox.getChildren().addAll(harold, table, hBox);

        Scene scene;
        scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    public void addButtonClicked (ActionEvent event) {
        if (isValidInput(event)){
            Phonebook product = new Phonebook();
            Date Times;
            Times = new Date();
            product.setFirstName(firstNameInput.getText());
            product.setLastName(lastNameInput.getText());
            product.setNumber(numberInput.getText());
            product.setNotes(notesInput.getText());
            product.setTime(Times.toString());
            table.getItems().add(product);
            System.out.println(product.toString());
            firstNameInput.clear();
            lastNameInput.clear();
            numberInput.clear();
            notesInput.clear();
        } else {
            Alert sizeAlert;
            sizeAlert = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner;
            owner = ((Node) event.getTarget()).getScene().getWindow();
            sizeAlert.setContentText("Watch out!");
            sizeAlert.initModality(Modality.APPLICATION_MODAL);
            sizeAlert.initOwner(owner);
            sizeAlert.showAndWait();
            if (sizeAlert.getResult() == ButtonType.OK){
                sizeAlert.close();
                firstNameInput.clear();
                lastNameInput.clear();
                numberInput.clear();
                notesInput.clear();
            }
        }
    }

    private boolean isValidInput(ActionEvent event){
        Boolean validInput;
        validInput = true;

        String Test1;
        String Test2;
        String Test3;
        Test1 = "(1)?-?[0-9]{3}?-?[0-9]{3}-?[0-9]{4}";
        Test2 = "[0-9]{3}-?[0-9]{4}";
        Test3 = "(1)?-?[(]?[0-9]{3}[)]?-?[0-9]{3}-?[0-9]{4}";

        if (firstNameInput.getLength() > 8||lastNameInput.getLength() > 8){
            validInput = false;
            Alert emptyFirstName;
            emptyFirstName = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner;
            owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyFirstName.setContentText("First or last name is too long.");
            emptyFirstName.initModality(Modality.APPLICATION_MODAL);
            emptyFirstName.initOwner(owner);
            emptyFirstName.showAndWait();
            if (emptyFirstName.getResult() == ButtonType.OK) {
                emptyFirstName.close();
                firstNameInput.requestFocus();
            }
        }

        if (!(numberInput.getText().matches(Test1) || numberInput.getText().matches(Test2) || numberInput.getText().matches(Test3))) {
            validInput = false;
            Alert emptyNumber;
            emptyNumber = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner;
            owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyNumber.setContentText("Invalid Phone Number.");
            emptyNumber.initModality(Modality.APPLICATION_MODAL);
            emptyNumber.initOwner(owner);
            emptyNumber.showAndWait();
            if (emptyNumber.getResult() == ButtonType.OK) {
                emptyNumber.close();
                numberInput.requestFocus();
            }
        }

        if (firstNameInput == null || firstNameInput.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyFirstName;
            emptyFirstName = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner;
            owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyFirstName.setContentText("First name is empty");
            emptyFirstName.initModality(Modality.APPLICATION_MODAL);
            emptyFirstName.initOwner(owner);
            emptyFirstName.showAndWait();
            if(emptyFirstName.getResult() == ButtonType.OK){
                emptyFirstName.close();
                firstNameInput.requestFocus();
            }
        }

        if (lastNameInput == null || lastNameInput.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyLastName;
            emptyLastName = new Alert(Alert.AlertType.WARNING,"Warning", ButtonType.OK);
            Window owner;
            owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyLastName.setContentText("Last name is empty");
            emptyLastName.initModality(Modality.APPLICATION_MODAL);
            emptyLastName.initOwner(owner);
            emptyLastName.showAndWait();
            if(emptyLastName.getResult() == ButtonType.OK){
                emptyLastName.close();
                lastNameInput.requestFocus();
            }
        }

        if (numberInput == null || numberInput.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyNumber;
            emptyNumber = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner;
            owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyNumber.setContentText("Number is empty.");
            emptyNumber.initModality(Modality.APPLICATION_MODAL);
            emptyNumber.initOwner(owner);
            emptyNumber.showAndWait();
            if(emptyNumber.getResult() == ButtonType.OK) {
                emptyNumber.close();
                numberInput.requestFocus();
            }
        }

        if (notesInput == null || notesInput.getText().trim().isEmpty()){
            validInput = false;
            Alert emptyNotes;
            emptyNotes = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner;
            owner = ((Node) event.getTarget()).getScene().getWindow();
            emptyNotes.setContentText("Notes are empty.");
            emptyNotes.initModality(Modality.APPLICATION_MODAL);
            emptyNotes.initOwner(owner);
            emptyNotes.showAndWait();
            if (emptyNotes.getResult() == ButtonType.OK){
                emptyNotes.close();
                notesInput.requestFocus();
            }
        }
        return validInput;
    }

    public void deleteButtonClicked(){
        ObservableList<Phonebook> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();

        productSelected.forEach(allProducts::remove);
    }

    public void clearButtonClicked(){
        firstNameInput.clear();
        lastNameInput.clear();
        numberInput.clear();
        notesInput.clear();
    }

    public void handleSave() throws NullPointerException {
        Stage secondaryStage;
        secondaryStage = new Stage();
        FileChooser fileChooser;
        fileChooser = new FileChooser();
        fileChooser.setTitle("Save Phonebook");
        File file;
        file = fileChooser.showSaveDialog(secondaryStage);
        saveFile(table.getItems(), file);
    }

    private static void saveFile(ObservableList<Phonebook> data, File file){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));

            for (Phonebook entries : data) {
                out.write(entries.toString());
                out.newLine();
            }
            System.out.println(data.toString());
            out.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendHelp(ActionEvent event) throws Exception{
        if (event != null){
            ProcessBuilder pb;
            pb = new ProcessBuilder("Notepad.exe","C:\\Users\\Frank Parker\\IdeaProjects\\Project 8\\src\\PhonebookPackage\\help.txt");
            pb.start();
        }
    }

    public void closeApp(ActionEvent event){
        if (event != null) {
            Alert exitAlert;
            exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm", ButtonType.OK, ButtonType.CANCEL);
            Stage stage;
            stage = (Stage) harold.getScene().getWindow();
            exitAlert.setContentText("Are you sure you want to exit?");
            exitAlert.initModality(Modality.APPLICATION_MODAL);
            exitAlert.initOwner(stage);
            exitAlert.showAndWait();
            if (exitAlert.getResult() == ButtonType.OK)
                Platform.exit();
            else
                exitAlert.close();

        } else {
            System.out.println("Error.");
        }
    }
}