package com.mcorrigal.javaFX.tables;

public class EditableTableCell<S extends Object, T extends String> extends AbstractEditableTableCell<S, T> {

    public EditableTableCell() {
        super();
    }

    @Override
    public void commitHelper(boolean loosingFocus) {
        if (getString().isEmpty()) {
            cancelEdit();
        } else {
            commitEdit((T) textField.getText());
        }
    }

    @Override
    protected String getString() {
        return getItem() == null ? "" : getItem();
    }

}
