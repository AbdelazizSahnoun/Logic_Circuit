package circuitDesignerPackage.JswingComposantes;

import circuitDesignerPackage.Portes.TableVerite;

import javax.swing.table.AbstractTableModel;

public class TableVeriteModel extends AbstractTableModel {

    TableVerite tableVerite;

    public TableVeriteModel(TableVerite tableVerite) {
        this.tableVerite=tableVerite;
    }

    @Override
    public int getRowCount() {

        return (int) Math.pow(2,this.tableVerite.getEntrees().size());
    }


    @Override
    public int getColumnCount() {
        return this.tableVerite.getEntrees().size()+this.tableVerite.getNb_sortie();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(this.tableVerite.isValide()) {
            int base = 0;
            if (rowIndex != 0) {
                base = getColumnCount() * rowIndex;
            }
            //les valeurs de la table de verite sont stocké dans le tableau rows
            //mentalement row est séparé par ligne donc si on 4 columnes dans
            // la table de verite :
            //rows : [1,0,1,1 , 0,1,1,0,....]
            //  1 0 1 1 c'est une ligne, 0 1 1 0 une autre ligne,etc.
            // et ensuite on get le columnIndex comme si de rien n'etait grace
            //au sublist pour eviter les conversion d'index de columns
            return this.tableVerite.getRows().subList(base,base+
                    this.tableVerite.getEntrees().size()
                    +this.tableVerite.getNb_sortie()).get(columnIndex);
        }else {
            return "ND";
        }

    }

    @Override
    public String getColumnName(int column) {
        return this.tableVerite.getColumnsName().get(column);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
