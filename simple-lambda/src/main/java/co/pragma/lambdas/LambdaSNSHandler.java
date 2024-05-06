package co.pragma.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;

import java.util.ArrayList;
import java.util.List;

public class LambdaSNSHandler implements RequestHandler<SNSEvent, List<String>> {

    @Override
    public List<String> handleRequest(SNSEvent event, Context context)
    {
        LambdaLogger logger = context.getLogger();
        logger.log("EVENT TYPE: " + event.getClass().toString());
        var messagesFound = new ArrayList<String>();
        for (SNSEvent.SNSRecord record : event.getRecords()) {
            SNSEvent.SNS message = record.getSNS();
            messagesFound.add(message.getMessage());
        }
        return messagesFound;
    }
}