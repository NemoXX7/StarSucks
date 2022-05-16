package com.st10120712.starsucks;

public class Order {
    //Attributes of Order objects
    private String productName;
    private String customerName;
    private String customerCell;
    private String orderDate;

    //Default Constructor
    public Order(){
    }

    public Order(String productName, String customerName, String orderDate) {
        this.productName = productName;
        this.customerName = customerName;
        this.orderDate = orderDate;
    }

    public Order(String productName, String customerName, String customerCell, String orderDate) {
        this.productName = productName;
        this.customerName = customerName;
        this.customerCell = customerCell;
        this.orderDate = orderDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCell() {
        return customerCell;
    }

    public void setCustomerCell(String customerCell) {
        this.customerCell = customerCell;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString()
    {
        return "Customer Name: " + customerName + '\n' +
                "Product Name: " + productName + '\n' +
                "Order Date: " + orderDate + '\n';
    }
}
