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

        printHeaders(output);
        privateCustomerDetail(output);

        printsLineItemDetails(output);

        double totalSalesTax = calculateSalesTax();
        output.append("Sales Tax").append('\t').append(totalSalesTax);
		output.append("Total Amount").append('\t').append(calculateTotalAmount(totalSalesTax));

		return output.toString();
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

    private double  calculateTotalAmount(double totalSales){
        double total = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            total += lineItem.totalAmount();
        }
        return total + totalSales;
    }

	private double  calculateSalesTax(){
        double totSalesTx = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            // calculate sales tax @ rate of 10%
            double salesTax = lineItem.totalAmount() * .10;
            totSalesTx += salesTax;
        }
        return totSalesTx;
    }

    private void privateCustomerDetail(StringBuilder output) {
        Customer customer = order.getCustomer();
        output.append(customer.getName());
        output.append(customer.getAddress());
    }

    private void printHeaders(StringBuilder output) {
        output.append("======Printing Orders======\n");
    }
}