package org.github.alexwibowo.spider.gui.model

import com.jgoodies.binding.adapter.AbstractTableAdapter
import org.github.alexwibowo.spider.catalogue.Product

import javax.swing.ListModel

/**
 * User: alexwibowo
 */
class CatalogueTableModel extends AbstractTableAdapter<Product> {

    CatalogueTableModel(ListModel<Product> listModel) {
        super(listModel)
    }

    protected enum Column {
        Barcode("Barcode", 0),
        ProductName("Product Name", 1)

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

        Product product = this.getRow(rowIndex)
        Column column = Column.forColumnIndex(columnIndex);
        switch (column) {
            case Column.Barcode:
                return product.barcode
            case Column.ProductName:
                return product.name
            default:
                throw new IllegalStateException("Unsupported column " + column)
        }
    }
}
