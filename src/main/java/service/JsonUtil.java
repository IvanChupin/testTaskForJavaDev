package service;

import model.Envelope;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    public static String convertObjectToJSON(Envelope envelope) throws IOException {
        String jsonResult = "";
        try {
            jsonResult = mapper.writeValueAsString(envelope);
        } catch (IOException e) {
            throw new IOException("there is a trouble in mapper while converting to JSON" + e.getMessage());
        }
        return jsonResult;
    }

}
