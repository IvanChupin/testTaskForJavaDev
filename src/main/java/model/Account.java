package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(namespace = "wsapi:Utils")
public class Account {

    @XmlAttribute(name = "type")
    private String type;
    @XmlValue
    private String value;

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @XmlValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Account{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
