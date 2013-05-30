/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TrungThanh
 */
public class sessionTool {
    // Ham dung de dua gia tri len session

    public static void setUpSession(String name, Object value) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.setAttribute(name, value);
    }
    // dung de lay gia tri tu session xuong

    public static Object getDownSession(String name) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        return session.getAttribute(name);
    }
    // dung de xoa gia tri khoi session

    public static void removeSession(String name) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.removeAttribute(name);
    }

    public static String getRealPath(String nameFolder) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        ServletContext sc = session.getServletContext();
        String path = sc.getRealPath(nameFolder);
        int pos = 0;
        while (pos >= 0) {
            pos = path.indexOf("\\", pos);
            if (pos >= 0) {
                path = path.substring(0, pos) + "/" + path.substring(pos + 1);
                pos += 1;
            }
        }
        return path;
    }
}
