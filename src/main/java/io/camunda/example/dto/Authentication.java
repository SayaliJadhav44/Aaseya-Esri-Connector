package io.camunda.example.dto;

import io.camunda.connector.generator.java.annotation.TemplateProperty;
import io.camunda.connector.generator.java.annotation.TemplateProperty.PropertyType;
import jakarta.validation.constraints.NotEmpty;

public record Authentication(
		@NotEmpty @TemplateProperty(group = "authentication", type = PropertyType.Text) String apiKeyValue) {
}
