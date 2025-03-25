package com.wardrobehub.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PaymentDetails {

    private String paymentMethod;

    private String status;

    private String paymentId;

    private String razorpayPaymentLinkId;

    private String getRazorpayPaymentLinkReferenceId;

    private String razorpayPaymentLinkStatus;

    private String razorpayPaymentId;
}
