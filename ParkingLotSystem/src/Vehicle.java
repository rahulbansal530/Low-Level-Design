public class Vehicle {

    String vehicleNumber;
    VehicleCategory vehicleCategory;

    public String getVehicleNumber() {

        return this.vehicleNumber;
    }

    public VehicleCategory getVehicleCategory() {

        return this.vehicleCategory;
    }

    public void setVehicleCategory(VehicleCategory vehicleCategory) {

        this.vehicleCategory = vehicleCategory;
    }

    public void setVehicleNumber(String vehicleNumber) {

        this.vehicleNumber = vehicleNumber;
    }
}