package id.ac.ui.cs.advprog.eshop.model.Payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public abstract class AbstractPayment {
    String id;
    String paymentMethod;
    String status;
    Map<String, String> paymentData;

    abstract public boolean validatePaymentCreation(Map<String, String> paymentData);
}

