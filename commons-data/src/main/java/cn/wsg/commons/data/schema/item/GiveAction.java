package cn.wsg.commons.data.schema.item;

/**
 * The act of transferring ownership of an object to a destination. Reciprocal of TakeAction.\n\nRelated actions:\n\n*
 * [[TakeAction]]: Reciprocal of
 * GiveAction.\n* [[SendAction]]: Unlike SendAction, GiveAction implies that ownership is being transferred (e.g. I may
 * send my laptop to you, but that doesn't
 * mean I'm giving it to you).
 *
 * @author Kingen
 */
public interface GiveAction extends TransferAction {
}