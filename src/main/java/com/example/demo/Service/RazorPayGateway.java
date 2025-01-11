package com.example.demo.Service;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class RazorPayGateway implements PaymentService {
    private final RazorpayClient razorpayClient;

    public RazorPayGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String generatePaymentLink(Long orderId) throws RazorpayException {
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",1000);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",true);
        paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",System.currentTimeMillis() + 10*60*1000);
        paymentLinkRequest.put("reference_id",orderId.toString());
        paymentLinkRequest.put("description","Payment for policy no #23456");

        JSONObject customer = new JSONObject();
        customer.put("name","Rishi Bawankule");
        customer.put("contact","+91 8855947071");
        customer.put("email","rishibawankule@gmail.com");
        paymentLinkRequest.put("customer",customer);

        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        notify.put("notify", notify);
        paymentLinkRequest.put("reminder_enable",true);

//        JSONObject notes = new JSONObject();
//        notes.put("policy_name","Jeevan Bima");
//        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://www.google.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        return payment.toString();

        //UPI Payments
//        JSONObject paymentLinkRequest = new JSONObject();
//        paymentLinkRequest.put("upi_link",true);
//        paymentLinkRequest.put("amount",1000);
//        paymentLinkRequest.put("currency","INR");
//        paymentLinkRequest.put("description","Payment for policy no #23456");
//        JSONObject customer = new JSONObject();
//        customer.put("name","Rs");
//        customer.put("contact","Gaurav Kumar");
//        customer.put("email","gaurav.kumar@example.com");
//        paymentLinkRequest.put("customer",customer);
//        JSONObject notify = new JSONObject();
//        notify.put("sms",true);
//        notify.put("email",true);
//        paymentLinkRequest.put("reminder_enable",true);
//        JSONObject notes = new JSONObject();
//        notes.put("policy_name","Jeevan Bima");
//        paymentLinkRequest.put("notes",notes);
//
//        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);

    }

    @Override
    public String fetchPaymentLink(Long orderId) throws RazorpayException {
        return razorpayClient.paymentLink.fetch(orderId.toString()).toString();
    }


}
