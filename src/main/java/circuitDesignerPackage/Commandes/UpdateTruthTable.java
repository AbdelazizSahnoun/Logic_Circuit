package circuitDesignerPackage.Commandes;

import circuitDesignerPackage.JswingComposantes.CircuitJLayredPane;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UpdateTruthTable implements Commande, PropertyChangeListener {

    //class qui met la jour la table de verité, implémente PropertyChangeListener
    // qui agit comme observer sur la class CircuitJLayredPane qui est l'observable
    //et de pouvoir déterminer si un ajout, suppresion, liaison a été faite pour mettre
    //à jour la table de verité


    CircuitJLayredPane circuitJLayredPane;
    private JScrollPane scrollPane;
    private Boolean circuit_a_change;


    public UpdateTruthTable(CircuitJLayredPane circuitJLayredPane, JScrollPane scrollPane) {
        this.circuitJLayredPane = circuitJLayredPane;
        this.circuitJLayredPane.ajouterPropertyChangeListener(this);
        this.scrollPane=scrollPane;
    }


    @Override
    public void execute(int x, int y) {
        if(!circuitJLayredPane.IscircuitValide()){
            System.out.println("Le circuit est invalide");
        }
        scrollPane.setViewportView(new JTable(this.circuitJLayredPane.updateTruthTable()));

    }

    public void setCircuit_a_change(Boolean circuit_a_change) {
        this.circuit_a_change = circuit_a_change;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.setCircuit_a_change((Boolean) evt.getNewValue());
        if(circuit_a_change){
            this.execute(0,0);
        }
    }
}
