#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.models.objs;

import java.util.List;

public class DemoObj {

    private String param1;
    private Number param2;

    public DemoObj(String param1, Number param2) {
        this.param1 = param1;
        this.param2 = param2;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public Number getParam2() {
        return param2;
    }

    public void setParam2(Number param2) {
        this.param2 = param2;
    }

}
