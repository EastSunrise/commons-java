package cn.wsg.commons.data.schema.item;

/**
 * Reserving a concrete object.\n\nRelated actions:\n\n* [[ScheduleAction]]: Unlike ScheduleAction, ReserveAction
 * reserves concrete objects (e.g. a table, a
 * hotel) towards a time slot / spatial allocation.
 *
 * @author Kingen
 */
public interface ReserveAction extends PlanAction {
}