package circuitDesignerPackage.JswingComposantes;




public class ConnexionJLabel {

    private ComposantJLabel entree;
    private ComposantJLabel sortie;
    private ConnecteurJLabel connecteurEntree;
    private ConnecteurJLabel connecteurSortie;


    public ConnexionJLabel(ComposantJLabel entree, ComposantJLabel sortie,ConnecteurJLabel connecteurEntree,ConnecteurJLabel connecteurSortie) {

        this.entree = entree;
        this.sortie = sortie;
        this.connecteurEntree=connecteurEntree;
        this.connecteurSortie=connecteurSortie;
    }

    public ConnecteurJLabel getConnecteurEntree() {
        return connecteurEntree;
    }



    public ConnecteurJLabel getConnecteurSortie() {
        return connecteurSortie;
    }

    public ComposantJLabel getEntree() {
        return entree;
    }

    public ComposantJLabel getSortie() {
        return sortie;
    }

    public boolean removeConnectionFromComponent(ComposantJLabel c1){
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
