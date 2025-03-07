package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment.AbstractPayment;
import id.ac.ui.cs.advprog.eshop.model.Payment.BankTransferPayment;
import id.ac.ui.cs.advprog.eshop.model.Payment.VoucherPayment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    private Order testOrder;
    private Map<String, String> validVoucherData;
    private Map<String, String> validBankTransferData;
    private Map<String, String> invalidVoucherData;
    private Map<String, String> invalidBankTransferData;
    private List<AbstractPayment> mockPaymentList;

    @BeforeEach
    void setUp() {
        // Create test order
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId(UUID.randomUUID().toString());
        product.setProductName("Test Product");
        product.setProductQuantity(1);
        products.add(product);

        testOrder = new Order(UUID.randomUUID().toString(), products,
                System.currentTimeMillis(), "Test User");

        // Setup valid voucher data
        validVoucherData = new HashMap<>();
        validVoucherData.put("voucherCode", "VALID123");
        validVoucherData.put("amount", "100000");

        // Setup invalid voucher data
        invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("amount", "100000");
        // Missing voucherCode

        // Setup valid bank transfer data
        validBankTransferData = new HashMap<>();
        validBankTransferData.put("bankName", "Test Bank");
        validBankTransferData.put("referenceCode", "REF123456");

        // Setup invalid bank transfer data
        invalidBankTransferData = new HashMap<>();
        invalidBankTransferData.put("bankName", "Test Bank");
        // Missing referenceCode

        // Mock payment list for repository
        mockPaymentList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            AbstractPayment payment = new VoucherPayment(
                    UUID.randomUUID().toString(),
                    PaymentMethod.BY_VOUCHER.getValue(),
                    PaymentStatus.SUCCESS.getValue(),
                    validVoucherData
            );
            mockPaymentList.add(payment);
        }
    }

    @Test
    void testAddPaymentWithValidVoucher() {
        // Use reflection to replace the constructor call with our spy
        try (MockedConstruction<VoucherPayment> mockedConstruction = Mockito.mockConstruction(
                VoucherPayment.class,
                (mock, context) -> {
                    when(mock.validatePaymentCreation(validVoucherData)).thenReturn(true);
                })) {

            AbstractPayment result = paymentService.addPayment(
                    testOrder,
                    PaymentMethod.BY_VOUCHER.getValue(),
                    validVoucherData
            );

            assertNotNull(result);
            assertEquals(PaymentMethod.BY_VOUCHER.getValue(), result.getPaymentMethod());
            assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
            assertEquals(validVoucherData, result.getPaymentData());
        }
    }

    @Test
    void testAddPaymentWithInvalidVoucher() {
        // Use reflection to replace the constructor call with our mock
        try (MockedConstruction<VoucherPayment> mockedConstruction = Mockito.mockConstruction(
                VoucherPayment.class,
                (mock, context) -> {
                    when(mock.validatePaymentCreation(invalidVoucherData)).thenReturn(false);
                })) {

            AbstractPayment result = paymentService.addPayment(
                    testOrder,
                    PaymentMethod.BY_VOUCHER.getValue(),
                    invalidVoucherData
            );

            assertNotNull(result);
            assertEquals(PaymentMethod.BY_VOUCHER.getValue(), result.getPaymentMethod());
            assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
            assertEquals(invalidVoucherData, result.getPaymentData());
        }
    }

    @Test
    void testAddPaymentWithValidBankTransfer() {
        // Use reflection to replace the constructor call with our mock
        try (MockedConstruction<BankTransferPayment> mockedConstruction = Mockito.mockConstruction(
                BankTransferPayment.class,
                (mock, context) -> {
                    when(mock.validatePaymentCreation(validBankTransferData)).thenReturn(true);
                })) {

            AbstractPayment result = paymentService.addPayment(
                    testOrder,
                    PaymentMethod.BANK_TRANSFER.getValue(),
                    validBankTransferData
            );

            assertNotNull(result);
            assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), result.getPaymentMethod());
            assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
            assertEquals(validBankTransferData, result.getPaymentData());
        }
    }

    @Test
    void testAddPaymentWithInvalidBankTransfer() {
        // Use reflection to replace the constructor call with our mock
        try (MockedConstruction<BankTransferPayment> mockedConstruction = Mockito.mockConstruction(
                BankTransferPayment.class,
                (mock, context) -> {
                    when(mock.validatePaymentCreation(invalidBankTransferData)).thenReturn(false);
                })) {

            AbstractPayment result = paymentService.addPayment(
                    testOrder,
                    PaymentMethod.BANK_TRANSFER.getValue(),
                    invalidBankTransferData
            );

            assertNotNull(result);
            assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), result.getPaymentMethod());
            assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
            assertEquals(invalidBankTransferData, result.getPaymentData());
        }
    }

    @Test
    void testAddPaymentWithInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(testOrder, "INVALID_METHOD", validVoucherData);
        });
    }

    @Test
    void testSetStatus() {
        AbstractPayment payment = new VoucherPayment(
                UUID.randomUUID().toString(),
                PaymentMethod.BY_VOUCHER.getValue(),
                PaymentStatus.SUCCESS.getValue(),
                validVoucherData
        );

        AbstractPayment result = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());

        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getPaymentMethod(), result.getPaymentMethod());
    }

    @Test
    void testGetPayment() {
        String paymentId = UUID.randomUUID().toString();
        AbstractPayment mockPayment = new VoucherPayment(
                paymentId,
                PaymentMethod.BY_VOUCHER.getValue(),
                PaymentStatus.SUCCESS.getValue(),
                validVoucherData
        );

        when(paymentRepository.findById(paymentId)).thenReturn(mockPayment);

        AbstractPayment result = paymentService.getPayment(paymentId);

        assertNotNull(result);
        assertEquals(paymentId, result.getId());
        assertEquals(PaymentMethod.BY_VOUCHER.getValue(), result.getPaymentMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());

        verify(paymentRepository, times(1)).findById(paymentId);
    }

    @Test
    void testGetAllPayments() {
        when(paymentRepository.findAll()).thenReturn(mockPaymentList.iterator());

        List<AbstractPayment> result = paymentService.getAllPayments();

        assertNotNull(result);
        assertEquals(mockPaymentList.size(), result.size());

        verify(paymentRepository, times(1)).findAll();
    }

    @Test
    void testBankTransferValidation() {
        BankTransferPayment payment = new BankTransferPayment();

        // Test valid case
        assertTrue(payment.validatePaymentCreation(validBankTransferData));

        // Test invalid case
        assertFalse(payment.validatePaymentCreation(invalidBankTransferData));

        // Test with null values
        Map<String, String> nullValueData = new HashMap<>();
        nullValueData.put("bankName", null);
        nullValueData.put("referenceCode", "REF123");
        assertFalse(payment.validatePaymentCreation(nullValueData));

        nullValueData = new HashMap<>();
        nullValueData.put("bankName", "Test Bank");
        nullValueData.put("referenceCode", null);
        assertFalse(payment.validatePaymentCreation(nullValueData));
    }
}