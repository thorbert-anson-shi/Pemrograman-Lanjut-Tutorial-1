package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment.AbstractPayment;

import java.util.Map;
import java.util.List;

public interface PaymentService {
    public AbstractPayment addPayment(Order order, String method, Map<String, String> paymentData);

    public AbstractPayment setStatus(AbstractPayment payment, String status);

    public AbstractPayment getPayment(String paymentId);

    public List<AbstractPayment> getAllPayments();
}
