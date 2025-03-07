package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum PaymentMethod {
    BY_VOUCHER("BY_VOUCHER"),
    BANK_TRANSFER("BANK_TRANSFER");

    private final String value;

    private PaymentMethod(String value) {
        this.value = value;
    }

    public static boolean contains(String method) {
        return Arrays.asList(PaymentMethod.values()).contains(PaymentMethod.valueOf(method));
    }
}
