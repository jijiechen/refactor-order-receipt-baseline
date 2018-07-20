package org.katas.refactoring;

import java.util.List;

public class Order {
    private Customer customer;
    List<LineItem> lineItems;

    public Order(String customerName, String customerAddr, List<LineItem> lineItems) {
        this.customer  = new Customer(customerName,customerAddr);
        this.lineItems = lineItems;
    }

    public String getCustomerAddress() {
        return customer.getAddress();
    }

    public String getCustomerName(){
        return customer.getName();
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    String generateLineItemDetails() {
        StringBuilder lineItemsDetailBuilder = new StringBuilder();
        for (LineItem lineItem : getLineItems()) {
            lineItemsDetailBuilder.append(lineItem.generateDetail());
        }
        return lineItemsDetailBuilder.toString();
    }

    double getTotalWithoutTax() {
        return getLineItems()
                .stream()
                .mapToDouble(item -> item.totalAmount())
                .sum();
    }

    String generateSummary() {
        double totalWithoutTax = getTotalWithoutTax();
        double totalSalesTax = .10d * totalWithoutTax;

        StringBuilder summaryBuilder = new StringBuilder();
        summaryBuilder.append("Sales Tax")
                .append('\t')
                .append(totalSalesTax);
        summaryBuilder.append("Total Amount")
                .append('\t')
                .append(totalWithoutTax + totalSalesTax);

        return summaryBuilder.toString();
    }
}
