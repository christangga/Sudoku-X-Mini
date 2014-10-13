package sudokuxmini;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableData extends AbstractTableModel {
    
    final Object rowData[][] = { { "", "", "", "", "", "" },
                           { "", "", "", "", "", "" },
                           { "", "", "", "", "", "" },
                           { "", "", "", "", "", "" },
                           { "", "", "", "", "", "" },
                           { "", "", "", "", "", "" }, };
    private String columnNames[] = { "1", "2", "3", "4", "5", "6" };
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public int getRowCount() {
        return rowData.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        return rowData[row][col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
            return false;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        rowData[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    
    public void RefreshTable(List<String> angka)
    {
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<6;j++)
            {
                setValueAt("",i,j);
                if(angka.get(i).charAt(j)!='*')
                {
                    int x = Character.getNumericValue(angka.get(i).charAt(j));
                    setValueAt(x, i, j);
                }
            }
        }
    }
}

