#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import ${package}.models.GetDemoObjListResponse;
import ${package}.models.objs.DemoObj;
import ${package}.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DemoDAO {

    @Autowired
    JdbcTemplate template;

    public GetDemoObjListResponse getDemoObj() {

        GetDemoObjListResponse resp = new GetDemoObjListResponse();
        String query = "SELECT * FROM ${artifactId}_tbl";

        try {

            List<DemoObj> ${artifactId}Obj =  template.query(query, new Object[] { }, new RowMapper<DemoObj>() {
                public DemoObj mapRow(ResultSet rs, int rownumber) throws SQLException {
                    return new DemoObj(rs.getString("param1"), rs.getInt("param2"));
                }
            });

            if (${artifactId}Obj.isEmpty()) {

                /**
                 * Setting raw response code and message
                 */
                resp.setRawProtocols(
                        Settings.getInstance("").getProperty("NO_RECORD_FOUND"),
                        Settings.getInstance("").getProperty("NO_RECORD_FOUND_MSG")
                );
            } else {
                /**
                 * Setting response code and message using the string key in application.properties file
                 */
                resp.setProtocolsFromProperties("SUCCESS", "SUCCESS_MSG");

                resp.setDemoObjList(${artifactId}Obj);
            }

        } catch (Exception ex) {
            resp.setProtocolsFromProperties("INTERFACE_ERROR", "INTERFACE_ERROR_MSG");
        }

        return resp;
    }


}
