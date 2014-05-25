package com.mcorrigal.javaFX.tables;

public class EditableTableCell<S extends Object, T extends String> extends AbstractEditableTableCell<S, T> {

    public EditableTableCell() {}

    @Override
    public void commitHelper(boolean loosingFocus) {
        if ("".equals(getString())) {
            cancelEdit();
        } else {
            commitEdit((T) textField.getText());
        }
    }

    @Override
    protected String getString() {
        return getItem() == null ? "" : getItem().toString();
    }

}
