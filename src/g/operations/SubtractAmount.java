package g.operations;

public class SubtractAmount implements Operation {
    private static final long serialVersionUID = 7496681587975659097L;

    private final int amount;

    public SubtractAmount(int subtractAmount) {
        amount = subtractAmount;
    }

    @Override
    public int execute(int originalValue) {
        return originalValue - amount;
    }
}
