package cn.wsg.commons.data.schema.item;

import cn.wsg.commons.data.schema.common.Source;

/**
 * A payment method using a credit, debit, store or other card to associate the payment with an account.
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#FIBO"})
public interface PaymentCard extends PaymentMethod, FinancialProduct {
}