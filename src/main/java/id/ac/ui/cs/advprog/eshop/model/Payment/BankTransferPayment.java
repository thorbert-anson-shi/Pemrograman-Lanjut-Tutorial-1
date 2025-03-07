package id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.Map;

public class BankTransferPayment extends AbstractPayment {
    public BankTransferPayment(String id, String paymentMethod, String status, Map<String, String> paymentData) {
        super(id, paymentMethod, status, paymentData);
    }

    public BankTransferPayment() {
        super();
    }

    @Override
    public boolean validatePaymentCreation(Map<String, String> paymentData) {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");

        return bankName != null && referenceCode != null;
    }
}
