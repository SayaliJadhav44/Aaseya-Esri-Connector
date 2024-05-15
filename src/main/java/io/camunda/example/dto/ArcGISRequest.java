package io.camunda.example.dto;

import io.camunda.connector.generator.java.annotation.TemplateProperty;
import io.camunda.connector.generator.java.annotation.TemplateProperty.PropertyType;
import jakarta.validation.constraints.NotEmpty;

public record ArcGISRequest(
		@NotEmpty @TemplateProperty(group = "input", type = PropertyType.Text) String url,
		@NotEmpty @TemplateProperty(group = "input", type = PropertyType.Text) String placeID,
		@NotEmpty @TemplateProperty(group = "input", type = PropertyType.Text) String f,
		@NotEmpty @TemplateProperty(group = "input", label = "Requested Fields", type = PropertyType.Text) String requestedFields,
		@NotEmpty @TemplateProperty(group = "input", label = "API key", type = PropertyType.Text) String apiKeyValue
//		@NotNull Authentication authentication
		) {
}
