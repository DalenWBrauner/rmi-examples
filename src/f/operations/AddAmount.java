package f.operations;

public class AddAmount implements Operation {
    private static final long serialVersionUID = 7496681587975659097L;

    private final int amount;

    public AddAmount(int addAmount) {
        amount = addAmount;
    }

    @Override
    public int execute(int originalValue) {
        return originalValue + amount;
    }
}
