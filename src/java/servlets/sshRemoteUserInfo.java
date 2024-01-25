/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

/**
 *
 * @author jyacelga
 */
import com.jcraft.jsch.*;

public class sshRemoteUserInfo implements UserInfo {

    private final String pwd;

    public sshRemoteUserInfo(String userName, String password) {
        pwd = password;
    }

    @Override
    public String getPassphrase() {
        throw new UnsupportedOperationException("getPassphrase Not supported yet.");
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public boolean promptPassword(String string) {
        /*mod*/
        return true;
    }

    @Override
    public boolean promptPassphrase(String string) {
        throw new UnsupportedOperationException("promptPassphrase Not supported yet.");
    }

    @Override
    public boolean promptYesNo(String string) {
        /*mod*/
        return true;
    }

    @Override
    public void showMessage(String string) {
    }
}
