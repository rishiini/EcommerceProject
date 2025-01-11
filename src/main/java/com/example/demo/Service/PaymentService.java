package com.example.demo.Service;

import com.razorpay.RazorpayException;

public interface PaymentService {
    String generatePaymentLink(Long orderId) throws RazorpayException;
    String fetchPaymentLink(Long orderId) throws RazorpayException;
}
