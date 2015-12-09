package f.operations;

public class DivideAmount implements Operation {
    private static final long serialVersionUID = 7496681587975659097L;

    private final int amount;

    public DivideAmount(int divideAmount) {
        amount = divideAmount;
    }

    @Override
    public int execute(int originalValue) {
        return originalValue / amount;
    }
}
