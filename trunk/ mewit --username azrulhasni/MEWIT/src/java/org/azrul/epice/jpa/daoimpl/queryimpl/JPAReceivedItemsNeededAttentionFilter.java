/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.azrul.epice.jpa.daoimpl.queryimpl;

//import org.azrul.epice.dao.ItemDAO;
//import org.azrul.epice.dao.factory.ItemDAOFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.azrul.epice.domain.Item;

/**
 *
 * @author Azrul
 */
public class JPAReceivedItemsNeededAttentionFilter implements JPAItemsFilter {
    //private ItemDAO itemDAO = ItemDAOFactory.getInstance();

    public boolean filter(Item item, String currentUser) {
        //itemDAO.labelWithType(item, currentUser);
        //if (item.getType().contains("RECEIVED")) {
        if (item.getRecipient().equals(currentUser)) {
            if ((Item.Status.UNCONFIRMED).equals(item.getStatus())) {
                return true;
            } else if ((Item.Status.NEGOTIATED).equals(item.getStatus())) {
                return false;
            } else if ((Item.Status.ACCEPTED).equals(item.getStatus())) {
                return true;
            } else if ((Item.Status.DELEGATED).equals(item.getStatus())) {
                return true;
            } else if ((Item.Status.REJECTED).equals(item.getStatus())) {
                return false;
            } else if ((Item.Status.DONE_UNCONFIRMED).equals(item.getStatus())) {
                return false;
            } else if ((Item.Status.DONE_CONFIRMED).equals(item.getStatus())) {
                return false;
            } else if ((Item.Status.NEED_REDO).equals(item.getStatus())) {
                return true;
            } else if ((Item.Status.NEED_REDO_DELEGATED).equals(item.getStatus())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
//        } else if (item.getType().contains("SENT") || item.getType().contains("SUPERVISED")) { //for sent, supervised and sent-supervised
//            jbtnShowCurrentItemFileRepository.setEnabled(false);
//
//            if ((Item.Status.UNCONFIRMED).equals(item.getStatus())) {
//                jbtnShowCurrentItemFileRepository.setEnabled(true); //you can still upload
//
//            } else if ((Item.Status.NEGOTIATED).equals(item.getStatus())) {
//                jtpReasonForNegoReject.setVisible(true);
//                jtaReasonForNegoReject.setText(item.getReasonForNegotiatiationOfDeadLine());
//                jtaReasonForNegoReject.setEditable(false);
//
//                jtpNegotiatedDeadline.setVisible(true);
//                jxdpNegotiatedDeadline.setDate(item.getNegotiatedDeadLine());
//                GregorianCalendar gcal2 = (GregorianCalendar) GregorianCalendar.getInstance();
//                gcal2.setTime(item.getNegotiatedDeadLine());
//                SimpleDateFormat sdf2 = new SimpleDateFormat(ResourceBundle.getBundle("org.azrul.epice.config.epice").getString("HM_ONLY_DATE_FORMAT"));
//                jtfNegotiatedDeadlineTime.setText(sdf2.format(gcal2.getTime()));
//                if (gcal2.get(GregorianCalendar.AM_PM) == GregorianCalendar.AM) {
//                    jrbNegotiatedDeadlineAM.setSelected(true);
//                    jrbNegotiatedDeadlinePM.setSelected(false);
//                } else {
//                    jrbNegotiatedDeadlineAM.setSelected(false);
//                    jrbNegotiatedDeadlinePM.setSelected(true);
//                }
//                jlbActionNeeded.setText("Please accept or reject the negotiated deadline");
//
//                if (("SUPERVISED").equals(item.getType())) {
//                    jxdpNegotiatedDeadline.setEditable(false);
//                    jxdpNegotiatedDeadline.setEnabled(false);
//                    jtfNegotiatedDeadlineTime.setEditable(false);
//                    jrbNegotiatedDeadlineAM.setEnabled(false);
//                    jrbNegotiatedDeadlinePM.setEnabled(false);
//                    jlbActionNeeded.setText("None");
//
//                }
//            } else if ((Item.Status.ACCEPTED).equals(item.getStatus())) {
//                jbtnShowCurrentItemFileRepository.setEnabled(true);
//            } else if ((Item.Status.DELEGATED).equals(item.getStatus())) {
//                jbtnShowCurrentItemFileRepository.setEnabled(true);
//            } else if ((Item.Status.REJECTED).equals(item.getStatus())) {
//                jtpReasonForNegoReject.setVisible(true);
//                jtaReasonForNegoReject.setText(item.getReasonForRejectionOfTask());
//                jtaReasonForNegoReject.setEditable(false);
//            } else if ((Item.Status.DONE_UNCONFIRMED).equals(item.getStatus())) {
//                jtpFeedback.setVisible(true);
//                jtaFeedback.setText(item.getFeedback());
//                jtaFeedback.setEditable(false);
//
//
//                if (!("SUPERVISED").equals(item.getType())) {
//                    jlbActionNeeded.setText("Please confirm the feedback and give your comments");
//
//                    jtpComments.setVisible(true);
//                    jtaComments.setText(item.getCommentsOnFeedback());
//                    jtpComments.setBackground(Color.RED);
//
//                    jcbApproveOrNot.setVisible(true);
//
//                }
//
//            } else if ((Item.Status.DONE_CONFIRMED).equals(item.getStatus())) {
//                jtpFeedback.setVisible(true);
//                jtaFeedback.setText(item.getFeedback());
//                jtaFeedback.setEditable(false);
//
//                jtpComments.setVisible(true);
//                jtaComments.setText(item.getCommentsOnFeedback());
//                jtaComments.setEditable(false);
//            } else if ((Item.Status.NEED_REDO).equals(item.getStatus())) {
//                jbtnShowCurrentItemFileRepository.setEnabled(true);
//
//                jcbApproveOrNot.setVisible(true);
//                jcbApproveOrNot.setEnabled(false);
//                jcbApproveOrNot.setSelectedIndex(1); //0=approve, 1=not, need redo
//
//            } else if ((Item.Status.NEED_REDO_DELEGATED).equals(item.getStatus())) {
//                jbtnShowCurrentItemFileRepository.setEnabled(true);
//
//                jcbApproveOrNot.setVisible(true);
//                jcbApproveOrNot.setEnabled(false);
//                jcbApproveOrNot.setSelectedIndex(1); //0=approve, 1=not, need redo
//
//            } else if (("REFERENCE").equals(item.getStatus())) {
//            }
//        }
    }

    public String getType() {
        return "RECEIVED_ITEMS_NEEDED_ATTENTION";
    }

    public Predicate filter(String user, CriteriaBuilder cb, Root<Item> ritem) {
        return cb.and(
                cb.equal(ritem.get("recipient").as(String.class), user),
                cb.and(
                cb.notEqual(ritem.get("status").as(Integer.class), Item.Status.NEGOTIATED),
                cb.notEqual(ritem.get("status").as(Integer.class), Item.Status.REJECTED),
                cb.notEqual(ritem.get("status").as(Integer.class), Item.Status.DONE_UNCONFIRMED),
                cb.notEqual(ritem.get("status").as(Integer.class), Item.Status.REJECTED),
                cb.notEqual(ritem.get("status").as(Integer.class), Item.Status.NEED_REDO),
                cb.notEqual(ritem.get("status").as(Integer.class), Item.Status.DONE_CONFIRMED)));
    }
}
