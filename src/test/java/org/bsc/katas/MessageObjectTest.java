package org.bsc.katas;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import com.google.code.gson.JsonElement;
import com.google.code.gson.JsonObject;


public class MessageObjectTest {

    @Test public void
    verify_message_can_be_created() {
        String fromValue = "George";
        String toValue = "Fred";
        String messageValue = "A test message";
        MessageObject messageObject = new MessageObject(fromValue, toValue, messageValue);
        String fromJsonAttrib = makeJsonAttrib("from", fromValue);
        String toJsonAttrib = makeJsonAttrib("to", toValue);
        String messageJsonAttrib = makeJsonAttrib("message", messageValue);
        String expectedJson = "{"+fromJsonAttrib+","+toJsonAttrib+","+messageJsonAttrib+"}";

        String actualJson = messageObject.getJsonStrMessage();
   JsonElement jelement = new JsonParser().parse(actualJson);

//    JsonObject  jobject = jelement.getAsJsonObject();
    assertEquals(expectedJson,actualJson);
    }

    private static String makeJsonAttrib(String prompt, String value) {
        String q = "\"";
        return q+prompt+q+":"+q+value+q;
    }

}
