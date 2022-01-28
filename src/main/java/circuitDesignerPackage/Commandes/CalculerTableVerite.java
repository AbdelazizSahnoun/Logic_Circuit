package circuitDesignerPackage.Commandes;

import circuitDesignerPackage.JswingComposantes.CircuitJLayredPane;

import javax.swing.*;

public class CalculerTableVerite implements Commande{

    CircuitJLayredPane circuitJLayredPane;
    private JScrollPane scrollPane;//c'est ce qui va nous permettre de mettre à jour
    //la table de vertie dans le frame

    public CalculerTableVerite(CircuitJLayredPane circuitJLayredPane, JScrollPane scrollPane) {
        this.circuitJLayredPane = circuitJLayredPane;
        this.scrollPane=scrollPane;
    }


    @Override
    public void execute(int x, int y) {
        if(!circuitJLayredPane.IscircuitValide()){
            System.out.println("Le circuit est invalide");
        }
        scrollPane.setViewportView(new JTable(this.circuitJLayredPane.getTableVerite()));

    }


}
