package edu.university.file.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController {

    private final static String UPLOAD_PATH = "/Users/ce/Downloads/test/";

    @RequestMapping("/upload")
    public ModelAndView showUpload() {
        return new ModelAndView("upload");
    }

    @PostMapping("/upload")
    public ModelAndView fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            return new ModelAndView("status", "message", "Please select a file and try again");
        }
        try {
            //read and write the file to the seleted location
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_PATH + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("status", "message", "File Uploaded sucessfully");
    }

}
