/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ent.querys;

/**
 *
 * @author jyacelga
 */
public class Aplications {

    private int id;
    private String apps_user;
    private String apps_full_names;
    private String description_scheme;
    private String name_application;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApps_user() {
        return apps_user;
    }

    public void setApps_user(String apps_user) {
        this.apps_user = apps_user;
    }

    public String getApps_full_names() {
        return apps_full_names;
    }

    public void setApps_full_names(String apps_full_names) {
        this.apps_full_names = apps_full_names;
    }

    public String getDescription_scheme() {
        return description_scheme;
    }

    public void setDescription_scheme(String description_scheme) {
        this.description_scheme = description_scheme;
    }

    public String getName_application() {
        return name_application;
    }

    public void setName_application(String name_application) {
        this.name_application = name_application;
    }

}
