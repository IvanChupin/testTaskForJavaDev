package service;

import model.Envelope;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class CustomParserXMLtoObj {


    public static <T> T convertXMLtoObject(String xml, Class<T> type) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Envelope.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader sr = new StringReader(xml);
        return type.cast(unmarshaller.unmarshal(sr));
    }
}