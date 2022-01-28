package circuitDesignerPackage.Operations;

import java.util.*;

public class Operande {

   private HashMap<Integer,Integer> operateurs_values;
   private int compteur_operandes_max;
   private int compteur_operandes;

    public Operande(int compteur_operandes_max) {
        this.operateurs_values = new HashMap<>();
        this.compteur_operandes_max=compteur_operandes_max;
        this.compteur_operandes=0;
    }

    public void addOperandeValue(Integer value){
        if(compteur_operandes_max!=compteur_operandes) {
            this.operateurs_values.put(compteur_operandes,value);
            compteur_operandes++;
        }
    }

    public List<Integer> getOperandes(){

       List<Integer> retour_operandes=new ArrayList<>();
       operateurs_values.forEach((key, value) -> {
           retour_operandes.add(value);
       });

       return Collections.unmodifiableList(retour_operandes);
    }
}
