package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ItemToolController {
    @FXML public ImageView imgLabelTool;
    @FXML public Label labelToolName;
    @FXML public Label labelBrand;
    @FXML public Label labelSerialNumber;
    @FXML public Label labelCondition;
    @FXML public Label labelStartOfOperation;
    @FXML public GridPane gridPaneItemTool;

    public void setImgLabelTool(ImageView imgLabelTool) {
        this.imgLabelTool = imgLabelTool;
    }

    public void setLabelToolName(String labelToolName) {
        this.labelToolName.setText(labelToolName);
    }

    public void setLabelBrand(String labelBrand) {
        this.labelBrand.setText(labelBrand);
    }

    public void setLabelSerialNumber(String labelSerialNumber) {
        this.labelSerialNumber.setText(labelSerialNumber);
    }

    public void setLabelCondition(String labelCondition) {
        this.labelCondition.setText(labelCondition);
    }

    public void setLabelStartOfOperation(String labelStartOfOperation) {
        this.labelStartOfOperation.setText(labelStartOfOperation);
    }
}
