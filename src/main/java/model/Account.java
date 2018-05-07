package model;


import javax.xml.bind.annotation.*;


@XmlRootElement(namespace = "wsapi:Utils")
@XmlAccessorType(XmlAccessType.FIELD)
public class Account {

    @XmlAttribute(name = "type")
    private String type;

    @XmlValue
    private String value;

    public String getValue() {
        return value;
    }

}