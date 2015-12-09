package h.operations;

import java.io.Serializable;

/** An Operation represents some arbitrary mathematical operation. */
public interface Operation extends Serializable {
    public int execute(int originalValue);

}
