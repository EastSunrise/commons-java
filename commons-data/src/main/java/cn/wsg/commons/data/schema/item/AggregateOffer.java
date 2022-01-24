package cn.wsg.commons.data.schema.item;

/**
 * When a single product is associated with multiple offers (for example, the same pair of shoes is offered by different
 * merchants), then AggregateOffer can be
 * used.\n\nNote: AggregateOffers are normally expected to associate multiple offers that all share the same defined
 * [[businessFunction]] value, or default to
 * http://purl.org/goodrelations/v1#Sell if businessFunction is not explicitly defined.
 *
 * @author Kingen
 */
public interface AggregateOffer extends Offer {
}