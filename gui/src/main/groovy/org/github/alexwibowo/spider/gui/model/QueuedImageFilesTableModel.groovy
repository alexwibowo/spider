package org.github.alexwibowo.spider.gui.model

import com.jgoodies.binding.adapter.AbstractTableAdapter

import javax.swing.*
import java.text.SimpleDateFormat

/**
 * User: alexwibowo
 */
class QueuedImageFilesTableModel extends AbstractTableAdapter<FileEntry> {

    QueuedImageFilesTableModel(ListModel<FileEntry> listModel) {
        super(listModel)
    }

    protected enum Column {
        Status("", 0),
        FileName("File Name", 1),
        LastModifiedDate("Last Modified", 2),
        Barcode("Barcode", 3),
        ProductName("Product Name", 4)

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
            throw new RuntimeException("Unknown column index for  " + columnIndex);
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
        def df = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss")

        FileEntry fileEntry = this.getRow(rowIndex)
        File file = fileEntry.file
        Column column = Column.forColumnIndex(columnIndex);
        switch (column) {
            case Column.FileName:
                return file.name
            case Column.LastModifiedDate:
                return df.format(file.lastModified())
            case Column.Status:
                return fileEntry.status
            case Column.Barcode:
                return fileEntry.barcode
            case Column.ProductName:
                return fileEntry.itemName
        }
        return file
    }
}
