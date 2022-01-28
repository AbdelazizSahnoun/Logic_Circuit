package circuitDesignerPackage.Operations;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "EntreeSortie")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class EntreeSortie implements Operateur {

    public EntreeSortie() {
    }

    @Override
    public int applyEffectOnOperande(Operande operande) {
        List<Integer> operandes=operande.getOperandes();
        if(operandes.size() != 1){
            throw new IllegalArgumentException("Nombre d'operandes incorrect");
        }else {
            return operandes.get(0);
        }
    }
}
