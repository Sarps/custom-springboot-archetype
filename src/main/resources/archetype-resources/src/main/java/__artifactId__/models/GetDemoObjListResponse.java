#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.models;

import ${package}.models.objs.DemoObj;

import java.util.List;

public class GetDemoObjListResponse extends GenericResponse {

    private List<DemoObj> ${artifactId}ObjList; // Model-Specific Response Parameter

    // Model-Specific Getters and Setters
    public List<DemoObj> getDemoObjList() {
        return ${artifactId}ObjList;
    }

    public void setDemoObjList(List<DemoObj> ${artifactId}Obj) {
        this.${artifactId}ObjList = ${artifactId}Obj;
    }

}
