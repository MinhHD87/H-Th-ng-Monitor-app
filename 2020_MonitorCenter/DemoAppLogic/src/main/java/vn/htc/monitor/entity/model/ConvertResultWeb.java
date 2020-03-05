/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.htc.monitor.entity.model;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author MinhKudo
 */
public class ConvertResultWeb {
    
    public static ClientResponse ReturnJson(String link) {
        ClientConfig clientConfig = new DefaultClientConfig();

        // Tạo đối tượng Client dựa trên cấu hình.
        Client client = Client.create(clientConfig);

        WebResource webResource = client.resource(link);
        WebResource.Builder builder = webResource.accept(MediaType.APPLICATION_JSON) //
                .header("content-type", MediaType.APPLICATION_JSON);

        ClientResponse response = builder.get(ClientResponse.class);

        // Trạng thái 200 là thành công
        if (response.getStatus() != 200) {
            System.out.println("Failed with HTTP Error code: " + response.getStatus());
            String error = response.getEntity(String.class);
            System.out.println("Error: " + error);
            return null;
        }

        return response;
    }
}
