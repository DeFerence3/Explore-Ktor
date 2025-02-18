package com.diffy.database.entity

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object MyCompany : Table("MyCompany") {
    val myCompanyId = long("MyCompanyId").autoIncrement()
    val companyCode = integer("CompanyCode")
    val companyName = varchar("CompanyName", 50)
    val ownerName = varchar("OwnerName", 50).nullable()
    val companyTag = varchar("CompanyTag", 3).nullable()
    val address1 = varchar("Address1", 100).nullable()
    val address2 = varchar("Address2", 250).nullable()
    val address3 = varchar("Address3", 50).nullable()
    val address4 = varchar("Address4", 50).nullable()
    val address5 = varchar("Address5", 50).nullable()
    val address6 = varchar("Address6", 50).nullable()
    val url = varchar("URL", 50).nullable()
    val email = varchar("Email", 50).nullable()
    val gstNo = varchar("GstNo", 50).nullable()
    val tinNo = varchar("TinNo", 50).nullable()
    val gstDate = date("GstDate").nullable()
    val cstNo = varchar("CstNo", 50).nullable()
    val cstDate = date("CstDate").nullable()
    val regNo = varchar("RegNo", 30).nullable()
    val phoneOff = varchar("phoneOff", 50).nullable()
    val phoneResi = varchar("PhoneResi", 50).nullable()
    val fax = varchar("Fax", 50).nullable()
    val mobile = varchar("Mobile", 50).nullable()
    val dLNo1 = varchar("DLNo1", 50).nullable()
    val dLNo2 = varchar("DLNo2", 50).nullable()
    val eccCode = varchar("ECCCode", 30).nullable()
    val commissionerate = varchar("Commissionerate", 30).nullable()
    val division = varchar("Division", 30).nullable()
    val range = varchar("Range", 30).nullable()
    val enNo = varchar("EnNo", 30).nullable()
    val jurisdiction = varchar("Jurisdiction", 30).nullable()
    val yearStartDate = date("YearStartDate").nullable()
    val yearEndDate = date("YearEndDate").nullable()
    val branchCode = varchar("BranchCode", 50).nullable()
    override val primaryKey = PrimaryKey(myCompanyId)
}
