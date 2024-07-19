package ma.zs.zyn.zynerator.transverse.epay;

import com.fasterxml.jackson.annotation.JsonProperty;



public class CreatePaymentResponse {
    @JsonProperty("clientSecret")
    private String clientSecret;


    public CreatePaymentResponse() {
    }

    public CreatePaymentResponse(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
