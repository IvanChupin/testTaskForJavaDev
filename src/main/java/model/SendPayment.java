package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sendPayment", namespace = "wsapi:Payment")
public class SendPayment {

    @XmlElement
    private int token;
    @XmlElement
    private String cardNumber;
    @XmlElement
    private String requestId;
    @XmlElement
    private double amount;
    @XmlElement
    private String currency;
    @XmlElement(name = "account",namespace = "wsapi:Utils")
    private List<Account> accounts;
    @XmlElement
    private int page;
    @XmlElement(name = "field")
    private List<Field> fields;

    public SendPayment(){}

    public SendPayment(int token, String cardNumber, String requestId, double amount, String currency, List<Account> accounts, int page, List<Field> fields) {
        this.token = token;
        this.cardNumber = cardNumber;
        this.requestId = requestId;
        this.amount = amount;
        this.currency = currency;
        this.accounts = accounts;
        this.page = page;
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "SendPayment{" +
                "token=" + token +
                ", cardNumber='" + cardNumber + '\'' +
                ", requestId='" + requestId + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", accounts=" + accounts +
                ", page=" + page +
                ", fields=" + fields +
                '}';
    }
}
