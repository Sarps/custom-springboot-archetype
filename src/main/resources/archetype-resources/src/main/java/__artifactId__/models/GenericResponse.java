#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.models;

import ${package}.utils.Settings;

/**
 * Base Response Class
 */
public class GenericResponse {

    private String responseCode;
    private String responseMessage;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void setRawProtocols(String responseCode, String responseMessage) {
        setResponseCode(responseCode);
        setResponseMessage(responseMessage);
    }

    public void setProtocolsFromProperties(String responseCodeProperty, String responseMessageProperty) {
        setResponseCode(Settings.getInstance("").getProperty(responseCodeProperty));
        setResponseMessage(Settings.getInstance("").getProperty(responseMessageProperty));
    }
}
