package com.altr.core.controller;

import com.altr.core.helper.CoreTools;
import com.altr.core.helper.SystemConstants;
import com.altr.core.service.TObjectService;
import com.altr.core.webcontext.CommonPageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private CommonPageContext commonPageContext;
    private TObjectService tObjectService;

    @Autowired(required = true)
    @Qualifier(value = "tObjectService")
    public void settObjectService(TObjectService ts) {
        this.tObjectService = ts;
    }


    @Autowired(required = true)
    @Qualifier(value = "commonPage")
    public void setCommonPageContext(CommonPageContext co) {
        this.commonPageContext = co;
    }

    @RequestMapping(value = "/common", method = RequestMethod.GET)
    public String defaultPage(@RequestParam(value = "id", required = false) String id,
                              @RequestParam(value = "tab", required = false) String tab,
                              @RequestParam(value = "mode", required = false) String mode,
                              @RequestParam(value = "aid", required = false) String attrId,
                              @RequestParam(value = "command", required = false) String command,
                              @RequestParam(value = "jAdapter", required = false) String jAdapter,
                              @RequestParam Map<String, String> selectedItems,
                              Model model) {
        CoreTools.cleanMap(selectedItems, new String[]{"id", "tab", "mode", "aid", "command", "jAdapter"});
        if (id == null) id = SystemConstants.IDS.DEFAULT_OBJECT;
        if (CoreTools.isEmpty(tab)) tab = "empty";
        if (!CoreTools.isEmpty(command)){
            if ("delete".equals(command)){
                tObjectService.deleteObjectBulk(selectedItems, Integer.parseInt(attrId));
            }
        }
        String url = "";
        if ("1".equals(mode)) {
            url = "edit";
        } else {
            url = "common";
        }
        commonPageContext.getPageData(Integer.parseInt(id), tab, mode);
        model.addAttribute("info", commonPageContext);
        return url;
    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Login Form - Database Authentication");
        model.addObject("message", "This page is for ROLE_ADMIN only!");
        model.setViewName("admin");
        return model;

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("403");
        return model;

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String rootPage(@RequestParam(value = "id", required = false) String id,
                           @RequestParam(value = "tab", required = false) String tab) {
        if (id == null) id = SystemConstants.IDS.DEFAULT_OBJECT;
        String redirectUrl = "redirect:/common?id=" + id;
        if (!CoreTools.isEmpty(tab)) redirectUrl += "&tab=" + tab;
        return redirectUrl;
    }

    @RequestMapping(value = "/common", method = RequestMethod.POST)
    public String update(@RequestParam(value = "objectid", required = false) String objectId,
                         @RequestParam(value = "tab", required = false) String tab,
                         @RequestParam Map<String, String> updateParam, Model model) {
        updateParam.remove("objectid");
        updateParam.remove("tab");
        updateParam.remove("mode");
        updateParam.remove("id");
        updateParam.remove("_csrf");
        //model.addAttribute("value1", updateParam.get(7));
        tObjectService.updateParamBulk(Integer.parseInt(objectId), updateParam);

        return "redirect:/common?id=" + objectId + "&tab=" + tab;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        tObjectService.createObject("test object1", 11, 10);
        return "common1";
    }

}
