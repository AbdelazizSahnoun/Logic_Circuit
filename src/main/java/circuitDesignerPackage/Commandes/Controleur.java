package circuitDesignerPackage.Commandes;

import circuitDesignerPackage.JswingComposantes.CircuitJLayredPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class Controleur implements ActionListener {

    //La classe controleur execute les commandes, on a distingue trois types de commande
    // les click qui vont dans commandesActuelle
    // les commande permanente qui sont exécuté en tout temps à chaque click
    //comme afficher le nom des composantes
    //et les actions qui sont des commandes a executé automatique après avoir clicke
    //dans un bouton jswing, donc le calcul de table le changement de nom d'une composante
    // il faut specifier le string qui va avec l'action dans le event,
    // pour destinguer les events

    CircuitJLayredPane circuitJLayredPane;
    private int x_actuelle=0;
    private int y_actuelle=0;
    private Queue<Commande> commandesActuelle;
    private Commande commandePermanente;
    private HashMap<String,Commande> actions;
    private boolean active=false;


    public Controleur(CircuitJLayredPane circuitJLayredPane) {
        this.circuitJLayredPane=circuitJLayredPane;
        this.commandesActuelle =new LinkedList<>();
        this.actions =new HashMap<>();
        this.circuitJLayredPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x_actuelle = e.getX();
                y_actuelle = e.getY();
                executePermanente();
                if(active) {
                    executeCommande(commandesActuelle.poll());
                }
            }
        });
    }

    public void executeCommande(Commande commande){
        if(commande!=null) {
            commande.execute(x_actuelle, y_actuelle);

        }
    }

    public void forceExecute(Commande c1,int x,int y){

        c1.execute(x,y);
        this.commandesActuelle.remove(c1);

    }

    public void addNextCommande(Commande... commande){
        commandesActuelle.addAll(Arrays.asList(commande));
        active=true;
    }

    public boolean isEmpty(){
        return this.commandesActuelle.isEmpty();
    }

    public void DeletePastCommande(){
        active=false;
        this.commandesActuelle.clear();
    }

    public void DeletePastAction(){
        active=false;
        this.actions =null;
    }

    public void DeletePastCommandePermanente(){
        active=false;
        this.commandePermanente=null;
    }

    public void ajouterCommandePermanente(Commande commande){
        this.commandePermanente=commande;
    }

    public void executePermanente(){
        this.commandePermanente.execute(x_actuelle,y_actuelle);
    }

    public void ajouterAction(String nom_action,Commande action){
        this.actions.put(nom_action,action);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        for (String key : this.actions.keySet()) {
            if(key.equals(e.getSource())){
                actions.get(key).execute(x_actuelle,y_actuelle);
            }
        }
    }
}
