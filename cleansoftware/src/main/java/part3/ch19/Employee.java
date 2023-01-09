package part3.ch19;

public class Employee {
    private String name;
    private String address;
    private PaymentClassification classification;
    private PaymentSchedule schedule;
    private PaymentMethod method;
    private Affiliation affiliation;

    public Employee(final String name, final String address) {
        this.name = name;
        this.address = address;
        this.affiliation = new NoAffiliation();
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

    public PaymentMethod getMethod() {
        return method;
    }

    public void setClassification(final PaymentClassification classification) {
        this.classification = classification;
    }

    public void setSchedule(final PaymentSchedule schedule) {
        this.schedule = schedule;
    }

    public void setMethod(final PaymentMethod paymentMethod) {
        this.method = paymentMethod;
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

    public void setAffiliation(final Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    public UnionAffiliation getUnion() {
        if (affiliation != null && affiliation instanceof UnionAffiliation) {
            return (UnionAffiliation) affiliation;
        }
        throw new RuntimeException("조합에 참여하지 않은 직원.");
    }
}
