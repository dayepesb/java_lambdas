package co.pragma.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaSumHandler implements RequestHandler<LambdaSumHandler.SumRecord, String> {

    @Override
    public String handleRequest(SumRecord sumRecord, Context context) {
        long sum = sumRecord.a + sumRecord.b;
        return "Sum: "+ sum;
    }

    record SumRecord (long a, long b) {
    }
}
