package co.pragma.lambdas;

import co.pragma.response.GetTestResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;

public class LambdaBasicHandler implements RequestHandler<Map<String,String>, String> {

    @Override
    public String handleRequest(Map<String, String> params, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Handler invoked");
        logger.log("Params: "+params);
        logger.log("Context: "+context.getAwsRequestId());

        GetTestResponse response = new GetTestResponse();
        try {
            response.setMessage("this is a test");
        } catch(Exception e) {
            logger.log(e.getMessage());
        }
        return "Total code size for your account is " + response.getMessage() + " bytes";
    }

}