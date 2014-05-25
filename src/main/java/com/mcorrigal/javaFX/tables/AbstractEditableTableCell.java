package com.mcorrigal.javaFX.tables;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.KeyCode.*;

public abstract class AbstractEditableTableCell<S, T> extends TableCell<S, T> {

    protected TextField textField;
    public AbstractEditableTableCell() {}
    public abstract void commitHelper(boolean loosingFocus);
    protected abstract String getString();

    @Override
    public void startEdit() {
        super.startEdit();
        if (textField == null) {
            createTextField();
        }
        setGraphic(textField);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                textField.selectAll();
                textField.requestFocus();
            }
        });
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getString());
        setContentDisplay(ContentDisplay.TEXT_ONLY);
        textField = null;
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setGraphic(textField);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            } else {
                setText(getString());
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            }
        }

    }

    private void createTextField() {
        textField = new TextField();
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (keyPressed(event, ENTER)) {
                    commitHelper(false);
                } else if (keyPressed(event, ESCAPE)) {
                    cancelEdit();
                } else if (keyPressed(event, TAB)) {
                    commitHelper(false);
                    TableColumn nextColumn = getNextTableColumn(!event.isShiftDown());
                    if (nextColumn != null) {
                        getTableView().edit(getTableRow().getIndex(), nextColumn);
                    }
                }
            }
        });
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue && textField != null) {
                    commitHelper(true);
                }
            }
        });
    }

    private TableColumn getNextTableColumn(boolean forward) {
        List<TableColumn<S, ?>> columns = new ArrayList<TableColumn<S, ?>>();
        for (TableColumn<S, ?> column : getTableView().getColumns()) {
            columns.addAll(getLeaves(column));
        }
        if (columns.size() < 2) {
            return null;
        }
        int currentIndex = columns.indexOf(getTableColumn());
        int nextIndex = currentIndex;
        if (forward) {
            nextIndex ++;
            if (nextIndex > columns.size() - 1) {
                nextIndex = 0;
            }
        } else {
            nextIndex --;
            if (nextIndex < 0) {
                nextIndex = columns.size() - 1;
            }
        }
        return columns.get(nextIndex);
    }

    private List<? extends TableColumn<S, ?>> getLeaves(TableColumn<S, ?> root) {
        List<TableColumn<S, ?>> columns = new ArrayList<TableColumn<S, ?>>();
        if (root.getColumns().isEmpty()) {
            if (root.isEditable()) {
                columns.add(root);
            }
            return columns;
        } else {
            for (TableColumn<S, ?> column : root.getColumns()) {
                columns.addAll(getLeaves(column));
            }
            return columns;
        }
    }

    private boolean keyPressed(KeyEvent event, KeyCode keyCode) {
        return event.getCode() == keyCode;
    }

}
