package circuitDesignerPackage.Portes;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@XmlRootElement(name = "Circuit")
@XmlAccessorType(XmlAccessType.FIELD)
public class Circuit {

    @XmlElements({
            @XmlElement(name="Porte", type=Composante.class)
    })
    List<Composante> composantes;
    @XmlElements({
            @XmlElement(name="Connexion", type=Connexion.class)
    })
    List<Connexion> connexions;
    @XmlElements({
            @XmlElement(name="Entree", type=Composante.class)
    })
    List<Composante> entrees;
    @XmlElements({
            @XmlElement(name="Sortie", type=Composante.class)
    })
    List<Composante> sorties;

    private static Circuit uniqueInstance = null;

    public Circuit() {
        this.composantes=new ArrayList<>();
        this.connexions=new ArrayList<>();
        this.entrees=new ArrayList<>();
        this.sorties=new ArrayList<>();
    }

    public boolean addnewConnection(Connexion connexion){

        boolean connection_valide=true;
        connexions.add(connexion);
        return connection_valide;

    }

    public boolean ajouter(Composante c1){

        if(composantes.size()<=50) {
            composantes.add(c1);
            if(c1.getPorteType()== PorteType.ENTREE){
                entrees.add(c1);
            }else if (c1.getPorteType()== PorteType.SORTIE){
                sorties.add(c1);
            }
            return true;
        }else {
            return false;
        }

    }

    public void supprimer(Composante c1){
        connexions.removeIf(connec -> connec.removeConnectionFromComponent(c1));
        composantes.remove(c1);
        if(c1.getPorteType()== PorteType.ENTREE){
            entrees.remove(c1);
        }else if (c1.getPorteType()== PorteType.SORTIE){
            sorties.remove(c1);
        }
    }

    public List<Composante> getComposantes() {
        return composantes;
    }

    public List<Connexion> getConnexions() {
        return connexions;
    }

    public List<Composante> getEntrees() {
        return entrees;
    }

    public List<Composante> getSorties() {
        return sorties;
    }


    public boolean IscircuitValide(){

        //D'abord on s'assure que toutes les composantes sont bien connecté
        // si une composante est full elle est donc pleinement connecté
        // si les nombre de composantes pleinnement connecté est égale au
        // nombre de composante du circuit, alors le circuit est complétement connecté
        int nb_composante_connecte=0;

        for (Composante c1:composantes
        ) {

            if(c1.isFull()){
                nb_composante_connecte++;
            }
        }


        //détecte si il y a une boucle dans le circuit pour vérifier sa validité
        //implémentation l'algorithme de The Tortoise and the Hare Algorithm
        //ou tortoise=comp_actuel et hare=startTemp

        boolean loop=false;
        if(nb_composante_connecte == composantes.size()) {
            for (Composante c1 : entrees
            ) {

                for (int i = 0; i < c1.getSorties().length; i++) {
                    if (!c1.getSorties()[i].isDisponible()) {

                        Composante comp_actuel = c1.getSorties()[i].getNext();
                        Composante startTemp;
                        //on gère tout les cas ou startTemp pointe sur null
                        // si startTemp point sur null c'est qu'il n'y a pas
                        //de boucle dans le circuit, on arrete donc
                        if(comp_actuel.getPorteType()!=PorteType.ENTREE
                                && comp_actuel.getSorties()!=null &&
                                !comp_actuel.getSorties()[0].isDisponible()) {
                            startTemp = (comp_actuel.getSorties()[0].getNext());

                        }else {

                            break;
                        }



                        while (comp_actuel.getPorteType()!=PorteType.ENTREE
                                && !comp_actuel.getSorties()[0].isDisponible()) {

                            if (startTemp!=null&&startTemp.getPorteType()!=PorteType.ENTREE
                                    && startTemp.getSorties()!=null
                                    && !startTemp.getSorties()[0].isDisponible()) {

                                startTemp = startTemp.getSorties()[0].getNext();

                                if (startTemp!=null&&startTemp.getPorteType()!=PorteType.ENTREE
                                        && startTemp.getSorties()!=null
                                        && !startTemp.getSorties()[0].isDisponible()) {

                                    startTemp = startTemp.getSorties()[0].getNext();

                                }else {
                                    break;
                                }

                            } else {
                                break;
                            }

                            comp_actuel = comp_actuel.getSorties()[0].getNext();


                            if (startTemp == comp_actuel) {
                                loop = true;
                                System.out.println("Une boucle a été détecté");
                                break;
                            }


                        }


                    }
                }
            }
        }

        //si toute les composantes sont connectés et qu'il n'y a pas de loop,
        // le circuit est valide
        return nb_composante_connecte==composantes.size()&&!loop;
    }


    //permet de calculer les valeurs de table de verite d'une porte personnalisé en circuit
    //il faut lui passer la listes valeurs à mettre dans ses entrées
    public HashMap<Composante,Integer> speedCalculValeurTableVerite(Integer... values){

        HashMap<Composante,Integer> valeurs_sorties=new HashMap<>();
        for(int i=0;i<entrees.size();i++){
            entrees.get(i).setValue(values[i]);
        }

        for (Composante sortie:sorties
             ) {
            valeurs_sorties.put(sortie,sortie.getValue());
        }

        return valeurs_sorties;

    }



}
