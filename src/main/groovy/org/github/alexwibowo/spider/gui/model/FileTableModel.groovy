package org.github.alexwibowo.spider.gui.model

import com.jgoodies.binding.adapter.AbstractTableAdapter

import javax.swing.*

/**
 * User: alexwibowo
 */
class FileTableModel extends AbstractTableAdapter<File> {

    FileTableModel(ListModel<File> listModel) {
        super(listModel)
    }

    protected enum Column {
        FileName("File Name", 0),
        LastModifiedDate("Last Modified", 1),

        final String display;
        final int columnIndex;

        private Column(String display, int column) {
            this.display = display;
            this.columnIndex = column;
        }

        public static Column forColumnIndex(int columnIndex) {
            for (Column column : values()) {
                if (column.columnIndex == columnIndex) {
                    return column;
                }
            }
            throw new RuntimeException("Unknown column index for PositionKeeperTableModel " + columnIndex);
        }
    }

    @Override
    public String getColumnName(int column) {
        return Column.forColumnIndex(column).getDisplay();
    }

    @Override
    public int getColumnCount() {
        return Column.values().size();
    }


    @Override
    Object getValueAt(int rowIndex, int columnIndex) {
        if (this.rowCount == 0) {
            return null;
        }
        File row = this.getRow(rowIndex)
        Column column = Column.forColumnIndex(columnIndex);
        switch (column) {
            case Column.FileName:
                return row.name
            case Column.LastModifiedDate:
                return new Date()
        }
        return row
    }
}
