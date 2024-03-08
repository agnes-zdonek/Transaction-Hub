package com.commerce.cashregister.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Data
@Getter
public class NewMoneyRequest {
    @JsonProperty(required = false)
    private int fiveHundredZl;

    @JsonProperty(required = false)
    private int twoHundredZl;

    @JsonProperty(required = false)
    private int oneHundredZl;

    @JsonProperty(required = false)
    private int fiftyZl;

    @JsonProperty(required = false)
    private int twentyZl;

    @JsonProperty(required = false)
    private int tenZl;

    @JsonProperty(required = false)
    private int fiveZl;

    @JsonProperty(required = false)
    private int twoZl;

    @JsonProperty(required = false)
    private int oneZl;

    @JsonProperty(required = false)
    private int fiftyGr;

    @JsonProperty(required = false)
    private int twentyGr;

    @JsonProperty(required = false)
    private int tenGr;

    @JsonProperty(required = false)
    private int fiveGr;

    @JsonProperty(required = false)
    private int twoGr;

    @JsonProperty(required = false)
    private int oneGr;
}