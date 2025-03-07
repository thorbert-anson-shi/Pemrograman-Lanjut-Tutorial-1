import lombok.Getter;

import java.util.Arrays;

@Getter
public enum OrderStatus {
    WAITING_PAYMENT("WAITING_PAYMENT"),
    FAILED("FAILED"),
    SUCCESS("SUCCESS"),
    CANCELLED("CANCELLED");

    private final String value;

    private OrderStatus(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        return Arrays.asList(OrderStatus.values()).contains(OrderStatus.valueOf(param));
    }
}
