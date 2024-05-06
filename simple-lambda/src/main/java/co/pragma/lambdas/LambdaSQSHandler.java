package co.pragma.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;

import java.util.ArrayList;
import java.util.List;

public class LambdaSQSHandler
        implements RequestHandler<SQSEvent, List<String>> {

    @Override
    public List<String> handleRequest(SQSEvent event, Context context)
    {
        LambdaLogger logger = context.getLogger();
        logger.log("EVENT TYPE: " + event.getClass().toString());
        var messagesFound = new ArrayList<String>();
        for(SQSEvent.SQSMessage msg : event.getRecords()){
            messagesFound.add(msg.getBody());
        }
        return messagesFound;
    }
}