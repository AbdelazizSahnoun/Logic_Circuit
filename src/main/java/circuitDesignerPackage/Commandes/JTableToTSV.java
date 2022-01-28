package circuitDesignerPackage.Commandes;

import javax.swing.table.TableModel;
import java.io.FileWriter;
import java.io.IOException;

public class JTableToTSV implements Commande{

    TableModel jTable;


    public JTableToTSV(TableModel jTable) {
        this.jTable= jTable;
    }

    @Override
    public void execute(int x, int y) {
        try {
            FileWriter tableVeriteTSV = new FileWriter("TableVeriteTSV.tsv");
            for(int i = 0; i < jTable.getColumnCount(); i++)
            {
                tableVeriteTSV.write(jTable.getColumnName(i) + '\t');
            }
            tableVeriteTSV.write('\n');
            for(int i=0; i< jTable.getRowCount(); i++)
            {
                for(int j=0; j < jTable.getColumnCount(); j++)
                {

                    tableVeriteTSV.write(jTable.getValueAt(i, j).toString()+'\t');
                }
                tableVeriteTSV.write('\n');
            }

            tableVeriteTSV.close();

        }catch (IOException e){
            System.out.println("IO Exception :"+e.getMessage());
        }
    }
}

