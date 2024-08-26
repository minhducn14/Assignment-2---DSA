package Database;

import Database.DataStructure.CustomStack;
import Database.DataStructure.QuadTree;
import Database.DataStructure.Set;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class QuadTreeSerializer {

    public static void saveData(QuadTree quadTree, String filename) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filename);
            CustomStack stack = new CustomStack();
            stack.push(quadTree.getRoot());

            while (!stack.isEmpty()) {
                QuadTree.Node node = stack.pop();
                String nodeData = nodeToJson(node);
                writer.write(nodeData + "\n");

                for (QuadTree.Node child : node.getChildren()) {
                    if (child != null) stack.push(child);
                }
            }

            System.out.println("Data saved successfully to: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + filename);
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String nodeToJson(QuadTree.Node node) {
        StringBuilder json = new StringBuilder("{");
        json.append("\"x\":").append(node.getX()).append(",");
        json.append("\"y\":").append(node.getY()).append(",");
        json.append("\"width\":").append(node.getWidth()).append(",");
        json.append("\"height\":").append(node.getHeight()).append(",");
        json.append("\"depth\":").append(node.getDepth()).append(",");
        json.append("\"places\":[");
        boolean first = true;
        Set<Place> places = node.getPlaces();
        if (places == null) return json.append("]}").toString();
        Iterator<Place> iterator = places.iterator();
        while (iterator.hasNext()){
            Place place = iterator.next();
            if (!first) json.append(",");
            json.append(placeToJson(place));
            first = false;
        }
        json.append("]}");
        return json.toString();
    }

    private static String placeToJson(Place place) {
        return String.format("{\"id\":%d,\"name\":\"%s\",\"x\":%.2f,\"y\":%.2f}",
                place.getId(), place.getPlaceName(), place.getX(), place.getY());
    }
}
