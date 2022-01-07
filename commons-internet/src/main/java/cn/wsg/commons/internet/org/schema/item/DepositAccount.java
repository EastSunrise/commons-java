package cn.wsg.commons.internet.org.schema.item;

import cn.wsg.commons.internet.org.schema.common.Source;

/**
 * A type of Bank Account with a main purpose of depositing funds to gain interest or other benefits.
 *
 * @author Kingen
 */
@Source({"http://www.w3.org/wiki/WebSchemas/SchemaDotOrgSources#FIBO"})
public interface DepositAccount extends InvestmentOrDeposit, BankAccount {
}