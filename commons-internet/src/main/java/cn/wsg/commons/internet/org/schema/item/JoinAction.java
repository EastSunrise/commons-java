package cn.wsg.commons.internet.org.schema.item;

/**
 * An agent joins an event/group with participants/friends at a location.\n\nRelated actions:\n\n* [[RegisterAction]]:
 * Unlike RegisterAction, JoinAction refers
 * to joining a group/team of people.\n* [[SubscribeAction]]: Unlike SubscribeAction, JoinAction does not imply that
 * you'll be receiving updates.\n*
 * [[FollowAction]]: Unlike FollowAction, JoinAction does not imply that you'll be polling for updates.
 *
 * @author Kingen
 */
public interface JoinAction extends InteractAction {
}