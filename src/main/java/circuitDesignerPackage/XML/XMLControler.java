package circuitDesignerPackage.XML;

import circuitDesignerPackage.JswingComposantes.CircuitJLayredPane;
import circuitDesignerPackage.Portes.Circuit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

public class XMLControler {


    public static void marshal(CircuitJLayredPane circuit, File path) throws JAXBException, IOException {

        JAXBContext context = JAXBContext.newInstance(Circuit.class);
        Marshaller mar= context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(circuit.getCircuit(), path);
    }

    public static CircuitJLayredPane unMarshal(String path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Circuit.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return  (CircuitJLayredPane) jaxbUnmarshaller.unmarshal(new File(path));
    }
}
