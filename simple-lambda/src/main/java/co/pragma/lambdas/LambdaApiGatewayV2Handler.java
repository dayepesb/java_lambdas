package co.pragma.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;

import java.util.HashMap;

public class LambdaApiGatewayV2Handler
        implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse> {
    @Override
    public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent event, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("EVENT TYPE: " + event.getClass().toString());
        APIGatewayV2HTTPResponse response = new APIGatewayV2HTTPResponse();
        response.setIsBase64Encoded(false);
        response.setStatusCode(200);
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "text/html");
        response.setHeaders(headers);
        String body = event.getBody() != null ? event.getBody() : "Empty body";
        response.setBody("{\"message\":\"this is a test\"}");
        return response;
    }
}
