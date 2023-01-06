package part3.ch19;

public class Employee {
    private String name;
    private String address;
    private PaymentClassification classification;
    private PaymentSchedule schedule;
    private HoldMethod method;

    public Employee(final String name, final String address) {
        this.name = name;
        this.address = address;
    }

    public PaymentClassification getClassification() {
        return classification;
    }

    public PaymentSchedule getSchedule() {
        return schedule;
    }

    public String getName() {
        return name;
    }

    public HoldMethod getMethod() {
        return method;
    }

    public void setClassification(final PaymentClassification classification) {
        this.classification = classification;
    }

    public void setSchedule(final PaymentSchedule schedule) {
        this.schedule = schedule;
    }

    public void setMethod(final HoldMethod holdMethod) {
        this.method = holdMethod;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAddress() {
       return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }
}
