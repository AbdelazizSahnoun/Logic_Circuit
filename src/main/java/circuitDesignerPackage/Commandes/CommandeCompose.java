package circuitDesignerPackage.Commandes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandeCompose implements Commande{

    //Cette classe sert a créer des commandes composés,
    //dans le cas où on veut exécuter deux commande l'une après l'autre
    //sans attendre un click, c'est utilisé dans notre projet dans le cas
    //où on doit cliquer sur la deuxième composante après avoir cliqué sur la première
    //dans le cas où on relit les composantes, on veut que quand on clique sur la deuxième
    //composante le lien se crée automatiquement,
    // donc il faut qu'après le click sur le deuxième connecteur
    // on relit directement les connecteur avec AjouterLien sans attendre un autre click

    private List<Commande> commandes_execute;
    public CommandeCompose(Commande ... commandes) {
        commandes_execute=new ArrayList<>();
        commandes_execute.addAll(Arrays.asList(commandes));
    }

    public void addCommande(Commande commande){
        this.commandes_execute.add(commande);
    }

    @Override
    public void execute(int x, int y) {
        for (Commande c1:commandes_execute
             ) {
            c1.execute(x,y);
        }
    }
}
