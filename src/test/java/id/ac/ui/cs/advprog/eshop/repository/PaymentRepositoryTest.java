package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment.AbstractPayment;
import id.ac.ui.cs.advprog.eshop.model.Payment.VoucherPayment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;

    List<AbstractPayment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        List<Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId(UUID.randomUUID().toString());
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");

        List<AbstractPayment> payments = new ArrayList<>();

        Map<String, String> voucherPaymentData = new HashMap<>();
        voucherPaymentData.put("voucherCode", "ESHOP12345678ABC");
        VoucherPayment voucherPayment = new VoucherPayment(
                UUID.randomUUID().toString(), PaymentMethod.BY_VOUCHER.getValue(), PaymentStatus.SUCCESS.getValue(), voucherPaymentData
        );

        Map<String, String> bankTrasferPaymentData = new HashMap<>();
        bankTrasferPaymentData.put("bankName", "BNI");
        bankTrasferPaymentData.put("referenceCode", "LMAOBIGBOI");
//        BankTransferPayment bankTransferPayment = new BankTransferPayment(
//                UUID.randomUUID().toString(), PaymentMethod.BANK_TRANSFER.getValue(), PaymentStatus.SUCCESS.getValue(), bankTrasferPaymentData
//        );

        payments.add(voucherPayment);
//        payments.add(bankTransferPayment);
    }

    @Test
    void testSaveCreate() {
        AbstractPayment payment = payments.get(1);
        AbstractPayment result = paymentRepository.save(payment);

        AbstractPayment findResult = paymentRepository.findById(payments.get(1).getId());
        assertSame(payment, findResult);
    }

    @Test
    void testSaveUpdateVoucherPayment() {
        AbstractPayment payment = payments.get(1);
        paymentRepository.save(payment);

        Map<String, String> updatedData = new HashMap<>(payment.getPaymentData());
        updatedData.put("voucherCode", "ESHOP12345678ABB");

        AbstractPayment newPayment = new VoucherPayment(
                payment.getId(),
                payment.getPaymentMethod(),
                "SUCCESS",
                updatedData);

        AbstractPayment result = paymentRepository.save(newPayment);

        AbstractPayment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getPaymentMethod(), findResult.getPaymentMethod());
        assertEquals("SUCCESS", findResult.getStatus());
        assertEquals("ESHOP12345678ABB", findResult.getPaymentData().get("voucherCode"));
    }

    @Test
    void testFindByIdFound() {
        payments.forEach((payment) -> paymentRepository.save(payment));

        AbstractPayment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getPaymentMethod(), findResult.getPaymentMethod());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
        assertEquals(payments.get(1).getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdNotFound() {
        payments.forEach(payment -> paymentRepository.save(payment));

        assertThrows(PaymentRepository.PaymentNotFoundException.class, () -> paymentRepository.findById("non-existent-id"));
    }

    @Test
    void testFindAllPayments() {
        payments.forEach((payment) -> paymentRepository.save(payment));

        Iterator<AbstractPayment> paymentIterator = paymentRepository.findAll();

        List<AbstractPayment> paymentList = new ArrayList<>();
        while (paymentIterator.hasNext()) {
            paymentList.add(paymentIterator.next());
        }

        assertEquals(2, paymentList.size());
    }

    @Test
    void testValidatePaymentCreation() {
        AbstractPayment payment = payments.get(0);
        assertTrue(payment.validatePaymentCreation(payment.getPaymentData()));

        // Test with empty data
        Map<String, String> emptyData = new HashMap<>();
        assertFalse(payment.validatePaymentCreation(emptyData));

        // Test with null data
        assertFalse(payment.validatePaymentCreation(null));
    }
}

