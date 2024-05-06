package co.pragma.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;

public class LambdaS3Handler implements RequestHandler<S3Event, String> {

    @Override
    public String handleRequest(S3Event event, Context context)
    {
        LambdaLogger logger = context.getLogger();
        S3EventNotification.S3EventNotificationRecord record = event.getRecords().get(0);
        String srcBucket = record.getS3().getBucket().getName();
        String srcKey = record.getS3().getObject().getUrlDecodedKey();
        logger.log("RECORD: " + record);
        logger.log("SOURCE BUCKET: " + srcBucket);
        logger.log("SOURCE KEY: " + srcKey);
        return srcBucket + "/" + srcKey;
    }
}