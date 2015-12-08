package d.food;

public class Raspberry implements Food {
    // Static vars
    private final static String whatKind = "single raspberry";
    private final static int nutritionalContent = 15; // Still healthier than pizza

    // Local vars
    /* We're representing a single raspberry here, no percents allowed */
    private boolean eaten = false;

    @Override
    public int consume() {
        if (eaten)  return 0;
        else {
            eaten = true;
            return nutritionalContent;
        }
    }

    @Override
    public int consumeSome(int percent) {
        return consume();
    }

    @Override
    public int isLeft() {
        if (eaten)  return 0;
        else        return 100;
    }

    @Override
    public String whatKind() {
        return whatKind;
    }
}
