package co.pragma.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class LambdaKinesisHandler implements RequestHandler<KinesisEvent, List<String>> {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public List<String> handleRequest(KinesisEvent event, Context context)
    {
        LambdaLogger logger = context.getLogger();
        logger.log("EVENT TYPE: " + event.getClass().toString());
        var dataRecords = new ArrayList<String>();
        for(KinesisEvent.KinesisEventRecord record : event.getRecords()) {
            dataRecords.add(gson.toJson(record.getKinesis().getData()));
        }
        return dataRecords;
    }
}