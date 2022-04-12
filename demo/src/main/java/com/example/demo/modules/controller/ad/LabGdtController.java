package com.example.demo.modules.controller.ad;

import com.example.demo.common.util.TableControlUtil;
import com.example.demo.modules.entity.LabEntity;
import com.example.demo.modules.service.LabGdtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LabGdtController {
    private final LabGdtService labGdtService;

    @Autowired
    public LabGdtController(LabGdtService labGdtService){
        this.labGdtService=labGdtService;
    }
    @PostMapping("/insertLabGdt")
    public boolean insertLabGdt(LabEntity labEntity){

    }
    @PostMapping("/updateLabGdt")
    public boolean updateLabGdt(LabEntity labEntity){

    }
    @GetMapping("/queryLabGdtTable")
    public boolean queryLabGdtTable(LabEntity labEntity){

    }
    @PostMapping("/deleteLabGdt/{labId}")
    public boolean deleteLabGdtById(@PathVariable("labId") Integer labId){

    }


}
