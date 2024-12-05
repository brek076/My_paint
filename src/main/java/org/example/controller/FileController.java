package org.example.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.StreamWriteConstraints;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.MyShapeJSON;
import org.example.model.shape.factory.ShapeCreator;
import org.example.model.shape.factory.ShapeType;
import org.example.model.shape.fill.Fill;

import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileController {

    public static void save(Model model) {
        // Взять файлы из списка и положить в файл
        ObjectMapper objectMapper = new ObjectMapper();
        FileWriter fileWriter = null;
        JsonGenerator jsonGenerator = null;

        // Создаем/перезаписываем файл
        try {
            fileWriter = new FileWriter("1.json");
        } catch (IOException e) {
            System.out.println("Ошибка при открытии или записи в файл");
            e.printStackTrace();
        }

        try {
            jsonGenerator = objectMapper.getFactory().createGenerator(fileWriter);
            jsonGenerator.writeStartArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            System.out.println("============================");
            for (MyShape myShape : Model.getMyShapes()) {
                objectMapper.writeValue(jsonGenerator, new MyShapeJSON(
                        myShape.getColor(),
                        (int) myShape.getFb().getShape().getX(),
                        (int) myShape.getFb().getShape().getY(),
                        (int) myShape.getFb().getShape().getWidth(),
                        (int)  myShape.getFb().getShape().getHeight(),
                        myShape.getFb().getShape().getClass().equals(Ellipse2D.Double.class) ? ShapeType.ELLIPSE.ordinal(): ShapeType.RECTANGLE.ordinal(),
                        myShape.getFb().getClass().equals(Fill.class)
                ));
                System.out.println(myShape);
                System.out.println("------------------");
            }
            jsonGenerator.writeEndArray();  // Конец массива
            jsonGenerator.close();
            System.out.println("============================");
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<MyShape> open() {
        // Взять список объектов из файла и поместить

        // Полностью читаем файл
        String str = null;
        try {
            str = Files.readString(Path.of("1.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Чтение файла и создание временных классов
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(str);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<MyShapeJSON> loadedMyShapeJSONS = new ArrayList<>();
        try {
             loadedMyShapeJSONS = mapper.readValue(
                    new File("1.json"),
                    mapper.getTypeFactory().constructCollectionType(List.class, MyShapeJSON.class)
            );

            //myShapes = mapper.readValue(reader, new TypeReference<ArrayList<MyShape>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

        ShapeCreator shapeCreator = new ShapeCreator();
        ArrayList<MyShape> myShapes = new ArrayList<>();
        for(MyShapeJSON shapeJSON: loadedMyShapeJSONS){
            myShapes.add(shapeCreator.createShape(shapeJSON));
        }

        return myShapes;
    }
}
