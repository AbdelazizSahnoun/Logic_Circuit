package circuitDesignerPackage.Portes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "PorteType")
@XmlEnum
public enum PorteType {
    AND,
    OR,
    NOT,
    PERSONNALISE,
    ENTREE,
    SORTIE
}
