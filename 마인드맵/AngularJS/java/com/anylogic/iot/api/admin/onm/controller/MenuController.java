/*
   ADP Platform version 1.0

   Copyright ⓒ 2018 anylogic corp. All rights reserved.

   This is a proprietary software of anylogic corp, and you may not use this file except in
   compliance with license agreement with anylogic corp. Any redistribution or use of this
   software, with or without modification shall be strictly prohibited without prior written
   approval of anylogic corp, and the copyright notice above does not evidence any actual or
   intended publication of such software.
*/


package com.anylogic.iot.api.admin.onm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.onm.service.MenuService;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.mvc.message.Messages;


@RestController
@RequestMapping("/" + Version.V1+"/admin/onm")
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;




    @RequestMapping(value = "/selectMenu", method = RequestMethod.GET)
    public ResultListVO selectMenu(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(menuService.selectMenu(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }



    @RequestMapping(value = "/selectMenuL", method = RequestMethod.GET)
    public ResultListVO selectMenuL(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(menuService.selectMenuL(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }



    @RequestMapping(value = "/selectMenuM", method = RequestMethod.GET)
    public ResultListVO selectMenuM(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(menuService.selectMenuM(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }



    @RequestMapping(value = "/selectMenuS", method = RequestMethod.GET)
    public ResultListVO selectMenuS(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(menuService.selectMenuS(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }



    @RequestMapping(value = "/selectMenuD", method = RequestMethod.GET)
    public ResultListVO selectMenuD(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(menuService.selectMenuD(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }



    @RequestMapping(value = "/viewMenu", method = RequestMethod.GET)
    public ResultListVO viewMenu(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(menuService.viewMenu(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }



    @RequestMapping(value = "/selectIDL", method = RequestMethod.GET)
    public ResultListVO selectIDL(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(menuService.selectIDL(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }



    @RequestMapping(value = "/selectIDM", method = RequestMethod.GET)
    public ResultListVO selectIDM(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(menuService.selectIDM(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }



    @RequestMapping(value = "/selectIDS", method = RequestMethod.GET)
    public ResultListVO selectIDS(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(menuService.selectIDS(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }



    @RequestMapping(value = "/selectIDD", method = RequestMethod.GET)
    public ResultListVO selectIDD(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(menuService.selectIDD(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }


    @RequestMapping(value = "/insertMenuL", method = RequestMethod.POST)
    public void insertMenuL(@RequestBody Map<String, Object> parameter, Messages messages) {
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        sendParam = (List<Map<String, Object>>) parameter.get("list");
        
        for (int i = 0; i < sendParam.size(); i++) {
            res = menuService.insertMenuL(sendParam.get(i));
            
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }


    @RequestMapping(value = "/insertMenuM", method = RequestMethod.POST)
    public void insertMenuM(@RequestBody Map<String, Object> parameter, Messages messages) {
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        sendParam = (List<Map<String, Object>>) parameter.get("list");
        
        for (int i = 0; i < sendParam.size(); i++) {
            res = menuService.insertMenuM(sendParam.get(i));
            
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }


    @RequestMapping(value = "/insertMenuS", method = RequestMethod.POST)
    public void insertMenuS(@RequestBody Map<String, Object> parameter, Messages messages) {
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        sendParam = (List<Map<String, Object>>) parameter.get("list");
        
        for (int i = 0; i < sendParam.size(); i++) {
            res = menuService.insertMenuS(sendParam.get(i));
            
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }


    @RequestMapping(value = "/insertMenuD", method = RequestMethod.POST)
    public void insertMenuD(@RequestBody Map<String, Object> parameter, Messages messages) {
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        sendParam = (List<Map<String, Object>>) parameter.get("list");
        
        for (int i = 0; i < sendParam.size(); i++) {
            res = menuService.insertMenuD(sendParam.get(i));
            
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }


    @RequestMapping(value = "/updateMenuL", method = RequestMethod.POST)
    public void updateMenuL(@RequestBody Map<String, Object> parameter, Messages messages) {
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        sendParam = (List<Map<String, Object>>) parameter.get("list");
        
        for (int i = 0; i < sendParam.size(); i++) {
            res = menuService.updateMenuL(sendParam.get(i));
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }


    @RequestMapping(value = "/updateMenuM", method = RequestMethod.POST)
    public void updateMenuM(@RequestBody Map<String, Object> parameter, Messages messages) {
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        sendParam = (List<Map<String, Object>>) parameter.get("list");
        
        for (int i = 0; i < sendParam.size(); i++) {
            res = menuService.updateMenuM(sendParam.get(i));
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }


    @RequestMapping(value = "/updateMenuS", method = RequestMethod.POST)
    public void updateMenuS(@RequestBody Map<String, Object> parameter, Messages messages) {
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        sendParam = (List<Map<String, Object>>) parameter.get("list");
        
        for (int i = 0; i < sendParam.size(); i++) {
            res = menuService.updateMenuS(sendParam.get(i));
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }


    @RequestMapping(value = "/updateMenuD", method = RequestMethod.POST)
    public void updateMenuD(@RequestBody Map<String, Object> parameter, Messages messages) {
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        sendParam = (List<Map<String, Object>>) parameter.get("list");
        
        for (int i = 0; i < sendParam.size(); i++) {
            res = menuService.updateMenuD(sendParam.get(i));
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }


    @RequestMapping(value = "/deleteMenuL", method = RequestMethod.POST)
    public void deleteMenuL(@RequestBody Map<String, Object> parameter, Messages messages) {
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        sendParam = (List<Map<String, Object>>) parameter.get("list");
        
        for (int i = 0; i < sendParam.size(); i++) {
            res = menuService.deleteMenuL(sendParam.get(i));
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }


    @RequestMapping(value = "/deleteMenuM", method = RequestMethod.POST)
    public void deleteMenuM(@RequestBody Map<String, Object> parameter, Messages messages) {
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        sendParam = (List<Map<String, Object>>) parameter.get("list");
        
        for (int i = 0; i < sendParam.size(); i++) {
            res = menuService.deleteMenuM(sendParam.get(i));
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }


    @RequestMapping(value = "/deleteMenuS", method = RequestMethod.POST)
    public void deleteMenuS(@RequestBody Map<String, Object> parameter, Messages messages) {
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        sendParam = (List<Map<String, Object>>) parameter.get("list");
        
        for (int i = 0; i < sendParam.size(); i++) {
            res = menuService.deleteMenuS(sendParam.get(i));
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }


    @RequestMapping(value = "/deleteMenuD", method = RequestMethod.POST)
    public void deleteMenuD(@RequestBody Map<String, Object> parameter, Messages messages) {
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        sendParam = (List<Map<String, Object>>) parameter.get("list");
        
        for (int i = 0; i < sendParam.size(); i++) {
            res = menuService.deleteMenuD(sendParam.get(i));
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }



    @RequestMapping(value = "/selectMenuAll", method = RequestMethod.GET)
    public ResultListVO selectMenuAll(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(menuService.selectMenuAll(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }



    @RequestMapping(value = "/selectMyMenu", method = RequestMethod.GET)
    public ResultListVO selectMyMenu(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(menuService.selectMyMenu(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }


}
