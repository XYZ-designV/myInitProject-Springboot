package com.xyz.application.menu;

import com.xyz.domain.menu.controller.IMenu;
import com.xyz.domain.menu.controller.MenuDTO;
import com.xyz.domain.menu.service.impl.MenuServiceImpl;
import com.xyz.domain.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/menu")
@Validated
public class MenuController implements IMenu {

    @Autowired
    private MenuServiceImpl menuService;

    @GetMapping("/all")
    @Override
    public ResponseResult allMenu(@NotNull(message = "分页数据不能为空;") Integer pageNum,
                                  @NotNull(message = "分页数据不能为空1;") Integer pageSize) {
        return menuService.allMenu(pageNum,pageSize);
    }
    @PostMapping("/add")
    @Override
    public ResponseResult addMenu(@RequestBody MenuDTO menuDTO) {
        return menuService.addMenu(menuDTO);
    }
    @PutMapping("/edit")
    @Override
    public ResponseResult editMenu(@RequestBody MenuDTO menuDTO) {
        return menuService.editMenu(menuDTO);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseResult deleteMenuById(@PathVariable("id") Long id) {
        return menuService.deleteMenuById(id);
    }
}
