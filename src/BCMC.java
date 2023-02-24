import carvault.Garage;
import carvault.Racing;
import carvault.Scrapyard;
import com.formdev.flatlaf.extras.FlatInspector;
import objects.Car;
import objects.Player;
import ui.Handler;
import utils.ImageHandler;

import java.util.ArrayList;

public class BCMC {

    public static void main(String[] args) {
    //Create all cars
    CarPresets.addPresetCars();
    //Create scrapyards cars
    scrapyard.initCars(allCars);
    //Create opponent cars for racing
    racing.initCars(allCars);
    //Create UI
    Handler handler = new Handler(images,player,garage,scrapyard,racing);

    FlatInspector.install("alt X");
    handler.showMainMenu();
    }

    static ImageHandler images=new ImageHandler();

    public static ArrayList<Car> allCars = new ArrayList<Car>();

    static Scrapyard scrapyard = new Scrapyard();

    static Player player = new Player(20000);

    static Garage garage = new Garage();

    static Racing racing = new Racing();


}
