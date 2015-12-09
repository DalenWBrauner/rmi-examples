package e.food;

public class Sub implements Food {
    private static final long serialVersionUID = -9135577315393968638L;

    // Static vars
    private static final String whatKind = "Submarine Sandwhich";
    private static final int nutritionalContent = 100; // Healthier than pizza

    // Local vars
    private int subLeft = 100; // In percent

    @Override
    public int consume() {
        return consumeSome(100);
    }

    @Override
    public int consumeSome(int percent) {

        // If you try to eat too much, just eat the rest
        if (percent > subLeft) {
            percent = subLeft;
        }

        // Calculate how much nutrition we're getting
        int nutrition = nutritionalContent / subLeft;

        // Eat the amount we said we would
        subLeft -= percent;

        return nutrition;
    }

    @Override
    public int isLeft() {
        return subLeft;
    }

    @Override
    public String whatKind() {
        return whatKind;
    }

}
