package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A payment method is a standardized procedure for transferring the monetary amount for a purchase. Payment methods are
 * characterized by the legal and
 * technical structures used, and by the organization or group carrying out the transaction.\n\nCommonly used
 * values:\n\n*
 * http://purl.org/goodrelations/v1#ByBankTransferInAdvance\n* http://purl.org/goodrelations/v1#ByInvoice\n*
 * http://purl.org/goodrelations/v1#Cash\n*
 * http://purl.org/goodrelations/v1#CheckInAdvance\n* http://purl.org/goodrelations/v1#COD\n*
 * http://purl.org/goodrelations/v1#DirectDebit\n*
 * http://purl.org/goodrelations/v1#GoogleCheckout\n* http://purl.org/goodrelations/v1#PayPal\n*
 * http://purl.org/goodrelations/v1#PaySwarm
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#source_GoodRelationsClass"})
public interface PaymentMethod extends Enumeration {
}