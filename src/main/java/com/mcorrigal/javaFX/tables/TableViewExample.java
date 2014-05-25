package com.mcorrigal.javaFX.tables;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import static javafx.scene.control.TableColumn.CellEditEvent;

public class TableViewExample extends Application {

    private TableView table = new TableView();
    private final ObservableList<Trade> data = FXCollections.observableArrayList(
            new Trade(true, "FO", "AMT", "BMT", "B", "CAD", "10", "123.45"),
            new Trade(true, "FO", "AMT", "BMT", "S", "PBD", "5", "98.765"));


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Example");
        stage.setWidth(850);
        stage.setHeight(500);
        Label label = new Label("Trade Script");
        Callback<TableColumn, TableCell> editableCellFactory = new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                return new EditableTableCell();
            }
        };
        TableColumn instrumentTypeCol = createInstrumentTypeColumn(editableCellFactory);
        TableColumn memberCol = createMemberColumn(editableCellFactory);
        TableColumn counterPartyCol = createCounterPartyColumn(editableCellFactory);
        TableColumn sideCol = createSideColumn(editableCellFactory);
        TableColumn symbolCol = createSymbolColumn(editableCellFactory);
        TableColumn lotsCol = createLotsColumn(editableCellFactory);
        TableColumn priceCol = createPriceColumn(editableCellFactory);
        table.setItems(data);
        table.getColumns().addAll(
                instrumentTypeCol,
                memberCol,
                counterPartyCol,
                sideCol,
                symbolCol,
                lotsCol,
                priceCol);
        table.setEditable(true);
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.getChildren().addAll(label, table);
        vBox.setPadding(new Insets(10, 0, 0, 10));
        ((Group) scene.getRoot()).getChildren().addAll(vBox);
        stage.setScene(scene);
        stage.show();
    }

    private TableColumn createActiveColumn(Callback<TableColumn, TableCell> checkBoxFactory) {
        TableColumn activeCol = new TableColumn("Active");
        activeCol.setMinWidth(25);
        activeCol.setCellValueFactory(new PropertyValueFactory<TableColumn, TableCell>("active"));
        activeCol.setCellFactory(checkBoxFactory);
        activeCol.setOnEditCommit(new EventHandler<CellEditEvent<Trade, Boolean>>() {
            @Override
            public void handle(CellEditEvent<Trade, Boolean> event) {
                for (Trade trade : data) {
                    trade.setIsActive(!trade.getIsActive());
                }
            }
        });
        return activeCol;
    }

    private TableColumn createInstrumentTypeColumn(Callback<TableColumn, TableCell> editableCellFactory) {
        TableColumn instrumentTypeCol = new TableColumn();
        instrumentTypeCol.setMinWidth(50);
        instrumentTypeCol.setCellValueFactory(new PropertyValueFactory<Trade, String>("instrumentType"));
        instrumentTypeCol.setCellFactory(editableCellFactory);
        instrumentTypeCol.setOnEditCommit(new EventHandler<CellEditEvent<Trade, String>>() {
            @Override
            public void handle(CellEditEvent<Trade, String> event) {
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setInstrumentType(event.getNewValue());
            }
        });
        return instrumentTypeCol;
    }

    private TableColumn createMemberColumn(Callback<TableColumn, TableCell> editableCellFactory) {
        TableColumn memberCol = new TableColumn();
        memberCol.setMinWidth(50);
        memberCol.setCellValueFactory(new PropertyValueFactory<Trade, String>("member"));
        memberCol.setCellFactory(editableCellFactory);
        memberCol.setOnEditCommit(new EventHandler<CellEditEvent<Trade, String>>() {
            @Override
            public void handle(CellEditEvent<Trade, String> event) {
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setMember(event.getNewValue());
            }
        });
        return memberCol;
    }

    private TableColumn createCounterPartyColumn(Callback<TableColumn, TableCell> editableCellFactory) {
        TableColumn counterpartyColumn = new TableColumn();
        counterpartyColumn.setMinWidth(50);
        counterpartyColumn.setCellValueFactory(new PropertyValueFactory<Trade, String>("counterparty"));
        counterpartyColumn.setCellFactory(editableCellFactory);
        counterpartyColumn.setOnEditCommit(new EventHandler<CellEditEvent<Trade, String>>() {
            @Override
            public void handle(CellEditEvent<Trade, String> event) {
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setCounterparty(event.getNewValue());
            }
        });
        return counterpartyColumn;
    }

    private TableColumn createSideColumn(Callback<TableColumn, TableCell> editableCellFactory) {
        TableColumn sideCol = new TableColumn();
        sideCol.setMinWidth(50);
        sideCol.setCellValueFactory(new PropertyValueFactory<Trade, String>("side"));
        sideCol.setCellFactory(editableCellFactory);
        sideCol.setOnEditCommit(new EventHandler<CellEditEvent<Trade, String>>() {
            @Override
            public void handle(CellEditEvent<Trade, String> event) {
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setSide(event.getNewValue());
            }
        });
        return sideCol;
    }

    private TableColumn createSymbolColumn(Callback<TableColumn, TableCell> editableCellFactory) {
        TableColumn sideCol = new TableColumn();
        sideCol.setMinWidth(50);
        sideCol.setCellValueFactory(new PropertyValueFactory<Trade, String>("side"));
        sideCol.setCellFactory(editableCellFactory);
        sideCol.setOnEditCommit(new EventHandler<CellEditEvent<Trade, String>>() {
            @Override
            public void handle(CellEditEvent<Trade, String> event) {
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setSide(event.getNewValue());
            }
        });
        return sideCol;
    }

    private TableColumn createLotsColumn(Callback<TableColumn, TableCell> editableCellFactory) {
        TableColumn lotsCol = new TableColumn();
        lotsCol.setMinWidth(50);
        lotsCol.setCellValueFactory(new PropertyValueFactory<Trade, String>("lots"));
        lotsCol.setCellFactory(editableCellFactory);
        lotsCol.setOnEditCommit(new EventHandler<CellEditEvent<Trade, String>>() {
            @Override
            public void handle(CellEditEvent<Trade, String> event) {
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setLots(event.getNewValue());
            }
        });
        return lotsCol;
    }

    private TableColumn createPriceColumn(Callback<TableColumn, TableCell> editableCellFactory) {
        TableColumn priceCol = new TableColumn();
        priceCol.setMinWidth(50);
        priceCol.setCellValueFactory(new PropertyValueFactory<Trade, String>("price"));
        priceCol.setCellFactory(editableCellFactory);
        priceCol.setOnEditCommit(new EventHandler<CellEditEvent<Trade, String>>() {
            @Override
            public void handle(CellEditEvent<Trade, String> event) {
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setPrice(event.getNewValue());
            }
        });
        return priceCol;
    }

}
