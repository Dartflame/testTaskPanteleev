//package com.darflame.test_task;
//
//import com.github.tomakehurst.wiremock.WireMockServer;
//import com.github.tomakehurst.wiremock.client.WireMock;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//
//import java.io.IOException;
//
//import static java.nio.charset.Charset.defaultCharset;
//import static org.springframework.util.StreamUtils.copyToString;
//
//public class CurrencyMock {
//
//    public static void setupMockCurResponse(WireMockServer mockService) throws IOException {
//        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/latest.json"))
//                .willReturn(WireMock.aResponse()
//                        .withStatus(HttpStatus.OK.value())
//                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                        .withBody(
//                                copyToString(
//                                        CurrencyMock.class.getClassLoader().getResourceAsStream("currency.json"),
//                                        defaultCharset()))));
//    }
//}
