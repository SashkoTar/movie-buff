package net.sparja.text;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonFormatter {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        File jsonTextFile = new File("C:\\projects\\learning\\movie-buff\\data\\gss-response\\eTMF.json");
        JsonNode root = mapper.readTree(jsonTextFile);
        //System.out.println(root);
        printDetailedLayout(root);
    }

    private static void printDetailedLayout(JsonNode root) {
        root.get("results").forEach(jsonNode -> {
            System.out.println(jsonNode.get("id"));
            System.out.println(jsonNode.get("documentUrl"));
            System.out.println(jsonNode.get("sourceId"));
            System.out.println(jsonNode.get("sourceUrl"));
            jsonNode.get("standardLayout").forEach(detail -> {
                System.out.println(detail.get("label") + ":" + detail.get("value"));
            });
            jsonNode.get("detailsLayout").forEach(detail -> {
                System.out.println(detail.get("label") + ":" + detail.get("value"));
            });
            System.out.println("-------------------------------------");
        });
    }
}
