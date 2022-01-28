package circuitDesignerPackage.Operations;



import java.util.List;

public class OperateurNOT implements Operateur {
    public OperateurNOT() {
    }

    @Override
    public int applyEffectOnOperande(Operande operande) {

        List<Integer> operandes=operande.getOperandes();
        if(operandes.size() != 1){
            throw new IllegalArgumentException("Nombre d'operandes incorrect");
        }else {
            return operandes.get(0) == 0 ? 1 : 0;
        }
    }
}
