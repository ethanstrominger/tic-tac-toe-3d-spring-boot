package com.ethan;

import com.google.gson.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.google.gson.JsonParser;


public class MessageObjectTest {

    @Test
    public void verify_message_can_be_created() {
        String expectedFromValue = "George";
        String expectedToValue = "Fred";
        String expectedSendMessageValue = "A test message";

        MessageObject messageObject = new MessageObject(expectedFromValue, expectedToValue, expectedSendMessageValue);

        String actualJson = messageObject.getJsonStringMessage();
        String actualFromValue = getJsonStringAttributeValue(actualJson, "from");
        String actualToValue = getJsonStringAttributeValue(actualJson, "to");
        String actualSendMessageValue = getJsonStringAttributeValue(actualJson, "sendMessage");

        assertEquals(expectedFromValue, actualFromValue );
        assertEquals(expectedToValue, actualToValue );
        assertEquals(expectedSendMessageValue, actualSendMessageValue );
    }

    private String getJsonStringAttributeValue(String jsonString, String jsonAttribute) {
        JsonElement jelement = JsonParser.parseString(jsonString);
        JsonObject jobject = jelement.getAsJsonObject();
        JsonPrimitive jprimitive = jobject.getAsJsonPrimitive(jsonAttribute);
        String s = jprimitive.getAsString();
        return s;
    }

    private static String makeJsonAttrib(String prompt, String value) {
        String q = "\"";
        return q+prompt+q+":"+q+value+q;
    }

}
