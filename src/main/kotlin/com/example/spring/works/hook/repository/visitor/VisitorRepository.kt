package com.example.spring.works.hook.repository.visitor

import com.example.spring.works.hook.domain.visitor.Visitor
import org.springframework.data.jpa.repository.JpaRepository

interface VisitorRepository : JpaRepository<Visitor, Long>, VisitorCustomRepository
