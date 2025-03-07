package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment.AbstractPayment;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PaymentRepository {

    private List<AbstractPayment> payments = new ArrayList<AbstractPayment>();

    public AbstractPayment save(AbstractPayment payment) {
        for (int index = 0; index < payments.size(); index++) {
            if (Objects.equals(payments.get(index).getId(), payment.getId())) {
                payments.set(index, payment);
                return payment;
            }
        }

        payments.add(payment);
        return payment;
    }

    public AbstractPayment findById(String id) {
        return payments.stream().filter(payment -> payment.getId().equals(id)).findFirst().orElseThrow(() -> new NoSuchElementException("Payment with ID " + id + " not found"));
    }

    public Iterator<AbstractPayment> findAll() {
        return payments.iterator();
    }

    public static class PaymentNotFoundException extends RuntimeException {
        public PaymentNotFoundException(String paymentId) {
            super(String.format("Payment with id %s not found", paymentId));
        }
    }
}
