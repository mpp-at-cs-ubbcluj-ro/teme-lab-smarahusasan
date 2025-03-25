import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarsDBRepository implements CarRepository{

    private JdbcUtils dbUtils;



    private static final Logger logger= LogManager.getLogger();

    public CarsDBRepository(Properties props) {
        logger.info("Initializing CarsDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
        System.out.println(props);
    }

    @Override
    public List<Car> findByManufacturer(String manufacturerN) {
        logger.traceEntry("Finding cars by manufacturer: {}", manufacturerN);
        Connection conn = dbUtils.getConnection();
        List<Car> cars = new ArrayList<>();

        try (PreparedStatement preSmt = conn.prepareStatement("SELECT * FROM cars WHERE manufacturer = ?")) {
            preSmt.setString(1, manufacturerN);
            try (ResultSet rs = preSmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String manufacturer = rs.getString("manufacturer");
                    String model = rs.getString("model");
                    int year = rs.getInt("year");
                    Car car = new Car(manufacturer, model, year);
                    car.setId(id);
                    cars.add(car);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit(cars);
        return cars;
    }

    @Override
    public List<Car> findBetweenYears(int min, int max) {
        logger.traceEntry("Finding cars between years: {} - {}", min, max);
        Connection conn = dbUtils.getConnection();
        List<Car> cars = new ArrayList<>();

        try (PreparedStatement preSmt = conn.prepareStatement("SELECT * FROM cars WHERE year BETWEEN ? AND ?")) {
            preSmt.setInt(1, min);
            preSmt.setInt(2, max);
            try (ResultSet rs = preSmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String manufacturer = rs.getString("manufacturer");
                    String model = rs.getString("model");
                    int year = rs.getInt("year");
                    Car car = new Car(manufacturer, model, year);
                    car.setId(id);
                    cars.add(car);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit(cars);
        return cars;
    }

    @Override
    public void add(Car elem) {
        logger.traceEntry("saving task {}", elem);
        Connection conn=dbUtils.getConnection();

        try(PreparedStatement preSmt=conn.prepareStatement("insert into cars (manufacturer, model,year) values (?,?,?)")){
            preSmt.setString(1, elem.getManufacturer());
            preSmt.setString(2, elem.getModel());
            preSmt.setInt(3,elem.getYear());
            int res= preSmt.executeUpdate();
            logger.trace("Saved {} instances",res);
        }catch(SQLException ex){
            logger.error(ex);
            System.err.println("Error DB "+ex);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer id, Car elem) {
        logger.traceEntry("Updating car with id {}", id);
        Connection conn = dbUtils.getConnection();

        try (PreparedStatement preSmt = conn.prepareStatement("UPDATE cars SET manufacturer = ?, model = ?, year = ? WHERE id = ?")) {
            preSmt.setString(1, elem.getManufacturer());
            preSmt.setString(2, elem.getModel());
            preSmt.setInt(3, elem.getYear());
            preSmt.setInt(4, id);
            int res = preSmt.executeUpdate();
            logger.trace("Updated {} instances", res);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
    }

    @Override
    public Iterable<Car> findAll() {
        logger.traceEntry();
        Connection conn=dbUtils.getConnection();
        List<Car> cars=new ArrayList<>();
        try(PreparedStatement preparedStatement=conn.prepareStatement("select * from cars")){
            try(ResultSet rs=preparedStatement.executeQuery()){
                while(rs.next()){
                    int id=rs.getInt("id");
                    String manufacturer=rs.getString("manufacturer");
                    String model=rs.getString("model");
                    int year=rs.getInt("year");
                    Car car=new Car(manufacturer,model,year);
                    car.setId(id);
                    cars.add(car);
                }
            }
        }catch(SQLException ex){
            logger.error(ex);
            System.err.println("Error DB "+ex);
        }
        logger.traceExit();
        return cars;
    }
}
