package cn.wsg.commons.internet.org.schema.item;

import cn.wsg.commons.internet.org.schema.common.Source;

/**
 * An offer to transfer some rights to an item or to provide a service — for example, an offer to sell tickets to an
 * event, to rent the DVD of a movie, to
 * stream a TV show over the internet, to repair a motorcycle, or to loan a book.\n\nNote: As the [[businessFunction]]
 * property, which identifies the form of
 * offer (e.g. sell, lease, repair, dispose), defaults to http://purl.org/goodrelations/v1#Sell; an Offer without a
 * defined businessFunction value can be
 * assumed to be an offer to sell.\n\nFor [GTIN](http://www.gs1.org/barcodes/technical/idkeys/gtin)-related fields, see
 * [Check Digit
 * calculator](http://www.gs1.org/barcodes/support/check_digit_calculator) and [validation guide](http://www.gs1us
 * .org/resources/standards/gtin-validation-guide)
 * from [GS1](http://www.gs1.org/).
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_GoodRelationsTerms"})
public interface Offer extends Intangible {
}