package co.pragma.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.lambda.runtime.logging.LogLevel;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class LambdaStreamHandler implements RequestStreamHandler {

    @Override
    public void handleRequest(
            InputStream inputStream,
            OutputStream outputStream,
            Context context) throws IOException {

        LambdaLogger logger = context.getLogger();

        var input = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.US_ASCII));
        var output = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(outputStream, StandardCharsets.US_ASCII)));

        int nextChar;
        try {
            while ((nextChar = input.read()) != -1) {
                outputStream.write(nextChar);
            }
        } catch (IOException e) {
            logger.log(e.getMessage(), LogLevel.ERROR);
        } finally {
            input.close();
            String finalString = output.toString();
            logger.log("Final string result: " + finalString);
            output.close();
        }
    }
}
