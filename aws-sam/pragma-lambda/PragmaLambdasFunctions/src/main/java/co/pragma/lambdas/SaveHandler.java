package co.pragma.lambdas;

import co.pragma.dto.RegisterDTO;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SaveHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private LambdaLogger logger;
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {

        logger = context.getLogger();
        RegisterDTO registerDTO = gson.fromJson(event.getBody(), RegisterDTO.class);
        logger.log(String.valueOf(registerDTO));

        String tableName = System.getenv().get("TABLE_NAME");
        insertData(registerDTO.getName(), tableName);

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setStatusCode(200);

        return response;
    }

    private void insertData(String name, String tableName) {
        // Create the DynamoDB client
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        DynamoDB dynamoDB = new DynamoDB(client);

        // Get a reference to the DynamoDB table
        Table table = dynamoDB.getTable(tableName);

        // Create a new item and populate it with data
        Item item = new Item()
                .withPrimaryKey("id", "id-"+Math.random()*10000)
                .withString("name", name);

        // Insert the item into the table
        table.putItem(item);


        logger.log("Item inserted successfully.");
    }
}