package d.food;

import java.io.Serializable;

public interface Food extends Serializable {

    // Eat the whole thing
    public int consume();

    // Eat some percent of it
    public int consumeSome(int percent);

    // Returns how much is left
    public int isLeft();

    // Returns what kind of food it is
    public String whatKind();

}
