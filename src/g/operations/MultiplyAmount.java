package g.operations;

public class MultiplyAmount implements Operation {
    private static final long serialVersionUID = 7496681587975659097L;

    private final int amount;

    public MultiplyAmount(int multiplyAmount) {
        amount = multiplyAmount;
    }

    @Override
    public int execute(int originalValue) {
        return originalValue * amount;
    }
}
