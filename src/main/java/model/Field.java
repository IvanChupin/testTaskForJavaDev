package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Field {

    @XmlAttribute
    private int id;
    @XmlAttribute
    private String value;

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}