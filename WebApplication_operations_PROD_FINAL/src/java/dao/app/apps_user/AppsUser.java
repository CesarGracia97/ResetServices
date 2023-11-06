/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.app.apps_user;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jyacelga
 */
@Entity
@Table(name = "apps_user", catalog = "aprovgrupotv", uniqueConstraints = {})
public class AppsUser implements Serializable {

    // Fields
    private Integer id;
    private String apps_user;
    private String apps_full_names;
    private String apps_descrition_user;
    
    public AppsUser() {
    }

    public AppsUser(Integer id,
            String apps_user,
            String apps_full_names,
            String apps_descrition_user) {
        this.id = id;
        this.apps_user = apps_user;
        this.apps_full_names = apps_full_names;
        this.apps_descrition_user = apps_descrition_user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "apps_user", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
    public String getApps_user() {
        return apps_user;
    }

    public void setApps_user(String apps_user) {
        this.apps_user = apps_user;
    }

    @Column(name = "apps_full_names", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
    public String getApps_full_names() {
        return apps_full_names;
    }

    public void setApps_full_names(String apps_full_names) {
        this.apps_full_names = apps_full_names;
    }

    @Column(name = "apps_descrition_user", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
    public String getApps_descrition_user() {
        return apps_descrition_user;
    }

    public void setApps_descrition_user(String apps_descrition_user) {
        this.apps_descrition_user = apps_descrition_user;
    }
    
}
