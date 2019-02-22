package com.company.project.service.impl;

import com.company.project.service.TableService;
import com.company.project.util.FuncHelper;
import com.company.project.util.TableTools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TableServiceImpl implements TableService{

    /**
     * 初始化配表路径
     */
    @Override
    public void initTable() {
        TableTools tableTools = new TableTools();
        tableTools.InitTableTools(FuncHelper.getResourcesPath() + "table");
    }
}
