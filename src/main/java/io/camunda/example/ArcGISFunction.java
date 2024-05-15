package io.camunda.example;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import io.camunda.connector.generator.java.annotation.ElementTemplate;
import io.camunda.example.dto.ArcGISRequest;
import io.camunda.example.dto.ArcGISResult;

@OutboundConnector(name = "Aaseya ArcGIS Connector", inputVariables = { "apiKeyValue",
		"url", "placeID", "f", "requestedFields" }, type = "io.camunda:arcgis-json:1")
@ElementTemplate(id = "io.camunda.connector.Template.v1", name = "Template connector", version = 1, description = "ArcGIS Connector for place services.", icon = "icon.svg", documentationRef = "https://docs.camunda.io/docs/components/connectors/out-of-the-box-connectors/available-connectors-overview/", propertyGroups = {
		@ElementTemplate.PropertyGroup(id = "authentication", label = "Authentication"),
		@ElementTemplate.PropertyGroup(id = "input", label = "Input") }, inputDataClass = ArcGISRequest.class)
public class ArcGISFunction implements OutboundConnectorFunction {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArcGISFunction.class);

	@Override
	public Object execute(OutboundConnectorContext context) throws IOException {
		final var connectorRequest = context.bindVariables(ArcGISRequest.class);
		return executeConnector(connectorRequest);
	}

	private ArcGISResult executeConnector(final ArcGISRequest connectorRequest) throws IOException {
		// TODO: implement connector logic
		LOGGER.info("Executing my connector with request {}", connectorRequest);
		String urlStr = connectorRequest.url();
		String placeID = connectorRequest.placeID();		
		String apiKey = connectorRequest.apiKeyValue();
		String resFormat = connectorRequest.f();
		String requestedFields = connectorRequest.requestedFields();

//		if (placeID != null && placeID.toLowerCase().startsWith("fail")) {
//			throw new ConnectorException("FAIL", "My property started with 'fail', was: " + placeID);
//		}		

		String urlString = urlStr + placeID
				+ "?requestedFields=" + requestedFields + "&f=" + resFormat + "&token=" + apiKey;
		URL url = new URL(urlString);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setRequestProperty("Accept", "*/*");

		http.disconnect();

		String placeDetails;
		if (http.getResponseCode() == 200) {
			placeDetails = new String(http.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
			LOGGER.info("Place Details" + placeDetails);
		} else {
			LOGGER.error("Error accessing ArcGIS API: " + http.getResponseCode() + " - " + http.getResponseMessage());
			// Throwing an exception will fail the job
			throw new IOException(http.getResponseMessage());
		}

		return new ArcGISResult(placeDetails);
	}
}
