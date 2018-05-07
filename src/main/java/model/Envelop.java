package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Envelop")
public class Envelop {

    @XmlElement(name = "Body")
    private Body body;

    public Envelop() {
    }

    public Envelop(Body body) {
        this.body = body;
    }

    @XmlElement
    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Envelop{" +
                "body=" + body +
                '}';
    }
}
