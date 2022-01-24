package cn.wsg.commons.data.schema.item;

/**
 * The act of gaining ownership of an object from an origin. Reciprocal of GiveAction.\n\nRelated actions:\n\n*
 * [[GiveAction]]: The reciprocal of TakeAction.\n*
 * [[ReceiveAction]]: Unlike ReceiveAction, TakeAction implies that ownership has been transfered.
 *
 * @author Kingen
 */
public interface TakeAction extends TransferAction {
}