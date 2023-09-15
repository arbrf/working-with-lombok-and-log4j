package com.cpt.payments;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cpt.payments.pojo.copy.AddRequest;
import com.cpt.payments.pojo.copy.AddResponse;
import com.cpt.payments.service.TestService;
@RestController
@RequestMapping("/controller")
public class TestController {
    private final TestService testService;
    private static final Logger logger = LogManager.getLogger(TestController.class);

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @GetMapping("/addGet")
    public int addGet(@RequestParam(value = "num1") int val1, @RequestParam(value = "num2") int val2) {
    	
    	logger.info("This is an info message.");
        logger.error("This is an error message.");
        logger.debug("This is Debug log error");
        logger.warn("This is Warn log error");
        logger.trace("This is Trace log error");
         
        int res = val1 + val2;
        return res;
    }

    @PostMapping("/addPost")
    public int addPost(@RequestParam(value = "num1") int val1, @RequestParam(value = "num2") int val2) {
        int res = val1 + val2;
        return res;
    }

    @PostMapping("/processJson")
    @ResponseBody
    public AddResponse processJson(@RequestBody AddRequest request) {
        int res = request.getNum1() + request.getNum2();
        AddResponse response = new AddResponse();
        response.setResValue(res);
        return response;
    }

    @PostMapping("/validateAndprocess")
    @ResponseBody
    public AddResponse validateAndprocessJson(@RequestHeader("signature") String signature,
            @RequestBody AddRequest request) {
        System.out.println("Calling validateAndProcessJson  " + request + "String " + signature);
        TestService testService = new TestService();
        int res = testService.validateAndProcess(request, signature);
        AddResponse response = new AddResponse();
        response.setResValue(res);
        System.out.println("Add Response  :" + response);
        return response;
    }
}
