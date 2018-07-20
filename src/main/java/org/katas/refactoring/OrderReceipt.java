package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 *
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
	}

	public String printReceipt() {
		StringBuilder output = new StringBuilder();

        output.append(getHeader());
        output.append(order.getCustomer().generateCustomerInfo());
        output.append(order.generateLineItemDetails());
        output.append(order.generateSummary());

        return output.toString();
	}

    private String getHeader() {
        return "======Printing Orders======\n";
    }

}