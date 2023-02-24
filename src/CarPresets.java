import objects.Car;

import java.util.ArrayList;

public class CarPresets {


    static void addPresetCars() {
        ArrayList<Car> allCars = BCMC.allCars;
        //Done
        //Tier 1 0-200
        allCars.add(new Car("Volkswagen", "Passat", 10, 27145, 186, 1));
        allCars.add(new Car("Kia", "Ceed", 10, 26395, 187, 1));
        allCars.add(new Car("Toyota", "Yaris", 10, 18176, 168.9, 1));
        allCars.add(new Car("Volvo", "V60", 10, 48800, 180, 1));
        allCars.add(new Car("Volkswagen", "Golf", 10, 33225, 168.9, 1));
        allCars.add(new Car("Volkswagen", "Tiguan", 10, 39995, 180, 1));

        //done
        //Tier 2 200-250
        allCars.add(new Car("Honda", "Civic", 10, 25450, 225.3, 2));
        allCars.add(new Car("Hyundai", "i30", 10, 23720, 249, 2));
        allCars.add(new Car("Mazda", "MX-5", 10, 26324, 230.13, 2));
        allCars.add(new Car("BMW", "M3", 10, 75094, 249.45, 2));
        allCars.add(new Car("Lexus", "IS", 10, 40585, 230.13, 2));
        allCars.add(new Car("Subaru", "Impreza", 10, 19795, 204.38, 2));

        //done
        //Tier 3 250-300
        allCars.add(new Car("Audi", "S4", 10, 52800, 250, 3));
        allCars.add(new Car("Audi", "A3", 10, 61995, 289, 3));
        allCars.add(new Car("Mercedes", "C63", 10, 77250, 250, 3));
        allCars.add(new Car("Mitsubishi", "Lancer", 10, 32495, 250, 3));
        allCars.add(new Car("Ford", "Mustang GT", 10, 55749, 260.7, 3));
        allCars.add(new Car("Renault", "Megane", 10, 53990, 258, 3));

        //done
        //Tier 4 300-400
        allCars.add(new Car("Maserati", "Ghibli", 10, 77695, 326.69, 4));
        allCars.add(new Car("Ferrari", "F40", 10, 72500, 367, 4));
        allCars.add(new Car("Porsche", "911 GT3 RS", 10, 241300, 317, 4));
        allCars.add(new Car("Nissan", "GTR", 10, 113540, 320, 4));
        allCars.add(new Car("Lamborghini", "Ursus", 10, 230000, 328.3, 4));
        allCars.add(new Car("Lamborghini", "Aventador", 10, 507353, 350, 4));


        //Tier 5 400-infinity
        allCars.add(new Car("Aspark", "Owl", 10, 4000000, 400, 5));
        allCars.add(new Car("Koenigsegg", "Jesko Absolut", 10, 2840000, 498.8, 5));
        allCars.add(new Car("Bugatti", "Chiron", 10, 3005000, 490.5, 5));
        allCars.add(new Car("Hennessy", "Venom", 10, 4000000, 484, 5));
        allCars.add(new Car("SSC", "Tuatara", 10, 1900000, 474, 5));
        allCars.add(new Car("McLaren", "Speedtail", 10, 2250000, 402.3, 5));



    }
}
