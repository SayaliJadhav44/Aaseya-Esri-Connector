//package io.camunda.example;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.catchThrowable;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.camunda.connector.api.error.ConnectorException;
//import io.camunda.connector.test.outbound.OutboundConnectorContextBuilder;
//import io.camunda.example.dto.Authentication;
//import io.camunda.example.dto.ArcGISRequest;
//import io.camunda.example.dto.ArcGISResult;
//import org.junit.jupiter.api.Test;
//
//public class MyFunctionTest {
//
//  ObjectMapper objectMapper = new ObjectMapper();
//
//  @Test
//  void shouldReturnReceivedMessageWhenExecute() throws Exception {
//    // given
//    var input = new ArcGISRequest(
//            "Hello World!",
//            new Authentication("testUser", "testToken")
//    );
//    var function = new ArcGISFunction();
//    var context = OutboundConnectorContextBuilder.create()
//      .variables(objectMapper.writeValueAsString(input))
//      .build();
//    // when
//    var result = function.execute(context);
//    // then
//    assertThat(result)
//      .isInstanceOf(ArcGISResult.class)
//      .extracting("myProperty")
//      .isEqualTo("Message received: Hello World!");
//  }
//
//  @Test
//  void shouldThrowWithErrorCodeWhenMessageStartsWithFail() throws Exception {
//    // given
//    var input = new ArcGISRequest(
//            "Fail: unauthorized",
//            new Authentication("testUser", "testToken")
//    );
//    var function = new ArcGISFunction();
//    var context = OutboundConnectorContextBuilder.create()
//        .variables(objectMapper.writeValueAsString(input))
//        .build();
//    // when
//    var result = catchThrowable(() -> function.execute(context));
//    // then
//    assertThat(result)
//        .isInstanceOf(ConnectorException.class)
//        .hasMessageContaining("started with 'fail'")
//        .extracting("errorCode").isEqualTo("FAIL");
//  }
//}