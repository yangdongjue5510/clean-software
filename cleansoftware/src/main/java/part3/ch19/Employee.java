package part3.ch19;

import java.time.LocalDate;

public class Employee {
    private Integer id;
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

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public boolean isPayDay(final LocalDate payDate) {
    return schedule.isPayDay(payDate);
    }

    public void payday(final Paycheck paycheck) {
        double grossPay = classification.calculatePay(paycheck);
        double deductions = affiliation.calculateDeductions(paycheck);
        double netPay = grossPay - deductions;
        paycheck.setGrossPay(grossPay);
        paycheck.setDeductions(deductions);
        paycheck.setNetPay(netPay);
        method.pay(paycheck);
    }
}
