/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entities.Faq;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author PHATVQ
 */
@ManagedBean
@SessionScoped
public class FaqBean {

    @EJB
    private FaqFacade faqFacade;
    private String answer;
    private String question;
    private String message;
    private int faqId;
    private Faq faq;

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
        Faq f = new Faq(question, answer);
        if (faqFacade.addFaq(f)) {
            message = "Added Faq successfully!";
            return "viewFAQ.xhtml?result=" + message + "&faces-redirect=true";
        } else {
            message = "Failed!";
            return "viewFAQ.xhtml?result=" + message + "&faces-redirect=true";
        }
    }
    public String editFaq(Faq f){
        faq = f;
        return "editFAQ.xhtml?faqId="+f.getFaqid()+"?faces-redirect=true";
    }
    public String updateFaq(){
        if(faqFacade.editFaq(faq.getFaqid(), faq.getQuestion(), faq.getAnswer())){
            message = "Updated Faq "+faq.getFaqid()+" successfully!";
            return "editFAQ.xhtml?result=" + message + "&faces-redirect=true";
        } else {
            message = "Updated Faq "+faq.getFaqid()+" failed!";
            return "editFAQ.xhtml?result=" + message + "&faces-redirect=true";
        }
    }
    public List<Faq> showAllFaqs(){
        return faqFacade.findAll();
    }
}
