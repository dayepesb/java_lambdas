package co.pragma.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.CognitoEvent;

import java.util.ArrayList;
import java.util.List;

public class LambdaCognitoHandler implements RequestHandler<CognitoEvent, List<String>> {

    @Override
    public List<String> handleRequest(CognitoEvent event, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("EVENT TYPE: " + event.getClass().toString());
        List<String> operationsFound = new ArrayList<>();
        for (CognitoEvent.DatasetRecord record : event.getDatasetRecords().values()) {
            operationsFound.add(record.getOp());
        }
        return operationsFound;
    }
}