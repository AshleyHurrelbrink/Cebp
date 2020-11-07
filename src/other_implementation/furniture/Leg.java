package other_implementation.furniture;

public class Leg extends Furniture {

    private Wood wood1, wood2;

    public Leg(Wood wood1, Wood wood2, String furnitureName) {
        super(furnitureName);
        this.wood1 = wood1;
        this.wood2 = wood2;
    }
}
