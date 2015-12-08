package d.food;

/** A food item that can cleanly be cut into slices. */
public class Pizza implements Food {
    // Static vars
    private final static String whatKind = "Pizza";
    private final static int nutritionalContent = 10;

    // Local vars
    private int pizzaLeft = 100; // In percent

    @Override
    public int consume() {
        return consumeSome(100);
    }

    @Override
    public int consumeSome(int percent) {

        // If you try to eat too much, just eat the rest
        if (percent > pizzaLeft) {
            percent = pizzaLeft;
        }

        // Calculate how much nutrition we're getting
        int nutrition = nutritionalContent / pizzaLeft;

        // Eat the amount we said we would
        pizzaLeft -= percent;

        return nutrition;
    }

    @Override
    public int isLeft() {
        return pizzaLeft;
    }

    @Override
    public String whatKind() {
        return whatKind;
    }
}
