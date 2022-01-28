package circuitDesignerPackage.Portes;

import circuitDesignerPackage.Operations.*;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Porte")
@XmlAccessorType(XmlAccessType.FIELD)

public class Porte {

    @XmlElements({
            @XmlElement(name="OperateurAND", type= OperateurAND.class),
            @XmlElement(name="OperateurOR", type= OperateurOR.class),
            @XmlElement(name="OperateurNOT", type= OperateurNOT.class),
            @XmlElement(name="EntreeSortie", type= EntreeSortie.class)
    })
    private Operateur operateur;
    @XmlElement(required = true)
    private PorteType porteType;

    public PorteType getPorteType() {
        return porteType;
    }

    public Porte(){
        // JAXB oblige d'avoir un constructeur sans argement.
    }

    public Porte(Operateur operateur, PorteType porteType) {
        this.operateur = operateur;
        this.porteType = porteType;
    }

    public int applyOperation(int nombres_operandes, Integer[] values){
        Operande operande=new Operande(nombres_operandes);
        for(int i=0;i<nombres_operandes;i++){
            operande.addOperandeValue(values[i]);
        }
        return operateur.applyEffectOnOperande(operande);
    }
}
