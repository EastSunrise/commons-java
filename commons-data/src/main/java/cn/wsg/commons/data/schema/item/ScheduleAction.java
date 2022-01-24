package cn.wsg.commons.data.schema.item;

/**
 * Scheduling future actions, events, or tasks.\n\nRelated actions:\n\n* [[ReserveAction]]: Unlike ReserveAction,
 * ScheduleAction allocates future actions (e.g.
 * an event, a task, etc) towards a time slot / spatial allocation.
 *
 * @author Kingen
 */
public interface ScheduleAction extends PlanAction {
}