/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Faq;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author PHATVQ
 */
@ManagedBean
@SessionScoped
public class FaqBean implements Serializable {

    @EJB
    private FaqFacade faqFacade;
    private String answer;
    private String question;
    private String message;
    private int faqId;
    private Faq faq;
    private HttpServletRequest rq;
    private List<Faq> filteredFAQ;

    public List<Faq> getFilteredFAQ() {
        return filteredFAQ;
    }

    public void setFilteredFAQ(List<Faq> filteredFAQ) {
        this.filteredFAQ = filteredFAQ;
    }

    public Faq getFaq() {
        return faq;
    }

    public void setFaq(Faq faq) {
        this.faq = faq;
    }

    public int getFaqId() {
        return faqId;
    }

    public void setFaqId(int fid) {
        this.faqId = fid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public FaqBean() {
    }

    public String addFaq() {
        if (question.equals("") || answer.equals("")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Please input field");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            Faq f = new Faq(question, answer);
            if (faqFacade.addFaq(f)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESSFULL", "Added Faq successfully!");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "FAIL", "Failed");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
        return "";
    }

    public String editFaq(Faq f) {
        faq = f;
        return "editFAQ.xhtml?faqId=" + f.getFaqid() + "?faces-redirect=true";
    }

    public String updateFaq() {
        if (faqFacade.editFaq(faq.getFaqid(), faq.getQuestion(), faq.getAnswer())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESSFULL", "Update Faq " + faq.getFaqid() + " successfully");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "FAIL", "Update Faq " + faq.getFaqid() + " failed!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "";
    }

    public void deleteFaq(Faq faq) {
        if (faqFacade.delete(faq)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESSFULL", "Delete " + faq.getFaqid() + " successfully");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Delete " + faq.getFaqid() + " Unsuccessfully");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public List<Faq> showAllFaqs() {
        return faqFacade.findAll();
    }
}
