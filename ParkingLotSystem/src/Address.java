public class Address {
    
    String zipcode;
    String city;
    String state;
    String street;

    

    public Address(String zipcode, String city, String state, String street) {
        this.zipcode = zipcode;
        this.city = city;
        this.state = state;
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getStreet() {
        return street;
    }

    public void setZipzode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStreet(String street) {
        this.street = street;
    }

}
