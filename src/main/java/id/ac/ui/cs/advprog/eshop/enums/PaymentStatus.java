package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PaymentStatus {
    SUCCESS("SUCCESS"),
    REJECTED("REJECTED");

    private final String value;

    private PaymentStatus(String value) {
        this.value = value;
    }

    public static boolean contains(String method) {
        return Arrays.asList(PaymentStatus.values()).contains(PaymentStatus.valueOf(method));
    }
}