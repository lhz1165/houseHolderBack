package com.example.householderback.controller;

import com.example.householderback.commom.Result;
import com.example.householderback.service.AdminUserService;
import com.example.householderback.utils.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
@RestController
public class FileController {
    @Autowired
    private AdminUserService userService;

    @Value("${file.address}")
    String fileAddress;

    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) throws FileNotFoundException {
        String avatarAddr = saveFile(file);
        return Result.succeed(avatarAddr);

    }



    private String saveFile(MultipartFile file){
        if (file.isEmpty()){
            return "未选择文件";
        }
        String filename = UUID.randomUUID() +".jpg";
        String filePath=fileAddress+filename;

        File localFile = new File(filePath);
        try {
            file.transferTo(localFile); //把上传的文件保存至本地
           // userService.upDateAvatar(id, "/headImage/" + filename);
            System.out.println(file.getOriginalFilename()+" 上传成功");
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }

        return "/headImage/"+filename;
    }

}
