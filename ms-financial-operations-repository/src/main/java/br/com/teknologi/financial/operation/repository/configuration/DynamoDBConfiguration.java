package br.com.teknologi.financial.operation.repository.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

import java.net.URI;

@Configuration
@Slf4j
public class DynamoDBConfiguration {

    @Value("${aws.dynamodb.region}")
    private String region;

    @Value("${aws.dynamodb.endpoint}")
    private String endpoint;

    @Value("${aws.dynamodb.tablePrefix}")
    private String prefix;

    @Value("${aws.dynamodb.accesskey}")
    private String accesskey;

    @Value("${aws.dynamodb.secretkey}")
    private String secretkey;

    @Bean
    public DynamoDbAsyncClient getDynamoDbAsyncClient() {
        log.debug("[Configuration] ===== DynamoDbAsyncClient bean configured =====");
        return DynamoDbAsyncClient.builder()
                .region(Region.of(region))
                .endpointOverride(URI.create(endpoint))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accesskey, secretkey)))
                .build();
    }

    @Bean
    public DynamoDbEnhancedAsyncClient getDynamoDbEnhancedAsyncClient() {
        log.debug("[Configuration] ===== DynamoDbEnhancedAsyncClient bean configured =====");
        return DynamoDbEnhancedAsyncClient.builder()
                .dynamoDbClient(getDynamoDbAsyncClient())
                .build();
    }
}
