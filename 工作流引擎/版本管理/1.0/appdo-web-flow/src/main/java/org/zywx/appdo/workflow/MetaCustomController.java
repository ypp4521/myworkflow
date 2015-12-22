package org.zywx.appdo.workflow;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/meta")
public class MetaCustomController {

	/**
	 * 元数据列表页面
	 * @return
	 */
    @RequestMapping("metaCustomList")  
    public ModelAndView metaCustomList(HttpServletRequest request){  
        ModelAndView modelAndView = new ModelAndView("workflow/metaCustomList") ;  
        return modelAndView ;  
    } 
    /**
     * 新增元数据
     * @return
     */
    @RequestMapping("metaCustomAdd")  
    public ModelAndView metaCustomAdd(HttpServletRequest request){  
        ModelAndView modelAndView = new ModelAndView("workflow/metaCustomAdd") ;  
        return modelAndView ;  
    }
    
    /**
     * 修改元数据
     * @return
     */
    @RequestMapping("metaCustomEdit")  
    public ModelAndView metaCustomEdit(String id,HttpServletRequest request){  
        ModelAndView modelAndView = new ModelAndView("workflow/metaCustomEdit") ;  
        return modelAndView ;  
    }
    /**
     * 删除元数据
     * @return
     */
    @RequestMapping(value="/metaCustomDel")  
    public String metaCustomDel(@RequestParam("deploymentId") String deploymentId){  
        return "redirect:/meta/metaCustomList";  
    }
}
