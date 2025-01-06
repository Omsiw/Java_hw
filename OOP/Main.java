package OOP;

public class Main {
    public static void main(String[] args) {
        Plane[] planes = new Plane[] {
                new MilitaryPlane(),
                new PassengerPlane(),
                new CargoPlane()
        };

        for (Plane item : planes)
            item.doStuff();
    }
}
