package org.example.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.StreamWriteConstraints;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Model;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class FileController {

    public static void save(Model model) {
        // Взять файлы из списка и положить в файл

        StringWriter writer = new FileWriter("1.json");
        ObjectMapper mapper = new ObjectMapper();

        try {
            StreamWriteConstraints constraints = StreamWriteConstraints.builder()
                    .maxNestingDepth(1500) // Увеличение лимита вложенности
                    .build();
            mapper.getFactory().setStreamWriteConstraints(constraints);
            mapper.writeValue( writer, Model.getMyShapes());
            System.out.println(writer.toString());
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void open() {
        // Взять список объектов из файла и поместить


    }
}
