package com.decidir.sdk

import com.decidir.sdk.dto.ConfirmPayment
import com.decidir.sdk.dto.Status
import spock.lang.Specification

/**
 * Created by biandra on 25/11/16.
 */
class PaymentConfirmServiceTests extends Specification {

    public static final String secretAccessToken = '00040407'//'4cf891e492384cdeadf211564aa87007'
    public static final String apiUrl = "http://localhost:9002"

    def decidir

    def setup(){
        decidir = new Decidir(secretAccessToken, apiUrl, 15)
    }

    def "test confirm payment"() {
        setup:
        def paymentId = 3370
        def confirmPayment = new ConfirmPayment()
        confirmPayment.amount = 20L

        when:
        def result = decidir.confirmPayment(paymentId, confirmPayment)

        then:
        result.status == 201
        result.result.id != null
        result.result.amount != null
        result.result.status == Status.APPROVED
    }

    def "test get confirm"() {
        setup:
        def paymentId = 3369

        when:
        def result = decidir.getConfirm(paymentId)

        then:
        result.status == 200
        result.result.id != null
        result.result.amount != null
        result.result.status == Status.APPROVED
    }
}
