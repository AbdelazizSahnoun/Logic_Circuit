package circuitDesignerPackage.Operations;



import java.util.List;

public class OperateurOR implements Operateur {

    public OperateurOR() {
    }

    @Override
    public int applyEffectOnOperande(Operande operande) {

        List<Integer> operandes=operande.getOperandes();
        if(operandes.size() != 2){
            throw new IllegalArgumentException("Nombre d'operandes incorrect");
        }else {
           return operandes.get(0) | operandes.get(1);
        }
    }
}
