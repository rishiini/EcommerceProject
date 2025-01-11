package com.example.demo.Controller;

import com.example.demo.DTO.GeneratePaymentLinkRequestDTO;
import com.example.demo.Service.PaymentService;
import com.example.demo.Service.RazorPayGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    private final RazorPayGateway razorPayGateway;

    public PaymentController(RazorPayGateway razorPayGateway) {
        this.razorPayGateway = razorPayGateway;
    }

    @PostMapping("/payments")
    public String generatePaymentLink(@RequestBody GeneratePaymentLinkRequestDTO generatePaymentLinkRequestDTO){
        return razorPayGateway.generatePaymentLink(generatePaymentLinkRequestDTO.getOrderId());
    }

}
