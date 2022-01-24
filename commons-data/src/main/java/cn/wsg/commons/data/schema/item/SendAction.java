package cn.wsg.commons.data.schema.item;

/**
 * The act of physically/electronically dispatching an object for transfer from an origin to a destination.Related
 * actions:\n\n* [[ReceiveAction]]: The
 * reciprocal of SendAction.\n* [[GiveAction]]: Unlike GiveAction, SendAction does not imply the transfer of ownership
 * (e.g. I can send you my laptop, but I'm
 * not necessarily giving it to you).
 *
 * @author Kingen
 */
public interface SendAction extends TransferAction {
}