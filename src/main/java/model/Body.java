package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Body {

    @XmlElement(namespace = "wsapi:Payment")
    private SendPayment sendPayment;

    public Body() {
    }

    public Body(SendPayment sendPayment) {
        this.sendPayment = sendPayment;
    }

    @XmlElement
    public SendPayment getSendPayment() {
        return sendPayment;
    }

    public void setSendPayment(SendPayment sendPayment) {
        this.sendPayment = sendPayment;
    }
}
