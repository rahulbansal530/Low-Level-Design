public class Person {
    
    int personId;
    String personName;
    Address address;

    public Person(int personId, String personName, Address address) {
        this.address = address;
        this.personId = personId;
        this.personName = personName;
    }

    public int getPersonId() {
        return this.personId;
    }

    public String getPersonName() {
        return this.personName;
    }
    
    public Address getAddress() {
        return this.address;
    }
}
