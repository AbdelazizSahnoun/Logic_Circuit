package circuitDesignerPackage.Portes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TableVerite {

    List<Composante> composants;
    List<Composante> entrees;
    HashMap<String, Integer[]> valeurs;
    List<String> columnsName = new ArrayList<>();
    int nb_sortie=0;
    List <Integer> rows =new ArrayList<>();
    boolean valide;


    public List<Composante> getEntrees() {
        return entrees;
    }

    public HashMap<String, Integer[]> getValeurs() {
        return valeurs;
    }

    public List<String> getColumnsName() {
        return columnsName;
    }

    public int getNb_sortie() {
        return nb_sortie;
    }

    public List<Integer> getRows() {
        return rows;
    }

    public boolean isValide() {
        return valide;
    }

    public TableVerite(List<Composante> composants, List<Composante> entrees, boolean valide) {
        this.composants = composants;
        this.entrees = entrees;
        this.valide=valide;

        calculerTableVerite();
    }


    public static int lignes;
    public static int noeud;
    public static int[][] table_verite_values = new int[0][0];

    public void initialiseValeursEntresTableVerite(int n) {
        this.noeud = n;
        this.lignes = (int) Math.pow(2, n);
        table_verite_values = new int[lignes][noeud];

        for (int i = 0; i < lignes; i++) {
            for (int j = n - 1; j >= 0; j--) {
                table_verite_values[i][j] = (i / (int) Math.pow(2, j)) % 2;
            }
        }
    }



    public void calculateTruthTable() {
        initialiseValeursEntresTableVerite(entrees.size());
        //intialise les valeurs a mettre dans entrée
        for (int ii = 0; ii < lignes; ii++) {
            int z=0;
            for (int j = noeud - 1; j >= 0; j--) {
                entrees.get(z).setValue(table_verite_values[ii][j]);
                rows.add(entrees.get(z).getValueWithoutCalcul());
                z++;
            }
            for (Composante c1 : composants
            ) {
                //on exécute un getValue() sur les sorties car
                //vu que getValue est récursive, toutes les composantes qui lui
                //sont connectés sont calculés en même temps et on obtient
                //la valeur a mettre dans la table de verite
                if(c1.getPorteType()== PorteType.SORTIE){
                    rows.add(c1.getValue());
                }
            }

        }
    }


    public void initializeCOlumnsName(){
        for (Composante c1 : entrees
        ) {
            columnsName.add(c1.getName());
        }

        for (Composante c1 : composants
        ) {
            if (c1.getPorteType() == PorteType.SORTIE) {
                nb_sortie++;
                columnsName.add(c1.getName());
            }
        }
    }

    public void calculerTableVerite(){
        if(valide){
            initializeCOlumnsName();
            calculateTruthTable();
        }else {
            initializeCOlumnsName();
        }
    }




}
