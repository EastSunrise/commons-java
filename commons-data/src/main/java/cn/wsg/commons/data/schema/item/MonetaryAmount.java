package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A monetary value or range. This type can be used to describe an amount of money such as $50 USD, or a range as in
 * describing a bank account being suitable
 * for a balance between £1,000 and £1,000,000 GBP, or the value of a salary, etc. It is recommended to use
 * [[PriceSpecification]] Types to describe the price
 * of an Offer, Invoice, etc.
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#FIBO"})
public interface MonetaryAmount extends StructuredValue {
}