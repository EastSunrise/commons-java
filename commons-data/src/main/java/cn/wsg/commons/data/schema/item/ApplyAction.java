package cn.wsg.commons.data.schema.item;

/**
 * The act of registering to an organization/service without the guarantee to receive it.\n\nRelated actions:\n\n*
 * [[RegisterAction]]: Unlike RegisterAction,
 * ApplyAction has no guarantees that the application will be accepted.
 *
 * @author Kingen
 */
public interface ApplyAction extends OrganizeAction {
}