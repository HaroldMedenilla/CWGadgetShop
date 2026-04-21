import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class GadgetShop extends Application {

    private TextField modelField        = new TextField();
    private TextField priceField        = new TextField();
    private TextField weightField       = new TextField();
    private TextField sizeField         = new TextField();
    private TextField creditField       = new TextField();
    private TextField memoryField       = new TextField();
    private TextField phoneNumberField  = new TextField();
    private TextField durationField     = new TextField();
    private TextField downloadSizeField = new TextField();
    private TextField displayNumField   = new TextField();
    private TextArea  logArea           = new TextArea();

    private ArrayList<Gadget> gadgets = new ArrayList<>();

    @Override
    public void start(Stage stage) {

        Pane root = new Pane();

        // Labels
        Label modelLbl        = new Label("Model:");
        Label priceLbl        = new Label("Price:");
        Label weightLbl       = new Label("Weight:");
        Label sizeLbl         = new Label("Size:");
        Label creditLbl       = new Label("Credit:");
        Label memoryLbl       = new Label("Memory:");
        Label phoneLbl        = new Label("Phone Number:");
        Label durationLbl     = new Label("Duration:");
        Label downloadLbl     = new Label("Download Size:");
        Label displayLbl      = new Label("Display Number:");

        // Position labels
        modelLbl.setLayoutX(10);        modelLbl.setLayoutY(10);
        priceLbl.setLayoutX(10);        priceLbl.setLayoutY(35);
        weightLbl.setLayoutX(10);       weightLbl.setLayoutY(60);
        sizeLbl.setLayoutX(10);         sizeLbl.setLayoutY(85);
        creditLbl.setLayoutX(10);       creditLbl.setLayoutY(110);
        memoryLbl.setLayoutX(10);       memoryLbl.setLayoutY(135);
        phoneLbl.setLayoutX(10);        phoneLbl.setLayoutY(160);
        durationLbl.setLayoutX(10);     durationLbl.setLayoutY(185);
        downloadLbl.setLayoutX(10);     downloadLbl.setLayoutY(210);
        displayLbl.setLayoutX(10);      displayLbl.setLayoutY(235);

        // Position fields
        modelField.setLayoutX(130);         modelField.setLayoutY(10);
        priceField.setLayoutX(130);         priceField.setLayoutY(35);
        weightField.setLayoutX(130);        weightField.setLayoutY(60);
        sizeField.setLayoutX(130);          sizeField.setLayoutY(85);
        creditField.setLayoutX(130);        creditField.setLayoutY(110);
        memoryField.setLayoutX(130);        memoryField.setLayoutY(135);
        phoneNumberField.setLayoutX(130);   phoneNumberField.setLayoutY(160);
        durationField.setLayoutX(130);      durationField.setLayoutY(185);
        downloadSizeField.setLayoutX(130);  downloadSizeField.setLayoutY(210);
        displayNumField.setLayoutX(130);    displayNumField.setLayoutY(235);

        // Buttons
        Button addMobileBtn  = new Button("Add Mobile");
        Button addMp3Btn     = new Button("Add MP3");
        Button displayAllBtn = new Button("Display All");
        Button makeCallBtn   = new Button("Make A Call");
        Button downloadBtn   = new Button("Download Music");
        Button clearBtn      = new Button("Clear");

        addMobileBtn.setLayoutX(10);    addMobileBtn.setLayoutY(270);
        addMp3Btn.setLayoutX(100);      addMp3Btn.setLayoutY(270);
        displayAllBtn.setLayoutX(180);  displayAllBtn.setLayoutY(270);
        makeCallBtn.setLayoutX(270);    makeCallBtn.setLayoutY(270);
        downloadBtn.setLayoutX(10);     downloadBtn.setLayoutY(300);
        clearBtn.setLayoutX(140);       clearBtn.setLayoutY(300);

        // Log area
        logArea.setLayoutX(10);
        logArea.setLayoutY(340);
        logArea.setPrefSize(460, 150);
        logArea.setEditable(false);

        // Add everything to pane
        root.getChildren().addAll(
            modelLbl, priceLbl, weightLbl, sizeLbl, creditLbl,
            memoryLbl, phoneLbl, durationLbl, downloadLbl, displayLbl,
            modelField, priceField, weightField, sizeField, creditField,
            memoryField, phoneNumberField, durationField, downloadSizeField, displayNumField,
            addMobileBtn, addMp3Btn, displayAllBtn, makeCallBtn, downloadBtn, clearBtn,
            logArea
        );

        // Button actions - each calls its own handle method
        addMobileBtn.setOnAction(e -> handleAddMobile());
        addMp3Btn.setOnAction(e -> handleAddMP3());
        clearBtn.setOnAction(e -> handleClear());
        displayAllBtn.setOnAction(e -> handleDisplayAll());
        makeCallBtn.setOnAction(e -> handleMakeCall());
        downloadBtn.setOnAction(e -> handleDownloadMusic());

        stage.setScene(new Scene(root, 490, 510));
        stage.setTitle("Gadget Shop");
        stage.show();
    }

    private void handleAddMobile() {
        try {
            Mobile m = new Mobile(
                modelField.getText(),
                Double.parseDouble(priceField.getText()),
                Integer.parseInt(weightField.getText()),
                sizeField.getText(),
                Integer.parseInt(creditField.getText())
            );
            gadgets.add(m);
            logArea.appendText("Added Mobile: " + m.getModel() + "\n");
        } catch (Exception ex) {
            logArea.appendText("Invalid input for Mobile.\n");
        }
    }

    private void handleAddMP3() {
        try {
            MP3 mp3 = new MP3(
                modelField.getText(),
                Double.parseDouble(priceField.getText()),
                Integer.parseInt(weightField.getText()),
                sizeField.getText(),
                Integer.parseInt(memoryField.getText())
            );
            gadgets.add(mp3);
            logArea.appendText("Added MP3: " + mp3.getModel() + "\n");
        } catch (Exception ex) {
            logArea.appendText("Invalid input for MP3.\n");
        }
    }

    private void handleClear() {
        modelField.clear();
        priceField.clear();
        weightField.clear();
        sizeField.clear();
        creditField.clear();
        memoryField.clear();
        phoneNumberField.clear();
        durationField.clear();
        downloadSizeField.clear();
        displayNumField.clear();
    }

    private void handleDisplayAll() {
        if (gadgets.isEmpty()) {
            logArea.appendText("No gadgets.\n");
        } else {
            for (int i = 0; i < gadgets.size(); i++) {
                logArea.appendText("--- Gadget " + i + " ---\n");
                logArea.appendText(getGadgetDetails(gadgets.get(i)));
            }
        }
    }

    private void handleMakeCall() {
        int index = getDisplayNumber();
        if (index != -1) {
            Gadget g = gadgets.get(index);
            if (g instanceof Mobile) {
                Mobile m = (Mobile) g;
                try {
                    int duration = Integer.parseInt(durationField.getText());
                    m.makeCall(phoneNumberField.getText(), duration);
                    logArea.appendText("Remaining credit: " + m.getCallCredit() + " mins\n");
                } catch (NumberFormatException ex) {
                    logArea.appendText("Duration must be a whole number.\n");
                }
            } else {
                logArea.appendText("Gadget " + index + " is not a Mobile.\n");
            }
        }
    }

    private void handleDownloadMusic() {
        int index = getDisplayNumber();
        if (index != -1) {
            Gadget g = gadgets.get(index);
            if (g instanceof MP3) {
                MP3 mp3 = (MP3) g;
                try {
                    int size = Integer.parseInt(downloadSizeField.getText());
                    mp3.downloadMusic(size);
                    logArea.appendText("Memory remaining: " + mp3.getMemory() + " MB\n");
                } catch (NumberFormatException ex) {
                    logArea.appendText("Download size must be a whole number.\n");
                }
            } else {
                logArea.appendText("Gadget " + index + " is not an MP3.\n");
            }
        }
    }

    private int getDisplayNumber() {
        int displayNumber = -1;
        try {
            int input = Integer.parseInt(displayNumField.getText());
            if (input >= 0 && input < gadgets.size()) {
                displayNumber = input;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Index");
                alert.setContentText("Enter a number between 0 and " + (gadgets.size() - 1) + ".");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setContentText("Display number must be a whole number.");
            alert.showAndWait();
        }
        return displayNumber;
    }

    private String getGadgetDetails(Gadget g) {
        String d = "Model: " + g.getModel() + "\n" +
                   "Price: " + g.getPrice() + "\n" +
                   "Weight: " + g.getWeight() + "g\n" +
                   "Size: " + g.getSize() + "\n";
        if (g instanceof Mobile)
            d += "Credit: " + ((Mobile) g).getCallCredit() + " mins\n";
        else if (g instanceof MP3)
            d += "Memory: " + ((MP3) g).getMemory() + " MB\n";
        return d;
    }

    public static void main(String[] args) {
        launch();
    }
}
