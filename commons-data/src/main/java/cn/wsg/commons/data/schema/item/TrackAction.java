package cn.wsg.commons.data.schema.item;

/**
 * An agent tracks an object for updates.\n\nRelated actions:\n\n* [[FollowAction]]: Unlike FollowAction, TrackAction
 * refers to the interest on the location of
 * innanimates objects.\n* [[SubscribeAction]]: Unlike SubscribeAction, TrackAction refers to  the interest on the
 * location of innanimate objects.
 *
 * @author Kingen
 */
public interface TrackAction extends FindAction {
}