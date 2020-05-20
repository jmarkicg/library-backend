package hr.markic.library.rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.markic.library.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.Optional;

/**
 * Generic BLINK web client. Could be used for multiple endpoints depending on the given parameters.
 */
@Component
public class BlinkWebClient {

    private final Logger log = LoggerFactory.getLogger(BlinkWebClient.class);

    private static final String AUTHORIZATION_HEADER="Authorization";
    private static final String TOKEN_TYPE = "Bearer";

    private FileService fileService;

    private ObjectMapper objectMapper;

    @Value("${microblink.api.url}")
    private String blinkIDUrl;

    @Value("${microblink.api.web-api-key}")
    private String webApiKey;

    @Value("${microblink.api.web-api-secret}")
    private String webApiSecret;

    @Autowired
    public BlinkWebClient(FileService fileService,
                          ObjectMapper objectMapper){
        this.fileService = fileService;
        this.objectMapper = objectMapper;
    }

    public Optional<Object> performPOSTRequest(Object requestData, Class responseDataClass, String path){
        try {
            String stringJson = objectMapper.writeValueAsString(requestData);

            HttpClient httpClient = HttpClient.newBuilder().build();
            HttpRequest request =  HttpRequest.newBuilder()
                        .uri(new URI(blinkIDUrl + path))
                        .header(AUTHORIZATION_HEADER, String.format("%s %s", TOKEN_TYPE, Base64.getEncoder().encodeToString((webApiKey + ":" + webApiSecret).getBytes())))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(stringJson))
                        .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            Object responseData = (new ObjectMapper()).readValue(body, responseDataClass);
            return Optional.of(responseData);
        } catch (Exception e) {
            log.error("Failed to retrieve the data from BLINK API.", e);
            return Optional.empty();
        }

    }

}

