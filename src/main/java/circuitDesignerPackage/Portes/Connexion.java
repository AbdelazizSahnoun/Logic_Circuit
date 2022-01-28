package circuitDesignerPackage.Portes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Connexion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Connexion {

    private Composante entree;
    private Composante sortie;
    private Connecteur connecteurEntree;
    private Connecteur connecteurSortie;

    public Connexion(){}

    public Connexion(Composante entree, Composante sortie, Connecteur connecteurEntree, Connecteur connecteurSortie) {
        this.entree = entree;
        this.sortie = sortie;
        this.connecteurEntree = connecteurEntree;
        this.connecteurSortie = connecteurSortie;
    }

    public Connecteur getConnecteurEntree() {
        return connecteurEntree;
    }

    public Connecteur getConnecteurSortie() {
        return connecteurSortie;

    }

    public Composante getEntree() {
        return entree;
    }

    public Composante getSortie() {
        return sortie;
    }

    //return true if it's the right component
    public boolean removeConnectionFromComponent(Composante c1){
        if(entree==c1){
            connecteurSortie.resetConnexion();
            return true;
        }else if(sortie==c1){
            connecteurEntree.resetConnexion();
            return true;
        }else {
            return false;
        }
    }


}
