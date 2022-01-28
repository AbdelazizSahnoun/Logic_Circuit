package circuitDesignerPackage.Portes;
import java.util.ArrayList;
import java.util.List;


import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Composante")
@XmlAccessorType(XmlAccessType.FIELD)
public class Composante {

    @XmlElement(name = "Porte")
    Porte porte;
    @XmlElement(name = "PorteType")
    PorteType porteType;

    @XmlElements({
            @XmlElement(name="Entree", type=Connecteur.class)
    })
    Connecteur[] entres;
    @XmlElements({
            @XmlElement(name="Sortie", type=Connecteur.class)
    })
    Connecteur[] sorties;
    @XmlElement(name = "NbConnecteurEntree")
    int nb_conecteur_entree;
    @XmlElement(name = "NbConnecteurSortie")
    int nb_connecteur_sortie;
    @XmlElement(name = "Connexions")
    int connexions;
    @XmlElement(name = "Value")
    int value;
    @XmlElement(name = "Name")
    private String name;

    public int getConnexions() {
        return connexions;
    }

    public void incrementConnexion(){
        this.connexions++;
    }

    public void decrementConnexion(){
        this.connexions--;
    }

    public Composante(){}

    public Composante(Porte porte, int nb_connecteur_entree, int nb_connecteur_sortie,String name) {
        this.porte = porte;
        this.porteType = porte.getPorteType();
        this.nb_conecteur_entree=nb_connecteur_entree;
        this.nb_connecteur_sortie=nb_connecteur_sortie;

        if(nb_conecteur_entree!=0) {
            entres = new Connecteur[nb_conecteur_entree];
            for(int i=0;i<nb_conecteur_entree;i++){
                entres[i]=new Connecteur(this,ConnecteurType.entree);
            }
        }
        if(nb_connecteur_sortie!=0) {
            sorties = new Connecteur[nb_connecteur_sortie];
            for(int i=0;i<nb_connecteur_sortie;i++){
                sorties[i]=new Connecteur(this,ConnecteurType.sortie);
            }
        }

        this.name=name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public PorteType getPorteType() {
        return porteType;
    }

    public int getNb_conecteur_entree() {
        return nb_conecteur_entree;
    }

    public int getNb_connecteur_sortie() {
        return nb_connecteur_sortie;
    }

    //getValue est récursif, la composante actuelle
    //obtient les values de ses connecteurs entrée

    public int getValue() {
        List<Integer> integerList=new ArrayList<>();

        if(entres==null){
            return this.calculateValue(1,this.value);
        }else {
            for (int i = 0; i < entres.length; i++) {
                integerList.add(entres[i].getPrecedant().getValue());
            }
        }
        return this.calculateValue(entres.length,
                integerList.toArray(new Integer[0]));

    }

    public int getValueWithoutCalcul(){
        return this.value;
    }

    private int calculateValue(int nombres_operandes, Integer... values) {
        this.value= porte.applyOperation(nombres_operandes,values);
        return this.value;
    }

    public Connecteur[] getEntres() {
        return entres;
    }

    public Connecteur[] getSorties() {
        return sorties;
    }

    //calcul pour savoir si une composante est complétement connecté
    //il faut que tout ses conencteurs soit connecté sauf pour une entrée
    //ou seule un connecteur connecte suffit pour etre complétement connecté
    public boolean isFull(){

        int nb_entre=0;
        if(entres!=null){
            nb_entre=entres.length;
        }
        int nb_sortie=0;
        if(sorties!=null){
            nb_sortie=sorties.length;
        }
        if(porteType== PorteType.ENTREE){
            return (connexions == (nb_entre + nb_sortie)-1 || connexions == (nb_entre + nb_sortie)) ;
        }else {
            return connexions == nb_entre + nb_sortie;
        }
    }


    public void setValue(int value) {
        this.value=value;
    }
}
