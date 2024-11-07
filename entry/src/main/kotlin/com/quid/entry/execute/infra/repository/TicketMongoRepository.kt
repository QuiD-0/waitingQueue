package com.quid.entry.execute.infra.repository

import org.springframework.data.mongodb.repository.MongoRepository

interface TicketMongoRepository : MongoRepository<TicketEntity, Long> {
    fun existsByRedirectUrlAndMemberSeq(redirectUrl: String, memberSeq: Long): Boolean
    fun findByRedirectUrlAndMemberSeq(redirectUrl: String, memberSeq: Long): TicketEntity?
}
