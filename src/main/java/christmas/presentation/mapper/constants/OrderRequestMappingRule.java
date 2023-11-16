package christmas.presentation.mapper.constants;

public class OrderRequestMappingRule {

    public static final String SPLIT_DELIMITER = ",";
    public static final String VALUE_DELIMITER = "-";
    private static final String ORDER_LINE_ITEM_FORMAT = "%s" + VALUE_DELIMITER + "%d";

    private OrderRequestMappingRule() {
    }

    public static CharSequence createSample() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ORDER_LINE_ITEM_FORMAT, "해산물파스타", 2));
        sb.append(SPLIT_DELIMITER);
        sb.append(String.format(ORDER_LINE_ITEM_FORMAT, "레드와인", 2));
        sb.append(SPLIT_DELIMITER);
        sb.append(String.format(ORDER_LINE_ITEM_FORMAT, "초코케이크", 1));

        return sb;
    }
}
