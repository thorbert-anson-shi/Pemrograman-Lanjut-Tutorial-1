package id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.Map;

public class VoucherPayment extends AbstractPayment {
    public VoucherPayment(String id, String paymentMethod, String status, Map<String, String> paymentData) {
        super(id, paymentMethod, status, paymentData);
    }

    public VoucherPayment() {
        super();
    }

    @Override
    public boolean validatePaymentCreation(Map<String, String> paymentData) {
        String voucherCode = paymentData.get("voucherCode");

        if (voucherCode.length() != 16) {
            return false;
        }

        if (!voucherCode.startsWith("ESHOP")) {
            return false;
        }

        if (voucherCode.chars().filter(ch -> ch >= (int) '0' && ch <= (int) '9').count() != 8) {
            return false;
        }

        return true;
    }
}
