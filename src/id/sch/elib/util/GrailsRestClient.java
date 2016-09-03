/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.util;

import java.io.InputStream;
import java.io.Serializable;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author RAP
 */
public class GrailsRestClient implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String url;
    private String restUrl;

    public GrailsRestClient() {
        try {
//            this.url = SessionUtil.getInitialParam("grailsEndPoint");
            this.url = "http://localhost:8090/BackendTest/";
            this.restUrl = this.url + "rest/";
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("ERROR GRC CONSTRUCTOR: " + e.getMessage());
        }
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Do the logout
     */
    public String logout(String token) {
        String output = "";
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(url + "api/logout");
            ClientResponse response = webResource.type("application/json")
                    .header("X-Auth-Token", token).post(ClientResponse.class);

            if (response.getStatus() != 200) {
                handleError(response.getStatus());
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            output = response.getEntity(String.class);
        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(
//                    null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e
//                            .getMessage(), ""));
//            e.printStackTrace();
        }
        return output;
    }

    public String login(Object post) {
        String output = "";
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(url + "api/login");
            Gson gson = new Gson();
            String input = gson.toJson(post);
            System.out.println("url: " + url + "api/login");
            System.out.println("input: " + input);

            ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                    .post(ClientResponse.class, input);
            System.out.println(response.getEntityTag());
            if (response.getStatus() != 200) {
                handleError(response.getStatus());
                output = response.getEntity(String.class);
//                output = "failed";
//                output=response.getStatus()+"";

            } else {
                output = response.getEntity(String.class);
//                  output="asdasdasd";
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "LOGIN-ERROR: " + e.getMessage(), "LOGIN-ERROR", 0);
//            FacesContext.getCurrentInstance().addMessage(
//                    null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e
//                            .getMessage(), ""));
//            e.printStackTrace();
        }
        return output;
    }

    /**
     * Assuming the technology used is Primefaces, this method gets token from
     * current session
     *
     * @param path
     * @return
     */
    public String getToken() {
//        ExternalContext externalContext = FacesContext.getCurrentInstance()
//                .getExternalContext();
//        Map<String, Object> sessionMap = externalContext.getSessionMap();

//        return sessionMap.get("token").toString(); // use this when spring security is enabled
        return null;
    }

    /**
     * Assuming the technology used is Primefaces, this method gets username
     * from current session
     *
     * @param path
     * @return
     */
    public String getUsername() {
//        ExternalContext externalContext
//                = FacesContext.getCurrentInstance().getExternalContext();
//        Map<String, Object> sessionMap = externalContext.getSessionMap();
//        return sessionMap.get("username").toString(); //use this when security enabled
        return null;
    }

    /**
     * Get data with any given URL, with GET HTTP method
     *
     * @param path
     * @return
     */
    public String get(String path) {
        String output = "";
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(this.url + path);
            ClientResponse response = webResource.accept("application/json")
                    //                    .header("X-Auth-Token", this.getToken())
                    //                    .header("username", this.getUsername())
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                handleError(response.getStatus());
                System.out.println("Failed : HTTP error code : "
                        + response.getStatus());
            }

            output = response.getEntity(String.class);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "GET-ERROR: " + e.getMessage(), "GET-ERROR", 0);
//            FacesContext.getCurrentInstance().addMessage(
//                    null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e
//                            .getMessage(), ""));
//            e.printStackTrace();
        }
        return output;
    }

    /**
     * Insert data with any given URL, with POST HTTP method
     *
     * @param path
     * @return
     */
    public String add(String path, Object post)
            throws RuntimeException {
        String output = "";
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(this.url + path);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.S")
                    .create();
            String input = gson.toJson(post);
//            System.out.println("===INPUT=====");
//            System.out.println(input);
//            System.out.println("URL: " + url + path);
            ClientResponse response = webResource.type("application/json")
                    //                    .header("X-Auth-Token", this.getToken())
                    //                    .header("username", this.getUsername())
                    .post(ClientResponse.class, input);

            if (response.getStatus() != 200) {
                handleError(response.getStatus());
                System.out.println("Failed : HTTP error code : "
                        + response.getStatus() + "\n"
                        + response.getEntity(String.class));
            }
            output = response.getEntity(String.class);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "ADD-ERROR-RUNTIME: " + e.getMessage(), "ADD-ERROR", 0);
//            FacesContext.getCurrentInstance().addMessage(
//                    null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e
//                            .getMessage(), ""));
//            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ADD-ERROR: " + e.getMessage(), "ADD-ERROR", 0);
//            FacesContext.getCurrentInstance().addMessage(
//                    null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e
//                            .getMessage(), ""));
//            e.printStackTrace();
        }
        return output;
    }

    /**
     * Update data with any given URL, with PUT HTTP method
     *
     * @param path
     * @return
     */
    public String put(String path, Object post) {
        String output = "";

        try {
            Client client = Client.create();
            WebResource webResource = client.resource(url + path);

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.S").create();
            String input = gson.toJson(post);
//            System.out.println("===UPDATE=====");
//            System.out.println(input);
            ClientResponse response = webResource.type("application/json")
                    .header("X-Auth-Token", this.getToken())
                    .header("username", this.getUsername())
                    .put(ClientResponse.class, input);

            if (response.getStatus() != 200) {
                handleError(response.getStatus());
                System.out.println("Failed : HTTP error code : "
                        + response.getStatus());
            }
            output = response.getEntity(String.class);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "PUT-ERROR: " + e.getMessage(), "PUT-ERROR", 0);
//            FacesContext.getCurrentInstance().addMessage(
//                    null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e
//                            .getMessage(), ""));
//            e.printStackTrace();
        }
        return output;
    }

    /**
     * Deleting from database with any given URL
     *
     * @param path
     * @return
     * @throws RuntimeException
     */
    public String deleteDirect(String path) throws RuntimeException {
        String output = "";
        Client client = Client.create();
        WebResource webResource = client.resource(path);
        try {
            ClientResponse response = webResource.accept("application/json")
                    .header("X-Auth-Token", this.getToken())
                    .header("username", this.getUsername())
                    .delete(ClientResponse.class);
            if (response.getStatus() != 200) {
                handleError(response.getStatus());
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }
            output = response.getEntity(String.class);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "DETELE_RIRECT-ERROR: " + e.getMessage(), "DETELE_RIRECT-ERROR", 0);
//            FacesContext.getCurrentInstance().addMessage(
//                    null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, e
//                            .getMessage(), ""));
//            e.printStackTrace();
        }
        return output;
    }

    /**
     * Send message whenever error occurs
     *
     * @param status
     */
    private void handleError(Integer status) {
        String summary = null, detail = null;
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                summary, detail);

        if (status == 400) {
            summary = "Gagal memuat/menyimpan data. Hal ini terjadi karena kesalahan aplikasi dalam mengirimkan data request ke server, silakan hubungi administrator.";
        } else if (status == 401) {
            summary = "Username/password yang Anda masukkan salah";
        } else if (status == 403) {
            summary = "Gagal memuat/menyimpan data. Hal ini terjadi karena Anda tidak diijinkan mengakses data pada fitur ini.\nJika Anda seharusnya berhak mengakses data, silakan hubungi administrator. Aplikasi akan mengarahkan Anda ke halaman utama.";
        } else if (status == 404) {
            summary = "Gagal memuat/menyimpan data. Hal ini terjadi karena aplikasi mengakses url service yang tidak ada di server, silakan hubungi administrator.";
        } else if (status >= 500) {
            summary = "Gagal memuat/menyimpan data. Hal ini terjadi karena server mengalami gangguan, silakan hubungi administrator.";
        } else {
            summary = "Gagal memuat/menyimpan data.";
        }
//        message.setSummary(summary);
//        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public String upload(String uploadPath, InputStream is, String filename) {
        String sContentDisposition = String.format(
                "attachment;id=1; filename=\"%s\"", filename);
        String output = "";
        Client client = Client.create();

        WebResource webResource = client.resource(url + uploadPath);
        MultivaluedMap formData = new MultivaluedMapImpl();

        formData.add("id", "1");
        formData.add("filename", filename);
        formData.add("file", is);

        ClientResponse response = webResource
                .type(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", sContentDisposition)
                .header("X-Auth-Token", this.getToken())
                .post(ClientResponse.class, formData);

        output = response.getEntity(String.class);
        return output;
    }

    public InputStream download(String path) {
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(url + path);
            ClientResponse response = webResource
//                    .header("X-Auth-Token", this.getToken())
                    .get(ClientResponse.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }
            if (response.getEntityInputStream() == null) {
            JOptionPane.showMessageDialog(null, "DOWNLOAD-ERROR: " + path + "IS NULL", "DOWNLOAD-ERROR", 0);
            }
            return response.getEntityInputStream();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "DOWNLOAD-ERROR: " + e.getMessage(), "DOWNLOAD-ERROR", 0);
        }
        return null;
    }

    public String upload(String uploadPath,
            InputStream is,
            String filename,
            String content_type) {
        Client client = Client.create();
        WebResource webResource = client.resource(url + uploadPath);
        FormDataMultiPart fdmp = new FormDataMultiPart();
        FormDataBodyPart fdbp;
        try {
            fdbp = new FormDataBodyPart(
                    FormDataContentDisposition.name("file").fileName(URLEncoder.encode((filename), "UTF-8")).build(),
                    is,
                    MediaType.valueOf(content_type)
            );
            fdmp.bodyPart(fdbp);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(GrailsRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        ClientResponse response = webResource.accept("application/json")
                .type(MediaType.MULTIPART_FORM_DATA)
                .header("X-Auth-Token", this.getToken())
                .post(ClientResponse.class, fdmp);

        return response.getEntity(String.class);
    }
}
