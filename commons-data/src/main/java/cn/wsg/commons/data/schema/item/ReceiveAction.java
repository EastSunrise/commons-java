package cn.wsg.commons.data.schema.item;

/**
 * The act of physically/electronically taking delivery of an object that has been transferred from an origin to a
 * destination. Reciprocal of
 * SendAction.\n\nRelated actions:\n\n* [[SendAction]]: The reciprocal of ReceiveAction.\n* [[TakeAction]]: Unlike
 * TakeAction, ReceiveAction does not imply that
 * the ownership has been transfered (e.g. I can receive a package, but it does not mean the package is now mine).
 *
 * @author Kingen
 */
public interface ReceiveAction extends TransferAction {
}