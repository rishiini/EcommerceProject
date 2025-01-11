package com.example.demo.Controller;

import com.example.demo.DTO.GeneratePaymentLinkRequestDTO;
import com.example.demo.Service.PaymentService;
import com.example.demo.Service.RazorPayGateway;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    private final RazorPayGateway razorPayGateway;
    private final PaymentService paymentService;

    public PaymentController(RazorPayGateway razorPayGateway, PaymentService paymentService) {
        this.razorPayGateway = razorPayGateway;
        this.paymentService = paymentService;
    }

    @PostMapping("/payments")
    public String generatePaymentLink(@RequestBody GeneratePaymentLinkRequestDTO generatePaymentLinkRequestDTO) throws RazorpayException {
        return razorPayGateway.generatePaymentLink(generatePaymentLinkRequestDTO.getOrderId());
    }

    @GetMapping("/payments")
    public String fetchPaymentLink(@RequestBody GeneratePaymentLinkRequestDTO fetchPaymentLinkRequestDTO) throws RazorpayException{
        return razorPayGateway.fetchPaymentLink(fetchPaymentLinkRequestDTO.getOrderId());
    }
}
