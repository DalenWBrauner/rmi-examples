package d.food;

public class Garbage implements Food {
    private static final long serialVersionUID = -5237845250868369313L;

    @Override
    public int consume() {
        return 0;
    }

    @Override
    public int consumeSome(int percent) {
        return 0;
    }

    @Override
    public int isLeft() {
        // THERE'S ALWAYS MORE GARBAGE
        return 100;
    }

    @Override
    public String whatKind() {
        return "Literally garbage";
    }

}
