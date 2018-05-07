package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Body")
@XmlAccessorType(XmlAccessType.FIELD)
public class Body {
    @XmlElement(namespace = "wsapi:Payment")
    private SendPayment sendPayment;


    public SendPayment getSendPayment() {
        return sendPayment;
    }

    public void setSendPayment(SendPayment sendPayment) {
        this.sendPayment = sendPayment;
    }
}
