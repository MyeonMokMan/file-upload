package com.hello.fileUpload.controller;

import com.hello.fileUpload.domain.Item;
import com.hello.fileUpload.domain.ItemRepository;
import com.hello.fileUpload.domain.UploadFile;
import com.hello.fileUpload.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final FileService fileService;

    @GetMapping("/items/new")
    public String newItem(@ModelAttribute ItemForm form) {
        return "item-form";
    }

    @PostMapping("/items/new")
    @ResponseBody
    public String saveItem(@ModelAttribute ItemForm form) throws IOException {

        UploadFile attachFile = fileService.storeFile(form.getAttachFile());
        List<UploadFile> storeImgFiles = fileService.storeFiles(form.getImageFiles());

        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setAttachFile(attachFile);
        item.setImageFiles(storeImgFiles);
        itemRepository.save(item);

        return "ok";
    }
}
