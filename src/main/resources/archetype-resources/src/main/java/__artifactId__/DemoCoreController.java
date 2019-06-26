#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import ${package}.models.GetDemoObjListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class DemoCoreController {

    @Autowired
    private DemoDAO processor;

    @RequestMapping(value="getCountries", method = RequestMethod.GET)
    public @ResponseBody
    GetDemoObjListResponse getProperties(){

        GetDemoObjListResponse resp = new GetDemoObjListResponse();

        resp = processor.getDemoObj();

        return resp;

    }

}
