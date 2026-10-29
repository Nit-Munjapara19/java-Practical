package model;

import java.io.Serializable;

public class Customer implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Address address;

    public Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() { return name; }
    public Address getAddress() { return address; }

    public static class Address implements Serializable {
        private static final long serialVersionUID = 1L;
        private String line, city, pincode;

        public Address(String line, String city, String pincode) {
            this.line = line; this.city = city; this.pincode = pincode;
        }

        public String getLine() { return line; }
        public String getCity() { return city; }
        public String getPincode() { return pincode; }
    }

    @Override
    public Customer clone() {
        try { return (Customer) super.clone(); }
        catch (CloneNotSupportedException e) { throw new AssertionError(e); }
    }
}