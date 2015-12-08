package d.food;

public interface Food {

    // Eat the whole thing
    public int consume();

    // Eat some percent of it
    public int consumeSome(int percent);

    // Returns how much is left
    public int isLeft();

    // Returns what kind of food it is
    public String whatKind();

}
