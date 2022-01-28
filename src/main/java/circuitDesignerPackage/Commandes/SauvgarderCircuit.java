package circuitDesignerPackage.Commandes;

import circuitDesignerPackage.JswingComposantes.CircuitJLayredPane;
import circuitDesignerPackage.XML.XMLControler;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class SauvgarderCircuit implements Command{
    private CircuitJLayredPane circuit;
    //private DocumentElement root;

    public SauvgarderCircuit(CircuitJLayredPane circuit)
    {
        this.circuit = circuit;
    }

    @Override
    public void execute() {
        // Créer un sélecteur de fichier
        JFileChooser jFileChooser = new JFileChooser("f:");
        jFileChooser.setFileFilter(new FileNameExtensionFilter("XML Document File", new String[] {"XML"}));
        jFileChooser.setDialogTitle("Specifier l'emplacement de sauvegrade");


        int userSelection = jFileChooser.showSaveDialog(jFileChooser);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = jFileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());

            SwingUtilities.updateComponentTreeUI(jFileChooser);
            // Si l'utilisateur sélectionne le fichier
            // Définir le chemin absolu vers le répertoire sélectionné
            File file = new File(jFileChooser.getSelectedFile().getAbsolutePath().replaceAll("\\.xml", "") + ".xml");

            try {
                XMLControler.marshal(circuit,file);
            } catch (Exception evt) {
                evt.printStackTrace();
                JOptionPane.showMessageDialog(jFileChooser, "Erreur: " +evt.getMessage());
                //mainWindow.showDialog(evt.getMessage(), "Erreur");
            }
        }
        // Si l'utilisateur annule l'opération
        else{
            //mainWindow.showDialog("Annuler l'opération de sauvegarde du fichier", "OK");
            JOptionPane.showMessageDialog(jFileChooser, "Annuler l'opération de sauvegarde du fichier");
        }

    }



}
