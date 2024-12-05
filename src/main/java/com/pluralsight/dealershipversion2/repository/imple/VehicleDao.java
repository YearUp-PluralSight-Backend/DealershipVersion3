package com.pluralsight.dealershipversion2.repository.imple;

import com.pluralsight.dealershipversion2.config.DataBase;
import com.pluralsight.dealershipversion2.entity.vehicle.Vehicle;
import com.pluralsight.dealershipversion2.repository.CRUDOperation;
import com.pluralsight.dealershipversion2.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class VehicleDao implements VehicleRepository, CRUDOperation {

    private final DataBase dataBase;


    public VehicleDao(DataBase dataBase) {
        this.dataBase = DataBase.getInstance();
    }

    /**
     * Create a vehicle
     * @param o represents the object to be created
     */
    @Override
    public boolean create(Object o) {

        try (Connection connection = dataBase.getConnection()){
            Vehicle vehicle = (Vehicle) o;
            String query = "INSERT INTO vehicle (make, model, year, type, color, price, mileage) VALUES (?, ?, ?, ?, ?, ?, ?)";
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, vehicle.getVin());
            preparedStatement.setString(1, vehicle.getMake());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setInt(3, vehicle.getYear());
            preparedStatement.setString(4, vehicle.getVehicleType());
            preparedStatement.setString(5, vehicle.getColor());
            preparedStatement.setDouble(6, vehicle.getPrice());
            preparedStatement.setInt(7, vehicle.getOdometer());
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
        }
        return false;
    }

    /**
     * Read all vehicles
     *
     * @return a list of vehicles
     */
    @Override
    public List<Vehicle> read() {

        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataBase.getConnection()) {
            String query = "SELECT * FROM vehicle";
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVin(resultSet.getInt("vin"));
                vehicle.setMake(resultSet.getString("make"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setYear(resultSet.getInt("year"));
                vehicle.setVehicleType(resultSet.getString("type"));
                vehicle.setColor(resultSet.getString("color"));
                vehicle.setPrice(resultSet.getDouble("price"));
                vehicle.setOdometer(resultSet.getInt("mileage"));
                vehicles.add(vehicle);
                log.info("Vehicle: {} | {} | {} | {} | {} | {} | {} | {}",
                        resultSet.getInt("vin"),
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getInt("year"),
                        resultSet.getString("type"),
                        resultSet.getString("color"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("mileage"));
            }
            return vehicles;

        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
        }
        return vehicles;
    }

    /**
     * Read a vehicle by id
     * @param  id represents the vehicle identification number
     * @return a vehicle
     */
    @Override
    public Optional<Vehicle> read(int id) {

        try(Connection connection = dataBase.getConnection()) {
            String query = "SELECT * FROM vehicle WHERE vin = ?";
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVin(resultSet.getInt("vin"));
                vehicle.setMake(resultSet.getString("make"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setYear(resultSet.getInt("year"));
                vehicle.setVehicleType(resultSet.getString("type"));
                vehicle.setColor(resultSet.getString("color"));
                vehicle.setPrice(resultSet.getDouble("price"));
                vehicle.setOdometer(resultSet.getInt("mileage"));
                log.info("Vehicle: {} | {} | {} | {} | {} | {} | {} | {}",
                        resultSet.getInt("vin"),
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getInt("year"),
                        resultSet.getString("type"),
                        resultSet.getString("color"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("mileage"));
                return Optional.of(vehicle);
            }
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
        }
        return Optional.empty();
    }

    /**
     * Update a vehicle
     *
     * @param id represents the vehicle identification number
     * @param o represents the object to be updated
     */
    @Override
    public boolean update(int id, Object o) {

        try(Connection connection = dataBase.getConnection()) {
            Vehicle vehicle = (Vehicle) o;
            String query = "UPDATE vehicle SET make = ?, model = ?, year = ?, type = ?, color = ?, price = ?, mileage = ? WHERE vin = ?";
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, vehicle.getMake());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setInt(3, vehicle.getYear());
            preparedStatement.setString(4, vehicle.getVehicleType());
            preparedStatement.setString(5, vehicle.getColor());
            preparedStatement.setDouble(6, vehicle.getPrice());
            preparedStatement.setInt(7, vehicle.getOdometer());
            preparedStatement.setInt(8, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
        }
        return false;
    }

    /**
     * Delete a vehicle
     * @param id represents the vehicle identification number
     * @return a boolean value
     */
    @Override
    public boolean delete(int id) {

        try(Connection connection = dataBase.getConnection()) {
            String query = "DELETE FROM vehicle WHERE vin = ?";
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
        }
        return false;
    }

    /**
     * get a vehicle by price range
     * @param minPrice minimum price
     * @param maxPrice maximum price
     * @return a list of vehicles
     */
    @Override
    public List<Vehicle> getVehicleByPriceRange(double minPrice, double maxPrice) {
        List<Vehicle> vehicles = new ArrayList<>();
        try(Connection connection = dataBase.getConnection()) {
            String query = "SELECT * FROM vehicle WHERE price BETWEEN ? AND ?";
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2, maxPrice);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVin(resultSet.getInt("vin"));
                vehicle.setMake(resultSet.getString("make"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setYear(resultSet.getInt("year"));
                vehicle.setVehicleType(resultSet.getString("type"));
                vehicle.setColor(resultSet.getString("color"));
                vehicle.setPrice(resultSet.getDouble("price"));
                vehicle.setOdometer(resultSet.getInt("mileage"));
                vehicles.add(vehicle);
                log.info("Vehicle: {} | {} | {} | {} | {} | {} | {} | {}",
                        resultSet.getInt("vin"),
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getInt("year"),
                        resultSet.getString("type"),
                        resultSet.getString("color"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("mileage"));
            }
            return vehicles;
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
        }
        return vehicles;
    }

    /**
     * get a vehicle by make
     * @param make represents the vehicle make
     * @return a list of vehicles
     */
    @Override
    public List<Vehicle> getVehicleByMake(String make) {

        List<Vehicle> vehicles = new ArrayList<>();
        try(Connection connection = dataBase.getConnection()) {
            String query = "SELECT * FROM vehicle WHERE make = ?";
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, make);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVin(resultSet.getInt("vin"));
                vehicle.setMake(resultSet.getString("make"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setYear(resultSet.getInt("year"));
                vehicle.setVehicleType(resultSet.getString("type"));
                vehicle.setColor(resultSet.getString("color"));
                vehicle.setPrice(resultSet.getDouble("price"));
                vehicle.setOdometer(resultSet.getInt("mileage"));
                vehicles.add(vehicle);
                log.info("Vehicle: {} | {} | {} | {} | {} | {} | {} | {}",
                        resultSet.getInt("vin"),
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getInt("year"),
                        resultSet.getString("type"),
                        resultSet.getString("color"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("mileage"));
            }
            return vehicles;
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
        }
        return vehicles;
    }

    /**
     * get a vehicle by model
     * @param model represents the vehicle model
     * @return a list of vehicles
     */
    @Override
    public List<Vehicle> getVehicleByModel(String model) {

        List<Vehicle> vehicles = new ArrayList<>();
        try(Connection connection = dataBase.getConnection()) {
            String query = "SELECT * FROM vehicle WHERE model = ?";
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, model);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVin(resultSet.getInt("vin"));
                vehicle.setMake(resultSet.getString("make"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setYear(resultSet.getInt("year"));
                vehicle.setVehicleType(resultSet.getString("type"));
                vehicle.setColor(resultSet.getString("color"));
                vehicle.setPrice(resultSet.getDouble("price"));
                vehicle.setOdometer(resultSet.getInt("mileage"));
                vehicles.add(vehicle);
                log.info("Vehicle: {} | {} | {} | {} | {} | {} | {} | {}",
                        resultSet.getInt("vin"),
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getInt("year"),
                        resultSet.getString("type"),
                        resultSet.getString("color"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("mileage"));
            }
            return vehicles;
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
        }
        return vehicles ;
    }

    /**
     * get a vehicle by year range
     * @param minYear  starting year
     * @param maxYear  ending year
     * @return a list of vehicles
     */
    @Override
    public List<Vehicle> getVehicleByYearRange(int minYear, int maxYear) {

        List<Vehicle> vehicles = new ArrayList<>();
        try(Connection connection = dataBase.getConnection()) {
            String query = "SELECT * FROM vehicle WHERE year BETWEEN ? AND ?";
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, minYear);
            preparedStatement.setInt(2, maxYear);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVin(resultSet.getInt("vin"));
                vehicle.setMake(resultSet.getString("make"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setYear(resultSet.getInt("year"));
                vehicle.setVehicleType(resultSet.getString("type"));
                vehicle.setColor(resultSet.getString("color"));
                vehicle.setPrice(resultSet.getDouble("price"));
                vehicle.setOdometer(resultSet.getInt("mileage"));
                vehicles.add(vehicle);
                log.info("Vehicle: {} | {} | {} | {} | {} | {} | {} | {}",
                        resultSet.getInt("vin"),
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getInt("year"),
                        resultSet.getString("type"),
                        resultSet.getString("color"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("mileage"));
            }
            return vehicles;
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
        }
        return vehicles;
    }

    /**
     *  get a vehicle by type
     * @param type represents the vehicle type
     * @return a list of vehicles
     */
    @Override
    public List<Vehicle> getVehicleByType(String type) {
        List<Vehicle> vehicles = new ArrayList<>();
        try(Connection connection = dataBase.getConnection()) {
            String query = "SELECT * FROM vehicle WHERE type = ?";
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, type);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVin(resultSet.getInt("vin"));
                vehicle.setMake(resultSet.getString("make"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setYear(resultSet.getInt("year"));
                vehicle.setVehicleType(resultSet.getString("type"));
                vehicle.setColor(resultSet.getString("color"));
                vehicle.setPrice(resultSet.getDouble("price"));
                vehicle.setOdometer(resultSet.getInt("mileage"));
                vehicles.add(vehicle);
                log.info("Vehicle: {} | {} | {} | {} | {} | {} | {} | {}",
                        resultSet.getInt("vin"),
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getInt("year"),
                        resultSet.getString("type"),
                        resultSet.getString("color"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("mileage"));
            }
            return vehicles;
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
        }
        return vehicles;
    }

    /**
     * get a vehicle by color
     * @param color represents the vehicle color
     * @return a list of vehicles
     */
    @Override
    public List<Vehicle> getVehicleByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataBase.getConnection()) {
            String query = "SELECT * FROM vehicle WHERE color = ?";
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, color);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVin(resultSet.getInt("vin"));
                vehicle.setMake(resultSet.getString("make"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setYear(resultSet.getInt("year"));
                vehicle.setVehicleType(resultSet.getString("type"));
                vehicle.setColor(resultSet.getString("color"));
                vehicle.setPrice(resultSet.getDouble("price"));
                vehicle.setOdometer(resultSet.getInt("mileage"));
                vehicles.add(vehicle);
                log.info("Vehicle: {} | {} | {} | {} | {} | {} | {} | {}",
                        resultSet.getInt("vin"),
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getInt("year"),
                        resultSet.getString("type"),
                        resultSet.getString("color"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("mileage"));
            }
            return vehicles;
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
        }
        return vehicles;
    }

    /**
     * get a vehicle by mileage range
     * @param minMileage  minimum mileage
     * @param maxMileage  maximum mileage
     * @return a list of vehicles
     */
    @Override
    public List<Vehicle> getVehicleByMileageRange(int minMileage, int maxMileage) {
        List<Vehicle> vehicles = new ArrayList<>();
        try(Connection connection = dataBase.getConnection()) {
            String query = "SELECT * FROM vehicle WHERE mileage BETWEEN ? AND ?";
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, minMileage);
            preparedStatement.setInt(2, maxMileage);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVin(resultSet.getInt("vin"));
                vehicle.setMake(resultSet.getString("make"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setYear(resultSet.getInt("year"));
                vehicle.setVehicleType(resultSet.getString("type"));
                vehicle.setColor(resultSet.getString("color"));
                vehicle.setPrice(resultSet.getDouble("price"));
                vehicle.setOdometer(resultSet.getInt("mileage"));
                vehicles.add(vehicle);
                log.info("Vehicle: {} | {} | {} | {} | {} | {} | {} | {}",
                        resultSet.getInt("vin"),
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getInt("year"),
                        resultSet.getString("type"),
                        resultSet.getString("color"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("mileage"));
            }
            return vehicles;
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
        }
        return vehicles;
    }
}
