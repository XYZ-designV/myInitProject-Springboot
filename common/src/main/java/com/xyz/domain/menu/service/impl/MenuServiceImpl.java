package com.xyz.domain.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xyz.domain.common.PageResponse;
import com.xyz.domain.menu.controller.MenuDTO;
import com.xyz.domain.menu.controller.MenuVO;
import com.xyz.domain.menu.entity.MenuEntity;
import com.xyz.domain.menu.mapper.MenuMapper;
import com.xyz.domain.menu.service.MenuService;
import com.xyz.utils.BeanCopyUtils;
import com.xyz.domain.common.ResponseResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author 76596
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
* @createDate 2023-05-24 15:22:19
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity>
    implements MenuService {

    @Override
    public ResponseResult allMenu(Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<MenuEntity> queryWrapper = new LambdaQueryWrapper<>();
        Page<MenuEntity> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);

        List<MenuVO> menuVOS = page.getRecords().stream()
                .map(menuEntity -> BeanCopyUtils.copyBean(menuEntity, MenuVO.class))
                .collect(Collectors.toList());

        return ResponseResult.okResult(new PageResponse(menuVOS,page.getTotal()));
    }

    @Override
    public ResponseResult addMenu(MenuDTO menuDTO) {
        MenuEntity menuEntity = BeanCopyUtils.copyBean(menuDTO, MenuEntity.class);
        save(menuEntity);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult editMenu(MenuDTO menuDTO) {
        MenuEntity menuEntity = BeanCopyUtils.copyBean(menuDTO, MenuEntity.class);
        updateById(menuEntity);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteMenuById(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }
}




