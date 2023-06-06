package com.xyz.application.menu;

import com.xyz.aoplog.AopLog;
import com.xyz.domain.menu.controller.IMenu;
import com.xyz.domain.menu.controller.MenuDTO;
import com.xyz.domain.menu.service.impl.MenuServiceImpl;
import com.xyz.domain.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
@Validated
public class MenuController implements IMenu {

    @Autowired
    private MenuServiceImpl menuService;

    @GetMapping("/all")
    @AopLog
    public ResponseResult allMenu(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize) {
        return menuService.allMenu(pageNum, pageSize);
    }
    @PostMapping("/add")
    @Override
    @AopLog
    public ResponseResult addMenu(@RequestBody @Validated MenuDTO menuDTO) {
        if (null != menuDTO.getId()) {
            menuDTO.setId(null);
        }
        return menuService.addMenu(menuDTO);
    }
    @PutMapping("/edit")
    @Override
    @AopLog
    public ResponseResult editMenu(@RequestBody @Validated  MenuDTO menuDTO) {
        return menuService.editMenu(menuDTO);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    @AopLog
    public ResponseResult deleteMenuById(@PathVariable("id") Long id) {
        return menuService.deleteMenuById(id);
    }
}
