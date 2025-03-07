package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment.AbstractPayment;
import id.ac.ui.cs.advprog.eshop.model.Payment.BankTransferPayment;
import id.ac.ui.cs.advprog.eshop.model.Payment.VoucherPayment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public AbstractPayment addPayment(Order order, String method, Map<String, String> paymentData) throws IllegalArgumentException {
        boolean isValid = false;
        if (method.equals(PaymentMethod.BY_VOUCHER.getValue())) {
            isValid = new VoucherPayment().validatePaymentCreation(paymentData);
        } else if (method.equals(PaymentMethod.BANK_TRANSFER.getValue())) {
            isValid = new BankTransferPayment().validatePaymentCreation(paymentData);
        } else {
            throw new IllegalArgumentException("Invalid payment data");
        }

        if (!isValid) {
            if (method.equals(PaymentMethod.BY_VOUCHER.getValue())) {
                return new VoucherPayment(UUID.randomUUID().toString(), method, PaymentStatus.REJECTED.getValue(), paymentData);
            } else if (method.equals(PaymentMethod.BANK_TRANSFER.getValue())) {
                return new BankTransferPayment(UUID.randomUUID().toString(), method, PaymentStatus.REJECTED.getValue(), paymentData);
            }
        }

        if (method.equals(PaymentMethod.BY_VOUCHER.getValue())) {
            return new VoucherPayment(UUID.randomUUID().toString(), method, PaymentStatus.SUCCESS.getValue(), paymentData);
        } else if (method.equals(PaymentMethod.BANK_TRANSFER.getValue())) {
            return new BankTransferPayment(UUID.randomUUID().toString(), method, PaymentStatus.SUCCESS.getValue(), paymentData);
        }

        return null;
    }

    @Override
    public AbstractPayment setStatus(AbstractPayment payment, String status) {
        payment.setStatus(status);
        return payment;
    }

    @Override
    public AbstractPayment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<AbstractPayment> getAllPayments() {
        Iterator<AbstractPayment> paymentIterator = paymentRepository.findAll();
        List<AbstractPayment> paymentList = new ArrayList<AbstractPayment>();
        while (paymentIterator.hasNext()) {
            paymentList.add(paymentIterator.next());
        }
        return paymentList;
    }
}
