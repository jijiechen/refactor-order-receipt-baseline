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
        privateCustomerDetail(output);
        printsLineItemDetails(output);
        printSummary(output);

		return output.toString();
	}

    private String getHeader() {
        return "======Printing Orders======\n";
    }

    private void privateCustomerDetail(StringBuilder output) {
        Customer customer = order.getCustomer();
        output.append(customer.getName());
        output.append(customer.getAddress());
    }

    private void printsLineItemDetails(StringBuilder output) {
        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItem.getDescription());
            output.append('\t');
            output.append(lineItem.getPrice());
            output.append('\t');
            output.append(lineItem.getQuantity());
            output.append('\t');
            output.append(lineItem.totalAmount());
            output.append('\n');
        }
    }

    private void printSummary(StringBuilder output) {
        double totalWithoutTax = getTotalWithoutTax();
        double totalSalesTax = .10d * totalWithoutTax;
        output.append("Sales Tax")
                .append('\t')
                .append(totalSalesTax);
        output.append("Total Amount")
                .append('\t')
                .append(totalWithoutTax + totalSalesTax);
    }

    private double getTotalWithoutTax() {
        return order.getLineItems()
                .stream()
                .mapToDouble(item -> item.totalAmount())
                .sum();
    }
}